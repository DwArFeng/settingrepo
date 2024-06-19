package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.ImageNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.ImageNodeQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class ImageNodeQosServiceImpl implements ImageNodeQosService {

    private final ImageNodeOperateHandler imageNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public ImageNodeQosServiceImpl(ImageNodeOperateHandler imageNodeOperateHandler, ServiceExceptionMapper sem) {
        this.imageNodeOperateHandler = imageNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public ImageNodeInspectResult inspect(ImageNodeInspectInfo info) throws ServiceException {
        try {
            return imageNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看图片节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ImageNodeFile downloadFile(ImageNodeFileDownloadInfo info) throws ServiceException {
        try {
            return imageNodeOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载图片节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ImageNodeFileStream downloadFileStream(ImageNodeFileStreamDownloadInfo info) throws ServiceException {
        try {
            return imageNodeOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载图片节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ImageNodeThumbnail downloadThumbnail(ImageNodeThumbnailDownloadInfo info) throws ServiceException {
        try {
            return imageNodeOperateHandler.downloadThumbnail(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载图片节点缩略图时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFile(ImageNodeFileUploadInfo info) throws ServiceException {
        try {
            imageNodeOperateHandler.uploadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传图片节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFileStream(ImageNodeFileStreamUploadInfo info) throws ServiceException {
        try {
            imageNodeOperateHandler.uploadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传图片节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
