package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.VideoNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.VideoNodeQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class VideoNodeQosServiceImpl implements VideoNodeQosService {

    private final VideoNodeOperateHandler videoNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public VideoNodeQosServiceImpl(VideoNodeOperateHandler videoNodeOperateHandler, ServiceExceptionMapper sem) {
        this.videoNodeOperateHandler = videoNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public VideoNodeInspectResult inspect(VideoNodeInspectInfo info) throws ServiceException {
        try {
            return videoNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看视频节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VideoNodeFile downloadFile(VideoNodeFileDownloadInfo info) throws ServiceException {
        try {
            return videoNodeOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载视频节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VideoNodeFileStream downloadFileStream(VideoNodeFileStreamDownloadInfo info) throws ServiceException {
        try {
            return videoNodeOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载视频节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VideoNodeThumbnail downloadThumbnail(VideoNodeThumbnailDownloadInfo info) throws ServiceException {
        try {
            return videoNodeOperateHandler.downloadThumbnail(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载视频节点缩略图时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFile(VideoNodeFileUploadInfo info) throws ServiceException {
        try {
            videoNodeOperateHandler.uploadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传视频节点文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadFileStream(VideoNodeFileStreamUploadInfo info) throws ServiceException {
        try {
            videoNodeOperateHandler.uploadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传视频节点文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
