package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 文件节点 QOS 服务。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public interface FileNodeQosService extends Service {

    /**
     * 查看文件节点。
     *
     * @param info 文件节点查看信息。
     * @return 文件节点查看结果。
     * @throws ServiceException 服务异常。
     */
    FileNodeInspectResult inspect(FileNodeInspectInfo info) throws ServiceException;

    /**
     * 下载文件节点文件。
     *
     * @param info 文件节点文件下载信息。
     * @return 文件节点文件。
     * @throws ServiceException 服务异常。
     */
    FileNodeFile downloadFile(FileNodeFileDownloadInfo info) throws ServiceException;

    /**
     * 下载文件节点文件流。
     *
     * <p>
     * 如果返回的 <code>FileNodeFileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>FileNodeFileStream</code> 中的输入流，即其中的 <code>FileNodeFileStream.content</code>。
     *
     * @param info 文件节点文件流下载信息。
     * @return 文件节点文件流。
     * @throws ServiceException 服务异常。
     */
    FileNodeFileStream downloadFileStream(FileNodeFileStreamDownloadInfo info) throws ServiceException;

    /**
     * 上传文件节点文件。
     *
     * @param info 文件节点文件上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadFile(FileNodeFileUploadInfo info) throws ServiceException;

    /**
     * 上传文件节点文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>FileNodeFileStream</code> 中的输入流，
     * 即其中的 <code>FileNodeFileStream.content</code>。
     *
     * @param info 文件节点文件流上传信息。
     * @throws ServiceException 服务异常。
     */
    void uploadFileStream(FileNodeFileStreamUploadInfo info) throws ServiceException;
}
