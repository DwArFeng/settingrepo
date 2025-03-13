package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMek;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 国际化节点 Mek 维护服务。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeMekMaintainService extends BatchCrudService<IahnNodeMekKey, IahnNodeMek>,
        EntireLookupService<IahnNodeMek>, PresetLookupService<IahnNodeMek> {

    String CHILD_FOR_NODE = "child_for_node";
}
