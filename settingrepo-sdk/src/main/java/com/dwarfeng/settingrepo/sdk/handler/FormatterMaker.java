package com.dwarfeng.settingrepo.sdk.handler;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.exception.FormatterException;
import com.dwarfeng.settingrepo.stack.handler.Formatter;

/**
 * 格式化器构造器。
 *
 * @author DwArFeng
 * @since 2.3.0
 */
public interface FormatterMaker {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 根据指定的设置类别生成一个格式化器对象。
     * <p>可以保证传入的格式化器信息中的类型是支持的。</p>
     *
     * @param settingCategory 指定的设置类别。
     * @return 制造出的格式化器。
     * @throws FormatterException 格式化器异常。
     */
    Formatter makeFormatter(SettingCategory settingCategory) throws FormatterException;
}
