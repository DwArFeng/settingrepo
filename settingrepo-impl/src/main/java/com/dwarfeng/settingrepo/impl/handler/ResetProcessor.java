package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.PushHandler;
import com.dwarfeng.settingrepo.stack.handler.WarmupHandler;
import com.dwarfeng.settingrepo.stack.handler.WarmupLocalCacheHandler;
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

    private final WarmupHandler warmupHandler;
    private final WarmupLocalCacheHandler warmupLocalCacheHandler;

    private final PushHandler pushHandler;

    public ResetProcessor(
            FormatLocalCacheHandler formatLocalCacheHandler,
            WarmupHandler warmupHandler,
            WarmupLocalCacheHandler warmupLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.warmupHandler = warmupHandler;
        this.warmupLocalCacheHandler = warmupLocalCacheHandler;
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

    /**
     * 重置预热。
     *
     * <p>
     * 预热调度由集群唯一门面持有。重置时先停止门面以取消当前调度，再清空驱动配置本地缓存，
     * 最后按原上线状态恢复，使下一次加载读取到最新配置。
     *
     * @throws HandlerException 处理器异常。
     */
    public void resetWarmup() throws HandlerException {
        boolean warmupStarted = warmupHandler.isStarted();
        warmupHandler.stop();
        warmupLocalCacheHandler.clear();
        if (warmupStarted) {
            warmupHandler.start();
        }

        try {
            pushHandler.warmupReset();
        } catch (Exception e) {
            LOGGER.warn("推送预热被重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
