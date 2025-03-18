package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 国际化节点操作服务。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeOperateService extends Service {

    /**
     * 查询国际化消息。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws ServiceException 服务异常。
     */
    IahnNodeMessageInspectResult inspectMessage(IahnNodeMessageInspectInfo info) throws ServiceException;

    /**
     * 基于国际化地区批量查询国际化消息。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws ServiceException 服务异常。
     */
    IahnNodeMessageInspectByLocaleResult batchInspectMessageByLocale(IahnNodeMessageInspectByLocaleInfo info)
            throws ServiceException;

    /**
     * 推入国际化地区。
     *
     * @param info 创建信息。
     * @throws ServiceException 服务异常。
     */
    void putLocale(IahnNodeLocalePutInfo info) throws ServiceException;

    /**
     * 移除国际化地区。
     *
     * @param info 移除信息。
     * @throws ServiceException 服务异常。
     */
    void removeLocale(IahnNodeLocaleRemoveInfo info) throws ServiceException;

    /**
     * 推入国际化 Mek。
     *
     * @param info 创建信息。
     * @throws ServiceException 服务异常。
     */
    void putMek(IahnNodeMekPutInfo info) throws ServiceException;

    /**
     * 移除国际化 Mek。
     *
     * @param info 移除信息。
     * @throws ServiceException 服务异常。
     */
    void removeMek(IahnNodeMekRemoveInfo info) throws ServiceException;

    /**
     * 插入或更新国际化消息。
     *
     * @param info 插入或更新信息。
     * @throws ServiceException 服务异常。
     */
    void upsertMessage(IahnNodeMessageUpsertInfo info) throws ServiceException;

    /**
     * 基于国际化地区批量插入或更新国际化消息。
     *
     * @param info 插入或更新信息。
     * @throws ServiceException 服务异常。
     */
    void batchUpsertMessageByLocale(IahnNodeMessageUpsertByLocaleInfo info) throws ServiceException;

    /**
     * 基于国际化 Mek 批量插入或更新国际化消息。
     *
     * @param info 插入或更新信息。
     * @throws ServiceException 服务异常。
     */
    void batchUpsertMessageByMek(IahnNodeMessageUpsertByMekInfo info) throws ServiceException;
}
