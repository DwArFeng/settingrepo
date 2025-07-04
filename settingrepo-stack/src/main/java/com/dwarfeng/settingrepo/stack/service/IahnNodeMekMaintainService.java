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

    /**
     * 属于指定的国际化节点，并且按照顺序排序。
     *
     * <p>
     * 排序的依据为：
     * <ol>
     *   <li>Mek ID，即 <code>mekId</code></li>
     * </ol>
     *
     * @since 2.3.3
     */
    String CHILD_FOR_NODE_ORDERED = "child_for_node_ordered";
}
