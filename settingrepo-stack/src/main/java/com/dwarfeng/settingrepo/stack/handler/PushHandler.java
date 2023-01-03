package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 推送处理器。
 *
 * @author DwArFeng
 * @since 1.1.2
 */
public interface PushHandler extends Handler {

    /**
     * 格式化被重置时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     */
    void formatReset() throws HandlerException;
}
