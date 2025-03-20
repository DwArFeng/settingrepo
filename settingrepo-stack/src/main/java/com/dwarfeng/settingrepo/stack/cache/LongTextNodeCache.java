package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.LongTextNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 长文本节点缓存。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface LongTextNodeCache extends BatchBaseCache<StringIdKey, LongTextNode> {
}
