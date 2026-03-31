package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.KvNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 键值对节点缓存。
 *
 * @author wangxy
 * @since 2.5.0
 */
public interface KvNodeCache extends BatchBaseCache<StringIdKey, KvNode> {
}
