package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 设置节点不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class SettingNodeNotExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -5202119772341465341L;

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
        return StackMessages.message(StackMessageKey.EXCEPTION_SETTING_NODE_NOT_EXISTS, settingNodeKey);
    }
}
