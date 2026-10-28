package com.dwarfeng.settingrepo.impl.handler.wudriver;

import com.dwarfeng.settingrepo.sdk.handler.WarmupDriverSupporter;
import org.springframework.stereotype.Component;

/**
 * 固定间隔预热驱动器支持器。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Component
public class FixedDelayWarmupDriverSupporter implements WarmupDriverSupporter {

    public static final String SUPPORT_TYPE = "fixed_delay_driver";

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {
        return "固定间隔预热驱动器";
    }

    @Override
    public String provideDescription() {
        return "在上一次预热完成后按照指定间隔安排下一次预热。";
    }

    @Override
    public String provideExampleParam() {
        return "60000";
    }
}
