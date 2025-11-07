package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 导航节点维护服务。
 *
 * @author Zhaofz
 * @since 2.4.2
 */
public interface NavigationNodeMaintainService extends BatchCrudService<StringIdKey, NavigationNode>,
        EntireLookupService<NavigationNode>, PresetLookupService<NavigationNode> {

}
