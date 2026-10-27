package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 预热驱动信息缓存。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriverInfoCache extends BatchBaseCache<LongIdKey, WarmupDriverInfo> {
}
