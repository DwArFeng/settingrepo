package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeLocale;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 国际化节点地区维护服务。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeLocaleMaintainService extends BatchCrudService<IahnNodeLocaleKey, IahnNodeLocale>,
        EntireLookupService<IahnNodeLocale>, PresetLookupService<IahnNodeLocale> {

    String CHILD_FOR_NODE = "child_for_node";
}
