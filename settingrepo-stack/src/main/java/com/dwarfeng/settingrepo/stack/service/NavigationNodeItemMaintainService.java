package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNodeItem;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 导航节点条目维护服务。
 *
 * @author Zhaofz
 * @author DwArFeng
 * @since 2.4.2
 */
public interface NavigationNodeItemMaintainService extends BatchCrudService<LongIdKey, NavigationNodeItem>,
        EntireLookupService<NavigationNodeItem>, PresetLookupService<NavigationNodeItem> {

    String CHILD_FOR_NODE = "child_for_node";
    String CHILD_FOR_PARENT = "child_for_parent";

    /**
     * @since 2.4.5
     */
    String CHILD_FOR_PARENT_INDEX_ASC = "child_for_parent_index_asc";

    /**
     * @since 2.4.5
     */
    String CHILD_FOR_PARENT_INDEX_DESC = "child_for_parent_index_desc";

    /**
     * @since 2.4.5
     */
    String CHILD_FOR_PARENT_INDEX_EQ = "child_for_parent_index_eq";

    String CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_ASC = "child_for_node_child_for_parent_index_asc";
    String CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_DESC = "child_for_node_child_for_parent_index_desc";
    String CHILD_FOR_NODE_CHILD_FOR_PARENT_INDEX_EQ = "child_for_node_child_for_parent_index_eq";

    /**
     * @since 2.4.5
     */
    String CHILD_FOR_NODE_NAME_LIKE = "child_for_node_name_like";
}
