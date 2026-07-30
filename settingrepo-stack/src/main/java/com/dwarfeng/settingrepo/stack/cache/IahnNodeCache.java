package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNode;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.stack.cache.BatchBaseCache;

/**
 * 国际化节点缓存。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeCache extends BatchBaseCache<StringIdKey, IahnNode> {
}
