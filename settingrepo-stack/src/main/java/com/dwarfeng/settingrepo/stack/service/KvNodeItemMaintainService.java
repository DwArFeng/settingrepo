package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.KvNodeItem;
import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 键值对节点维护服务。
 *
 * @author wangxy
 * @since 2.5.0
 */
public interface KvNodeItemMaintainService extends BatchCrudService<KvNodeItemKey, KvNodeItem>,
        EntireLookupService<KvNodeItem>, PresetLookupService<KvNodeItem> {

    // region 预设查询 - 级联

    String CHILD_FOR_NODE = "child_for_node";

    // endregion

    // region 预设查询 - 业务逻辑

    String STRING_ID_ASC = "string_id_asc";
    String CHILD_FOR_NODE_ITEM_STRING_ID_ASC = "child_for_node_item_string_id_asc";

    // endregion
}
