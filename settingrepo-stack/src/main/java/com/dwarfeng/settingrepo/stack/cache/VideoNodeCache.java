package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 视频节点缓存。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoNodeCache extends BatchBaseCache<StringIdKey, VideoNode> {
}
