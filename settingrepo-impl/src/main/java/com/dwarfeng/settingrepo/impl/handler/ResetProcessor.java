package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.PushHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
@Component
public class ResetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetProcessor.class);

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    private final PushHandler pushHandler;

    public ResetProcessor(
            FormatLocalCacheHandler formatLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.pushHandler = pushHandler;
    }

    public void resetFormat() throws HandlerException {
        formatLocalCacheHandler.clear();

        try {
            pushHandler.formatReset();
        } catch (Exception e) {
            LOGGER.warn("推送路由被重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
