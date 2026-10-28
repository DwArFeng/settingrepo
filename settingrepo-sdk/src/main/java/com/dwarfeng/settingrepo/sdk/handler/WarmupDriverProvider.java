package com.dwarfeng.settingrepo.sdk.handler;

import com.dwarfeng.settingrepo.stack.handler.WarmupDriver;

/**
 * 预热驱动器提供器。
 *
 * <p>
 * 提供器负责判断自身支持的驱动策略类型，并返回与该类型对应的驱动器实例。
 * 驱动器实例由提供器管理，调用方不应自行创建或销毁。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriverProvider {

    /**
     * 返回提供器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 提供器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 提供驱动器。
     *
     * @return 提供的驱动器。
     */
    WarmupDriver provide();
}
