package com.dwarfeng.settingrepo.node.all.he.internal.i18n;

import static com.dwarfeng.settingrepo.node.all.he.internal.i18n.NodeMessages.Catalog.NODE;

/**
 * Node All HE 模块消息键。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public enum NodeMessageKey {

    LAUNCHER_RESETTING_FORMATTER_SUPPORT(NODE, "launcher.resetting_formatter_support"),
    LAUNCHER_RESET_FORMATTER_SUPPORT_FAILED(NODE, "launcher.reset_formatter_support_failed"),
    LAUNCHER_STARTING_RESET_IMMEDIATELY(NODE, "launcher.starting_reset_immediately"),
    LAUNCHER_START_RESET_FAILED(NODE, "launcher.start_reset_failed"),
    LAUNCHER_STARTING_RESET_DELAYED(NODE, "launcher.starting_reset_delayed"),
    LAUNCHER_STARTING_RESET(NODE, "launcher.starting_reset");

    private final NodeMessages.Catalog catalog;
    private final String key;

    NodeMessageKey(NodeMessages.Catalog catalog, String key) {
        this.catalog = catalog;
        this.key = key;
    }

    NodeMessages.Catalog catalog() {
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
