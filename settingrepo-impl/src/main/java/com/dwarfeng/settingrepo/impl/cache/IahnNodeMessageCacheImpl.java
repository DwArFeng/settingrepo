package com.dwarfeng.settingrepo.impl.cache;

import com.dwarfeng.settingrepo.sdk.bean.entity.FastJsonIahnNodeMessage;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeMessageCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class IahnNodeMessageCacheImpl implements IahnNodeMessageCache {

    private final RedisBatchBaseCache<IahnNodeMessageKey, IahnNodeMessage, FastJsonIahnNodeMessage>
            iahnNodeMessageBatchBaseDelegate;

    public IahnNodeMessageCacheImpl(
            RedisBatchBaseCache<IahnNodeMessageKey, IahnNodeMessage, FastJsonIahnNodeMessage>
                    iahnNodeMessageBatchBaseDelegate
    ) {
        this.iahnNodeMessageBatchBaseDelegate = iahnNodeMessageBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(IahnNodeMessageKey key) throws CacheException {
        return iahnNodeMessageBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public IahnNodeMessage get(IahnNodeMessageKey key) throws CacheException {
        return iahnNodeMessageBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(IahnNodeMessage value, long timeout) throws CacheException {
        iahnNodeMessageBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(IahnNodeMessageKey key) throws CacheException {
        iahnNodeMessageBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        iahnNodeMessageBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<IahnNodeMessageKey> keys) throws CacheException {
        return iahnNodeMessageBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<IahnNodeMessageKey> keys) throws CacheException {
        return iahnNodeMessageBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<IahnNodeMessage> batchGet(@SkipRecord List<IahnNodeMessageKey> keys) throws CacheException {
        return iahnNodeMessageBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<IahnNodeMessage> entities, long timeout) throws CacheException {
        iahnNodeMessageBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<IahnNodeMessageKey> keys) throws CacheException {
        iahnNodeMessageBatchBaseDelegate.batchDelete(keys);
    }
}
