package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 文件节点操作处理器。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public interface FileNodeOperateHandler extends Handler {

    /**
     * 查看文件节点。
     *
     * @param info 文件节点查看信息。
     * @return 文件节点查看结果。
     * @throws HandlerException 处理器异常。
     */
    FileNodeInspectResult inspect(FileNodeInspectInfo info) throws HandlerException;

    /**
     * 下载文件节点文件。
     *
     * @param info 文件节点文件下载信息。
     * @return 文件节点文件。
     * @throws HandlerException 处理器异常。
     */
    FileNodeFile downloadFile(FileNodeFileDownloadInfo info) throws HandlerException;

    /**
     * 下载文件节点文件流。
     *
     * <p>
     * 如果返回的 <code>FileNodeFileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>FileNodeFileStream</code> 中的输入流，即其中的 <code>FileNodeFileStream.content</code>。
     *
     * @param info 文件节点文件流下载信息。
     * @return 文件节点文件流。
     * @throws HandlerException 处理器异常。
     */
    FileNodeFileStream downloadFileStream(FileNodeFileStreamDownloadInfo info) throws HandlerException;

    /**
     * 上传文件节点文件。
     *
     * @param info 文件节点文件上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadFile(FileNodeFileUploadInfo info) throws HandlerException;

    /**
     * 上传文件节点文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>FileNodeFileStream</code> 中的输入流，
     * 即其中的 <code>FileNodeFileStream.content</code>。
     *
     * @param info 文件节点文件流上传信息。
     * @throws HandlerException 处理器异常。
     */
    void uploadFileStream(FileNodeFileStreamUploadInfo info) throws HandlerException;
}
