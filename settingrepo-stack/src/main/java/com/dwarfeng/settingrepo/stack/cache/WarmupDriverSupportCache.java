package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 预热驱动器支持缓存。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriverSupportCache extends BatchBaseCache<StringIdKey, WarmupDriverSupport> {
}
