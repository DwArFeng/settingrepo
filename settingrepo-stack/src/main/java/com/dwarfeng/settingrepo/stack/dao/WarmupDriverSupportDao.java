package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 预热驱动器支持数据访问层。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriverSupportDao extends BatchBaseDao<StringIdKey, WarmupDriverSupport>,
        EntireLookupDao<WarmupDriverSupport>, PresetLookupDao<WarmupDriverSupport> {
}
