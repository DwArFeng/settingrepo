package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 设置节点数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SettingNodeDao extends BatchBaseDao<StringIdKey, SettingNode>, EntireLookupDao<SettingNode>,
        PresetLookupDao<SettingNode> {
}
