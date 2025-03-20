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

    /**
     * 国际化节点 MEK 的长度约束。
     */
    public static final int LENGTH_IAHN_NODE_MEK_ID = 100;

    /**
     * 国际化节点地区的长度约束。
     *
     * @since 2.1.0
     */
    public static final int LENGTH_IAHN_NODE_LANGUAGE_MIN = 2;

    /**
     * 国际化节点地区的长度约束。
     *
     * @since 2.1.0
     */
    public static final int LENGTH_IAHN_NODE_LANGUAGE_MAX = 3;

    /**
     * 国际化节点地区的长度约束。
     *
     * @since 2.1.0
     */
    public static final int LENGTH_IAHN_NODE_COUNTRY_MIN = 2;

    /**
     * 国际化节点地区的长度约束。
     *
     * @since 2.1.0
     */
    public static final int LENGTH_IAHN_NODE_COUNTRY_MAX = 3;

    /**
     * 国际化节点变体的长度约束。
     *
     * @since 2.1.0
     */
    public static final int LENGTH_IAHN_NODE_VARIANT_MIN = 5;

    /**
     * 国际化节点变体的长度约束。
     *
     * @since 2.1.0
     */
    public static final int LENGTH_IAHN_NODE_VARIANT_MAX = 8;

    /**
     * 国际化节点消息的长度约束。
     *
     * @since 2.1.0
     */
    public static final int LENGTH_IAHN_NODE_MESSAGE = 250;

    /**
     * 长文本节点预览的长度约束。
     *
     * @since 2.2.0
     */
    public static final int LENGTH_LONG_TEXT_NODE_PREVIEW = Constants.LONG_TEXT_NODE_PREVIEW_LENGTH;

    private Constraints() {
        throw new IllegalStateException("禁止实例化");
    }
}
