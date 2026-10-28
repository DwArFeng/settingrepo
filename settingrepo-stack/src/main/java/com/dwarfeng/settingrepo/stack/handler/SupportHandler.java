package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 支持处理器。
 *
 * @author DwArFeng
 * @since 2.4.0
 */
public interface SupportHandler extends Handler {

    /**
     * 重置格式化器。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetFormatter() throws HandlerException;

    /**
     * 重置预热驱动器支持。
     *
     * @throws HandlerException 处理器异常。
     * @since 3.1.0
     */
    void resetWarmupDriver() throws HandlerException;
}
