package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodePutInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeRemoveInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
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
    public SettingNode inspect(SettingNodeInspectInfo settingNodeInspectInfo) throws ServiceException {
        try {
            return settingNodeOperateHandler.inspect(settingNodeInspectInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看设置节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void put(SettingNodePutInfo settingNodePutInfo) throws ServiceException {
        try {
            settingNodeOperateHandler.put(settingNodePutInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("放置设置节点时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(SettingNodeRemoveInfo settingNodeRemoveInfo) throws ServiceException {
        try {
            settingNodeOperateHandler.remove(settingNodeRemoveInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除设置节点时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
