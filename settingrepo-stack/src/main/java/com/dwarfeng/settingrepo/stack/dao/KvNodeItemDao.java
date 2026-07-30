package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.KvNodeItem;
import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.subgrade.data.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.data.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.data.stack.dao.PresetLookupDao;

/**
 * 键值对条目节点数据访问层。
 *
 * @author wangxy
 * @since 2.5.0
 */
public interface KvNodeItemDao extends BatchBaseDao<KvNodeItemKey, KvNodeItem>,
        EntireLookupDao<KvNodeItem>, PresetLookupDao<KvNodeItem> {
}
