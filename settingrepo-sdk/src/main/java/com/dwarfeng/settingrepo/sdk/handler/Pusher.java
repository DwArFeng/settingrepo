package com.dwarfeng.settingrepo.sdk.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 事件推送器。
 *
 * @author DwArFeng
 * @since 2.3.0
 */
public interface Pusher {

    /**
     * 返回事件推送器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 事件推送器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 格式化被重置时执行的调度。
     *
     * @throws HandlerException 处理器异常。
     */
    void formatReset() throws HandlerException;
}
