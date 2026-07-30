package com.dwarfeng.settingrepo.stack.internal.i18n;

import static com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages.Catalog.STACK;

/**
 * Stack 模块消息键。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public enum StackMessageKey {

    INDEX_RANGE_EMPTY(STACK, "index_range.empty"),
    FILE_LIST_NODE_INDEX_OUT_OF_BOUND(STACK, "exception.file_list_node_index_out_of_bound"),
    IAHN_NODE_LOCALE_NOT_EXISTS(STACK, "exception.iahn_node_locale_not_exists"),
    IAHN_NODE_MEK_NOT_EXISTS(STACK, "exception.iahn_node_mek_not_exists"),
    IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND(STACK, "exception.image_list_node_index_out_of_bound"),
    INVALID_IAHN_NODE_LOCALE(STACK, "exception.invalid_iahn_node_locale"),
    INVALID_IAHN_NODE_MEK_ID(STACK, "exception.invalid_iahn_node_mek_id"),
    NAVIGATION_NODE_ITEM_INDEX_CONFLICT(STACK, "exception.navigation_node_item_index_conflict"),
    NAVIGATION_NODE_ITEM_MISMATCHED(STACK, "exception.navigation_node_item_mismatched"),
    NAVIGATION_NODE_ITEM_NOT_EXISTS(STACK, "exception.navigation_node_item_not_exists"),
    SETTING_CATEGORY_NOT_EXISTS(STACK, "exception.setting_category_not_exists"),
    SETTING_NODE_EXISTS(STACK, "exception.setting_node_exists"),
    SETTING_NODE_NOT_EXISTS(STACK, "exception.setting_node_not_exists"),
    UNSUPPORTED_FORMATTER_TYPE(STACK, "exception.unsupported_formatter_type");

    private final StackMessages.Catalog catalog;
    private final String key;

    StackMessageKey(StackMessages.Catalog catalog, String key) {
        this.catalog = catalog;
        this.key = key;
    }

    StackMessages.Catalog catalog() {
        return catalog;
    }

    public String key() {
        return key;
    }
}
