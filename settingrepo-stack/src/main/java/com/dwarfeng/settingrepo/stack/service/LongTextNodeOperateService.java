package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

import javax.annotation.Nullable;

/**
 * 长文本节点操作服务。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface LongTextNodeOperateService extends Service {

    /**
     * 查看长文本节点。
     *
     * @param info 长文本节点查看信息。
     * @return 长文本节点查看结果。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    LongTextNodeInspectResult inspect(LongTextNodeInspectInfo info) throws ServiceException;

    /**
     * 下载长文本节点文本。
     *
     * @param info 长文本节点文本下载信息。
     * @return 长文本节点文本。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    LongTextNodeText downloadText(LongTextNodeTextDownloadInfo info) throws ServiceException;

    /**
     * 下载长文本节点文本流。
     *
     * <p>
     * 如果返回的 <code>LongTextNodeTextStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>LongTextNodeTextStream</code> 中的输入流，即其中的 <code>LongTextNodeTextStream.content</code>。
     *
     * @param info 长文本节点文本流下载信息。
     * @return 长文本节点文本流。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    LongTextNodeTextStream downloadTextStream(LongTextNodeTextStreamDownloadInfo info) throws ServiceException;

    /**
     * 上传长文本节点文本。
     *
     * @param info 长文本节点文本上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadText(LongTextNodeTextUploadInfo info) throws ServiceException;

    /**
     * 上传长文本节点文本流。
     *
     * <p>
     * 调用者有义务关闭 <code>LongTextNodeTextStream</code> 中的输入流，
     * 即其中的 <code>LongTextNodeTextStream.content</code>。
     *
     * @param info 长文本节点文本流上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadTextStream(LongTextNodeTextStreamUploadInfo info) throws ServiceException;
}
