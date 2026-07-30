package com.dwarfeng.settingrepo.sdk.internal.i18n;

import static com.dwarfeng.settingrepo.sdk.internal.i18n.SdkMessages.Catalog.SDK;

/**
 * SDK 模块消息键。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public enum SdkMessageKey {

    SERVICE_EXCEPTION_FORMATTER_FAILED(SDK, "service_exception.formatter_failed"),
    SERVICE_EXCEPTION_FORMATTER_EXECUTION_FAILED(SDK, "service_exception.formatter_execution_failed"),
    SERVICE_EXCEPTION_FORMATTER_MAKE_FAILED(SDK, "service_exception.formatter_make_failed"),
    SERVICE_EXCEPTION_UNSUPPORTED_FORMATTER_TYPE(SDK, "service_exception.unsupported_formatter_type"),
    SERVICE_EXCEPTION_SETTING_CATEGORY_NOT_EXISTS(SDK, "service_exception.setting_category_not_exists"),
    SERVICE_EXCEPTION_SETTING_NODE_EXISTS(SDK, "service_exception.setting_node_exists"),
    SERVICE_EXCEPTION_SETTING_NODE_NOT_EXISTS(SDK, "service_exception.setting_node_not_exists"),
    SERVICE_EXCEPTION_IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND(
            SDK, "service_exception.image_list_node_index_out_of_bound"
    ),
    SERVICE_EXCEPTION_INVALID_IAHN_NODE_LOCALE(SDK, "service_exception.invalid_iahn_node_locale"),
    SERVICE_EXCEPTION_INVALID_IAHN_NODE_MEK_ID(SDK, "service_exception.invalid_iahn_node_mek_id"),
    SERVICE_EXCEPTION_IAHN_NODE_LOCALE_NOT_EXISTS(SDK, "service_exception.iahn_node_locale_not_exists"),
    SERVICE_EXCEPTION_IAHN_NODE_MEK_NOT_EXISTS(SDK, "service_exception.iahn_node_mek_not_exists"),
    SERVICE_EXCEPTION_FILE_LIST_NODE_INDEX_OUT_OF_BOUND(
            SDK, "service_exception.file_list_node_index_out_of_bound"
    ),
    SERVICE_EXCEPTION_NAVIGATION_NODE_ITEM_INDEX_CONFLICT(
            SDK, "service_exception.navigation_node_item_index_conflict"
    ),
    SERVICE_EXCEPTION_NAVIGATION_NODE_ITEM_MISMATCHED(
            SDK, "service_exception.navigation_node_item_mismatched"
    ),
    SERVICE_EXCEPTION_NAVIGATION_NODE_ITEM_NOT_EXISTS(
            SDK, "service_exception.navigation_node_item_not_exists"
    );

    private final SdkMessages.Catalog catalog;
    private final String key;

    SdkMessageKey(SdkMessages.Catalog catalog, String key) {
        this.catalog = catalog;
        this.key = key;
    }

    SdkMessages.Catalog catalog() {
        return catalog;
    }

    /**
     * 获取消息键。
     *
     * @return 消息键。
     */
    public String key() {
        return key;
    }
}
