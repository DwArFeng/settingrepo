package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodePutInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeRemoveInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 设置节点操作服务。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SettingNodeOperateService extends Service {

    SettingNode inspect(SettingNodeInspectInfo settingNodeInspectInfo) throws ServiceException;

    void put(SettingNodePutInfo settingNodePutInfo) throws ServiceException;

    void remove(SettingNodeRemoveInfo settingNodeRemoveInfo) throws ServiceException;
}
