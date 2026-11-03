package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 视频节点操作处理器。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoNodeOperateHandler extends Handler {

    /**
     * 查看视频节点。
     *
     * @param info 视频节点查看信息。
     * @return 视频节点查看结果。
     * @throws HandlerException 处理器异常。
     */
    VideoNodeInspectResult inspect(VideoNodeInspectInfo info) throws HandlerException;

    /**
     * 下载视频节点文件。
     *
     * @param info 视频节点文件下载信息。
     * @return 视频节点文件。
     * @throws HandlerException 处理器异常。
     */
    VideoNodeFile downloadFile(VideoNodeFileDownloadInfo info) throws HandlerException;

    /**
     * 下载视频节点文件流。
     *
     * <p>
     * 如果返回的 <code>VideoNodeFileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>VideoNodeFileStream</code> 中的输入流，即其中的 <code>VideoNodeFileStream.content</code>。
     *
     * @param info 视频节点文件流下载信息。
     * @return 视频节点文件流。
     * @throws HandlerException 处理器异常。
     */
    VideoNodeFileStream downloadFileStream(VideoNodeFileStreamDownloadInfo info) throws HandlerException;

    /**
     * 下载视频节点缩略图。
     *
     * @param info 视频节点缩略图下载信息。
     * @return 视频节点缩略图。
     * @throws HandlerException 处理器异常。
     */
    VideoNodeThumbnail downloadThumbnail(VideoNodeThumbnailDownloadInfo info) throws HandlerException;

    /**
     * 上传视频节点文件。
     *
     * @param info 视频节点文件上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadFile(VideoNodeFileUploadInfo info) throws HandlerException;

    /**
     * 上传视频节点文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>VideoNodeFileStream</code> 中的输入流，
     * 即其中的 <code>VideoNodeFileStream.content</code>。
     *
     * @param info 视频节点文件流上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadFileStream(VideoNodeFileStreamUploadInfo info) throws HandlerException;
}
