package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import javax.annotation.Nullable;

/**
 * 文本列表节点 QOS 服务。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface TextListNodeQosService extends Service {

    /**
     * 文本列表节点的大小。
     *
     * @param info 文本列表节点大小信息。
     * @return 文本列表节点大小结果。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    TextListNodeSizeResult size(TextListNodeSizeInfo info) throws ServiceException;

    /**
     * 查看文本列表节点。
     *
     * @param info 文本列表节点查看信息。
     * @return 文本列表节点查看结果。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    TextListNodeInspectResult inspect(TextListNodeInspectInfo info) throws ServiceException;

    /**
     * 拉取文本列表节点中的文本。
     *
     * @param info 文本列表节点文本拉取信息。
     * @return 文本列表节点文本，不存在时返回 {@code null}。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    TextListNodeTextFetchResult fetchText(TextListNodeTextFetchInfo info) throws ServiceException;

    /**
     * 在指定索引处插入文本列表节点文本。
     *
     * <p>
     * 插入文本时，会将当前索引及之后的元素后移一位。<br>
     * 索引等于当前文本列表节点大小时，表示在列表末尾插入。<br>
     * 索引小于零或大于当前文本列表节点大小时，视为索引越界。
     *
     * @param info 文本列表节点文本插入信息。
     * @throws ServiceException 服务异常。
     */
    void insertText(TextListNodeTextInsertInfo info) throws ServiceException;

    /**
     * 更新指定索引处的文本列表节点文本。
     *
     * <p>
     * 更新文本时，仅替换指定索引处条目的文本内容，不会改变文本列表节点的大小。<br>
     * 索引小于零或不小于当前文本列表节点大小时，视为索引越界。
     *
     * @param info 文本列表节点文本更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateText(TextListNodeTextUpdateInfo info) throws ServiceException;

    /**
     * 变更文本列表节点顺序。
     *
     * @param info 文本列表节点变更顺序信息。
     * @throws ServiceException 服务异常。
     */
    void changeOrder(TextListNodeChangeOrderInfo info) throws ServiceException;

    /**
     * 移除文本列表节点。
     *
     * @param info 文本列表节点移除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(TextListNodeRemoveInfo info) throws ServiceException;
}
