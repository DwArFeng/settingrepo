package com.dwarfeng.settingrepo.impl.handler.wudriver;

import com.dwarfeng.settingrepo.sdk.handler.WarmupDriverSupporter;
import org.springframework.stereotype.Component;

/**
 * Cron 预热驱动器支持器。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Component
public class CronWarmupDriverSupporter implements WarmupDriverSupporter {

    public static final String SUPPORT_TYPE = "cron_driver";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "Cron 预热驱动器";
    }

    @Override
    public String provideDescription() {
        return "根据指定的 Cron 表达式定时触发一次缓存预热。";
    }

    @Override
    public String provideExampleParam() {
        return "0 0/5 * * * *";
    }
}
