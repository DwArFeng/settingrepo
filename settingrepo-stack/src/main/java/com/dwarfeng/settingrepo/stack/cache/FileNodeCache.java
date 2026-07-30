package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.FileNode;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.stack.cache.BatchBaseCache;

/**
 * 文件节点缓存。
 *
 * @author WangT
 * @since 2.4.2
 */
public interface FileNodeCache extends BatchBaseCache<StringIdKey, FileNode> {
}
