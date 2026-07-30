package com.dwarfeng.settingrepo.node.all.he.handler;

import com.dwarfeng.subgrade.basic.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${com.dwarfeng.settingrepo.launcher.reset_formatter_support}")
    private boolean resetFormatterSupport;

    @Value("${com.dwarfeng.settingrepo.launcher.start_reset_delay}")
    private long startResetDelay;

    public boolean isResetFormatterSupport() {
        return resetFormatterSupport;
    }

    public long getStartResetDelay() {
        return startResetDelay;
    }
}
