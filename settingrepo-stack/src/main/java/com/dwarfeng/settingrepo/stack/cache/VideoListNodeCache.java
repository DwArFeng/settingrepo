package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 视频列表节点缓存。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoListNodeCache extends BatchBaseCache<StringIdKey, VideoListNode> {
}
