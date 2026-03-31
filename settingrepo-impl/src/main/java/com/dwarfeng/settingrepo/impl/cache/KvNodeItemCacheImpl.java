package com.dwarfeng.settingrepo.impl.cache;

import com.dwarfeng.settingrepo.sdk.bean.entity.FastJsonKvNodeItem;
import com.dwarfeng.settingrepo.stack.bean.entity.KvNodeItem;
import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.settingrepo.stack.cache.KvNodeItemCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class KvNodeItemCacheImpl implements KvNodeItemCache {

    private final RedisBatchBaseCache<KvNodeItemKey, KvNodeItem, FastJsonKvNodeItem> kvNodeItemBatchBaseDelegate;

    public KvNodeItemCacheImpl(
            RedisBatchBaseCache<KvNodeItemKey, KvNodeItem, FastJsonKvNodeItem> kvNodeItemBatchBaseDelegate
    ) {
        this.kvNodeItemBatchBaseDelegate = kvNodeItemBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(KvNodeItemKey key) throws CacheException {
        return kvNodeItemBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public KvNodeItem get(KvNodeItemKey key) throws CacheException {
        return kvNodeItemBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(KvNodeItem value, long timeout) throws CacheException {
        kvNodeItemBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(KvNodeItemKey key) throws CacheException {
        kvNodeItemBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        kvNodeItemBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<KvNodeItemKey> keys) throws CacheException {
        return kvNodeItemBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<KvNodeItemKey> keys) throws CacheException {
        return kvNodeItemBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<KvNodeItem> batchGet(@SkipRecord List<KvNodeItemKey> keys) throws CacheException {
        return kvNodeItemBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<KvNodeItem> entities, long timeout) throws CacheException {
        kvNodeItemBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<KvNodeItemKey> keys) throws CacheException {
        kvNodeItemBatchBaseDelegate.batchDelete(keys);
    }
}
