package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 预热设置维护服务。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupSettingMaintainService extends BatchCrudService<LongIdKey, WarmupSetting>,
        EntireLookupService<WarmupSetting>, PresetLookupService<WarmupSetting> {

    // region 预设查询 - 业务逻辑

    String ENABLED = "enabled";

    // endregion
}
