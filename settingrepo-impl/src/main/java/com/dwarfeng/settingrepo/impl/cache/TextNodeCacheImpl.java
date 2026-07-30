package com.dwarfeng.settingrepo.impl.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.settingrepo.stack.cache.TextNodeCache;
import com.dwarfeng.subgrade.aop.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.aop.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.stack.cache.BatchBaseCache;
import com.dwarfeng.subgrade.data.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TextNodeCacheImpl implements TextNodeCache {

    private final BatchBaseCache<StringIdKey, TextNode> batchBaseCache;

    public TextNodeCacheImpl(
            BatchBaseCache<StringIdKey, TextNode> batchBaseCache
    ) {
        this.batchBaseCache = batchBaseCache;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(StringIdKey key) throws CacheException {
        return batchBaseCache.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public TextNode get(StringIdKey key) throws CacheException {
        return batchBaseCache.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(TextNode value, long timeout) throws CacheException {
        batchBaseCache.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(StringIdKey key) throws CacheException {
        batchBaseCache.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        batchBaseCache.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return batchBaseCache.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return batchBaseCache.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<TextNode> batchGet(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return batchBaseCache.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<TextNode> entities, long timeout) throws CacheException {
        batchBaseCache.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<StringIdKey> keys) throws CacheException {
        batchBaseCache.batchDelete(keys);
    }
}
