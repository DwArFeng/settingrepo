package com.dwarfeng.settingrepo.impl.handler.wudriver;

import com.dwarfeng.settingrepo.sdk.handler.WarmupDriverSupporter;
import org.springframework.stereotype.Component;

/**
 * 固定频率预热驱动器支持器。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Component
public class FixedRateWarmupDriverSupporter implements WarmupDriverSupporter {

    public static final String SUPPORT_TYPE = "fixed_rate_driver";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "固定频率预热驱动器";
    }

    @Override
    public String provideDescription() {
        return "按照指定间隔定时预热，执行延迟时仍以原计划时间进行后续调度。";
    }

    @Override
    public String provideExampleParam() {
        return "60000";
    }
}
