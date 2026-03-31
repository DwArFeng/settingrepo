package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.KvNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 键值对节点维护服务。
 *
 * @author wangxy
 * @since 2.5.0
 */
public interface KvNodeMaintainService extends BatchCrudService<StringIdKey, KvNode>,
        EntireLookupService<KvNode>, PresetLookupService<KvNode> {
}
