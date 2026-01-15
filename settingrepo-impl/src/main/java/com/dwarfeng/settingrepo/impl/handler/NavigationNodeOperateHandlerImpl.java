package com.dwarfeng.settingrepo.impl.handler;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.settingrepo.sdk.bean.dto.FastJsonNavigationNodeInspectResult;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNode;
import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNodeItem;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.NavigationNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NavigationNodeOperateHandlerImpl implements NavigationNodeOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationNodeOperateHandlerImpl.class);

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final NavigationNodeMaintainService navigationNodeMaintainService;
    private final NavigationNodeItemMaintainService navigationNodeItemMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    private final RedisTemplate<String, String> redisTemplate;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final HandlerValidator handlerValidator;

    @Value("${navigation.cache.prefix.inspect_result}")
    private String inspectResultCachePrefix;
    @Value("${navigation.cache.timeout.inspect_result}")
    private long inspectResultCacheTimeout;

    @Value("${navigation.format.start_index}")
    private int formatStartIndex;
    @Value("${navigation.format.index_step}")
    private int formatIndexStep;

    public NavigationNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            NavigationNodeMaintainService navigationNodeMaintainService,
            NavigationNodeItemMaintainService navigationNodeItemMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            RedisTemplate<String, String> redisTemplate,
            KeyGenerator<LongIdKey> keyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.navigationNodeMaintainService = navigationNodeMaintainService;
        this.navigationNodeItemMaintainService = navigationNodeItemMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.redisTemplate = redisTemplate;
        this.keyGenerator = keyGenerator;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public NavigationNodeSizeResult size(NavigationNodeSizeInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 获取导航节点实体。
            NavigationNode navigationNode = navigationNodeMaintainService.getIfExists(settingNodeKey);

            // 如果导航节点实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(navigationNode)) {
                navigationNode = new NavigationNode(settingNodeKey, 0, null);
                navigationNodeMaintainService.insertOrUpdate(navigationNode);
            }

            // 构造 NavigationNodeSizeResult 并返回。
            return new NavigationNodeSizeResult(navigationNode.getSize());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public NavigationNodeInspectResult inspect(NavigationNodeInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 获取导航节点实体。
            NavigationNode navigationNode = navigationNodeMaintainService.getIfExists(settingNodeKey);

            // 如果导航节点实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(navigationNode)) {
                navigationNode = new NavigationNode(settingNodeKey, 0, null);
                navigationNodeMaintainService.insertOrUpdate(navigationNode);
            }

            // 读取 NavigationNodeInspectResult。
            NavigationNodeInspectResult result;
            String inspectResultCacheKey = parseCacheKey(inspectResultCachePrefix, settingNodeKey);
            if (redisTemplate.hasKey(inspectResultCacheKey)) {
                String resultString = redisTemplate.opsForValue().get(inspectResultCacheKey);
                result = FastJsonNavigationNodeInspectResult.toStackBean(
                        JSON.parseObject(resultString, FastJsonNavigationNodeInspectResult.class)
                );
            } else {
                List<NavigationNodeInspectResult.Item> children = findNavigationNodeItemDescendant(settingNodeKey);
                result = new NavigationNodeInspectResult(
                        navigationNode.getSize(),
                        navigationNode.getContent(),
                        children
                );
                String resultString = JSON.toJSONString(FastJsonNavigationNodeInspectResult.of(result));
                redisTemplate.opsForValue().set(inspectResultCacheKey, resultString, inspectResultCacheTimeout);
            }

            // 返回结果。
            return result;
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private List<NavigationNodeInspectResult.Item> findNavigationNodeItemDescendant(StringIdKey settingNodeKey)
            throws Exception {
        // 定义结果变量。
        List<NavigationNodeInspectResult.Item> result = new ArrayList<>();

        // 定义迭代查询需要的对象，并初始化。
        Stack<LongIdKey> stack = new Stack<>();
        Map<LongIdKey, List<NavigationNodeInspectResult.Item>> childrenMap = new HashMap<>();
        // 查询时降序查询，推送到栈中后，出栈即为升序。
        List<NavigationNodeItem> navigationNodeItems = navigationNodeItemMaintainService.lookupAsList(
                NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_DESC,
                new Object[]{settingNodeKey, null}
        );
        for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
            stack.push(navigationNodeItem.getKey());
        }

        // 在栈清空之前，一直执行循环。
        while (!stack.isEmpty()) {
            // 从栈中取出当前的节点。
            LongIdKey navigationNodeItemKey = stack.pop();
            // 查询节点实体。
            NavigationNodeItem navigationNodeItem = navigationNodeItemMaintainService.get(navigationNodeItemKey);
            // 构造子节点列表，并 put 到 childrenMap 中。
            if (childrenMap.containsKey(navigationNodeItemKey)) {
                LOGGER.warn("不应该执行到此处, 将抛出异常, 请联系开发人员");
                throw new IllegalStateException("childrenMap should not contain key: " + navigationNodeItemKey);
            }
            List<NavigationNodeInspectResult.Item> children = new ArrayList<>();
            childrenMap.put(navigationNodeItemKey, children);
            // 构造查看结果条目。
            NavigationNodeInspectResult.Item item = new NavigationNodeInspectResult.Item(
                    navigationNodeItem.getKey(),
                    navigationNodeItem.getParentKey(),
                    navigationNodeItem.getIndex(),
                    navigationNodeItem.getName(),
                    navigationNodeItem.getContent(),
                    navigationNodeItem.getRemark(),
                    children
            );
            // 将条目放入对应父节点的子节点列表中，若无父节点则放入结果列表中。
            if (Objects.isNull(navigationNodeItem.getParentKey())) {
                result.add(item);
            } else {
                List<NavigationNodeInspectResult.Item> parentChildren =
                        childrenMap.get(navigationNodeItem.getParentKey());
                if (Objects.isNull(parentChildren)) {
                    LOGGER.warn("不应该执行到此处, 将抛出异常, 请联系开发人员");
                    throw new IllegalStateException(
                            "parent children list should not be null: " + navigationNodeItem.getParentKey()
                    );
                }
                parentChildren.add(item);
            }
            // 查询子节点，并推送到栈中。
            List<NavigationNodeItem> childNavigationNodeItems = navigationNodeItemMaintainService.lookupAsList(
                    NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_DESC,
                    new Object[]{settingNodeKey, navigationNodeItemKey}
            );
            for (NavigationNodeItem childNavigationNodeItem : childNavigationNodeItems) {
                stack.push(childNavigationNodeItem.getKey());
            }
        }

        // 返回结果。
        return result;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void updateNode(NavigationNodeUpdateInfo info) throws HandlerException {
        try {
            String category = info.getCategory();
            String[] args = info.getArgs();
            String content = info.getContent();

            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的导航节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_NAVIGATION, new Date(), settingNodeRemark,
                        true, category, args
                );
            } else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_NAVIGATION);
                settingNode.setLastModifiedDate(new Date());
            }
            settingNodeMaintainService.insertOrUpdate(settingNode);

            NavigationNode navigationNode = navigationNodeMaintainService.getIfExists(settingNodeKey);

            if (Objects.isNull(navigationNode)) {
                navigationNode = new NavigationNode(settingNodeKey, 0, content);
            } else {
                navigationNode.setContent(content);
            }

            navigationNodeMaintainService.insertOrUpdate(navigationNode);

            String inspectResultCacheKey = parseCacheKey(inspectResultCachePrefix, settingNodeKey);
            redisTemplate.delete(inspectResultCacheKey);

        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public NavigationNodeItemInsertResult insertItem(NavigationNodeItemInsertInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            LongIdKey parentItemKey = info.getParentItemKey();
            int index = info.getIndex();
            String name = info.getName();
            String content = info.getContent();
            String remark = info.getRemark();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认父条目存在。
            if (Objects.nonNull(parentItemKey)) {
                handlerValidator.makeSureNavigationNodeItemExists(parentItemKey);
            }

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 确认父条目属于对应的设置节点。
            if (Objects.nonNull(parentItemKey)) {
                handlerValidator.makeSureNavigationNodeItemMatched(parentItemKey, settingNodeKey);
            }
            // 确认索引合法。
            handlerValidator.makeSureNavigationNodeItemIndexNotConflict(settingNodeKey, parentItemKey, index);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的导航节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_NAVIGATION, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_NAVIGATION);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取导航节点实体。
            NavigationNode navigationNode = navigationNodeMaintainService.getIfExists(settingNodeKey);

            // 如果导航节点实体不存在，则初始化实体。
            if (Objects.isNull(navigationNode)) {
                navigationNode = new NavigationNode(settingNodeKey, 0, null);
            }

            // 构造 NavigationNodeItem 实体，插入。
            LongIdKey navigationNodeItemKey = keyGenerator.generate();
            NavigationNodeItem navigationNodeItem = new NavigationNodeItem(
                    navigationNodeItemKey, settingNodeKey, parentItemKey, index, name, content, remark
            );

            // 更新导航节点实体的 size 属性，插入或更新实体。
            navigationNode.setSize(navigationNode.getSize() + 1);
            navigationNodeMaintainService.insertOrUpdate(navigationNode);

            // 插入 NavigationNodeItem 实体。
            navigationNodeItemMaintainService.insert(navigationNodeItem);

            // 清除查看结果缓存。
            String inspectResultCacheKey = parseCacheKey(inspectResultCachePrefix, settingNodeKey);
            redisTemplate.delete(inspectResultCacheKey);

            // 构造并返回结果。
            return new NavigationNodeItemInsertResult(category, args, navigationNodeItemKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void updateItem(NavigationNodeItemUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            LongIdKey itemKey = info.getItemKey();
            LongIdKey parentItemKey = info.getParentItemKey();
            int index = info.getIndex();
            String name = info.getName();
            String content = info.getContent();
            String remark = info.getRemark();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认条目存在。
            handlerValidator.makeSureNavigationNodeItemExists(itemKey);
            // 确认父条目存在。
            if (Objects.nonNull(parentItemKey)) {
                handlerValidator.makeSureNavigationNodeItemExists(parentItemKey);
            }

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 确认条目属于对应的设置节点。
            handlerValidator.makeSureNavigationNodeItemMatched(itemKey, settingNodeKey);
            // 确认父条目属于对应的设置节点。
            if (Objects.nonNull(parentItemKey)) {
                handlerValidator.makeSureNavigationNodeItemMatched(parentItemKey, settingNodeKey);
            }
            // 确认索引合法。
            if (navigationNodeItemMaintainService.get(itemKey).getIndex() != index) {
                handlerValidator.makeSureNavigationNodeItemIndexNotConflict(settingNodeKey, parentItemKey, index);
            }

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的导航节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_NAVIGATION, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_NAVIGATION);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取导航节点实体。
            NavigationNode navigationNode = navigationNodeMaintainService.getIfExists(settingNodeKey);

            // 如果导航节点实体不存在，则初始化实体。
            if (Objects.isNull(navigationNode)) {
                navigationNode = new NavigationNode(settingNodeKey, 0, null);
            }

            // 获取 NavigationNodeItem 实体，更新。
            NavigationNodeItem navigationNodeItem = navigationNodeItemMaintainService.get(itemKey);
            navigationNodeItem.setParentKey(parentItemKey);
            navigationNodeItem.setIndex(index);
            navigationNodeItem.setName(name);
            navigationNodeItem.setContent(content);
            navigationNodeItem.setRemark(remark);
            navigationNodeItemMaintainService.update(navigationNodeItem);

            // 插入或更新导航节点实体。
            navigationNodeMaintainService.insertOrUpdate(navigationNode);

            // 清除查看结果缓存。
            String inspectResultCacheKey = parseCacheKey(inspectResultCachePrefix, settingNodeKey);
            redisTemplate.delete(inspectResultCacheKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void removeItem(NavigationNodeItemRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            LongIdKey itemKey = info.getItemKey();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认条目存在。
            handlerValidator.makeSureNavigationNodeItemExists(itemKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 确认条目属于对应的设置节点。
            handlerValidator.makeSureNavigationNodeItemMatched(itemKey, settingNodeKey);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的导航节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_NAVIGATION, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_NAVIGATION);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取导航节点实体。
            NavigationNode navigationNode = navigationNodeMaintainService.getIfExists(settingNodeKey);

            // 如果导航节点实体不存在，则初始化实体。
            if (Objects.isNull(navigationNode)) {
                navigationNode = new NavigationNode(settingNodeKey, 0, null);
            }

            // 删除 NavigationNodeItem 实体。
            navigationNodeItemMaintainService.delete(itemKey);

            // 更新导航节点实体的 size 属性，插入或更新实体。
            navigationNode.setSize(countNavigationNodeItemDescendant(settingNodeKey));
            navigationNodeMaintainService.insertOrUpdate(navigationNode);

            // 清除查看结果缓存。
            String inspectResultCacheKey = parseCacheKey(inspectResultCachePrefix, settingNodeKey);
            redisTemplate.delete(inspectResultCacheKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private int countNavigationNodeItemDescendant(StringIdKey settingNodeKey) throws Exception {
        // 定义结果变量。
        int result = 0;

        // 定义迭代查询需要的对象，并初始化。
        Stack<LongIdKey> stack = new Stack<>();
        // 查询时降序查询，推送到栈中后，出栈即为升序。
        List<NavigationNodeItem> navigationNodeItems = navigationNodeItemMaintainService.lookupAsList(
                NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_DESC,
                new Object[]{settingNodeKey, null}
        );
        for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
            stack.push(navigationNodeItem.getKey());
            result++;
        }

        // 在栈清空之前，一直执行循环。
        while (!stack.isEmpty()) {
            // 从栈中取出当前的节点。
            LongIdKey navigationNodeItemKey = stack.pop();
            // 查询子节点，并推送到栈中。
            List<NavigationNodeItem> childNavigationNodeItems = navigationNodeItemMaintainService.lookupAsList(
                    NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_DESC,
                    new Object[]{settingNodeKey, navigationNodeItemKey}
            );
            for (NavigationNodeItem childNavigationNodeItem : childNavigationNodeItems) {
                stack.push(childNavigationNodeItem.getKey());
                result++;
            }
        }

        // 返回结果。
        return result;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void formatIndex(NavigationNodeFormatIndexInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            LongIdKey parentItemKey = info.getParentItemKey();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);
            // 确认父条目存在。
            if (Objects.nonNull(parentItemKey)) {
                handlerValidator.makeSureNavigationNodeItemExists(parentItemKey);
            }

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 确认父条目属于对应的设置节点。
            if (Objects.nonNull(parentItemKey)) {
                handlerValidator.makeSureNavigationNodeItemMatched(parentItemKey, settingNodeKey);
            }

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String settingNodeRemark = "由 settingrepo 自动生成的导航节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_NAVIGATION, new Date(), settingNodeRemark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_NAVIGATION);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 以升序获取所有子节点，重新格式化索引。
            List<NavigationNodeItem> navigationNodeItems = navigationNodeItemMaintainService.lookupAsList(
                    NavigationNodeItemMaintainService.CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_ASC,
                    new Object[]{settingNodeKey, parentItemKey}
            );
            int currentIndex = formatStartIndex;
            for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
                navigationNodeItem.setIndex(currentIndex);
                currentIndex += formatIndexStep;
            }
            navigationNodeItemMaintainService.batchUpdate(navigationNodeItems);

            // 清除查看结果缓存。
            String inspectResultCacheKey = parseCacheKey(inspectResultCachePrefix, settingNodeKey);
            redisTemplate.delete(inspectResultCacheKey);
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
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_NAVIGATION);
    }

    private String parseCacheKey(String prefix, StringIdKey settingNodeKey) {
        return prefix + settingNodeKey.getStringId();
    }
}
