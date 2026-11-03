package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 视频列表节点条目缓存。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoListNodeItemCache extends BatchBaseCache<LongIdKey, VideoListNodeItem> {
}
