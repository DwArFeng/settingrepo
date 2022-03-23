package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.exception.FormatterMakeException;
import com.dwarfeng.settingrepo.stack.exception.UnsupportedFormatterTypeException;
import com.dwarfeng.settingrepo.stack.handler.Formatter;
import com.dwarfeng.settingrepo.stack.handler.FormatterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FormatterHandlerImpl implements FormatterHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormatterHandlerImpl.class);

    @Autowired(required = false)
    private List<FormatterMaker> formatterMakers = new ArrayList<>();

    @Override
    public Formatter make(SettingCategory settingCategory)
            throws UnsupportedFormatterTypeException, FormatterMakeException {
        try {
            // 生成触发器。
            LOGGER.debug("通过格式化器信息构建新的的格式化器...");
            FormatterMaker formatterMaker = formatterMakers.stream()
                    .filter(maker -> maker.supportType(settingCategory.getFormatterType())).findFirst()
                    .orElseThrow(() -> new UnsupportedFormatterTypeException(settingCategory.getFormatterType()));
            Formatter formatter = formatterMaker.makeFormatter(settingCategory);
            LOGGER.debug("格式化器构建成功!");
            LOGGER.debug("格式化器: " + formatter);
            return formatter;
        } catch (UnsupportedFormatterTypeException e) {
            throw e;
        } catch (Exception e) {
            throw new FormatterMakeException(e);
        }
    }
}
