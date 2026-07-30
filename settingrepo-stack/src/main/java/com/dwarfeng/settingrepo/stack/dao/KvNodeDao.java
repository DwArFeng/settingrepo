package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.KvNode;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.data.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.data.stack.dao.PresetLookupDao;

/**
 * 键值对节点数据访问层。
 *
 * @author wangxy
 * @since 2.5.0
 */
public interface KvNodeDao extends BatchBaseDao<StringIdKey, KvNode>,
        EntireLookupDao<KvNode>, PresetLookupDao<KvNode> {
}
