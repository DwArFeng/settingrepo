package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 设置类别不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SettingCategoryNotExistsException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -3778076986687696508L;

    private final StringIdKey settingCategoryKey;

    public SettingCategoryNotExistsException(StringIdKey settingCategoryKey) {
        this.settingCategoryKey = settingCategoryKey;
    }

    public SettingCategoryNotExistsException(Throwable cause, StringIdKey settingCategoryKey) {
        super(cause);
        this.settingCategoryKey = settingCategoryKey;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.SETTING_CATEGORY_NOT_EXISTS, settingCategoryKey);
    }
}
