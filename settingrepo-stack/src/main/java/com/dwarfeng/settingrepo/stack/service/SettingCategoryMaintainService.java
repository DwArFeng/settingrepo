package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 设置类别维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SettingCategoryMaintainService extends BatchCrudService<StringIdKey, SettingCategory>,
        EntireLookupService<SettingCategory>, PresetLookupService<SettingCategory> {

    String ID_LIKE = "id_like";
}
