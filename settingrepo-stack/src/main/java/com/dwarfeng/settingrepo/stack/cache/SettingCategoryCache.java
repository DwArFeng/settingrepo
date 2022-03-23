package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 设置类别缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SettingCategoryCache extends BatchBaseCache<StringIdKey, SettingCategory> {
}
