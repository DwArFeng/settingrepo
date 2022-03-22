package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 格式化器支持器数据访问层。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface FormatterSupportDao extends BatchBaseDao<StringIdKey, FormatterSupport>,
        EntireLookupDao<FormatterSupport>, PresetLookupDao<FormatterSupport> {
}
