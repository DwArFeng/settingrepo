package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.bean.entity.TextListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.TextListNodeItem;
import com.dwarfeng.settingrepo.stack.exception.TextListNodeIndexOutOfBoundException;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.TextListNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.TextListNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.TextListNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class TextListNodeOperateHandlerImpl implements TextListNodeOperateHandler {

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final TextListNodeMaintainService textListNodeMaintainService;
    private final TextListNodeItemMaintainService textListNodeItemMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    private final HandlerValidator handlerValidator;

    public TextListNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            TextListNodeMaintainService textListNodeMaintainService,
            TextListNodeItemMaintainService textListNodeItemMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.textListNodeMaintainService = textListNodeMaintainService;
        this.textListNodeItemMaintainService = textListNodeItemMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public TextListNodeSizeResult size(TextListNodeSizeInfo info) throws HandlerException {
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

            // 获取文本列表节点实体。
            TextListNode textListNode = textListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文本列表节点实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(textListNode)) {
                textListNode = new TextListNode(settingNodeKey, 0);
                textListNodeMaintainService.insertOrUpdate(textListNode);
            }

            // 构造 TextListNodeSizeResult 并返回。
            return new TextListNodeSizeResult(textListNode.getSize());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，此处不简化代码。
    @SuppressWarnings({"DuplicatedCode"})
    @BehaviorAnalyse
    @Override
    public TextListNodeInspectResult inspect(TextListNodeInspectInfo info) throws HandlerException {
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

            // 获取文本列表节点实体。
            TextListNode textListNode = textListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文本列表节点实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(textListNode)) {
                textListNode = new TextListNode(settingNodeKey, 0);
                textListNodeMaintainService.insertOrUpdate(textListNode);
            }

            // 获取文本列表节点条目。
            List<TextListNodeItem> textListNodeItems = textListNodeItemMaintainService.lookupAsList(
                    TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_ASC, new Object[]{settingNodeKey}
            );

            // 构造 TextListNodeInspectResult 并返回。
            List<TextListNodeInspectResult.Item> resultItems = new ArrayList<>();
            for (TextListNodeItem textListNodeItem : textListNodeItems) {
                TextListNodeInspectResult.Item resultItem = new TextListNodeInspectResult.Item(
                        textListNodeItem.getIndex(),
                        textListNodeItem.isNullFlag()
                );
                resultItems.add(resultItem);
            }
            return new TextListNodeInspectResult(resultItems);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public TextListNodeTextFetchResult fetchText(TextListNodeTextFetchInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();

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

            // 获取文本列表节点实体。
            TextListNode textListNode = textListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文本列表节点实体不存在，则直接返回 null。
            if (Objects.isNull(textListNode)) {
                return null;
            }

            // 获取指定索引对应的文本列表节点条目。
            TextListNodeItem textListNodeItem = textListNodeItemMaintainService.lookupFirst(
                    TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 如果条目不存在或 nullFlag 为 true，则直接返回 null。
            if (Objects.isNull(textListNodeItem)) {
                return null;
            }
            if (textListNodeItem.isNullFlag()) {
                return null;
            }

            // 构造 TextListNodeTextFetchResult 并返回。
            return new TextListNodeTextFetchResult(textListNodeItem.getContent());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void insertText(TextListNodeTextInsertInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            Integer index = info.getIndex();
            String content = info.getContent();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的文本列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_TEXT_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_TEXT_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文本列表节点实体。
            TextListNode textListNode = textListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文本列表节点实体不存在，则初始化实体。
            if (Objects.isNull(textListNode)) {
                textListNode = new TextListNode(settingNodeKey, 0);
            }

            // 构造待更新的 TextListNodeItem 列表。
            List<TextListNodeItem> textListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 根据 index 分三种情况讨论：
            // 如果 index 为 null。
            if (Objects.isNull(index)) {
                // 向文本列表的末尾插入新的文本。
                TextListNodeItem textListNodeItem = new TextListNodeItem(
                        null, settingNodeKey, textListNode.getSize(), false, content
                );
                textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
                // textListNode 实体的 size 属性加一。
                textListNode.setSize(textListNode.getSize() + 1);
            }
            // 如果 index 小于 textListNode.size()
            else if (index < textListNode.getSize()) {
                // 获取 index 之后（包括当前索引）的所有文本列表节点条目，对所有的文本列表节点条目的 index 属性加一。
                List<TextListNodeItem> textListNodeItems = textListNodeItemMaintainService.lookupAsList(
                        TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                        new Object[]{settingNodeKey, index}
                );
                for (TextListNodeItem textListNodeItem : textListNodeItems) {
                    textListNodeItem.setIndex(textListNodeItem.getIndex() + 1);
                    textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
                }
                // 添加插入文本的 TextListNodeItem。
                TextListNodeItem textListNodeItem = new TextListNodeItem(
                        null, settingNodeKey, index, false, content
                );
                textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
                // textListNode 实体的 size 属性加一。
                textListNode.setSize(textListNode.getSize() + 1);
            }
            // 其余情况（index >= textListNode.size()）
            else {
                // textListNode.size() 到 index 之前填充 nullFlag = true 的 TextListNodeItem。
                for (int i = textListNode.getSize(); i < index; i++) {
                    TextListNodeItem textListNodeItem = new TextListNodeItem(
                            null, settingNodeKey, i, true, null
                    );
                    textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
                }
                // 插入新的文本。
                TextListNodeItem textListNodeItem = new TextListNodeItem(
                        null, settingNodeKey, index, false, content
                );
                textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
                // textListNode 实体的 size 属性设置为 index + 1。
                textListNode.setSize(index + 1);
            }

            // 插入或更新文本列表节点实体。
            textListNodeMaintainService.insertOrUpdate(textListNode);

            // 批量插入或更新文本列表节点条目。
            textListNodeItemMaintainService.batchInsertOrUpdate(textListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void updateText(TextListNodeTextUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();
            String content = info.getContent();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的文本列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_TEXT_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_TEXT_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文本列表节点实体。
            TextListNode textListNode = textListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文本列表节点实体不存在，则初始化实体。
            if (Objects.isNull(textListNode)) {
                textListNode = new TextListNode(settingNodeKey, 0);
            }

            // 构造待更新的 TextListNodeItem 列表。
            List<TextListNodeItem> textListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 根据 index 分两种情况讨论：
            // 如果 index 小于 textListNode.size()
            if (index < textListNode.getSize()) {
                // 获取 index 对应的文本列表节点条目。
                TextListNodeItem textListNodeItem = textListNodeItemMaintainService.lookupFirst(
                        TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, index}
                );
                // 如果文本列表节点条目不存在，则初始化文本列表节点条目。
                if (Objects.isNull(textListNodeItem)) {
                    textListNodeItem = new TextListNodeItem(
                            null, settingNodeKey, index, false, content
                    );
                }
                // 更新文本列表节点条目。
                textListNodeItem.setNullFlag(false);
                textListNodeItem.setContent(content);
                textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
            }
            // 其余情况（index >= textListNode.size()）
            else {
                // textListNode.size() 到 index 之前填充 nullFlag = true 的 TextListNodeItem。
                for (int i = textListNode.getSize(); i < index; i++) {
                    TextListNodeItem textListNodeItem = new TextListNodeItem(
                            null, settingNodeKey, i, true, null
                    );
                    textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
                }
                // 插入新的文本。
                TextListNodeItem textListNodeItem = new TextListNodeItem(
                        null, settingNodeKey, index, false, content
                );
                textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
                // textListNode 实体的 size 属性设置为 index + 1。
                textListNode.setSize(index + 1);
            }

            // 插入或更新文本列表节点实体。
            textListNodeMaintainService.insertOrUpdate(textListNode);

            // 批量插入或更新文本列表节点条目。
            textListNodeItemMaintainService.batchInsertOrUpdate(textListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，此处不简化代码。
    @SuppressWarnings({"StatementWithEmptyBody", "DuplicatedCode"})
    @BehaviorAnalyse
    @Override
    public void changeOrder(TextListNodeChangeOrderInfo info) throws HandlerException {
        try {
            // 展开参数
            String category = info.getCategory();
            String[] args = info.getArgs();
            int oldIndex = info.getOldIndex();
            int neoIndex = info.getNeoIndex();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的文本列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_TEXT_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_TEXT_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文本列表节点实体。
            TextListNode textListNode = textListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文本列表节点实体不存在，则初始化实体。
            if (Objects.isNull(textListNode)) {
                textListNode = new TextListNode(settingNodeKey, 0);
            }

            // 确认 index 合法。
            if (oldIndex >= textListNode.getSize()) {
                throw new TextListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, textListNode.getSize() - 1, oldIndex
                );
            }
            if (neoIndex >= textListNode.getSize()) {
                throw new TextListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, textListNode.getSize() - 1, neoIndex
                );
            }

            // 构造待更新的 TextListNodeItem 列表。
            List<TextListNodeItem> textListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 根据 oldIndex 与 neoIndex 分三种情况讨论：
            // 如果 oldIndex == neoIndex。
            if (oldIndex == neoIndex) {
                // 什么都不做。
            }
            // 如果 oldIndex < neoIndex。
            else if (oldIndex < neoIndex) {
                // 查询 oldIndex（不包含）到 neoIndex（包含）之间的所有文本列表节点条目。
                List<TextListNodeItem> textListNodeItems = textListNodeItemMaintainService.lookupAsList(
                        TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GT_INDEX_LE,
                        new Object[]{settingNodeKey, oldIndex, neoIndex}
                );
                // 对所有的文本列表节点条目的 index 属性减一。
                for (TextListNodeItem textListNodeItem : textListNodeItems) {
                    textListNodeItem.setIndex(textListNodeItem.getIndex() - 1);
                    textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
                }
                // 获取 oldIndex 对应的文本列表节点条目。
                TextListNodeItem textListNodeItem = textListNodeItemMaintainService.lookupFirst(
                        TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, oldIndex}
                );
                // 如果 oldIndex 对应的文本列表节点条目不存在，则初始化。
                if (Objects.isNull(textListNodeItem)) {
                    textListNodeItem = new TextListNodeItem(
                            null, settingNodeKey, oldIndex, true, null
                    );
                }
                // 更新 oldIndex 对应的文本列表节点条目。
                textListNodeItem.setIndex(neoIndex);
                textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
            }
            // 其它情况（oldIndex > neoIndex）。
            else {
                // 查询 oldIndex（包含）到 neoIndex（不包含）之间的所有文本列表节点条目。
                List<TextListNodeItem> textListNodeItems = textListNodeItemMaintainService.lookupAsList(
                        TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE_INDEX_LT,
                        new Object[]{settingNodeKey, neoIndex, oldIndex}
                );
                // 对所有的文本列表节点条目的 index 属性加一。
                for (TextListNodeItem textListNodeItem : textListNodeItems) {
                    textListNodeItem.setIndex(textListNodeItem.getIndex() + 1);
                    textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
                }
                // 获取 oldIndex 对应的文本列表节点条目。
                TextListNodeItem textListNodeItem = textListNodeItemMaintainService.lookupFirst(
                        TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, oldIndex}
                );
                // 如果 oldIndex 对应的文本列表节点条目不存在，则初始化。
                if (Objects.isNull(textListNodeItem)) {
                    textListNodeItem = new TextListNodeItem(
                            null, settingNodeKey, oldIndex, true, null
                    );
                }
                // 更新 oldIndex 对应的文本列表节点条目。
                textListNodeItem.setIndex(neoIndex);
                textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
            }

            // 插入或更新文本列表节点实体。
            textListNodeMaintainService.insertOrUpdate(textListNode);

            // 批量插入或更新文本列表节点条目。
            textListNodeItemMaintainService.batchInsertOrUpdate(textListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void remove(TextListNodeRemoveInfo info) throws HandlerException {
        try {
            // 展开参数
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的文本列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_TEXT_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_TEXT_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文本列表节点实体。
            TextListNode textListNode = textListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文本列表节点实体不存在，则初始化实体。
            if (Objects.isNull(textListNode)) {
                textListNode = new TextListNode(settingNodeKey, 0);
            }

            // 确认 index 合法。
            if (index >= textListNode.getSize()) {
                throw new TextListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, textListNode.getSize() - 1, index
                );
            }

            // 查询待删除的文本列表节点条目。
            TextListNodeItem textListNodeItemToDelete = textListNodeItemMaintainService.lookupFirst(
                    TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 删除条目实体。
            boolean deleteEntityFlag = Objects.nonNull(textListNodeItemToDelete);
            if (deleteEntityFlag) {
                textListNodeItemMaintainService.delete(textListNodeItemToDelete.getKey());
            }

            // 构造待更新的 TextListNodeItem 列表。
            List<TextListNodeItem> textListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 调整索引。
            List<TextListNodeItem> textListNodeItems = textListNodeItemMaintainService.lookupAsList(
                    TextListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                    new Object[]{settingNodeKey, index}
            );
            for (TextListNodeItem textListNodeItem : textListNodeItems) {
                textListNodeItem.setIndex(textListNodeItem.getIndex() - 1);
                textListNodeItemsToInsertOrUpdate.add(textListNodeItem);
            }

            // 文本列表节点大小减一。
            textListNode.setSize(textListNode.getSize() - 1);

            // 插入或更新文本列表节点实体。
            textListNodeMaintainService.insertOrUpdate(textListNode);

            // 批量插入或更新文本列表节点条目。
            textListNodeItemMaintainService.batchInsertOrUpdate(textListNodeItemsToInsertOrUpdate);
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
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_TEXT_LIST);
    }
}
