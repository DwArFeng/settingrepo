package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.FileNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 文件节点数据访问层。
 *
 * @author WangT
 * @since 2.4.2
 */
public interface FileNodeDao extends BatchBaseDao<StringIdKey, FileNode>, EntireLookupDao<FileNode>,
        PresetLookupDao<FileNode> {
}
