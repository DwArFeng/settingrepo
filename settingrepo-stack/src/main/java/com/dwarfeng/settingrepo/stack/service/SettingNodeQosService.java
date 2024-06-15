package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 设置节点 QOS 服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface SettingNodeQosService extends Service {

    /**
     * 查看指定的设置节点是否存在。
     *
     * @param info 查看信息。
     * @return 指定的设置节点是否存在。
     * @throws ServiceException 服务异常。
     */
    SettingNodeExistsResult exists(SettingNodeExistsInfo info) throws ServiceException;

    /**
     * 查看指定的设置节点。
     *
     * @param info 查看信息。
     * @return 指定的设置节点查看结果。
     * @throws ServiceException 服务异常。
     */
    SettingNodeInspectResult inspect(SettingNodeInspectInfo info) throws ServiceException;

    /**
     * 初始化指定的设置节点。
     *
     * @param info 初始化信息。
     * @throws ServiceException 服务异常。
     */
    void init(SettingNodeInitInfo info) throws ServiceException;

    /**
     * 删除指定的设置节点。
     *
     * @param info 删除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(SettingNodeRemoveInfo info) throws ServiceException;
}
