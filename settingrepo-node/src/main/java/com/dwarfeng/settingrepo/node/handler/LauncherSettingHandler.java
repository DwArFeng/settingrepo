package com.dwarfeng.settingrepo.node.handler;

import com.dwarfeng.subgrade.stack.handler.Handler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LauncherSettingHandler implements Handler {

    @Value("${launcher.reset_formatter_support}")
    private boolean resetFormatterSupport;

    public boolean isResetFormatterSupport() {
        return resetFormatterSupport;
    }
}
