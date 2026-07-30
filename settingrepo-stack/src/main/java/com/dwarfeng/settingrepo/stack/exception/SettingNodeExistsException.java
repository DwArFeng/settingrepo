package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 设置节点存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class SettingNodeExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = 1759757175626546352L;

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
        return StackMessages.message(StackMessageKey.SETTING_NODE_EXISTS, settingNodeKey);
    }
}
