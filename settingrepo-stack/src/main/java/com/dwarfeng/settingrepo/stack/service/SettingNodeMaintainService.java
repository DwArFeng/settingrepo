package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 设置节点维护服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SettingNodeMaintainService extends BatchCrudService<StringIdKey, SettingNode>,
        EntireLookupService<SettingNode>, PresetLookupService<SettingNode> {

    String ID_LIKE = "id_like";
}
