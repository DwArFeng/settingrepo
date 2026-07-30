package com.dwarfeng.settingrepo.impl.handler.resetter;

import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessageKey;
import com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessages;
import com.dwarfeng.settingrepo.sdk.handler.resetter.AbstractResetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.concurrent.ScheduledFuture;

/**
 * 固定间隔的重置器。
 *
 * @author DwArFeng
 * @since 1.1.1
 */
@Component
public class FixedRateResetter extends AbstractResetter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FixedRateResetter.class);

    private final ThreadPoolTaskScheduler scheduler;

    @Value("${com.dwarfeng.settingrepo.resetter.fixed_rate.rate}")
    private long rate;

    private final ResetTask resetTask = new ResetTask();

    private ScheduledFuture<?> resetTaskFuture;

    public FixedRateResetter(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void doStart() {
        resetTaskFuture = scheduler.scheduleAtFixedRate(resetTask, Duration.ofMillis(rate));
    }

    @Override
    protected void doStop() {
        resetTaskFuture.cancel(true);
    }

    @Override
    public String toString() {
        return "FixedRateResetter{" +
                "rate=" + rate +
                '}';
    }

    private class ResetTask implements Runnable {

        @Override
        public void run() {
            try {
                LOGGER.info(ImplMessages.message(ImplMessageKey.LOG_RESET_SCHEDULE_TRIGGERED));
                context.resetFormat();
            } catch (Exception e) {
                String message = "重置器 " + FixedRateResetter.this +
                        " 执行重置调度时发生异常, 格式化将不会重置, 异常信息如下: ";
                LOGGER.warn(message, e);
            }
        }
    }
}
