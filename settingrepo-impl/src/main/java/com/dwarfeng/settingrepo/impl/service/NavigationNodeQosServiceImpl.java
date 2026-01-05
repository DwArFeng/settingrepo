package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.NavigationNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class NavigationNodeQosServiceImpl implements NavigationNodeQosService {

    private final NavigationNodeOperateHandler navigationNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public NavigationNodeQosServiceImpl(
            NavigationNodeOperateHandler navigationNodeOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.navigationNodeOperateHandler = navigationNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public NavigationNodeSizeResult size(NavigationNodeSizeInfo info) throws ServiceException {
        try {
            return navigationNodeOperateHandler.size(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看导航节点大小时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public NavigationNodeInspectResult inspect(NavigationNodeInspectInfo info) throws ServiceException {
        try {
            return navigationNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看导航节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public NavigationNodeItemInsertResult insertItem(NavigationNodeItemInsertInfo info) throws ServiceException {
        try {
            return navigationNodeOperateHandler.insertItem(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("插入导航节点条目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateItem(NavigationNodeItemUpdateInfo info) throws ServiceException {
        try {
            navigationNodeOperateHandler.updateItem(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新导航节点条目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeItem(NavigationNodeItemRemoveInfo info) throws ServiceException {
        try {
            navigationNodeOperateHandler.removeItem(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除导航节点条目时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void formatIndex(NavigationNodeFormatIndexInfo info) throws ServiceException {
        try {
            navigationNodeOperateHandler.formatIndex(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("格式化导航节点索引时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
