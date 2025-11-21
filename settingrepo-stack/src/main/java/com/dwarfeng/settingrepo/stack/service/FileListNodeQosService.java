package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 文件列表节点 QOS 服务。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public interface FileListNodeQosService extends Service {

    /**
     * 文件列表节点的大小。
     *
     * @param info 文件列表节点大小信息。
     * @return 文件列表节点大小结果。
     * @throws ServiceException 服务异常。
     */
    FileListNodeSizeResult size(FileListNodeSizeInfo info) throws ServiceException;

    /**
     * 查看文件列表节点。
     *
     * @param info 文件列表节点查看信息。
     * @return 文件列表节点查看结果。
     * @throws ServiceException 服务异常。
     */
    FileListNodeInspectResult inspect(FileListNodeInspectInfo info) throws ServiceException;

    /**
     * 下载文件列表节点文件。
     *
     * @param info 文件列表节点文件下载信息。
     * @return 文件列表节点文件。
     * @throws ServiceException 服务异常。
     */
    FileListNodeFile downloadFile(FileListNodeFileDownloadInfo info) throws ServiceException;

    /**
     * 下载文件列表节点文件流。
     *
     * <p>
     * 如果返回的 <code>FileListNodeFileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>FileListNodeFileStream</code> 中的输入流，即其中的 <code>FileListNodeFileStream.content</code>。
     *
     * @param info 文件列表节点文件流下载信息。
     * @return 文件列表节点文件流。
     * @throws ServiceException 服务异常。
     */
    FileListNodeFileStream downloadFileStream(FileListNodeFileStreamDownloadInfo info) throws ServiceException;

    /**
     * 上传文件列表节点文件。
     *
     * <p>
     * 上传文件列表节点文件时，会对文件列表节点做插入操作。<br>
     * 如果上传信息中的索引小于当前文件列表节点的大小，会自动将当前索引后的元素后移一位。<br>
     * 如果上传信息中的索引大于当前文件列表节点的大小，会自动填充中间的空缺，
     * 填充的空缺元素的 <code>nullFlag</code> 属性为 <code>true</code>。<br>
     *
     * @param info 文件列表节点文件上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadFile(FileListNodeFileUploadInfo info) throws ServiceException;

    /**
     * 上传文件列表节点文件流。
     *
     * <p>
     * 上传文件列表节点文件时，会对文件列表节点做插入操作。<br>
     * 如果上传信息中的索引小于当前文件列表节点的大小，会自动将当前索引后的元素后移一位。<br>
     * 如果上传信息中的索引大于当前文件列表节点的大小，会自动填充中间的空缺，
     * 填充的空缺元素的 <code>nullFlag</code> 属性为 <code>true</code>。<br>
     *
     * <p>
     * 调用者有义务关闭 <code>FileListNodeFileStream</code> 中的输入流，
     * 即其中的 <code>FileListNodeFileStream.content</code>。
     *
     * @param info 文件列表节点文件流上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadFileStream(FileListNodeFileStreamUploadInfo info) throws ServiceException;

    /**
     * 更新文件列表节点。
     *
     * <p>
     * 更新文件列表节点文件时，会对文件列表节点做设置操作。<br>
     * 如果更新信息中的索引小于当前文件列表节点的大小，会替换当前索引的元素。<br>
     * 如果上传信息中的索引大于当前文件列表节点的大小，会自动填充中间的空缺，
     * 填充的空缺元素的 <code>nullFlag</code> 属性为 <code>true</code>。<br>
     *
     * @param info 文件列表节点更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateFile(FileListNodeFileUpdateInfo info) throws ServiceException;

    /**
     * 更新文件列表节点文件流。
     *
     * <p>
     * 更新文件列表节点文件时，会对文件列表节点做设置操作。<br>
     * 如果更新信息中的索引小于当前文件列表节点的大小，会替换当前索引的元素。<br>
     * 如果上传信息中的索引大于当前文件列表节点的大小，会自动填充中间的空缺，
     * 填充的空缺元素的 <code>nullFlag</code> 属性为 <code>true</code>。<br>
     *
     * <p>
     * 调用者有义务关闭 <code>FileListNodeFileStream</code> 中的输入流，
     * 即其中的 <code>FileListNodeFileStream.content</code>。
     *
     * @param info 文件列表节点文件流更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateFileStream(FileListNodeFileStreamUpdateInfo info) throws ServiceException;

    /**
     * 变更文件列表节点顺序。
     *
     * @param info 文件列表节点变更顺序信息。
     * @throws ServiceException 服务异常。
     */
    void changeOrder(FileListNodeChangeOrderInfo info) throws ServiceException;

    /**
     * 移除文件列表节点。
     *
     * @param info 文件列表节点移除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(FileListNodeRemoveInfo info) throws ServiceException;
}
