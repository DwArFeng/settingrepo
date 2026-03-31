package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.KvNode;
import com.dwarfeng.settingrepo.stack.bean.entity.KvNodeItem;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.KvNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.KvNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.KvNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 键值对节点操作处理器实现。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
@Component
public class KvNodeOperateHandlerImpl implements KvNodeOperateHandler {

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final KvNodeMaintainService kvNodeMaintainService;
    private final KvNodeItemMaintainService kvNodeItemMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    private final HandlerValidator handlerValidator;

    public KvNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            KvNodeMaintainService kvNodeMaintainService,
            KvNodeItemMaintainService kvNodeItemMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.kvNodeMaintainService = kvNodeMaintainService;
        this.kvNodeItemMaintainService = kvNodeItemMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public KvNodeCountResult count(KvNodeCountInfo info) throws HandlerException {
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

            // 获取或初始化键值对节点实体，并返回条目数量。
            KvNode kvNode = ensureKvNode(settingNodeKey);

            // 构造 KvNodeCountResult 并返回。
            return new KvNodeCountResult(kvNode.getCount());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public KvNodeInspectResult inspect(KvNodeInspectInfo info) throws HandlerException {
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

            // 若键值对节点记录不存在则初始化，保证后续查询一致。
            ensureKvNode(settingNodeKey);

            // 按 itemStringId 升序查询该节点下全部条目。
            List<KvNodeItem> kvNodeItems = kvNodeItemMaintainService.lookupAsList(
                    KvNodeItemMaintainService.CHILD_FOR_NODE_ITEM_STRING_ID_ASC, new Object[]{settingNodeKey}
            );

            // 映射为查看结果条目列表。
            List<KvNodeInspectResult.Item> items = kvNodeItems.stream()
                    .map(
                            kvNodeItem -> new KvNodeInspectResult.Item(
                                    kvNodeItem.getKey().getItemStringId(),
                                    kvNodeItem.getValue()
                            )
                    )
                    .collect(Collectors.toList());

            // 构造 KvNodeInspectResult 并返回。
            return new KvNodeInspectResult(items);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public KvNodeItemInspectResult inspectItem(KvNodeItemInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String itemStringId = info.getItemStringId();

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

            // 若键值对节点记录不存在则初始化。
            ensureKvNode(settingNodeKey);

            // 构造条目主键并查询实体。
            KvNodeItemKey itemKey = new KvNodeItemKey(settingNodeKey.getStringId(), itemStringId);
            KvNodeItem kvNodeItem = kvNodeItemMaintainService.getIfExists(itemKey);

            // 如果条目不存在，则返回 value 为 null 的结果。
            if (Objects.isNull(kvNodeItem)) {
                return new KvNodeItemInspectResult(null);
            }

            // 构造 KvNodeItemInspectResult 并返回。
            return new KvNodeItemInspectResult(kvNodeItem.getValue());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @BehaviorAnalyse
    @Override
    public void putItem(KvNodeItemPutInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String itemStringId = info.getItemStringId();
            String value = info.getValue();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 插入或更新设置节点（类型为键值对节点）。
            prepareSettingNodeForWrite(category, args, settingNodeKey);

            // 获取或初始化键值对节点实体。
            KvNode kvNode = ensureKvNode(settingNodeKey);

            // 构造条目主键。
            KvNodeItemKey itemKey = new KvNodeItemKey(settingNodeKey.getStringId(), itemStringId);

            // 插入或更新键值对节点条目。
            kvNodeItemMaintainService.insertOrUpdate(new KvNodeItem(itemKey, value));

            // 根据当前条目数量同步键值对节点的 count。
            syncKvNodeCountFromItems(settingNodeKey, kvNode);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void removeItem(KvNodeItemRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String itemStringId = info.getItemStringId();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 插入或更新设置节点（类型为键值对节点）。
            prepareSettingNodeForWrite(category, args, settingNodeKey);

            // 获取或初始化键值对节点实体。
            KvNode kvNode = ensureKvNode(settingNodeKey);

            // 构造条目主键。
            KvNodeItemKey itemKey = new KvNodeItemKey(settingNodeKey.getStringId(), itemStringId);

            // 若条目存在则删除。
            kvNodeItemMaintainService.deleteIfExists(itemKey);

            // 根据当前条目数量同步键值对节点的 count。
            syncKvNodeCountFromItems(settingNodeKey, kvNode);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @BehaviorAnalyse
    @Override
    public void clear(KvNodeClearInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 插入或更新设置节点（类型为键值对节点）。
            prepareSettingNodeForWrite(category, args, settingNodeKey);

            // 获取或初始化键值对节点实体。
            KvNode kvNode = ensureKvNode(settingNodeKey);

            // 查询该节点下全部条目。
            List<KvNodeItem> kvNodeItems = kvNodeItemMaintainService.lookupAsList(
                    KvNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{settingNodeKey}
            );

            // 若存在条目则批量删除。
            if (!kvNodeItems.isEmpty()) {
                List<KvNodeItemKey> keys = kvNodeItems.stream()
                        .map(KvNodeItem::getKey)
                        .collect(Collectors.toList());
                kvNodeItemMaintainService.batchDelete(keys);
            }

            // 根据当前条目数量同步键值对节点的 count（清空后应为 0）。
            syncKvNodeCountFromItems(settingNodeKey, kvNode);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 按子表条目数量写回键值对节点的 count 字段。
    private void syncKvNodeCountFromItems(StringIdKey settingNodeKey, KvNode kvNode) throws Exception {
        int itemCount = kvNodeItemMaintainService.lookupCount(
                KvNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{settingNodeKey}
        );
        kvNode.setCount(itemCount);
        kvNodeMaintainService.insertOrUpdate(kvNode);
    }

    // 为写操作准备设置节点：不存在则创建为键值对类型，存在则更新类型与最后修改时间。
    private void prepareSettingNodeForWrite(String category, String[] args, StringIdKey settingNodeKey)
            throws Exception {
        // 获取设置节点实体。
        SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

        // 如果节点设置不存在，则初始化节点设置实体。
        if (Objects.isNull(settingNode)) {
            String remark = "由 settingrepo 自动生成的键值对节点";
            settingNode = new SettingNode(
                    settingNodeKey, Constants.SETTING_NODE_TYPE_KV, new Date(), remark,
                    true, category, args
            );
        }
        // 否则更新属性。
        else {
            settingNode.setType(Constants.SETTING_NODE_TYPE_KV);
            settingNode.setLastModifiedDate(new Date());
        }

        // 插入或更新设置节点。
        settingNodeMaintainService.insertOrUpdate(settingNode);
    }

    // 为了代码的可读性，不简化此方法。
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean settingNodeMatching(SettingNode settingNode) {
        if (Objects.isNull(settingNode)) {
            return false;
        }
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_KV);
    }

    // 获取键值对节点实体；若不存在则初始化为 count 为 0 并插入或更新。
    private KvNode ensureKvNode(StringIdKey settingNodeKey) throws Exception {
        KvNode kvNode = kvNodeMaintainService.getIfExists(settingNodeKey);
        // 如果键值对节点实体不存在，则初始化并插入或更新。
        if (Objects.isNull(kvNode)) {
            kvNode = new KvNode(settingNodeKey, 0);
            kvNodeMaintainService.insertOrUpdate(kvNode);
        }
        return kvNode;
    }
}
