package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 导航节点数据访问层。
 *
 * @author Zhaofz
 * @since 2.4.2
 */
public interface NavigationNodeDao extends BatchBaseDao<StringIdKey, NavigationNode>, EntireLookupDao<NavigationNode>,
        PresetLookupDao<NavigationNode> {
}
