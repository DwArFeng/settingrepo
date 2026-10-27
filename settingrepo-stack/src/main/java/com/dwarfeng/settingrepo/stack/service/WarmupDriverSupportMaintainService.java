package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 预热驱动器支持维护服务。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriverSupportMaintainService extends BatchCrudService<StringIdKey, WarmupDriverSupport>,
        EntireLookupService<WarmupDriverSupport>, PresetLookupService<WarmupDriverSupport> {

    // region 预设查询 - 业务逻辑

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";

    // endregion
}
