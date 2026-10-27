package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 预热设置缓存。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupSettingCache extends BatchBaseCache<LongIdKey, WarmupSetting> {
}
