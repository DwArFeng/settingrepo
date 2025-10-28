package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.FileListNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 文本列表项数据访问层。
 *
 * @author liulx
 * @since 2.4.2
 */
public interface FileListNodeItemDao extends BatchBaseDao<LongIdKey, FileListNodeItem>, EntireLookupDao<FileListNodeItem>,
        PresetLookupDao<FileListNodeItem> {
}
