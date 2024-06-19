package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 图片节点维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface ImageNodeMaintainService extends BatchCrudService<StringIdKey, ImageNode>,
        EntireLookupService<ImageNode>, PresetLookupService<ImageNode> {
}
