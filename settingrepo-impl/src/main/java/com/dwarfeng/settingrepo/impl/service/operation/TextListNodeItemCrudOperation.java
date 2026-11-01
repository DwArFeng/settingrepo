package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.TextListNodeItem;
import com.dwarfeng.settingrepo.stack.cache.TextListNodeItemCache;
import com.dwarfeng.settingrepo.stack.dao.TextListNodeItemDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文本列表节点条目 CRUD 操作。
 *
 * @author lihc
 * @since 3.2.0
 */
@Component
public class TextListNodeItemCrudOperation implements BatchCrudOperation<LongIdKey, TextListNodeItem> {

    private final TextListNodeItemDao textListNodeItemDao;
    private final TextListNodeItemCache textListNodeItemCache;

    @Value("${com.dwarfeng.settingrepo.cache.timeout.entity.text_list_node_item}")
    private long textListNodeItemTimeout;

    public TextListNodeItemCrudOperation(
            TextListNodeItemDao textListNodeItemDao,
            TextListNodeItemCache textListNodeItemCache
    ) {
        this.textListNodeItemDao = textListNodeItemDao;
        this.textListNodeItemCache = textListNodeItemCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return textListNodeItemCache.exists(key) || textListNodeItemDao.exists(key);
    }

    @Override
    public TextListNodeItem get(LongIdKey key) throws Exception {
        if (textListNodeItemCache.exists(key)) {
            return textListNodeItemCache.get(key);
        } else {
            if (!textListNodeItemDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            TextListNodeItem textListNodeItem = textListNodeItemDao.get(key);
            textListNodeItemCache.push(textListNodeItem, textListNodeItemTimeout);
            return textListNodeItem;
        }
    }

    @Override
    public LongIdKey insert(TextListNodeItem textListNodeItem) throws Exception {
        textListNodeItemCache.push(textListNodeItem, textListNodeItemTimeout);
        return textListNodeItemDao.insert(textListNodeItem);
    }

    @Override
    public void update(TextListNodeItem textListNodeItem) throws Exception {
        textListNodeItemCache.push(textListNodeItem, textListNodeItemTimeout);
        textListNodeItemDao.update(textListNodeItem);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 文本列表条目不涉及外部文件，直接删除记录与缓存。
        textListNodeItemDao.delete(key);
        textListNodeItemCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return textListNodeItemCache.allExists(keys) || textListNodeItemDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return textListNodeItemCache.nonExists(keys) && textListNodeItemDao.nonExists(keys);
    }

    @Override
    public List<TextListNodeItem> batchGet(List<LongIdKey> keys) throws Exception {
        if (textListNodeItemCache.allExists(keys)) {
            return textListNodeItemCache.batchGet(keys);
        } else {
            if (!textListNodeItemDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<TextListNodeItem> textListNodeItems = textListNodeItemDao.batchGet(keys);
            textListNodeItemCache.batchPush(textListNodeItems, textListNodeItemTimeout);
            return textListNodeItems;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<TextListNodeItem> textListNodeItems) throws Exception {
        textListNodeItemCache.batchPush(textListNodeItems, textListNodeItemTimeout);
        return textListNodeItemDao.batchInsert(textListNodeItems);
    }

    @Override
    public void batchUpdate(List<TextListNodeItem> textListNodeItems) throws Exception {
        textListNodeItemCache.batchPush(textListNodeItems, textListNodeItemTimeout);
        textListNodeItemDao.batchUpdate(textListNodeItems);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
