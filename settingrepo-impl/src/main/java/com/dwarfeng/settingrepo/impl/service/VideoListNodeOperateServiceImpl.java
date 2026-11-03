package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.VideoListNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.VideoListNodeOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class VideoListNodeOperateServiceImpl implements VideoListNodeOperateService {

    private final VideoListNodeOperateHandler videoListNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public VideoListNodeOperateServiceImpl(
            VideoListNodeOperateHandler videoListNodeOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.videoListNodeOperateHandler = videoListNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public VideoListNodeSizeResult size(VideoListNodeSizeInfo info) throws ServiceException {
        try {
            return videoListNodeOperateHandler.size(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("视频列表节点的大小时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VideoListNodeInspectResult inspect(VideoListNodeInspectInfo info) throws ServiceException {
        try {
            return videoListNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看视频列表节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VideoListNodeFile downloadFile(VideoListNodeFileDownloadInfo info) throws ServiceException {
        try {
            return videoListNodeOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载视频列表节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VideoListNodeFileStream downloadFileStream(VideoListNodeFileStreamDownloadInfo info)
            throws ServiceException {
        try {
            return videoListNodeOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载视频列表节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VideoListNodeThumbnail downloadThumbnail(VideoListNodeThumbnailDownloadInfo info) throws ServiceException {
        try {
            return videoListNodeOperateHandler.downloadThumbnail(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载视频列表节点缩略图时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFile(VideoListNodeFileUploadInfo info) throws ServiceException {
        try {
            videoListNodeOperateHandler.uploadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传视频列表节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFileStream(VideoListNodeFileStreamUploadInfo info) throws ServiceException {
        try {
            videoListNodeOperateHandler.uploadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传视频列表节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateFile(VideoListNodeFileUpdateInfo info) throws ServiceException {
        try {
            videoListNodeOperateHandler.updateFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新视频列表节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateFileStream(VideoListNodeFileStreamUpdateInfo info) throws ServiceException {
        try {
            videoListNodeOperateHandler.updateFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新视频列表节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void changeOrder(VideoListNodeChangeOrderInfo info) throws ServiceException {
        try {
            videoListNodeOperateHandler.changeOrder(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("改变视频列表节点的顺序时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(VideoListNodeRemoveInfo info) throws ServiceException {
        try {
            videoListNodeOperateHandler.remove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除视频列表节点时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
