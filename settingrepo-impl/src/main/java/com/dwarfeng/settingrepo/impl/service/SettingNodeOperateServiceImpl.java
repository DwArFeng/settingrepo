package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.handler.SettingNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.SettingNodeOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class SettingNodeOperateServiceImpl implements SettingNodeOperateService {

    private final SettingNodeOperateHandler settingNodeOperateHandler;

    private final ServiceExceptionMapper sem;

    public SettingNodeOperateServiceImpl(
            SettingNodeOperateHandler settingNodeOperateHandler, ServiceExceptionMapper sem
    ) {
        this.settingNodeOperateHandler = settingNodeOperateHandler;
        this.sem = sem;
    }

    @Override
    public SettingNodeExistsResult exists(SettingNodeExistsInfo info) throws ServiceException {
        try {
            return settingNodeOperateHandler.exists(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看设置节点是否存在时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public SettingNodeInspectResult inspect(SettingNodeInspectInfo info) throws ServiceException {
        try {
            return settingNodeOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看设置节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void init(SettingNodeInitInfo info) throws ServiceException {
        try {
            settingNodeOperateHandler.init(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("初始化设置节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(SettingNodeRemoveInfo info) throws ServiceException {
        try {
            settingNodeOperateHandler.remove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除设置节点时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
