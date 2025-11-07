package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNode;
import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNodeItem;
import com.dwarfeng.settingrepo.stack.cache.NavigationNodeCache;
import com.dwarfeng.settingrepo.stack.cache.NavigationNodeItemCache;
import com.dwarfeng.settingrepo.stack.dao.NavigationNodeDao;
import com.dwarfeng.settingrepo.stack.dao.NavigationNodeItemDao;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeItemMaintainService;
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

@Component
public class NavigationNodeCrudOperation implements BatchCrudOperation<StringIdKey, NavigationNode> {

    private final NavigationNodeDao navigationNodeDao;
    private final NavigationNodeCache navigationNodeCache;

    private final NavigationNodeItemDao navigationNodeItemDao;
    private final NavigationNodeItemCache navigationNodeItemCache;

    @Value("${cache.timeout.entity.navigation_node}")
    private long navigationNodeTimeout;

    public NavigationNodeCrudOperation(
            NavigationNodeDao navigationNodeDao,
            NavigationNodeCache navigationNodeCache,
            NavigationNodeItemDao navigationNodeItemDao,
            NavigationNodeItemCache navigationNodeItemCache
    ) {
        this.navigationNodeDao = navigationNodeDao;
        this.navigationNodeCache = navigationNodeCache;
        this.navigationNodeItemDao = navigationNodeItemDao;
        this.navigationNodeItemCache = navigationNodeItemCache;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return navigationNodeCache.exists(key) || navigationNodeDao.exists(key);
    }

    @Override
    public NavigationNode get(StringIdKey key) throws Exception {
        if (navigationNodeCache.exists(key)) {
            return navigationNodeCache.get(key);
        } else {
            if (!navigationNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            NavigationNode navigationNode = navigationNodeDao.get(key);
            navigationNodeCache.push(navigationNode, navigationNodeTimeout);
            return navigationNode;
        }
    }

    @Override
    public StringIdKey insert(NavigationNode navigationNode) throws Exception {
        navigationNodeCache.push(navigationNode, navigationNodeTimeout);
        return navigationNodeDao.insert(navigationNode);
    }

    @Override
    public void update(NavigationNode navigationNode) throws Exception {
        navigationNodeCache.push(navigationNode, navigationNodeTimeout);
        navigationNodeDao.update(navigationNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        List<LongIdKey> navigationNodeItemKeys = navigationNodeItemDao.lookup(
                NavigationNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(NavigationNodeItem::getKey).collect(Collectors.toList());
        navigationNodeItemCache.batchDelete(navigationNodeItemKeys);
        navigationNodeItemDao.batchDelete(navigationNodeItemKeys);

        navigationNodeDao.delete(key);
        navigationNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return navigationNodeCache.allExists(keys) || navigationNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return navigationNodeCache.nonExists(keys) && navigationNodeDao.nonExists(keys);
    }

    @Override
    public List<NavigationNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (navigationNodeCache.allExists(keys)) {
            return navigationNodeCache.batchGet(keys);
        } else {
            if (!navigationNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<NavigationNode> navigationNodes = navigationNodeDao.batchGet(keys);
            navigationNodeCache.batchPush(navigationNodes, navigationNodeTimeout);
            return navigationNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<NavigationNode> navigationNodes) throws Exception {
        List<StringIdKey> keys = new ArrayList<>();
        for (NavigationNode navigationNode : navigationNodes) {
            keys.add(insert(navigationNode));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<NavigationNode> navigationNodes) throws Exception {
        for (NavigationNode navigationNode : navigationNodes) {
            update(navigationNode);
        }
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
