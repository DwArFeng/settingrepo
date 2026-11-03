package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 视频节点 QOS 服务。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoNodeQosService extends Service {

    /**
     * 查看视频节点。
     *
     * @param info 视频节点查看信息。
     * @return 视频节点查看结果。
     * @throws ServiceException 服务异常。
     */
    VideoNodeInspectResult inspect(VideoNodeInspectInfo info) throws ServiceException;

    /**
     * 下载视频节点文件。
     *
     * @param info 视频节点文件下载信息。
     * @return 视频节点文件。
     * @throws ServiceException 服务异常。
     */
    VideoNodeFile downloadFile(VideoNodeFileDownloadInfo info) throws ServiceException;

    /**
     * 下载视频节点文件流。
     *
     * <p>
     * 如果返回的 <code>VideoNodeFileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>VideoNodeFileStream</code> 中的输入流，即其中的 <code>VideoNodeFileStream.content</code>。
     *
     * @param info 视频节点文件流下载信息。
     * @return 视频节点文件流。
     * @throws ServiceException 服务异常。
     */
    VideoNodeFileStream downloadFileStream(VideoNodeFileStreamDownloadInfo info) throws ServiceException;

    /**
     * 下载视频节点缩略图。
     *
     * @param info 视频节点缩略图下载信息。
     * @return 视频节点缩略图。
     * @throws ServiceException 服务异常。
     */
    VideoNodeThumbnail downloadThumbnail(VideoNodeThumbnailDownloadInfo info) throws ServiceException;

    /**
     * 上传视频节点文件。
     *
     * @param info 视频节点文件上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadFile(VideoNodeFileUploadInfo info) throws ServiceException;

    /**
     * 上传视频节点文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>VideoNodeFileStream</code> 中的输入流，
     * 即其中的 <code>VideoNodeFileStream.content</code>。
     *
     * @param info 视频节点文件流上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadFileStream(VideoNodeFileStreamUploadInfo info) throws ServiceException;
}
