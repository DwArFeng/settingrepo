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

    /**
     * 属于指定的国际化节点，并且按照顺序排序。
     *
     * <p>
     * 排序的依据为：
     * <ol>
     *   <li>语言，即 <code>language</code></li>
     *   <li>国家，即 <code>country</code></li>
     *   <li>变体，即 <code>variant</code></li>
     * </ol>
     *
     * @since 2.3.3
     */
    String CHILD_FOR_NODE_ORDERED = "child_for_node_ordered";
}
