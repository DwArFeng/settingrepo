package com.dwarfeng.settingrepo.stack.cache;

import com.dwarfeng.settingrepo.stack.bean.entity.FileListNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 文本列表项缓存。
 *
 * @author liulx
 * @since 2.4.2
 */
public interface FileListNodeItemCache extends BatchBaseCache<LongIdKey, FileListNodeItem> {
}
