package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 图片列表节点缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface ImageListNodeCache extends BatchBaseCache<StringIdKey, ImageListNode> {
}
