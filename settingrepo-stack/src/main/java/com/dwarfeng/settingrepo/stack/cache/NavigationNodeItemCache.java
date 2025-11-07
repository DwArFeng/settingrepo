package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 导航节点条目缓存。
 *
 * @author Zhaofz
 * @since 2.4.2
 */
public interface NavigationNodeItemCache extends BatchBaseCache<LongIdKey, NavigationNodeItem> {
}
