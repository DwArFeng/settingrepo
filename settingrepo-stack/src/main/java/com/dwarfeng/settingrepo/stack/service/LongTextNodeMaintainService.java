package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.LongTextNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 长文本节点维护服务。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface LongTextNodeMaintainService extends BatchCrudService<StringIdKey, LongTextNode>,
        EntireLookupService<LongTextNode>, PresetLookupService<LongTextNode> {
}
