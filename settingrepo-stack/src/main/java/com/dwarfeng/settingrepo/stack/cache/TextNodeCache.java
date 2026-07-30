package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.stack.cache.BatchBaseCache;

/**
 * 文本节点缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface TextNodeCache extends BatchBaseCache<StringIdKey, TextNode> {
}
