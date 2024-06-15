package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 设置节点不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class SettingNodeNotExistsException extends HandlerException {

    private static final long serialVersionUID = 9154495834510038766L;

    private final StringIdKey settingNodeKey;

    public SettingNodeNotExistsException(StringIdKey settingNodeKey) {
        this.settingNodeKey = settingNodeKey;
    }

    public SettingNodeNotExistsException(Throwable cause, StringIdKey settingNodeKey) {
        super(cause);
        this.settingNodeKey = settingNodeKey;
    }

    @Override
    public String getMessage() {
        return "设置节点 " + settingNodeKey + " 不存在";
    }
}
