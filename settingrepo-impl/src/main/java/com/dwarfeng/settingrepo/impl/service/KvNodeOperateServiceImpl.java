package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.KvNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.KvNodeOperateService;
import com.dwarfeng.subgrade.basic.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.basic.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class KvNodeOperateServiceImpl implements KvNodeOperateService {

    private final KvNodeOperateHandler kvNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public KvNodeOperateServiceImpl(KvNodeOperateHandler kvNodeOperateHandler, ServiceExceptionMapper sem) {
        this.kvNodeOperateHandler = kvNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public KvNodeCountResult count(KvNodeCountInfo info) throws ServiceException {
        try {
            return kvNodeOperateHandler.count(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("统计键值对节点条目数量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public KvNodeInspectResult inspect(KvNodeInspectInfo info) throws ServiceException {
        try {
            return kvNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看键值对节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public KvNodeItemInspectResult inspectItem(KvNodeItemInspectInfo info) throws ServiceException {
        try {
            return kvNodeOperateHandler.inspectItem(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看键值对节点条目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void putItem(KvNodeItemPutInfo info) throws ServiceException {
        try {
            kvNodeOperateHandler.putItem(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("向键值对节点放入条目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeItem(KvNodeItemRemoveInfo info) throws ServiceException {
        try {
            kvNodeOperateHandler.removeItem(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除键值对节点条目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clear(KvNodeClearInfo info) throws ServiceException {
        try {
            kvNodeOperateHandler.clear(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清空键值对节点时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
