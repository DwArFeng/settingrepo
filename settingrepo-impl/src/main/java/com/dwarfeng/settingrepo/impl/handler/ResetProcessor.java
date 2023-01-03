package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
@Component
class ResetProcessor {

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    public ResetProcessor(FormatLocalCacheHandler formatLocalCacheHandler) {
        this.formatLocalCacheHandler = formatLocalCacheHandler;
    }

    public void resetFormat() throws HandlerException {
        formatLocalCacheHandler.clear();
    }
}
