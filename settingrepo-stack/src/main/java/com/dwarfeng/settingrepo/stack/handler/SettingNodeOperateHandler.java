package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodePutInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeRemoveInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 设置节点操作处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface SettingNodeOperateHandler extends Handler {

    SettingNode inspect(SettingNodeInspectInfo settingNodeInspectInfo) throws HandlerException;

    void put(SettingNodePutInfo settingNodePutInfo) throws HandlerException;

    void remove(SettingNodeRemoveInfo settingNodeRemoveInfo) throws HandlerException;
}
