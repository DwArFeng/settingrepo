package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessages;
import com.dwarfeng.settingrepo.sdk.handler.FormatterMaker;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.exception.FormatterException;
import com.dwarfeng.settingrepo.stack.exception.FormatterMakeException;
import com.dwarfeng.settingrepo.stack.exception.UnsupportedFormatterTypeException;
import com.dwarfeng.settingrepo.stack.handler.Formatter;
import com.dwarfeng.settingrepo.stack.handler.FormatterHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class FormatterHandlerImpl implements FormatterHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(FormatterHandlerImpl.class);

    private final List<FormatterMaker> formatterMakers;

    public FormatterHandlerImpl(List<FormatterMaker> formatterMakers) {
        this.formatterMakers = Optional.ofNullable(formatterMakers).orElse(Collections.emptyList());
    }

    @Override
    public Formatter make(SettingCategory settingCategory) throws FormatterException {
        try {
            // 生成触发器。
            LOGGER.debug(ImplMessages.message(ImplMessageKey.LOG_FORMATTER_BUILDING));
            FormatterMaker formatterMaker = formatterMakers.stream()
                    .filter(maker -> maker.supportType(settingCategory.getFormatterType())).findFirst()
                    .orElseThrow(() -> new UnsupportedFormatterTypeException(settingCategory.getFormatterType()));
            Formatter formatter;
            try {
                formatter = formatterMaker.makeFormatter(settingCategory);
            } catch (Exception e) {
                throw new FormatterMakeException(e);
            }
            LOGGER.debug(ImplMessages.message(ImplMessageKey.LOG_FORMATTER_BUILT));
            LOGGER.debug(ImplMessages.message(ImplMessageKey.LOG_FORMATTER_DETAIL, formatter));
            return formatter;
        } catch (FormatterException e) {
            throw e;
        } catch (Exception e) {
            throw new FormatterException(e);
        }
    }
}
