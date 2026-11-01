package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.TextListNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 文本列表节点维护服务。
 *
 * @author lihc
 * @since 3.2.0
 */
public interface TextListNodeMaintainService extends BatchCrudService<StringIdKey, TextListNode>,
        EntireLookupService<TextListNode>, PresetLookupService<TextListNode> {
}
