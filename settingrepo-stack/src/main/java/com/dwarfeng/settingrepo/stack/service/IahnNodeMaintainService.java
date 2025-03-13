package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 国际化节点维护服务。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeMaintainService extends BatchCrudService<StringIdKey, IahnNode>,
        EntireLookupService<IahnNode>, PresetLookupService<IahnNode> {
}
