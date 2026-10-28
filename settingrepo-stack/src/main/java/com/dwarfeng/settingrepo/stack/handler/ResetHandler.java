package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.StartableHandler;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
public interface ResetHandler extends StartableHandler {

    /**
     * 重置格式化。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetFormat() throws HandlerException;

    /**
     * 重置预热。
     *
     * @throws HandlerException 处理器异常。
     * @since 3.1.0
     */
    void resetWarmup() throws HandlerException;
}
