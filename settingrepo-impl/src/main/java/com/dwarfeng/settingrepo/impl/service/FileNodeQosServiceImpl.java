package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.FileNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.FileNodeQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class FileNodeQosServiceImpl implements FileNodeQosService {

    private final FileNodeOperateHandler fileNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public FileNodeQosServiceImpl(FileNodeOperateHandler fileNodeOperateHandler, ServiceExceptionMapper sem) {
        this.fileNodeOperateHandler = fileNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public FileNodeInspectResult inspect(FileNodeInspectInfo info) throws ServiceException {
        try {
            return fileNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看文件节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileNodeFile downloadFile(FileNodeFileDownloadInfo info) throws ServiceException {
        try {
            return fileNodeOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载文件节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileNodeFileStream downloadFileStream(FileNodeFileStreamDownloadInfo info) throws ServiceException {
        try {
            return fileNodeOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载文件节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFile(FileNodeFileUploadInfo info) throws ServiceException {
        try {
            fileNodeOperateHandler.uploadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传文件节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFileStream(FileNodeFileStreamUploadInfo info) throws ServiceException {
        try {
            fileNodeOperateHandler.uploadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传文件节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
