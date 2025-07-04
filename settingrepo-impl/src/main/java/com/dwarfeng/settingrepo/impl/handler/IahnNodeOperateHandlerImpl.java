package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.sdk.util.IahnUtils;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.*;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.IahnNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.*;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
public class IahnNodeOperateHandlerImpl implements IahnNodeOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(IahnNodeOperateHandlerImpl.class);

    private static final String FORMATTED_LOCALE_DEFAULT = "#default";

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final IahnNodeMaintainService iahnNodeMaintainService;
    private final IahnNodeLocaleMaintainService iahnNodeLocaleMaintainService;
    private final IahnNodeMekMaintainService iahnNodeMekMaintainService;
    private final IahnNodeMessageMaintainService iahnNodeMessageMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    private final RedisTemplate<String, String> redisTemplate;

    private final HandlerValidator handlerValidator;

    @Value("${iahn.cache.prefix.locale_map}")
    private String localeMapCachePrefix;
    @Value("${iahn.cache.prefix.message_map}")
    private String messageMapCachePrefix;

    @Value("${iahn.cache.timeout.locale_map}")
    private long localeMapCacheTimeout;
    @Value("${iahn.cache.timeout.message_map}")
    private long messageMapCacheTimeout;

    public IahnNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            IahnNodeMaintainService iahnNodeMaintainService,
            IahnNodeLocaleMaintainService iahnNodeLocaleMaintainService,
            IahnNodeMekMaintainService iahnNodeMekMaintainService,
            IahnNodeMessageMaintainService iahnNodeMessageMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            RedisTemplate<String, String> redisTemplate,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.iahnNodeMaintainService = iahnNodeMaintainService;
        this.iahnNodeLocaleMaintainService = iahnNodeLocaleMaintainService;
        this.iahnNodeMekMaintainService = iahnNodeMekMaintainService;
        this.iahnNodeMessageMaintainService = iahnNodeMessageMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.redisTemplate = redisTemplate;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public IahnNodeMessageInspectResult inspectMessage(IahnNodeMessageInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String language = IahnUtils.preProcessRfc4546Field(info.getLanguage());
            String country = IahnUtils.preProcessRfc4546Field(info.getCountry());
            String variant = IahnUtils.preProcessRfc4546Field(info.getVariant());
            String mekId = IahnUtils.preProcessMekId(info.getMekId());

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认地区合法。
            handlerValidator.makeSureIahnNodeInspectLocaleValid(language, country, variant);
            // 确认 mekId 合法。
            handlerValidator.makeSureIahnNodeMekIdValid(mekId);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 从缓存中读取地区映射，获取匹配的地区。
            String localMapCacheKey = parseCacheKey(localeMapCachePrefix, settingNodeKey, language, country, variant);
            String matchedFormattedLocale;
            if (redisTemplate.hasKey(localMapCacheKey)) {
                matchedFormattedLocale = redisTemplate.opsForValue().get(localMapCacheKey);
            } else {
                matchedFormattedLocale = findMatchedFormattedLocale(settingNodeKey, language, country, variant);
                if (Objects.isNull(matchedFormattedLocale)) {
                    LOGGER.warn("不应该执行到此处, 将抛出异常, 请联系开发人员");
                    throw new NullPointerException("matchedLocale should not be null");
                }
                redisTemplate.opsForValue().set(
                        localMapCacheKey, matchedFormattedLocale, localeMapCacheTimeout, TimeUnit.MILLISECONDS
                );
            }

            // 根据匹配的地区获取地区消息主键。
            String messageMapCacheKey = parseCacheKey(messageMapCachePrefix, settingNodeKey, matchedFormattedLocale);
            String message;
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            if (redisTemplate.hasKey(messageMapCacheKey)) {
                message = hashOperations.get(messageMapCacheKey, mekId);
            } else {
                List<MekMessagePair> mekMessagePairs = lookupMekMessagePair(settingNodeKey, matchedFormattedLocale);
                message = null;
                for (MekMessagePair mekMessagePair : mekMessagePairs) {
                    String anchorMekId = mekMessagePair.getMekId();
                    hashOperations.put(messageMapCacheKey, anchorMekId, mekMessagePair.getMessage());
                    if (Objects.equals(anchorMekId, mekId)) {
                        message = mekMessagePair.getMessage();
                    }
                }
                redisTemplate.expire(messageMapCacheKey, messageMapCacheTimeout, TimeUnit.MILLISECONDS);
            }

            // 构造结果并返回。
            return new IahnNodeMessageInspectResult(message);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public IahnNodeMessageInspectByLocaleResult batchInspectMessageByLocale(IahnNodeMessageInspectByLocaleInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String language = IahnUtils.preProcessRfc4546Field(info.getLanguage());
            String country = IahnUtils.preProcessRfc4546Field(info.getCountry());
            String variant = IahnUtils.preProcessRfc4546Field(info.getVariant());

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认地区合法。
            handlerValidator.makeSureIahnNodeInspectLocaleValid(language, country, variant);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 从缓存中读取地区映射，获取匹配的地区。
            String localMapCacheKey = parseCacheKey(localeMapCachePrefix, settingNodeKey, language, country, variant);
            String matchedFormattedLocale;
            if (redisTemplate.hasKey(localMapCacheKey)) {
                matchedFormattedLocale = redisTemplate.opsForValue().get(localMapCacheKey);
            } else {
                matchedFormattedLocale = findMatchedFormattedLocale(settingNodeKey, language, country, variant);
                if (Objects.isNull(matchedFormattedLocale)) {
                    LOGGER.warn("不应该执行到此处, 将抛出异常, 请联系开发人员");
                    throw new NullPointerException("matchedLocale should not be null");
                }
                redisTemplate.opsForValue().set(
                        localMapCacheKey, matchedFormattedLocale, localeMapCacheTimeout, TimeUnit.MILLISECONDS
                );
            }

            // 根据匹配的地区获取地区消息主键。
            String messageMapCacheKey = parseCacheKey(messageMapCachePrefix, settingNodeKey, matchedFormattedLocale);
            List<IahnNodeMessageInspectByLocaleResult.Item> items = new ArrayList<>();
            HashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
            if (redisTemplate.hasKey(messageMapCacheKey)) {
                Map<String, String> itemMap = hashOperations.entries(messageMapCacheKey);
                for (Map.Entry<String, String> entry : itemMap.entrySet()) {
                    items.add(new IahnNodeMessageInspectByLocaleResult.Item(entry.getKey(), entry.getValue()));
                }
            } else {
                List<MekMessagePair> mekMessagePairs = lookupMekMessagePair(settingNodeKey, matchedFormattedLocale);
                for (MekMessagePair mekMessagePair : mekMessagePairs) {
                    String anchorMekId = mekMessagePair.getMekId();
                    String anchorMessage = mekMessagePair.getMessage();
                    hashOperations.put(messageMapCacheKey, anchorMekId, mekMessagePair.getMessage());
                    items.add(new IahnNodeMessageInspectByLocaleResult.Item(anchorMekId, anchorMessage));
                }
                redisTemplate.expire(messageMapCacheKey, messageMapCacheTimeout, TimeUnit.MILLISECONDS);
            }

            // 构造结果并返回。
            return new IahnNodeMessageInspectByLocaleResult(items);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private String findMatchedFormattedLocale(
            StringIdKey settingNodeKey, String language, String country, String variant
    ) throws Exception {
        // 参数预处理。
        language = IahnUtils.preProcessRfc4546Field(language);
        country = IahnUtils.preProcessRfc4546Field(country);
        variant = IahnUtils.preProcessRfc4546Field(variant);

        // 查询 settingNodeKey 下所有的 IahnNodeLocale。
        List<IahnNodeLocale> iahnNodeLocales = iahnNodeLocaleMaintainService.lookupAsList(
                IahnNodeLocaleMaintainService.CHILD_FOR_NODE, new Object[]{settingNodeKey}
        );

        // 定义匹配参数。
        String matchedLanguage = StringUtils.EMPTY;
        String matchedCountry = StringUtils.EMPTY;
        String matchedVariant = StringUtils.EMPTY;
        int matchLevel = 0;

        // 遍历所有 IahnNodeLocale，寻找最高匹配等级的地区。
        for (IahnNodeLocale iahnNodeLocale : iahnNodeLocales) {
            int anchorMatchLevel;
            // 等级 3 匹配判定。
            if (
                    Objects.equals(language, iahnNodeLocale.getKey().getLanguage()) &&
                            Objects.equals(country, iahnNodeLocale.getKey().getCountry()) &&
                            Objects.equals(variant, iahnNodeLocale.getKey().getVariant())
            ) {
                // 如果命中等级 3 匹配判断，那么可以肯定这是最高等级的判定，可以直接返回。
                matchedLanguage = iahnNodeLocale.getKey().getLanguage();
                matchedCountry = iahnNodeLocale.getKey().getCountry();
                matchedVariant = iahnNodeLocale.getKey().getVariant();
                break;
            }
            // 等级 2 匹配判定。
            else if (
                    Objects.equals(language, iahnNodeLocale.getKey().getLanguage()) &&
                            Objects.equals(country, iahnNodeLocale.getKey().getCountry())
            ) {
                anchorMatchLevel = 2;
            }
            // 等级 1 匹配判定。
            else if (
                    Objects.equals(language, iahnNodeLocale.getKey().getLanguage())
            ) {
                anchorMatchLevel = 1;
            }
            // 其它情况，匹配等级设为 -1。
            else {
                anchorMatchLevel = -1;
            }

            // 如果 anchorMatchLevel 大于 matchLevel，则更新匹配等级。
            if (anchorMatchLevel > matchLevel) {
                matchedLanguage = iahnNodeLocale.getKey().getLanguage();
                matchedCountry = iahnNodeLocale.getKey().getCountry();
                matchedVariant = iahnNodeLocale.getKey().getVariant();
                matchLevel = anchorMatchLevel;
            }
        }

        // 返回格式化的地区。
        return formatLocale(matchedLanguage, matchedCountry, matchedVariant);
    }

    private List<MekMessagePair> lookupMekMessagePair(StringIdKey settingNodeKey, String matchedFormattedLocale)
            throws Exception {
        // 如果 matchedFormattedLocale 与 FORMATTED_LOCALE_DEFAULT 相等，则查询默认的地区对应的 MekMessagePair 列表。
        if (Objects.equals(matchedFormattedLocale, FORMATTED_LOCALE_DEFAULT)) {
            return lookupMekMessagePairForDefaultLocale(settingNodeKey);
        }
        // 否则，解析地区，并查询特定地区对应的 MekMessagePair 列表。
        else {
            // 解析变量。
            String[] localeArgs = matchedFormattedLocale.split("_");
            String language = localeArgs[0];
            String country = localeArgs.length > 1 ? localeArgs[1] : StringUtils.EMPTY;
            String variant = localeArgs.length > 2 ? localeArgs[2] : StringUtils.EMPTY;

            // 查询地区对应的 MekMessagePair 列表。
            return lookupMekMessagePairForSpecificLocale(settingNodeKey, language, country, variant);
        }
    }

    private List<MekMessagePair> lookupMekMessagePairForDefaultLocale(StringIdKey settingNodeKey) throws Exception {
        // 调用维护服务，查询属于 settingNodeKey 的所有 IahnNodeMek。
        List<IahnNodeMek> iahnNodeMeks = iahnNodeMekMaintainService.lookupAsList(
                IahnNodeMekMaintainService.CHILD_FOR_NODE, new Object[]{settingNodeKey}
        );
        // 取 IahnNodeMek 的 defaultMessage 作为 message 值构造 MekMessagePair。
        return iahnNodeMeks.stream().map(
                iahnNodeMek -> new MekMessagePair(iahnNodeMek.getKey().getMekId(), iahnNodeMek.getDefaultMessage())
        ).collect(Collectors.toList());
    }

    private List<MekMessagePair> lookupMekMessagePairForSpecificLocale(
            StringIdKey settingNodeKey, String language, String country, String variant
    ) throws Exception {
        // 构造 IahnNodeLocaleKey。
        IahnNodeLocaleKey iahnNodeLocaleKey = new IahnNodeLocaleKey(
                settingNodeKey.getStringId(), language, country, variant
        );
        // 调用维护服务，查询属于 IahnNodeLocale 的所有 IahnNodeMessage。
        List<IahnNodeMessage> iahnNodeMessages = iahnNodeMessageMaintainService.lookupAsList(
                IahnNodeMessageMaintainService.CHILD_FOR_LOCALE, new Object[]{iahnNodeLocaleKey}
        );
        // 将 IahnNodeMessage 转换为 MekMessagePair 并返回。
        return iahnNodeMessages.stream().map(
                iahnNodeMessage -> new MekMessagePair(iahnNodeMessage.getKey().getMekId(), iahnNodeMessage.getMessage())
        ).collect(Collectors.toList());
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void putLocale(IahnNodeLocalePutInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String language = IahnUtils.preProcessRfc4546Field(info.getLanguage());
            String country = IahnUtils.preProcessRfc4546Field(info.getCountry());
            String variant = IahnUtils.preProcessRfc4546Field(info.getVariant());
            String label = info.getLabel();
            String remark = info.getRemark();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认地区合法。
            handlerValidator.makeSureIahnNodeModifyLocaleValid(language, country, variant);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IAHN);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 构造 IahnNode，并插入或更新。
            IahnNode iahnNode = new IahnNode(settingNodeKey);
            iahnNodeMaintainService.insertOrUpdate(iahnNode);

            // 构造 IahnNodeLocaleKey 键，如果该键对应的实体存在，则首先进行删除。
            IahnNodeLocaleKey iahnNodeLocaleKey = new IahnNodeLocaleKey(
                    settingNodeKey.getStringId(), language, country, variant
            );
            iahnNodeLocaleMaintainService.deleteIfExists(iahnNodeLocaleKey);

            // 构造待插入的 IahnNodeLocale 实体。
            IahnNodeLocale iahnNodeLocaleToInsert = new IahnNodeLocale(iahnNodeLocaleKey, label, remark);

            // 调用维护服务插入 IahnNodeLocale 实体。
            iahnNodeLocaleMaintainService.insert(iahnNodeLocaleToInsert);

            // 构造待插入或更新的 IahnMessage 实体。
            List<IahnNodeMessage> iahnNodeMessagesToUpsert = new ArrayList<>();
            List<IahnNodeMek> iahnNodeMeks = iahnNodeMekMaintainService.lookupAsList(
                    IahnNodeMekMaintainService.CHILD_FOR_NODE, new Object[]{settingNodeKey}
            );
            for (IahnNodeMek iahnNodeMek : iahnNodeMeks) {
                iahnNodeMessagesToUpsert.add(new IahnNodeMessage(
                        new IahnNodeMessageKey(
                                settingNodeKey.getStringId(),
                                language,
                                country,
                                variant,
                                iahnNodeMek.getKey().getMekId()
                        ),
                        iahnNodeMek.getDefaultMessage()
                ));
            }

            // 调用维护服务批量插入或更新 IahnNodeMessage 实体。
            iahnNodeMessageMaintainService.batchInsertOrUpdate(iahnNodeMessagesToUpsert);

            // 清除地区映射缓存。
            String localeMapCacheGeneralFormat = parseCacheGeneralFormat(localeMapCachePrefix, settingNodeKey);
            Set<String> localeMapCacheKeys = redisTemplate.keys(localeMapCacheGeneralFormat);
            redisTemplate.delete(localeMapCacheKeys);

            // 清除消息映射缓存。
            String messageMapCacheGeneralFormat = parseCacheGeneralFormat(messageMapCachePrefix, settingNodeKey);
            Set<String> messageMapCacheKeys = redisTemplate.keys(messageMapCacheGeneralFormat);
            redisTemplate.delete(messageMapCacheKeys);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void removeLocale(IahnNodeLocaleRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String language = IahnUtils.preProcessRfc4546Field(info.getLanguage());
            String country = IahnUtils.preProcessRfc4546Field(info.getCountry());
            String variant = IahnUtils.preProcessRfc4546Field(info.getVariant());

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认地区合法。
            handlerValidator.makeSureIahnNodeModifyLocaleValid(language, country, variant);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IAHN);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 构造 IahnNode，并插入或更新。
            IahnNode iahnNode = new IahnNode(settingNodeKey);
            iahnNodeMaintainService.insertOrUpdate(iahnNode);

            // 构造 IahnNodeLocaleKey 键。
            IahnNodeLocaleKey iahnNodeLocaleKey = new IahnNodeLocaleKey(
                    settingNodeKey.getStringId(), language, country, variant
            );

            // 调用实体维护服务，如果存在则删除。
            iahnNodeLocaleMaintainService.deleteIfExists(iahnNodeLocaleKey);

            // 清除地区映射缓存。
            String localeMapCacheGeneralFormat = parseCacheGeneralFormat(localeMapCachePrefix, settingNodeKey);
            Set<String> localeMapCacheKeys = redisTemplate.keys(localeMapCacheGeneralFormat);
            redisTemplate.delete(localeMapCacheKeys);

            // 清除消息映射缓存。
            String messageMapCacheGeneralFormat = parseCacheGeneralFormat(messageMapCachePrefix, settingNodeKey);
            Set<String> messageMapCacheKeys = redisTemplate.keys(messageMapCacheGeneralFormat);
            redisTemplate.delete(messageMapCacheKeys);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void putMek(IahnNodeMekPutInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String mekId = IahnUtils.preProcessMekId(info.getMekId());
            String label = info.getLabel();
            String defaultMessage = info.getDefaultMessage();
            String remark = info.getRemark();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认 mekId 合法。
            handlerValidator.makeSureIahnNodeMekIdValid(mekId);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IAHN);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 构造 IahnNode，并插入或更新。
            IahnNode iahnNode = new IahnNode(settingNodeKey);
            iahnNodeMaintainService.insertOrUpdate(iahnNode);

            // 构造 IahnNodeMekKey 键，如果该键对应的实体存在，则首先进行删除。
            IahnNodeMekKey iahnNodeMekKey = new IahnNodeMekKey(settingNodeKey.getStringId(), mekId);
            iahnNodeMekMaintainService.deleteIfExists(iahnNodeMekKey);

            // 构造待插入的 IahnNodeMek 实体。
            IahnNodeMek iahnNodeMek = new IahnNodeMek(iahnNodeMekKey, label, defaultMessage, remark);

            // 调用维护服务插入 IahnNodeMek 实体。
            iahnNodeMekMaintainService.insert(iahnNodeMek);

            // 构造待插入或更新的 IahnMessage 实体。
            List<IahnNodeMessage> iahnNodeMessagesToUpsert = new ArrayList<>();
            List<IahnNodeLocale> iahnNodeLocales = iahnNodeLocaleMaintainService.lookupAsList(
                    IahnNodeLocaleMaintainService.CHILD_FOR_NODE, new Object[]{settingNodeKey}
            );
            for (IahnNodeLocale iahnNodeLocale : iahnNodeLocales) {
                iahnNodeMessagesToUpsert.add(new IahnNodeMessage(
                        new IahnNodeMessageKey(
                                settingNodeKey.getStringId(),
                                iahnNodeLocale.getKey().getLanguage(),
                                iahnNodeLocale.getKey().getCountry(),
                                iahnNodeLocale.getKey().getVariant(),
                                mekId
                        ),
                        defaultMessage
                ));
            }

            // 调用维护服务批量插入或更新 IahnNodeMessage 实体。
            iahnNodeMessageMaintainService.batchInsertOrUpdate(iahnNodeMessagesToUpsert);

            // 清除地区映射缓存。
            String localeMapCacheGeneralFormat = parseCacheGeneralFormat(localeMapCachePrefix, settingNodeKey);
            Set<String> localeMapCacheKeys = redisTemplate.keys(localeMapCacheGeneralFormat);
            redisTemplate.delete(localeMapCacheKeys);

            // 清除消息映射缓存。
            String messageMapCacheGeneralFormat = parseCacheGeneralFormat(messageMapCachePrefix, settingNodeKey);
            Set<String> messageMapCacheKeys = redisTemplate.keys(messageMapCacheGeneralFormat);
            redisTemplate.delete(messageMapCacheKeys);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void removeMek(IahnNodeMekRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String mekId = IahnUtils.preProcessMekId(info.getMekId());

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认 mekId 合法。
            handlerValidator.makeSureIahnNodeMekIdValid(mekId);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IAHN);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 构造 IahnNode，并插入或更新。
            IahnNode iahnNode = new IahnNode(settingNodeKey);
            iahnNodeMaintainService.insertOrUpdate(iahnNode);

            // 构造 IahnNodeMekKey 键。
            IahnNodeMekKey iahnNodeMekKey = new IahnNodeMekKey(settingNodeKey.getStringId(), mekId);

            // 调用实体维护服务，如果存在则删除。
            iahnNodeMekMaintainService.deleteIfExists(iahnNodeMekKey);

            // 清除地区映射缓存。
            String localeMapCacheGeneralFormat = parseCacheGeneralFormat(localeMapCachePrefix, settingNodeKey);
            Set<String> localeMapCacheKeys = redisTemplate.keys(localeMapCacheGeneralFormat);
            redisTemplate.delete(localeMapCacheKeys);

            // 清除消息映射缓存。
            String messageMapCacheGeneralFormat = parseCacheGeneralFormat(messageMapCachePrefix, settingNodeKey);
            Set<String> messageMapCacheKeys = redisTemplate.keys(messageMapCacheGeneralFormat);
            redisTemplate.delete(messageMapCacheKeys);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void upsertMessage(IahnNodeMessageUpsertInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String language = IahnUtils.preProcessRfc4546Field(info.getLanguage());
            String country = IahnUtils.preProcessRfc4546Field(info.getCountry());
            String variant = IahnUtils.preProcessRfc4546Field(info.getVariant());
            String mekId = IahnUtils.preProcessMekId(info.getMekId());
            String message = info.getMessage();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认地区合法。
            handlerValidator.makeSureIahnNodeModifyLocaleValid(language, country, variant);
            // 确认 mekId 合法。
            handlerValidator.makeSureIahnNodeMekIdValid(mekId);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IAHN);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 构造 IahnNode，并插入或更新。
            IahnNode iahnNode = new IahnNode(settingNodeKey);
            iahnNodeMaintainService.insertOrUpdate(iahnNode);

            // 确认国际化节点地区存在。
            IahnNodeLocaleKey iahnNodeLocaleKey = new IahnNodeLocaleKey(settingNodeKey.getStringId(),
                    language, country, variant
            );
            handlerValidator.makeSureIahnNodeLocaleExists(iahnNodeLocaleKey);
            // 确认国际化节点 mekId 存在。
            IahnNodeMekKey iahnNodeMekKey = new IahnNodeMekKey(settingNodeKey.getStringId(), mekId);
            handlerValidator.makeSureIahnNodeMekExists(iahnNodeMekKey);

            // 构造待插入或更新的 IahnNodeMessage 实体。
            IahnNodeMessage iahnNodeMessageToUpsert = new IahnNodeMessage(
                    new IahnNodeMessageKey(settingNodeKey.getStringId(), language, country, variant, mekId), message
            );

            // 调用实体维护服务插入或更新 IahnNodeMessage 实体。
            iahnNodeMessageMaintainService.insertOrUpdate(iahnNodeMessageToUpsert);

            // 清除地区映射缓存。
            String localeMapCacheGeneralFormat = parseCacheGeneralFormat(localeMapCachePrefix, settingNodeKey);
            Set<String> localeMapCacheKeys = redisTemplate.keys(localeMapCacheGeneralFormat);
            redisTemplate.delete(localeMapCacheKeys);

            // 清除消息映射缓存。
            String messageMapCacheGeneralFormat = parseCacheGeneralFormat(messageMapCachePrefix, settingNodeKey);
            Set<String> messageMapCacheKeys = redisTemplate.keys(messageMapCacheGeneralFormat);
            redisTemplate.delete(messageMapCacheKeys);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void batchUpsertMessageByLocale(IahnNodeMessageUpsertByLocaleInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String language = IahnUtils.preProcessRfc4546Field(info.getLanguage());
            String country = IahnUtils.preProcessRfc4546Field(info.getCountry());
            String variant = IahnUtils.preProcessRfc4546Field(info.getVariant());
            List<IahnNodeMessageUpsertByLocaleInfo.Item> items = info.getItems();

            // item 中的 mekId 预处理。
            List<IahnNodeMessageUpsertByLocaleInfo.Item> itemsDejaVu = new ArrayList<>(items.size());
            for (IahnNodeMessageUpsertByLocaleInfo.Item item : items) {
                itemsDejaVu.add(new IahnNodeMessageUpsertByLocaleInfo.Item(
                        IahnUtils.preProcessMekId(item.getMekId()), item.getMessage()
                ));
            }
            items = itemsDejaVu;

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认地区合法。
            handlerValidator.makeSureIahnNodeModifyLocaleValid(language, country, variant);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IAHN);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 构造 IahnNode，并插入或更新。
            IahnNode iahnNode = new IahnNode(settingNodeKey);
            iahnNodeMaintainService.insertOrUpdate(iahnNode);

            // 确认国际化节点地区存在。
            IahnNodeLocaleKey iahnNodeLocaleKey = new IahnNodeLocaleKey(settingNodeKey.getStringId(),
                    language, country, variant
            );
            handlerValidator.makeSureIahnNodeLocaleExists(iahnNodeLocaleKey);
            // 确认国际化节点 mekId 存在。
            for (IahnNodeMessageUpsertByLocaleInfo.Item item : items) {
                IahnNodeMekKey iahnNodeMekKey = new IahnNodeMekKey(settingNodeKey.getStringId(), item.getMekId());
                handlerValidator.makeSureIahnNodeMekExists(iahnNodeMekKey);
            }

            // 构造待插入或更新的 IahnNodeMessage 实体列表。
            List<IahnNodeMessage> iahnNodeMessagesToUpsert = new ArrayList<>();
            for (IahnNodeMessageUpsertByLocaleInfo.Item item : items) {
                iahnNodeMessagesToUpsert.add(new IahnNodeMessage(
                        new IahnNodeMessageKey(
                                settingNodeKey.getStringId(), language, country, variant, item.getMekId()
                        ),
                        item.getMessage()
                ));
            }

            // 调用实体维护服务批量插入或更新 IahnNodeMessage 实体列表。
            iahnNodeMessageMaintainService.batchInsertOrUpdate(iahnNodeMessagesToUpsert);

            // 清除地区映射缓存。
            String localeMapCacheGeneralFormat = parseCacheGeneralFormat(localeMapCachePrefix, settingNodeKey);
            Set<String> localeMapCacheKeys = redisTemplate.keys(localeMapCacheGeneralFormat);
            redisTemplate.delete(localeMapCacheKeys);

            // 清除消息映射缓存。
            String messageMapCacheGeneralFormat = parseCacheGeneralFormat(messageMapCachePrefix, settingNodeKey);
            Set<String> messageMapCacheKeys = redisTemplate.keys(messageMapCacheGeneralFormat);
            redisTemplate.delete(messageMapCacheKeys);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void batchUpsertMessageByMek(IahnNodeMessageUpsertByMekInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String mekId = IahnUtils.preProcessMekId(info.getMekId());
            List<IahnNodeMessageUpsertByMekInfo.Item> items = info.getItems();

            // item 中的 language, country, variant 预处理。
            List<IahnNodeMessageUpsertByMekInfo.Item> itemsDejaVu = new ArrayList<>(items.size());
            for (IahnNodeMessageUpsertByMekInfo.Item item : items) {
                itemsDejaVu.add(new IahnNodeMessageUpsertByMekInfo.Item(
                        IahnUtils.preProcessRfc4546Field(item.getLanguage()),
                        IahnUtils.preProcessRfc4546Field(item.getCountry()),
                        IahnUtils.preProcessRfc4546Field(item.getVariant()),
                        item.getMessage()
                ));
            }
            items = itemsDejaVu;

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认 mekId 合法。
            handlerValidator.makeSureIahnNodeMekIdValid(mekId);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IAHN);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 构造 IahnNode，并插入或更新。
            IahnNode iahnNode = new IahnNode(settingNodeKey);
            iahnNodeMaintainService.insertOrUpdate(iahnNode);

            // 确认国际化节点地区存在。
            for (IahnNodeMessageUpsertByMekInfo.Item item : items) {
                IahnNodeLocaleKey iahnNodeLocaleKey = new IahnNodeLocaleKey(settingNodeKey.getStringId(),
                        item.getLanguage(), item.getCountry(), item.getVariant()
                );
                handlerValidator.makeSureIahnNodeLocaleExists(iahnNodeLocaleKey);
            }
            // 确认国际化节点 mekId 存在。
            IahnNodeMekKey iahnNodeMekKey = new IahnNodeMekKey(settingNodeKey.getStringId(), mekId);
            handlerValidator.makeSureIahnNodeMekExists(iahnNodeMekKey);

            // 构造待插入或更新的 IahnNodeMessage 实体列表。
            List<IahnNodeMessage> iahnNodeMessagesToUpsert = new ArrayList<>();
            for (IahnNodeMessageUpsertByMekInfo.Item item : items) {
                String language = IahnUtils.preProcessRfc4546Field(item.getLanguage());
                String country = IahnUtils.preProcessRfc4546Field(item.getCountry());
                String variant = IahnUtils.preProcessRfc4546Field(item.getVariant());
                iahnNodeMessagesToUpsert.add(new IahnNodeMessage(
                        new IahnNodeMessageKey(settingNodeKey.getStringId(), language, country, variant, mekId),
                        item.getMessage()
                ));
            }

            // 调用实体维护服务批量插入或更新 IahnNodeMessage 实体列表。
            iahnNodeMessageMaintainService.batchInsertOrUpdate(iahnNodeMessagesToUpsert);

            // 清除地区映射缓存。
            String localeMapCacheGeneralFormat = parseCacheGeneralFormat(localeMapCachePrefix, settingNodeKey);
            Set<String> localeMapCacheKeys = redisTemplate.keys(localeMapCacheGeneralFormat);
            redisTemplate.delete(localeMapCacheKeys);

            // 清除消息映射缓存。
            String messageMapCacheGeneralFormat = parseCacheGeneralFormat(messageMapCachePrefix, settingNodeKey);
            Set<String> messageMapCacheKeys = redisTemplate.keys(messageMapCacheGeneralFormat);
            redisTemplate.delete(messageMapCacheKeys);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，不简化此方法。
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean settingNodeMatching(SettingNode settingNode) {
        if (Objects.isNull(settingNode)) {
            return false;
        }
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_IAHN);
    }

    private String formatLocale(String language, String country, String variant) {
        if (StringUtils.isEmpty(language)) {
            return FORMATTED_LOCALE_DEFAULT;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(language);
        if (StringUtils.isNotEmpty(country)) {
            sb.append("-").append(country);
        }
        if (StringUtils.isNotEmpty(variant)) {
            sb.append("-").append(variant);
        }
        return sb.toString();
    }

    private String parseCacheKey(
            String prefix, StringIdKey settingNodeKey, String language, String country, String variant
    ) {
        return prefix + settingNodeKey.getStringId() + "." + formatLocale(language, country, variant);
    }

    private String parseCacheKey(String prefix, StringIdKey settingNodeKey, String formattedLocale) {
        return prefix + settingNodeKey.getStringId() + "." + formattedLocale;
    }

    private String parseCacheGeneralFormat(String prefix, StringIdKey settingNodeKey) {
        return prefix + settingNodeKey.getStringId() +
                com.dwarfeng.subgrade.sdk.common.Constants.REDIS_KEY_WILDCARD_CHARACTER;
    }

    private static final class MekMessagePair {

        private final String mekId;
        private final String message;

        public MekMessagePair(String mekId, String message) {
            this.mekId = mekId;
            this.message = message;
        }

        public String getMekId() {
            return mekId;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;

            MekMessagePair that = (MekMessagePair) o;
            return Objects.equals(mekId, that.mekId) && Objects.equals(message, that.message);
        }

        @Override
        public int hashCode() {
            int result = Objects.hashCode(mekId);
            result = 31 * result + Objects.hashCode(message);
            return result;
        }

        @Override
        public String toString() {
            return "MekMessagePair{" +
                    "mekId='" + mekId + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
