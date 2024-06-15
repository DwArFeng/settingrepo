package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 文本节点缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface TextNodeCache extends BatchBaseCache<StringIdKey, TextNode> {
}
