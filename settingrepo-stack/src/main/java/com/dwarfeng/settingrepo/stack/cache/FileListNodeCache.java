package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.FileListNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 文本列表节点缓存。
 *
 * @author liulx
 * @since 2.4.2
 */
public interface FileListNodeCache extends BatchBaseCache<StringIdKey , FileListNode> {
}
