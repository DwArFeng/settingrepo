package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNodeItem;
import com.dwarfeng.settingrepo.stack.cache.NavigationNodeItemCache;
import com.dwarfeng.settingrepo.stack.dao.NavigationNodeItemDao;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeItemMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Component
public class NavigationNodeItemCrudOperation implements BatchCrudOperation<LongIdKey, NavigationNodeItem> {

    private final NavigationNodeItemDao navigationNodeItemDao;
    private final NavigationNodeItemCache navigationNodeItemCache;

    @Value("${cache.timeout.entity.navigation_node_item}")
    private long navigationNodeItemTimeout;

    public NavigationNodeItemCrudOperation(
            NavigationNodeItemDao navigationNodeItemDao,
            NavigationNodeItemCache navigationNodeItemCache
    ) {
        this.navigationNodeItemDao = navigationNodeItemDao;
        this.navigationNodeItemCache = navigationNodeItemCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return navigationNodeItemCache.exists(key) || navigationNodeItemDao.exists(key);
    }

    @Override
    public NavigationNodeItem get(LongIdKey key) throws Exception {
        if (navigationNodeItemCache.exists(key)) {
            return navigationNodeItemCache.get(key);
        } else {
            if (!navigationNodeItemDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            NavigationNodeItem navigationNodeItem = navigationNodeItemDao.get(key);
            navigationNodeItemCache.push(navigationNodeItem, navigationNodeItemTimeout);
            return navigationNodeItem;
        }
    }

    @Override
    public LongIdKey insert(NavigationNodeItem navigationNodeItem) throws Exception {
        navigationNodeItemCache.push(navigationNodeItem, navigationNodeItemTimeout);
        return navigationNodeItemDao.insert(navigationNodeItem);
    }

    @Override
    public void update(NavigationNodeItem navigationNodeItem) throws Exception {
        navigationNodeItemCache.push(navigationNodeItem, navigationNodeItemTimeout);
        navigationNodeItemDao.update(navigationNodeItem);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 寻找自身的子孙节点。
        DescendantStruct descendantStruct = findDescendant(key);

        // 依次删除子孙节点。
        List<NavigationNodeItem> descendantNavigationNodeItems = descendantStruct.getNavigationNodeItems();
        descendantNavigationNodeItems.forEach((item -> item.setParentKey(null)));
        navigationNodeItemDao.batchUpdate(descendantNavigationNodeItems);
        List<LongIdKey> descendantNavigationNodeItemKeys = descendantNavigationNodeItems.stream()
                .map(NavigationNodeItem::getKey).collect(Collectors.toList());
        navigationNodeItemCache.batchDelete(descendantNavigationNodeItemKeys);
        navigationNodeItemDao.batchDelete(descendantNavigationNodeItemKeys);

        // 删除 NavigationNodeItem 自身。
        navigationNodeItemCache.delete(key);
        navigationNodeItemDao.delete(key);
    }

    private DescendantStruct findDescendant(LongIdKey key) throws Exception {
        // 定义结果变量。
        List<NavigationNodeItem> navigationNodeItems = new LinkedList<>();

        // 定义一个栈，并初始化。
        Stack<LongIdKey> navigationNodeItemStack = new Stack<>();
        navigationNodeItemStack.push(key);

        // 在栈清空之前，一直执行循环。
        while (!navigationNodeItemStack.isEmpty()) {
            // 从栈中取出当前的节点。
            LongIdKey navigationNodeItemKey = navigationNodeItemStack.pop();
            // 查询节点的子节点。
            List<NavigationNodeItem> childNavigationNodeItems = navigationNodeItemDao.lookup(
                    NavigationNodeItemMaintainService.CHILD_FOR_PARENT, new Object[]{navigationNodeItemKey}
            );
            // 将结果添加到结果变量中。
            navigationNodeItems.addAll(childNavigationNodeItems);
            // 向栈中推送节点的子节点。
            childNavigationNodeItems.stream().map(NavigationNodeItem::getKey).forEach(navigationNodeItemStack::push);
        }

        // 返回结果。
        return new DescendantStruct(navigationNodeItems);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return navigationNodeItemCache.allExists(keys) || navigationNodeItemDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return navigationNodeItemCache.nonExists(keys) && navigationNodeItemDao.nonExists(keys);
    }

    @Override
    public List<NavigationNodeItem> batchGet(List<LongIdKey> keys) throws Exception {
        if (navigationNodeItemCache.allExists(keys)) {
            return navigationNodeItemCache.batchGet(keys);
        } else {
            if (!navigationNodeItemDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<NavigationNodeItem> navigationNodeItems = navigationNodeItemDao.batchGet(keys);
            navigationNodeItemCache.batchPush(navigationNodeItems, navigationNodeItemTimeout);
            return navigationNodeItems;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<NavigationNodeItem> navigationNodeItems) throws Exception {
        navigationNodeItemCache.batchPush(navigationNodeItems, navigationNodeItemTimeout);
        return navigationNodeItemDao.batchInsert(navigationNodeItems);
    }

    @Override
    public void batchUpdate(List<NavigationNodeItem> navigationNodeItems) throws Exception {
        navigationNodeItemCache.batchPush(navigationNodeItems, navigationNodeItemTimeout);
        navigationNodeItemDao.batchUpdate(navigationNodeItems);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }

    private static final class DescendantStruct {

        private final List<NavigationNodeItem> navigationNodeItems;

        private DescendantStruct(List<NavigationNodeItem> navigationNodeItems) {
            this.navigationNodeItems = navigationNodeItems;
        }

        public List<NavigationNodeItem> getNavigationNodeItems() {
            return navigationNodeItems;
        }

        @Override
        public String toString() {
            return "DescendantStruct{" +
                    "navigationNodeItems=" + navigationNodeItems +
                    '}';
        }
    }
}
