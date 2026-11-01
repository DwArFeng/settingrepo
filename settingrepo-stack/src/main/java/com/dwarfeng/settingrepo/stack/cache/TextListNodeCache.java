package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.TextListNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 文本列表节点缓存。
 *
 * @author lihc
 * @since 3.2.0
 */
public interface TextListNodeCache extends BatchBaseCache<StringIdKey, TextListNode> {
}
