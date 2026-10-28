package com.dwarfeng.settingrepo.sdk.handler;

/**
 * 预热驱动器支持器。
 *
 * <p>
 * 支持器只提供驱动策略的展示信息，不参与调度和业务执行。
 * 运维界面或管理端可以借助这些信息展示可用策略及参数示例。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriverSupporter {

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
     * 提供示例参数。
     *
     * @return 示例参数。
     */
    String provideExampleParam();
}
