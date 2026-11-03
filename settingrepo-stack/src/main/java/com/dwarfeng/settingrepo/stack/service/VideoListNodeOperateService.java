package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 视频列表节点操作服务。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoListNodeOperateService extends Service {

    /**
     * 视频列表节点的大小。
     *
     * @param info 视频列表节点大小信息。
     * @return 视频列表节点大小结果。
     * @throws ServiceException 服务异常。
     */
    VideoListNodeSizeResult size(VideoListNodeSizeInfo info) throws ServiceException;

    /**
     * 查看视频列表节点。
     *
     * @param info 视频列表节点查看信息。
     * @return 视频列表节点查看结果。
     * @throws ServiceException 服务异常。
     */
    VideoListNodeInspectResult inspect(VideoListNodeInspectInfo info) throws ServiceException;

    /**
     * 下载视频列表节点文件。
     *
     * @param info 视频列表节点文件下载信息。
     * @return 视频列表节点文件。
     * @throws ServiceException 服务异常。
     */
    VideoListNodeFile downloadFile(VideoListNodeFileDownloadInfo info) throws ServiceException;

    /**
     * 下载视频列表节点文件流。
     *
     * <p>
     * 如果返回的 <code>VideoListNodeFileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>VideoListNodeFileStream</code> 中的输入流，即其中的 <code>VideoListNodeFileStream.content</code>。
     *
     * @param info 视频列表节点文件流下载信息。
     * @return 视频列表节点文件流。
     * @throws ServiceException 服务异常。
     */
    VideoListNodeFileStream downloadFileStream(VideoListNodeFileStreamDownloadInfo info) throws ServiceException;

    /**
     * 下载视频列表节点缩略图。
     *
     * @param info 视频列表节点缩略图下载信息。
     * @return 视频列表节点缩略图。
     * @throws ServiceException 服务异常。
     */
    VideoListNodeThumbnail downloadThumbnail(VideoListNodeThumbnailDownloadInfo info) throws ServiceException;

    /**
     * 上传视频列表节点文件。
     *
     * <p>
     * 上传视频列表节点文件时，会对视频列表节点做插入操作。<br>
     * 如果上传信息中的索引小于当前视频列表节点的大小，会自动将当前索引后的元素后移一位。<br>
     * 如果上传信息中的索引大于当前视频列表节点的大小，会自动填充中间的空缺，
     * 填充的空缺元素的 <code>nullFlag</code> 属性为 <code>true</code>。<br>
     *
     * @param info 视频列表节点文件上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadFile(VideoListNodeFileUploadInfo info) throws ServiceException;

    /**
     * 上传视频列表节点文件流。
     *
     * <p>
     * 上传视频列表节点文件时，会对视频列表节点做插入操作。<br>
     * 如果上传信息中的索引小于当前视频列表节点的大小，会自动将当前索引后的元素后移一位。<br>
     * 如果上传信息中的索引大于当前视频列表节点的大小，会自动填充中间的空缺，
     * 填充的空缺元素的 <code>nullFlag</code> 属性为 <code>true</code>。<br>
     *
     * <p>
     * 调用者有义务关闭 <code>VideoListNodeFileStream</code> 中的输入流，
     * 即其中的 <code>VideoListNodeFileStream.content</code>。
     *
     * @param info 视频列表节点文件流上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadFileStream(VideoListNodeFileStreamUploadInfo info) throws ServiceException;

    /**
     * 更新视频列表节点。
     *
     * <p>
     * 更新视频列表节点文件时，会对视频列表节点做设置操作。<br>
     * 如果更新信息中的索引小于当前视频列表节点的大小，会替换当前索引的元素。<br>
     * 如果上传信息中的索引大于当前视频列表节点的大小，会自动填充中间的空缺，
     * 填充的空缺元素的 <code>nullFlag</code> 属性为 <code>true</code>。<br>
     *
     * @param info 视频列表节点更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateFile(VideoListNodeFileUpdateInfo info) throws ServiceException;

    /**
     * 更新视频列表节点文件流。
     *
     * <p>
     * 更新视频列表节点文件时，会对视频列表节点做设置操作。<br>
     * 如果更新信息中的索引小于当前视频列表节点的大小，会替换当前索引的元素。<br>
     * 如果上传信息中的索引大于当前视频列表节点的大小，会自动填充中间的空缺，
     * 填充的空缺元素的 <code>nullFlag</code> 属性为 <code>true</code>。<br>
     *
     * <p>
     * 调用者有义务关闭 <code>VideoListNodeFileStream</code> 中的输入流，
     * 即其中的 <code>VideoListNodeFileStream.content</code>。
     *
     * @param info 视频列表节点文件流更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateFileStream(VideoListNodeFileStreamUpdateInfo info) throws ServiceException;

    /**
     * 变更视频列表节点顺序。
     *
     * @param info 视频列表节点变更顺序信息。
     * @throws ServiceException 服务异常。
     */
    void changeOrder(VideoListNodeChangeOrderInfo info) throws ServiceException;

    /**
     * 移除视频列表节点。
     *
     * @param info 视频列表节点移除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(VideoListNodeRemoveInfo info) throws ServiceException;
}
