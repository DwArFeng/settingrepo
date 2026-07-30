package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.FileNode;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.data.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.data.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.data.stack.service.PresetLookupService;

/**
 * 文件节点维护服务。
 *
 * @author WangT
 * @since 2.4.2
 */
public interface FileNodeMaintainService extends BatchCrudService<StringIdKey, FileNode>,
        EntireLookupService<FileNode>, PresetLookupService<FileNode> {
}
