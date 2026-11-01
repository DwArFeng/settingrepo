package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.TextListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.TextListNodeItem;
import com.dwarfeng.settingrepo.stack.cache.TextListNodeCache;
import com.dwarfeng.settingrepo.stack.dao.TextListNodeDao;
import com.dwarfeng.settingrepo.stack.dao.TextListNodeItemDao;
import com.dwarfeng.settingrepo.stack.service.TextListNodeItemMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 文本列表节点 CRUD 操作。
 *
 * @author lihc
 * @since 3.2.0
 */
@Component
public class TextListNodeCrudOperation implements BatchCrudOperation<StringIdKey, TextListNode> {

    private final TextListNodeDao textListNodeDao;
    private final TextListNodeCache textListNodeCache;

    private final TextListNodeItemDao textListNodeItemDao;
    private final TextListNodeItemCrudOperation textListNodeItemCrudOperation;

    @Value("${com.dwarfeng.settingrepo.cache.timeout.entity.text_list_node}")
    private long textListNodeTimeout;

    public TextListNodeCrudOperation(
            TextListNodeDao textListNodeDao,
            TextListNodeCache textListNodeCache,
            TextListNodeItemDao textListNodeItemDao,
            TextListNodeItemCrudOperation textListNodeItemCrudOperation
    ) {
        this.textListNodeDao = textListNodeDao;
        this.textListNodeCache = textListNodeCache;

        this.textListNodeItemDao = textListNodeItemDao;
        this.textListNodeItemCrudOperation = textListNodeItemCrudOperation;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return textListNodeCache.exists(key) || textListNodeDao.exists(key);
    }

    @Override
    public TextListNode get(StringIdKey key) throws Exception {
        if (textListNodeCache.exists(key)) {
            return textListNodeCache.get(key);
        } else {
            if (!textListNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            TextListNode textListNode = textListNodeDao.get(key);
            textListNodeCache.push(textListNode, textListNodeTimeout);
            return textListNode;
        }
    }

    @Override
    public StringIdKey insert(TextListNode textListNode) throws Exception {
        textListNodeCache.push(textListNode, textListNodeTimeout);
        return textListNodeDao.insert(textListNode);
    }

    @Override
    public void update(TextListNode textListNode) throws Exception {
        textListNodeCache.push(textListNode, textListNodeTimeout);
        textListNodeDao.update(textListNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 查找并删除所有相关的文本列表节点条目。
        List<LongIdKey> textListNodeItemKeys = textListNodeItemDao.lookup(
                TextListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(TextListNodeItem::getKey).collect(Collectors.toList());
        textListNodeItemCrudOperation.batchDelete(textListNodeItemKeys);

        textListNodeDao.delete(key);
        textListNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return textListNodeCache.allExists(keys) || textListNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return textListNodeCache.nonExists(keys) && textListNodeDao.nonExists(keys);
    }

    @Override
    public List<TextListNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (textListNodeCache.allExists(keys)) {
            return textListNodeCache.batchGet(keys);
        } else {
            if (!textListNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<TextListNode> textListNodes = textListNodeDao.batchGet(keys);
            textListNodeCache.batchPush(textListNodes, textListNodeTimeout);
            return textListNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<TextListNode> textListNodes) throws Exception {
        List<StringIdKey> keys = new ArrayList<>();
        for (TextListNode textListNode : textListNodes) {
            keys.add(insert(textListNode));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<TextListNode> textListNodes) throws Exception {
        for (TextListNode textListNode : textListNodes) {
            update(textListNode);
        }
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
