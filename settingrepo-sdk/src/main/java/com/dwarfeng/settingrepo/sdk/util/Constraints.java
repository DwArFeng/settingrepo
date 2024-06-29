package com.dwarfeng.settingrepo.sdk.util;

/**
 * 约束类。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class Constraints {

    /**
     * 备注的长度约束。
     */
    public static final int LENGTH_REMARK = 100;

    /**
     * 格式化器类型的长度约束。
     */
    public static final int LENGTH_TYPE = 50;

    /**
     * 名称的长度约束。
     */
    public static final int LENGTH_NAME = 100;

    /**
     * 设置类型 ID 的长度约束。
     *
     * @since 2.0.0
     */
    public static final int LENGTH_SETTING_CATEGORY_ID = 100;

    /**
     * 设置节点 ID 的长度约束。
     *
     * @since 2.0.0
     */
    public static final int LENGTH_SETTING_NODE_ID = 250;

    private Constraints() {
        throw new IllegalStateException("禁止实例化");
    }
}
