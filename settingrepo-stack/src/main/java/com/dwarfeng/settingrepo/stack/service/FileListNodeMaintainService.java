package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.FileListNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 文本列表节点维护服务。
 *
 * @author liulx
 * @since 2.4.2
 */
public interface FileListNodeMaintainService extends BatchCrudService<StringIdKey , FileListNode>,
        EntireLookupService<FileListNode>, PresetLookupService<FileListNode> {

}
