package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.TextListNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 文本列表节点条目缓存。
 *
 * @author lihc
 * @since 3.2.0
 */
public interface TextListNodeItemCache extends BatchBaseCache<LongIdKey, TextListNodeItem> {
}
