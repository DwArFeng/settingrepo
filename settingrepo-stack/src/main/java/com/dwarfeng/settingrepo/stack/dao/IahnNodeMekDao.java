package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMek;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 国际化节点 Mek 数据访问层。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeMekDao extends BatchBaseDao<IahnNodeMekKey, IahnNodeMek>, EntireLookupDao<IahnNodeMek>,
        PresetLookupDao<IahnNodeMek> {
}
