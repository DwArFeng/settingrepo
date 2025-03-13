package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeLocale;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 国际化节点地区数据访问层。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeLocaleDao extends BatchBaseDao<IahnNodeLocaleKey, IahnNodeLocale>,
        EntireLookupDao<IahnNodeLocale>, PresetLookupDao<IahnNodeLocale> {
}
