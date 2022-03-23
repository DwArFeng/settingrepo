package com.dwarfeng.settingrepo.impl.handler;

/**
 * 判断器支持器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface FormatterSupporter {

    /**
     * 提供类型。
     *
     * @return 类型。
     */
    String provideType();

    /**
     * 提供标签。
     *
     * @return 标签。
     */
    String provideLabel();

    /**
     * 提供描述。
     *
     * @return 描述。
     */
    String provideDescription();

    /**
     * 提供示例内容。
     *
     * @return 示例内容。
     */
    String provideExampleContent();
}
