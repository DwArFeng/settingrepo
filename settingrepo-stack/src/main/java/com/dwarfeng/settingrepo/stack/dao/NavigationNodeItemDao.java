package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 导航节点条目数据访问层。
 *
 * @author Zhaofz
 * @since 2.4.2
 */
public interface NavigationNodeItemDao extends BatchBaseDao<LongIdKey, NavigationNodeItem>,
        EntireLookupDao<NavigationNodeItem>, PresetLookupDao<NavigationNodeItem> {
}
