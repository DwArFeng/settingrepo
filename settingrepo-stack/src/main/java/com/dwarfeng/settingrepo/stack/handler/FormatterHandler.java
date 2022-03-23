package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.exception.FormatterException;

/**
 * 格式化器处理器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public interface FormatterHandler {

    /**
     * 根据指定的设置类别构造一个格式化器。
     *
     * @param settingCategory 指定的设置类别。
     * @return 指定的设置类别构造的格式化器。
     * @throws FormatterException 格式化器异常。
     */
    Formatter make(SettingCategory settingCategory) throws FormatterException;
}
