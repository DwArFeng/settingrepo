package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.TextListNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.TextListNodeOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class TextListNodeOperateServiceImpl implements TextListNodeOperateService {

    private final TextListNodeOperateHandler textListNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public TextListNodeOperateServiceImpl(
            TextListNodeOperateHandler textListNodeOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.textListNodeOperateHandler = textListNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public TextListNodeSizeResult size(TextListNodeSizeInfo info) throws ServiceException {
        try {
            return textListNodeOperateHandler.size(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("文本列表节点的大小时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public TextListNodeInspectResult inspect(TextListNodeInspectInfo info) throws ServiceException {
        try {
            return textListNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看文本列表节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public TextListNodeTextFetchResult fetchText(TextListNodeTextFetchInfo info) throws ServiceException {
        try {
            return textListNodeOperateHandler.fetchText(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("拉取文本列表节点文本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void insertText(TextListNodeTextInsertInfo info) throws ServiceException {
        try {
            textListNodeOperateHandler.insertText(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("插入文本列表节点文本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateText(TextListNodeTextUpdateInfo info) throws ServiceException {
        try {
            textListNodeOperateHandler.updateText(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新文本列表节点文本时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void changeOrder(TextListNodeChangeOrderInfo info) throws ServiceException {
        try {
            textListNodeOperateHandler.changeOrder(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("改变文本列表节点的顺序时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(TextListNodeRemoveInfo info) throws ServiceException {
        try {
            textListNodeOperateHandler.remove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除文本列表节点时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
