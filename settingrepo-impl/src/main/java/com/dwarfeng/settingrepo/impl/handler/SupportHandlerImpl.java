package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.sdk.handler.FormatterSupporter;
import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.settingrepo.stack.handler.SupportHandler;
import com.dwarfeng.settingrepo.stack.service.FormatterSupportMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SupportHandlerImpl implements SupportHandler {

    private final FormatterSupportMaintainService formatterSupportMaintainService;

    private final List<FormatterSupporter> formatterSupporters;

    public SupportHandlerImpl(
            FormatterSupportMaintainService formatterSupportMaintainService,
            List<FormatterSupporter> formatterSupporters
    ) {
        this.formatterSupportMaintainService = formatterSupportMaintainService;
        this.formatterSupporters = Optional.ofNullable(formatterSupporters).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public void resetFormatter() throws HandlerException {
        try {
            doResetFormatter();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetFormatter() throws Exception {
        List<StringIdKey> formatterKeys = formatterSupportMaintainService.lookupAsList().stream()
                .map(FormatterSupport::getKey).collect(Collectors.toList());
        formatterSupportMaintainService.batchDelete(formatterKeys);
        List<FormatterSupport> formatterSupports = formatterSupporters.stream().map(supporter -> new FormatterSupport(
                new StringIdKey(supporter.provideType()),
                supporter.provideLabel(),
                supporter.provideDescription(),
                supporter.provideExampleContent()
        )).collect(Collectors.toList());
        formatterSupportMaintainService.batchInsert(formatterSupports);
    }
}
