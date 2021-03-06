package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 设置节点缓存。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SettingNodeCache extends BatchBaseCache<StringIdKey, SettingNode> {
}
