package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 国际化节点数据访问层。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeDao extends BatchBaseDao<StringIdKey, IahnNode>, EntireLookupDao<IahnNode>,
        PresetLookupDao<IahnNode> {
}
