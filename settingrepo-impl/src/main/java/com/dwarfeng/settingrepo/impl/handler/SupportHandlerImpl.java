package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.sdk.handler.FormatterSupporter;
import com.dwarfeng.settingrepo.sdk.handler.WarmupDriverSupporter;
import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverSupport;
import com.dwarfeng.settingrepo.stack.handler.SupportHandler;
import com.dwarfeng.settingrepo.stack.service.FormatterSupportMaintainService;
import com.dwarfeng.settingrepo.stack.service.WarmupDriverSupportMaintainService;
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
    private final WarmupDriverSupportMaintainService warmupDriverSupportMaintainService;

    private final List<FormatterSupporter> formatterSupporters;
    private final List<WarmupDriverSupporter> warmupDriverSupporters;

    public SupportHandlerImpl(
            FormatterSupportMaintainService formatterSupportMaintainService,
            WarmupDriverSupportMaintainService warmupDriverSupportMaintainService,
            List<FormatterSupporter> formatterSupporters,
            List<WarmupDriverSupporter> warmupDriverSupporters
    ) {
        this.formatterSupportMaintainService = formatterSupportMaintainService;
        this.warmupDriverSupportMaintainService = warmupDriverSupportMaintainService;
        this.formatterSupporters = Optional.ofNullable(formatterSupporters).orElse(Collections.emptyList());
        this.warmupDriverSupporters = Optional.ofNullable(warmupDriverSupporters).orElse(Collections.emptyList());
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

    @Override
    @BehaviorAnalyse
    public void resetWarmupDriver() throws HandlerException {
        try {
            doResetWarmupDriver();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetWarmupDriver() throws Exception {
        // 清理当前数据库中的预热驱动器支持快照。
        List<StringIdKey> warmupDriverKeys = warmupDriverSupportMaintainService.lookupAsList().stream()
                .map(WarmupDriverSupport::getKey).collect(Collectors.toList());
        warmupDriverSupportMaintainService.batchDelete(warmupDriverKeys);
        // 使用当前 Spring 容器中已注册的支持器重新生成支持快照。
        List<WarmupDriverSupport> warmupDriverSupports = warmupDriverSupporters.stream().map(
                supporter -> new WarmupDriverSupport(
                        new StringIdKey(supporter.provideType()),
                        supporter.provideLabel(),
                        supporter.provideDescription(),
                        supporter.provideExampleParam()
                )
        ).collect(Collectors.toList());
        warmupDriverSupportMaintainService.batchInsert(warmupDriverSupports);
    }
}
