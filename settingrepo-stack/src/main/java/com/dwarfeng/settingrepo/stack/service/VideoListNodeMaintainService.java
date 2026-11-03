package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 视频列表节点维护服务。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoListNodeMaintainService extends BatchCrudService<StringIdKey, VideoListNode>,
        EntireLookupService<VideoListNode>, PresetLookupService<VideoListNode> {
}
