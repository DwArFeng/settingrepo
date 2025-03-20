package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import javax.annotation.Nullable;

/**
 * 长文本节点操作处理器。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface LongTextNodeOperateHandler extends Handler {

    /**
     * 查看长文本节点。
     *
     * @param info 长文本节点查看信息。
     * @return 长文本节点查看结果。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    LongTextNodeInspectResult inspect(LongTextNodeInspectInfo info) throws HandlerException;

    /**
     * 下载长文本节点文本。
     *
     * @param info 长文本节点文本下载信息。
     * @return 长文本节点文本。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    LongTextNodeText downloadText(LongTextNodeTextDownloadInfo info) throws HandlerException;

    /**
     * 下载长文本节点文本流。
     *
     * <p>
     * 如果返回的 <code>LongTextNodeTextStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>LongTextNodeTextStream</code> 中的输入流，即其中的 <code>LongTextNodeTextStream.content</code>。
     *
     * @param info 长文本节点文本流下载信息。
     * @return 长文本节点文本流。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    LongTextNodeTextStream downloadTextStream(LongTextNodeTextStreamDownloadInfo info) throws HandlerException;

    /**
     * 上传长文本节点文本。
     *
     * @param info 长文本节点文本上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadText(LongTextNodeTextUploadInfo info) throws HandlerException;

    /**
     * 上传长文本节点文本流。
     *
     * <p>
     * 调用者有义务关闭 <code>LongTextNodeTextStream</code> 中的输入流，
     * 即其中的 <code>LongTextNodeTextStream.content</code>。
     *
     * @param info 长文本节点文本流上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadTextStream(LongTextNodeTextStreamUploadInfo info) throws HandlerException;
}
