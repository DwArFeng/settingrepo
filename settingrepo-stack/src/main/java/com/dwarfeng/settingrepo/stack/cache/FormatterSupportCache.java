package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 格式化器支持缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface FormatterSupportCache extends BatchBaseCache<StringIdKey, FormatterSupport> {
}
