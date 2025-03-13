package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNode;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeLocale;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMek;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeCache;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeLocaleCache;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeMekCache;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeMessageCache;
import com.dwarfeng.settingrepo.stack.dao.IahnNodeDao;
import com.dwarfeng.settingrepo.stack.dao.IahnNodeLocaleDao;
import com.dwarfeng.settingrepo.stack.dao.IahnNodeMekDao;
import com.dwarfeng.settingrepo.stack.dao.IahnNodeMessageDao;
import com.dwarfeng.settingrepo.stack.service.IahnNodeLocaleMaintainService;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMekMaintainService;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMessageMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IahnNodeCrudOperation implements BatchCrudOperation<StringIdKey, IahnNode> {

    private final IahnNodeDao iahnNodeDao;
    private final IahnNodeCache iahnNodeCache;

    private final IahnNodeLocaleDao iahnNodeLocaleDao;
    private final IahnNodeLocaleCache iahnNodeLocaleCache;

    private final IahnNodeMekDao iahnNodeMekDao;
    private final IahnNodeMekCache iahnNodeMekCache;

    private final IahnNodeMessageDao iahnNodeMessageDao;
    private final IahnNodeMessageCache iahnNodeMessageCache;

    @Value("${cache.timeout.entity.iahn_node}")
    private long iahnNodeTimeout;

    public IahnNodeCrudOperation(
            IahnNodeDao iahnNodeDao,
            IahnNodeCache iahnNodeCache,
            IahnNodeLocaleDao iahnNodeLocaleDao,
            IahnNodeLocaleCache iahnNodeLocaleCache,
            IahnNodeMekDao iahnNodeMekDao,
            IahnNodeMekCache iahnNodeMekCache,
            IahnNodeMessageDao iahnNodeMessageDao,
            IahnNodeMessageCache iahnNodeMessageCache
    ) {
        this.iahnNodeDao = iahnNodeDao;
        this.iahnNodeCache = iahnNodeCache;
        this.iahnNodeLocaleDao = iahnNodeLocaleDao;
        this.iahnNodeLocaleCache = iahnNodeLocaleCache;
        this.iahnNodeMekDao = iahnNodeMekDao;
        this.iahnNodeMekCache = iahnNodeMekCache;
        this.iahnNodeMessageDao = iahnNodeMessageDao;
        this.iahnNodeMessageCache = iahnNodeMessageCache;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return iahnNodeCache.exists(key) || iahnNodeDao.exists(key);
    }

    @Override
    public IahnNode get(StringIdKey key) throws Exception {
        if (iahnNodeCache.exists(key)) {
            return iahnNodeCache.get(key);
        } else {
            if (!iahnNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            IahnNode iahnNode = iahnNodeDao.get(key);
            iahnNodeCache.push(iahnNode, iahnNodeTimeout);
            return iahnNode;
        }
    }

    @Override
    public StringIdKey insert(IahnNode iahnNode) throws Exception {
        iahnNodeCache.push(iahnNode, iahnNodeTimeout);
        return iahnNodeDao.insert(iahnNode);
    }

    @Override
    public void update(IahnNode iahnNode) throws Exception {
        iahnNodeCache.push(iahnNode, iahnNodeTimeout);
        iahnNodeDao.update(iahnNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 查找并删除所有相关的国际化节点消息。
        List<IahnNodeMessageKey> iahnNodeMessageKeys = iahnNodeMessageDao.lookup(
                IahnNodeMessageMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(IahnNodeMessage::getKey).collect(Collectors.toList());
        iahnNodeMessageDao.batchDelete(iahnNodeMessageKeys);
        iahnNodeMessageCache.batchDelete(iahnNodeMessageKeys);

        // 查找并删除所有相关的国际化地区消息。
        List<IahnNodeLocaleKey> iahnNodeLocaleKeys = iahnNodeLocaleDao.lookup(
                IahnNodeLocaleMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(IahnNodeLocale::getKey).collect(Collectors.toList());
        iahnNodeLocaleDao.batchDelete(iahnNodeLocaleKeys);
        iahnNodeLocaleCache.batchDelete(iahnNodeLocaleKeys);

        // 查找并删除所有相关的国际化 Mek 消息。
        List<IahnNodeMekKey> iahnNodeMekKeys = iahnNodeMekDao.lookup(
                IahnNodeMekMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(IahnNodeMek::getKey).collect(Collectors.toList());
        iahnNodeMekDao.batchDelete(iahnNodeMekKeys);
        iahnNodeMekCache.batchDelete(iahnNodeMekKeys);

        // 删除记录设置自身。
        iahnNodeDao.delete(key);
        iahnNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return iahnNodeCache.allExists(keys) || iahnNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return iahnNodeCache.nonExists(keys) && iahnNodeDao.nonExists(keys);
    }

    @Override
    public List<IahnNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (iahnNodeCache.allExists(keys)) {
            return iahnNodeCache.batchGet(keys);
        } else {
            if (!iahnNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<IahnNode> iahnNodes = iahnNodeDao.batchGet(keys);
            iahnNodeCache.batchPush(iahnNodes, iahnNodeTimeout);
            return iahnNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<IahnNode> iahnNodes) throws Exception {
        iahnNodeCache.batchPush(iahnNodes, iahnNodeTimeout);
        return iahnNodeDao.batchInsert(iahnNodes);
    }

    @Override
    public void batchUpdate(List<IahnNode> iahnNodes) throws Exception {
        iahnNodeCache.batchPush(iahnNodes, iahnNodeTimeout);
        iahnNodeDao.batchUpdate(iahnNodes);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
