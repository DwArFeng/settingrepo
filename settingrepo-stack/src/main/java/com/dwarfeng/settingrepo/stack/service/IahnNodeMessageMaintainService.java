package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 国际化节点消息维护服务。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeMessageMaintainService extends BatchCrudService<IahnNodeMessageKey, IahnNodeMessage>,
        EntireLookupService<IahnNodeMessage>, PresetLookupService<IahnNodeMessage> {

    String CHILD_FOR_NODE = "child_for_node";
    String CHILD_FOR_LOCALE = "child_for_locale";
    String CHILD_FOR_MEK = "child_for_mek";
}
