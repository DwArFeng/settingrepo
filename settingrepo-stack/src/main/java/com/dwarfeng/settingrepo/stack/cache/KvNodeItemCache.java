package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.KvNodeItem;
import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 键值对条目节点缓存。
 *
 * @author wangxy
 * @since 2.5.0
 */
public interface KvNodeItemCache extends BatchBaseCache<KvNodeItemKey, KvNodeItem> {
}
