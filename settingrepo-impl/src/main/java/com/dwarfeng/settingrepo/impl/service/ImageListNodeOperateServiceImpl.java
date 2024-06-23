package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.ImageListNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.ImageListNodeOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class ImageListNodeOperateServiceImpl implements ImageListNodeOperateService {

    private final ImageListNodeOperateHandler imageListNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public ImageListNodeOperateServiceImpl(
            ImageListNodeOperateHandler imageListNodeOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.imageListNodeOperateHandler = imageListNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public ImageListNodeSizeResult size(ImageListNodeSizeInfo info) throws ServiceException {
        try {
            return imageListNodeOperateHandler.size(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("图片列表节点的大小时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ImageListNodeInspectResult inspect(ImageListNodeInspectInfo info) throws ServiceException {
        try {
            return imageListNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看图片列表节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ImageListNodeFile downloadFile(ImageListNodeFileDownloadInfo info) throws ServiceException {
        try {
            return imageListNodeOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载图片列表节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ImageListNodeFileStream downloadFileStream(ImageListNodeFileStreamDownloadInfo info)
            throws ServiceException {
        try {
            return imageListNodeOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载图片列表节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ImageListNodeThumbnail downloadThumbnail(ImageListNodeThumbnailDownloadInfo info) throws ServiceException {
        try {
            return imageListNodeOperateHandler.downloadThumbnail(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载图片列表节点缩略图时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFile(ImageListNodeFileUploadInfo info) throws ServiceException {
        try {
            imageListNodeOperateHandler.uploadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传图片列表节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFileStream(ImageListNodeFileStreamUploadInfo info) throws ServiceException {
        try {
            imageListNodeOperateHandler.uploadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传图片列表节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateFile(ImageListNodeFileUpdateInfo info) throws ServiceException {
        try {
            imageListNodeOperateHandler.updateFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新图片列表节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateFileStream(ImageListNodeFileStreamUpdateInfo info) throws ServiceException {
        try {
            imageListNodeOperateHandler.updateFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新图片列表节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void changeOrder(ImageListNodeChangeOrderInfo info) throws ServiceException {
        try {
            imageListNodeOperateHandler.changeOrder(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("改变图片列表节点的顺序时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(ImageListNodeRemoveInfo info) throws ServiceException {
        try {
            imageListNodeOperateHandler.remove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除图片列表节点时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
