package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 预热驱动信息维护服务。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriverInfoMaintainService extends BatchCrudService<LongIdKey, WarmupDriverInfo>,
        EntireLookupService<WarmupDriverInfo>, PresetLookupService<WarmupDriverInfo> {

    // region 预设查询 - 级联

    String CHILD_FOR_WARMUP_SETTING = "child_for_warmup_setting";

    // endregion

    // region 预设查询 - 业务逻辑

    String CHILD_FOR_WARMUP_SETTING_ENABLED = "child_for_warmup_setting_enabled";

    // endregion
}
