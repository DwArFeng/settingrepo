package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import javax.annotation.Nullable;

/**
 * 国际化节点操作处理器。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public interface IahnNodeOperateHandler extends Handler {

    /**
     * 查询国际化消息。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    IahnNodeMessageInspectResult inspectMessage(IahnNodeMessageInspectInfo info) throws HandlerException;

    /**
     * 基于国际化地区批量查询国际化消息。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    IahnNodeMessageInspectByLocaleResult batchInspectMessageByLocale(IahnNodeMessageInspectByLocaleInfo info)
            throws HandlerException;

    /**
     * 查询国际化地区列表。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     * @since 2.3.3
     */
    @Nullable
    IahnNodeLocaleListInspectResult inspectLocaleList(IahnNodeLocaleListInspectInfo info) throws HandlerException;

    /**
     * 查询国际化 Mek 列表。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     * @since 2.3.3
     */
    @Nullable
    IahnNodeMekListInspectResult inspectMekList(IahnNodeMekListInspectInfo info) throws HandlerException;

    /**
     * 查询国际化消息表。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     * @since 2.3.3
     */
    @Nullable
    IahnNodeMessageTableInspectResult inspectMessageTable(IahnNodeMessageTableInspectInfo info) throws HandlerException;

    /**
     * 推入国际化地区。
     *
     * @param info 创建信息。
     * @throws HandlerException 处理器异常。
     */
    void putLocale(IahnNodeLocalePutInfo info) throws HandlerException;

    /**
     * 移除国际化地区。
     *
     * @param info 移除信息。
     * @throws HandlerException 处理器异常。
     */
    void removeLocale(IahnNodeLocaleRemoveInfo info) throws HandlerException;

    /**
     * 推入国际化 Mek。
     *
     * @param info 创建信息。
     * @throws HandlerException 处理器异常。
     */
    void putMek(IahnNodeMekPutInfo info) throws HandlerException;

    /**
     * 移除国际化 Mek。
     *
     * @param info 移除信息。
     * @throws HandlerException 处理器异常。
     */
    void removeMek(IahnNodeMekRemoveInfo info) throws HandlerException;

    /**
     * 插入或更新国际化消息。
     *
     * @param info 插入或更新信息。
     * @throws HandlerException 处理器异常。
     */
    void upsertMessage(IahnNodeMessageUpsertInfo info) throws HandlerException;

    /**
     * 基于国际化地区批量插入或更新国际化消息。
     *
     * @param info 插入或更新信息。
     * @throws HandlerException 处理器异常。
     */
    void batchUpsertMessageByLocale(IahnNodeMessageUpsertByLocaleInfo info) throws HandlerException;

    /**
     * 基于国际化 Mek 批量插入或更新国际化消息。
     *
     * @param info 插入或更新信息。
     * @throws HandlerException 处理器异常。
     */
    void batchUpsertMessageByMek(IahnNodeMessageUpsertByMekInfo info) throws HandlerException;
}
