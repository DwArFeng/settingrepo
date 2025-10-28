package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.FileListNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 文本列表节点数据访问层。
 *
 * @author liulx
 * @since 2.4.2
 */
public interface FileListNodeDao extends BatchBaseDao<StringIdKey , FileListNode>, EntireLookupDao<FileListNode>,
        PresetLookupDao<FileListNode> {
}
