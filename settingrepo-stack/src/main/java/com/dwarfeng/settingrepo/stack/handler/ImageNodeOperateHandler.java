package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 图片节点操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface ImageNodeOperateHandler extends Handler {

    /**
     * 查看图片节点。
     *
     * @param info 图片节点查看信息。
     * @return 图片节点查看结果。
     * @throws HandlerException 处理器异常。
     */
    ImageNodeInspectResult inspect(ImageNodeInspectInfo info) throws HandlerException;

    /**
     * 下载图片节点文件。
     *
     * @param info 图片节点文件下载信息。
     * @return 图片节点文件。
     * @throws HandlerException 处理器异常。
     */
    ImageNodeFile downloadFile(ImageNodeFileDownloadInfo info) throws HandlerException;

    /**
     * 下载图片节点文件流。
     *
     * <p>
     * 如果返回的 <code>ImageNodeFileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>ImageNodeFileStream</code> 中的输入流，即其中的 <code>ImageNodeFileStream.content</code>。
     *
     * @param info 图片节点文件流下载信息。
     * @return 图片节点文件流。
     * @throws HandlerException 处理器异常。
     */
    ImageNodeFileStream downloadFileStream(ImageNodeFileStreamDownloadInfo info) throws HandlerException;

    /**
     * 下载图片节点缩略图。
     *
     * @param info 图片节点缩略图下载信息。
     * @return 图片节点缩略图。
     * @throws HandlerException 处理器异常。
     */
    ImageNodeThumbnail downloadThumbnail(ImageNodeThumbnailDownloadInfo info) throws HandlerException;

    /**
     * 上传图片节点文件。
     *
     * @param info 图片节点文件上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadFile(ImageNodeFileUploadInfo info) throws HandlerException;

    /**
     * 上传图片节点文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>ImageNodeFileStream</code> 中的输入流，
     * 即其中的 <code>ImageNodeFileStream.content</code>。
     *
     * @param info 图片节点文件流上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadFileStream(ImageNodeFileStreamUploadInfo info) throws HandlerException;
}
