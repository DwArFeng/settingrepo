package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.FileListNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.FileListNodeQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class FileListNodeQosServiceImpl implements FileListNodeQosService {

    private final FileListNodeOperateHandler fileListNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public FileListNodeQosServiceImpl(
            FileListNodeOperateHandler fileListNodeOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.fileListNodeOperateHandler = fileListNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public FileListNodeSizeResult size(FileListNodeSizeInfo info) throws ServiceException {
        try {
            return fileListNodeOperateHandler.size(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("文件列表节点的大小时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileListNodeInspectResult inspect(FileListNodeInspectInfo info) throws ServiceException {
        try {
            return fileListNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看文件列表节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileListNodeFile downloadFile(FileListNodeFileDownloadInfo info) throws ServiceException {
        try {
            return fileListNodeOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载文件列表节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileListNodeFileStream downloadFileStream(FileListNodeFileStreamDownloadInfo info) throws ServiceException {
        try {
            return fileListNodeOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载文件列表节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFile(FileListNodeFileUploadInfo info) throws ServiceException {
        try {
            fileListNodeOperateHandler.uploadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传文件列表节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFileStream(FileListNodeFileStreamUploadInfo info) throws ServiceException {
        try {
            fileListNodeOperateHandler.uploadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传文件列表节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateFile(FileListNodeFileUpdateInfo info) throws ServiceException {
        try {
            fileListNodeOperateHandler.updateFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新文件列表节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateFileStream(FileListNodeFileStreamUpdateInfo info) throws ServiceException {
        try {
            fileListNodeOperateHandler.updateFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新文件列表节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void changeOrder(FileListNodeChangeOrderInfo info) throws ServiceException {
        try {
            fileListNodeOperateHandler.changeOrder(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("改变文件列表节点的顺序时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(FileListNodeRemoveInfo info) throws ServiceException {
        try {
            fileListNodeOperateHandler.remove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除文件列表节点时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
