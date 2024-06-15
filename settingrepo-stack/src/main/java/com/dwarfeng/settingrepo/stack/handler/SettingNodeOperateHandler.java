package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 设置节点操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface SettingNodeOperateHandler extends Handler {

    /**
     * 查看指定的设置节点是否存在。
     *
     * @param info 查看信息。
     * @return 指定的设置节点是否存在。
     * @throws HandlerException 处理器异常。
     */
    SettingNodeExistsResult exists(SettingNodeExistsInfo info) throws HandlerException;

    /**
     * 查看指定的设置节点。
     *
     * @param info 查看信息。
     * @return 指定的设置节点查看结果。
     * @throws HandlerException 处理器异常。
     */
    SettingNodeInspectResult inspect(SettingNodeInspectInfo info) throws HandlerException;

    /**
     * 初始化指定的设置节点。
     *
     * @param info 初始化信息。
     * @throws HandlerException 处理器异常。
     */
    void init(SettingNodeInitInfo info) throws HandlerException;

    /**
     * 删除指定的设置节点。
     *
     * @param info 删除信息。
     * @throws HandlerException 处理器异常。
     */
    void remove(SettingNodeRemoveInfo info) throws HandlerException;
}
