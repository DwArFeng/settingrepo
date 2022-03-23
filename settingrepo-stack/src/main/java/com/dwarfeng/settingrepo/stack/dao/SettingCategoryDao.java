package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 设置类别支持器数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SettingCategoryDao extends BatchBaseDao<StringIdKey, SettingCategory>,
        EntireLookupDao<SettingCategory>, PresetLookupDao<SettingCategory> {
}
