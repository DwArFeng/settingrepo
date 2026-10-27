package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 预热设置数据访问层。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupSettingDao extends BatchBaseDao<LongIdKey, WarmupSetting>,
        EntireLookupDao<WarmupSetting>, PresetLookupDao<WarmupSetting> {
}
