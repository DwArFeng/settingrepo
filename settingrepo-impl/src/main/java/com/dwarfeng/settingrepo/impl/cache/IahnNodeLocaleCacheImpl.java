package com.dwarfeng.settingrepo.impl.cache;

import com.dwarfeng.settingrepo.sdk.bean.entity.FastJsonIahnNodeLocale;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeLocale;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeLocaleCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class IahnNodeLocaleCacheImpl implements IahnNodeLocaleCache {

    private final RedisBatchBaseCache<IahnNodeLocaleKey, IahnNodeLocale, FastJsonIahnNodeLocale>
            iahnNodeLocaleBatchBaseDelegate;

    public IahnNodeLocaleCacheImpl(
            RedisBatchBaseCache<IahnNodeLocaleKey, IahnNodeLocale, FastJsonIahnNodeLocale>
                    iahnNodeLocaleBatchBaseDelegate
    ) {
        this.iahnNodeLocaleBatchBaseDelegate = iahnNodeLocaleBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(IahnNodeLocaleKey key) throws CacheException {
        return iahnNodeLocaleBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public IahnNodeLocale get(IahnNodeLocaleKey key) throws CacheException {
        return iahnNodeLocaleBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(IahnNodeLocale value, long timeout) throws CacheException {
        iahnNodeLocaleBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(IahnNodeLocaleKey key) throws CacheException {
        iahnNodeLocaleBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        iahnNodeLocaleBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<IahnNodeLocaleKey> keys) throws CacheException {
        return iahnNodeLocaleBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<IahnNodeLocaleKey> keys) throws CacheException {
        return iahnNodeLocaleBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<IahnNodeLocale> batchGet(@SkipRecord List<IahnNodeLocaleKey> keys) throws CacheException {
        return iahnNodeLocaleBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<IahnNodeLocale> entities, long timeout) throws CacheException {
        iahnNodeLocaleBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<IahnNodeLocaleKey> keys) throws CacheException {
        iahnNodeLocaleBatchBaseDelegate.batchDelete(keys);
    }
}
