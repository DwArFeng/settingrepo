package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 本文节点缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface ImageNodeCache extends BatchBaseCache<StringIdKey, ImageNode> {
}
