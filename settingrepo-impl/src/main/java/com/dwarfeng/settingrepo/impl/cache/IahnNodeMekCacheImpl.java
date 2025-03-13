package com.dwarfeng.settingrepo.impl.cache;

import com.dwarfeng.settingrepo.sdk.bean.entity.FastJsonIahnNodeMek;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMek;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeMekCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class IahnNodeMekCacheImpl implements IahnNodeMekCache {

    private final RedisBatchBaseCache<IahnNodeMekKey, IahnNodeMek, FastJsonIahnNodeMek> iahnNodeMekBatchBaseDelegate;

    public IahnNodeMekCacheImpl(
            RedisBatchBaseCache<IahnNodeMekKey, IahnNodeMek, FastJsonIahnNodeMek> iahnNodeMekBatchBaseDelegate
    ) {
        this.iahnNodeMekBatchBaseDelegate = iahnNodeMekBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(IahnNodeMekKey key) throws CacheException {
        return iahnNodeMekBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public IahnNodeMek get(IahnNodeMekKey key) throws CacheException {
        return iahnNodeMekBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(IahnNodeMek value, long timeout) throws CacheException {
        iahnNodeMekBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(IahnNodeMekKey key) throws CacheException {
        iahnNodeMekBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        iahnNodeMekBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<IahnNodeMekKey> keys) throws CacheException {
        return iahnNodeMekBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<IahnNodeMekKey> keys) throws CacheException {
        return iahnNodeMekBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<IahnNodeMek> batchGet(@SkipRecord List<IahnNodeMekKey> keys) throws CacheException {
        return iahnNodeMekBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<IahnNodeMek> entities, long timeout) throws CacheException {
        iahnNodeMekBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<IahnNodeMekKey> keys) throws CacheException {
        iahnNodeMekBatchBaseDelegate.batchDelete(keys);
    }
}
