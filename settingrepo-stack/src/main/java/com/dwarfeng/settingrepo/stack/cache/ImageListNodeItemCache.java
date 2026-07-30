package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNodeItem;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.data.stack.cache.BatchBaseCache;

/**
 * 图片列表节点条目节点缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface ImageListNodeItemCache extends BatchBaseCache<LongIdKey, ImageListNodeItem> {
}
