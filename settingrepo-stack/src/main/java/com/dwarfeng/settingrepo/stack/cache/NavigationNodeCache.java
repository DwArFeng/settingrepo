package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 导航节点缓存。
 *
 * @author Zhaofz
 * @since 2.4.2
 */
public interface NavigationNodeCache extends BatchBaseCache<StringIdKey, NavigationNode> {
}
