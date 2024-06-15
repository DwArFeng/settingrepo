package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 设置节点存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class SettingNodeExistsException extends HandlerException {

    private static final long serialVersionUID = -7687873256433515287L;

    private final StringIdKey settingNodeKey;

    public SettingNodeExistsException(StringIdKey settingNodeKey) {
        this.settingNodeKey = settingNodeKey;
    }

    public SettingNodeExistsException(Throwable cause, StringIdKey settingNodeKey) {
        super(cause);
        this.settingNodeKey = settingNodeKey;
    }

    @Override
    public String getMessage() {
        return "设置节点 " + settingNodeKey + " 已经存在";
    }
}
