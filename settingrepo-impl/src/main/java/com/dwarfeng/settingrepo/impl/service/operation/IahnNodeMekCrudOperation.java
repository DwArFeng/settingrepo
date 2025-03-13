package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMek;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeMekCache;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeMessageCache;
import com.dwarfeng.settingrepo.stack.dao.IahnNodeMekDao;
import com.dwarfeng.settingrepo.stack.dao.IahnNodeMessageDao;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMessageMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IahnNodeMekCrudOperation implements BatchCrudOperation<IahnNodeMekKey, IahnNodeMek> {

    private final IahnNodeMekDao iahnNodeMekDao;
    private final IahnNodeMekCache iahnNodeMekCache;

    private final IahnNodeMessageDao iahnNodeMessageDao;
    private final IahnNodeMessageCache iahnNodeMessageCache;

    @Value("${cache.timeout.entity.iahn_node_mek}")
    private long iahnNodeMekTimeout;

    public IahnNodeMekCrudOperation(
            IahnNodeMekDao iahnNodeMekDao,
            IahnNodeMekCache iahnNodeMekCache,
            IahnNodeMessageDao iahnNodeMessageDao,
            IahnNodeMessageCache iahnNodeMessageCache
    ) {
        this.iahnNodeMekDao = iahnNodeMekDao;
        this.iahnNodeMekCache = iahnNodeMekCache;
        this.iahnNodeMessageDao = iahnNodeMessageDao;
        this.iahnNodeMessageCache = iahnNodeMessageCache;
    }

    @Override
    public boolean exists(IahnNodeMekKey key) throws Exception {
        return iahnNodeMekCache.exists(key) || iahnNodeMekDao.exists(key);
    }

    @Override
    public IahnNodeMek get(IahnNodeMekKey key) throws Exception {
        if (iahnNodeMekCache.exists(key)) {
            return iahnNodeMekCache.get(key);
        } else {
            if (!iahnNodeMekDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            IahnNodeMek iahnNodeMek = iahnNodeMekDao.get(key);
            iahnNodeMekCache.push(iahnNodeMek, iahnNodeMekTimeout);
            return iahnNodeMek;
        }
    }

    @Override
    public IahnNodeMekKey insert(IahnNodeMek iahnNodeMek) throws Exception {
        iahnNodeMekCache.push(iahnNodeMek, iahnNodeMekTimeout);
        return iahnNodeMekDao.insert(iahnNodeMek);
    }

    @Override
    public void update(IahnNodeMek iahnNodeMek) throws Exception {
        iahnNodeMekCache.push(iahnNodeMek, iahnNodeMekTimeout);
        iahnNodeMekDao.update(iahnNodeMek);
    }

    @Override
    public void delete(IahnNodeMekKey key) throws Exception {
        // 查找并删除所有相关的国际化节点消息。
        List<IahnNodeMessageKey> iahnNodeMessageKeys = iahnNodeMessageDao.lookup(
                IahnNodeMessageMaintainService.CHILD_FOR_MEK, new Object[]{key}
        ).stream().map(IahnNodeMessage::getKey).collect(Collectors.toList());
        iahnNodeMessageDao.batchDelete(iahnNodeMessageKeys);
        iahnNodeMessageCache.batchDelete(iahnNodeMessageKeys);

        // 删除记录设置自身。
        iahnNodeMekDao.delete(key);
        iahnNodeMekCache.delete(key);
    }

    @Override
    public boolean allExists(List<IahnNodeMekKey> keys) throws Exception {
        return iahnNodeMekCache.allExists(keys) || iahnNodeMekDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<IahnNodeMekKey> keys) throws Exception {
        return iahnNodeMekCache.nonExists(keys) && iahnNodeMekDao.nonExists(keys);
    }

    @Override
    public List<IahnNodeMek> batchGet(List<IahnNodeMekKey> keys) throws Exception {
        if (iahnNodeMekCache.allExists(keys)) {
            return iahnNodeMekCache.batchGet(keys);
        } else {
            if (!iahnNodeMekDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<IahnNodeMek> iahnNodeMeks = iahnNodeMekDao.batchGet(keys);
            iahnNodeMekCache.batchPush(iahnNodeMeks, iahnNodeMekTimeout);
            return iahnNodeMeks;
        }
    }

    @Override
    public List<IahnNodeMekKey> batchInsert(List<IahnNodeMek> iahnNodeMeks) throws Exception {
        iahnNodeMekCache.batchPush(iahnNodeMeks, iahnNodeMekTimeout);
        return iahnNodeMekDao.batchInsert(iahnNodeMeks);
    }

    @Override
    public void batchUpdate(List<IahnNodeMek> iahnNodeMeks) throws Exception {
        iahnNodeMekCache.batchPush(iahnNodeMeks, iahnNodeMekTimeout);
        iahnNodeMekDao.batchUpdate(iahnNodeMeks);
    }

    @Override
    public void batchDelete(List<IahnNodeMekKey> keys) throws Exception {
        for (IahnNodeMekKey key : keys) {
            delete(key);
        }
    }
}
