package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import com.dwarfeng.subgrade.basic.stack.service.Service;

/**
 * 键值对节点操作服务。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public interface KvNodeOperateService extends Service {

    /**
     * 键值对节点的条目数量。
     *
     * @param info 键值对节点数量信息。
     * @return 键值对节点数量结果。
     * @throws ServiceException 服务异常。
     */
    KvNodeCountResult count(KvNodeCountInfo info) throws ServiceException;

    /**
     * 查看键值对节点。
     *
     * @param info 键值对节点查看信息。
     * @return 键值对节点查看结果。
     * @throws ServiceException 服务异常。
     */
    KvNodeInspectResult inspect(KvNodeInspectInfo info) throws ServiceException;

    /**
     * 查看键值对节点中的指定条目。
     *
     * @param info 键值对节点条目查看信息。
     * @return 键值对节点条目查看结果。
     * @throws ServiceException 服务异常。
     */
    KvNodeItemInspectResult inspectItem(KvNodeItemInspectInfo info) throws ServiceException;

    /**
     * 向键值对节点中放入指定条目。
     *
     * <p>
     * 若指定 <code>itemStringId</code> 已存在则覆盖其值，否则新增条目。
     *
     * @param info 键值对节点条目放入信息。
     * @throws ServiceException 服务异常。
     */
    void putItem(KvNodeItemPutInfo info) throws ServiceException;

    /**
     * 移除键值对节点中的指定条目。
     *
     * @param info 键值对节点条目移除信息。
     * @throws ServiceException 服务异常。
     */
    void removeItem(KvNodeItemRemoveInfo info) throws ServiceException;

    /**
     * 清空键值对节点中的全部条目。
     *
     * @param info 键值对节点清空信息。
     * @throws ServiceException 服务异常。
     */
    void clear(KvNodeClearInfo info) throws ServiceException;
}
