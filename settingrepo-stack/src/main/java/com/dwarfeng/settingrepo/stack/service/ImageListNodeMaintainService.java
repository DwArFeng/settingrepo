package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 图片列表节点维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface ImageListNodeMaintainService extends BatchCrudService<StringIdKey, ImageListNode>,
        EntireLookupService<ImageListNode>, PresetLookupService<ImageListNode> {
}
