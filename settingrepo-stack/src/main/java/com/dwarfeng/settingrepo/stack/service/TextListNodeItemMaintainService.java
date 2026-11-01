package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.TextListNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 文本列表节点条目维护服务。
 *
 * @author lihc
 * @since 3.2.0
 */
public interface TextListNodeItemMaintainService extends BatchCrudService<LongIdKey, TextListNodeItem>,
        EntireLookupService<TextListNodeItem>, PresetLookupService<TextListNodeItem> {

    String CHILD_FOR_NODE = "child_for_node";
    String CHILD_FOR_NODE_INDEX_ASC = "child_for_node_index_asc";
    String CHILD_FOR_NODE_INDEX_EQ = "child_for_node_index_eq";
    String CHILD_FOR_NODE_INDEX_GE = "child_for_node_index_ge";
    String CHILD_FOR_NODE_INDEX_GT_INDEX_LE = "child_for_node_index_gt_index_le";
    String CHILD_FOR_NODE_INDEX_GE_INDEX_LT = "child_for_node_index_ge_index_lt";
}
