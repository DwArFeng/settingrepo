package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 视频节点维护服务。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoNodeMaintainService extends BatchCrudService<StringIdKey, VideoNode>,
        EntireLookupService<VideoNode>, PresetLookupService<VideoNode> {
}
