package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.KvNode;
import com.dwarfeng.settingrepo.stack.bean.entity.KvNodeItem;
import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.settingrepo.stack.cache.KvNodeCache;
import com.dwarfeng.settingrepo.stack.cache.KvNodeItemCache;
import com.dwarfeng.settingrepo.stack.dao.KvNodeDao;
import com.dwarfeng.settingrepo.stack.dao.KvNodeItemDao;
import com.dwarfeng.settingrepo.stack.service.KvNodeItemMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import com.dwarfeng.subgrade.data.sdk.exception.ServiceExceptionCodeSuppliers;
import com.dwarfeng.subgrade.data.sdk.service.custom.operation.BatchCrudOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KvNodeCrudOperation implements BatchCrudOperation<StringIdKey, KvNode> {

    private final KvNodeDao kvNodeDao;
    private final KvNodeCache kvNodeCache;

    private final KvNodeItemDao kvNodeItemDao;
    private final KvNodeItemCache kvNodeItemCache;

    @Value("${com.dwarfeng.settingrepo.cache.timeout.entity.kv_node}")
    private long kvNodeTimeout;

    public KvNodeCrudOperation(
            KvNodeDao kvNodeDao,
            KvNodeCache kvNodeCache,
            KvNodeItemDao kvNodeItemDao,
            KvNodeItemCache kvNodeItemCache
    ) {
        this.kvNodeDao = kvNodeDao;
        this.kvNodeCache = kvNodeCache;

        this.kvNodeItemDao = kvNodeItemDao;
        this.kvNodeItemCache = kvNodeItemCache;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return kvNodeCache.exists(key) || kvNodeDao.exists(key);
    }

    @Override
    public KvNode get(StringIdKey key) throws Exception {
        if (kvNodeCache.exists(key)) {
            return kvNodeCache.get(key);
        } else {
            if (!kvNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodeSuppliers.ENTITY_NOT_EXIST.get());
            }
            KvNode kvNode = kvNodeDao.get(key);
            kvNodeCache.push(kvNode, kvNodeTimeout);
            return kvNode;
        }
    }

    @Override
    public StringIdKey insert(KvNode kvNode) throws Exception {
        kvNodeCache.push(kvNode, kvNodeTimeout);
        return kvNodeDao.insert(kvNode);
    }

    @Override
    public void update(KvNode kvNode) throws Exception {
        kvNodeCache.push(kvNode, kvNodeTimeout);
        kvNodeDao.update(kvNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 查找并删除所有相关的图片列表节点条目。
        List<KvNodeItemKey> kvNodeItemKeys = kvNodeItemDao.lookup(
                KvNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(KvNodeItem::getKey).collect(Collectors.toList());
        kvNodeItemCache.batchDelete(kvNodeItemKeys);
        kvNodeItemDao.batchDelete(kvNodeItemKeys);

        kvNodeDao.delete(key);
        kvNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return kvNodeCache.allExists(keys) || kvNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return kvNodeCache.nonExists(keys) && kvNodeDao.nonExists(keys);
    }

    @Override
    public List<KvNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (kvNodeCache.allExists(keys)) {
            return kvNodeCache.batchGet(keys);
        } else {
            if (!kvNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodeSuppliers.ENTITY_NOT_EXIST.get());
            }
            List<KvNode> kvNodes = kvNodeDao.batchGet(keys);
            kvNodeCache.batchPush(kvNodes, kvNodeTimeout);
            return kvNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<KvNode> kvNodes) throws Exception {
        List<StringIdKey> keys = new ArrayList<>();
        for (KvNode kvNode : kvNodes) {
            keys.add(insert(kvNode));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<KvNode> kvNodes) throws Exception {
        for (KvNode kvNode : kvNodes) {
            update(kvNode);
        }
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
