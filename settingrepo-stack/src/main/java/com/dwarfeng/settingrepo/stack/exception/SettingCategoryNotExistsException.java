package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 设置类别不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SettingCategoryNotExistsException extends HandlerException {

    private static final long serialVersionUID = -6547946517141879958L;

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
        return "设置类别 " + settingCategoryKey + " 不存在";
    }
}
