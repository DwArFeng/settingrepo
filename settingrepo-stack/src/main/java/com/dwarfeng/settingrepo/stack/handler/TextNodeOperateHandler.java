package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodePutInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 文本节点操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface TextNodeOperateHandler extends Handler {

    /**
     * 查看指定的文本节点。
     *
     * @param info 查看信息。
     * @return 指定的文本节点查看结果。
     * @throws HandlerException 处理器异常。
     */
    TextNodeInspectResult inspect(TextNodeInspectInfo info) throws HandlerException;

    /**
     * 向指定的文本节点中放入指定的信息。
     *
     * @param info 放入信息。
     * @throws HandlerException 处理器异常。
     */
    void put(TextNodePutInfo info) throws HandlerException;
}
