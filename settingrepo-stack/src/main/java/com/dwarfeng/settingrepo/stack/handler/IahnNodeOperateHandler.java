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
     * 查询国际化消息，并重置查询过程中使用的缓存 TTL。
     *
     * <p>
     * 查询逻辑与 {@link #inspectMessage(IahnNodeMessageInspectInfo)} 完全一致。<br>
     * 地区映射缓存与消息映射缓存命中时，分别将缓存的生存时间重置为各自的配置值。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     * @since 3.1.0
     */
    @Nullable
    IahnNodeMessageInspectResult inspectMessageWithResetTtl(IahnNodeMessageInspectInfo info)
            throws HandlerException;

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
     * 基于国际化地区批量查询国际化消息，并重置查询过程中使用的缓存 TTL。
     *
     * <p>
     * 查询逻辑与 {@link #batchInspectMessageByLocale(IahnNodeMessageInspectByLocaleInfo)} 完全一致。<br>
     * 地区映射缓存与消息映射缓存命中时，分别将缓存的生存时间重置为各自的配置值。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     * @since 3.1.0
     */
    @Nullable
    IahnNodeMessageInspectByLocaleResult batchInspectMessageByLocaleWithResetTtl(
            IahnNodeMessageInspectByLocaleInfo info
    ) throws HandlerException;

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
     * 查询国际化地区列表，并重置地区列表缓存 TTL。
     *
     * <p>
     * 查询逻辑与 {@link #inspectLocaleList(IahnNodeLocaleListInspectInfo)} 完全一致。<br>
     * 地区列表缓存命中时，将缓存的生存时间重置为配置值。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     * @since 3.1.0
     */
    @Nullable
    IahnNodeLocaleListInspectResult inspectLocaleListWithResetTtl(IahnNodeLocaleListInspectInfo info)
            throws HandlerException;

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
     * 查询国际化 Mek 列表，并重置 Mek 列表缓存 TTL。
     *
     * <p>
     * 查询逻辑与 {@link #inspectMekList(IahnNodeMekListInspectInfo)} 完全一致。<br>
     * Mek 列表缓存命中时，将缓存的生存时间重置为配置值。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     * @since 3.1.0
     */
    @Nullable
    IahnNodeMekListInspectResult inspectMekListWithResetTtl(IahnNodeMekListInspectInfo info)
            throws HandlerException;

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
     * 查询国际化消息表，并重置消息表缓存 TTL。
     *
     * <p>
     * 查询逻辑与 {@link #inspectMessageTable(IahnNodeMessageTableInspectInfo)} 完全一致。<br>
     * 消息表缓存命中时，将缓存的生存时间重置为配置值。
     *
     * @param info 查询信息。
     * @return 查询结果。
     * @throws HandlerException 处理器异常。
     * @since 3.1.0
     */
    @Nullable
    IahnNodeMessageTableInspectResult inspectMessageTableWithResetTtl(IahnNodeMessageTableInspectInfo info)
            throws HandlerException;

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

    /**
     * 清理国际化节点缓存。
     *
     * @param info 缓存清理信息。
     * @throws HandlerException 处理器异常。
     * @since 3.0.4
     */
    void clearCache(IahnNodeClearCacheInfo info) throws HandlerException;
}
