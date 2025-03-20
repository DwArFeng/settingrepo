package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.LongTextNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.LongTextNodeOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class LongTextNodeOperateServiceImpl implements LongTextNodeOperateService {

    private final LongTextNodeOperateHandler longTextNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public LongTextNodeOperateServiceImpl(
            LongTextNodeOperateHandler longTextNodeOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.longTextNodeOperateHandler = longTextNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongTextNodeInspectResult inspect(LongTextNodeInspectInfo info) throws ServiceException {
        try {
            return longTextNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看长文本节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public LongTextNodeText downloadText(LongTextNodeTextDownloadInfo info) throws ServiceException {
        try {
            return longTextNodeOperateHandler.downloadText(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载长文本节点文本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public LongTextNodeTextStream downloadTextStream(LongTextNodeTextStreamDownloadInfo info) throws ServiceException {
        try {
            return longTextNodeOperateHandler.downloadTextStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载长文本节点文本流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadText(LongTextNodeTextUploadInfo info) throws ServiceException {
        try {
            longTextNodeOperateHandler.uploadText(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传长文本节点文本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void uploadTextStream(LongTextNodeTextStreamUploadInfo info) throws ServiceException {
        try {
            longTextNodeOperateHandler.uploadTextStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上传长文本节点文本流时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
