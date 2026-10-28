package com.dwarfeng.settingrepo.impl.handler.wudriver;

import com.dwarfeng.settingrepo.sdk.handler.WarmupDriverProvider;
import com.dwarfeng.settingrepo.sdk.handler.driver.AbstractWarmupDriver;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.settingrepo.stack.exception.WarmupDriverException;
import com.dwarfeng.settingrepo.stack.handler.WarmupDriver;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定间隔预热驱动提供器。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Component
public class FixedDelayWarmupDriverProvider implements WarmupDriverProvider {

    public static final String SUPPORT_TYPE = "fixed_delay_driver";

    private final FixedDelayWarmupDriver fixedDelayWarmupDriver;

    @SuppressWarnings({"SpringJavaInjectionPointsAutowiringInspection", "RedundantSuppression"})
    public FixedDelayWarmupDriverProvider(FixedDelayWarmupDriver fixedDelayWarmupDriver) {
        this.fixedDelayWarmupDriver = fixedDelayWarmupDriver;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public WarmupDriver provide() {
        return fixedDelayWarmupDriver;
    }

    @Component
    public static class FixedDelayWarmupDriver extends AbstractWarmupDriver {

        private final ThreadPoolTaskScheduler scheduler;

        private final Lock lock = new ReentrantLock();
        private final Set<ScheduledFuture<?>> scheduledFutures = new HashSet<>();
        private final Set<FixedDelayProcessor> fixedDelayProcessors = new HashSet<>();

        public FixedDelayWarmupDriver(ThreadPoolTaskScheduler scheduler) {
            this.scheduler = scheduler;
        }

        @Override
        public void register(WarmupDriverInfo driverInfo) throws WarmupDriverException {
            lock.lock();
            try {
                LongIdKey warmupSettingKey = driverInfo.getWarmupSettingKey();
                long delay = Long.parseLong(driverInfo.getParam());
                FixedDelayProcessor fixedDelayProcessor = new FixedDelayProcessor(context, warmupSettingKey);
                ScheduledFuture<?> scheduledFuture =
                        scheduler.scheduleWithFixedDelay(fixedDelayProcessor, delay);
                fixedDelayProcessors.add(fixedDelayProcessor);
                scheduledFutures.add(scheduledFuture);
            } catch (Exception e) {
                throw new WarmupDriverException(e);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void unregisterAll() {
            lock.lock();
            try {
                for (ScheduledFuture<?> scheduledFuture : scheduledFutures) {
                    scheduledFuture.cancel(true);
                }
                for (FixedDelayProcessor fixedDelayProcessor : fixedDelayProcessors) {
                    fixedDelayProcessor.shutdown();
                }
                scheduledFutures.clear();
                fixedDelayProcessors.clear();
            } finally {
                lock.unlock();
            }
        }

        @Override
        public String toString() {
            return "FixedDelayWarmupDriver{" +
                    "context=" + context +
                    '}';
        }
    }

    private static class FixedDelayProcessor implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(FixedDelayProcessor.class);

        private final WarmupDriver.Context context;
        private final LongIdKey warmupSettingKey;

        private final Lock lock = new ReentrantLock();
        private boolean runningFlag = true;

        private FixedDelayProcessor(WarmupDriver.Context context, LongIdKey warmupSettingKey) {
            this.context = context;
            this.warmupSettingKey = warmupSettingKey;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                if (!runningFlag) {
                    return;
                }
                LOGGER.debug("计划时间已到达, fixed delay 驱动器驱动预热设置 {} 执行预热动作...", warmupSettingKey);
                context.warmup(warmupSettingKey);
            } catch (Exception e) {
                LOGGER.warn("预热设置 {} 执行预热动作时出现异常, 放弃本次预热", warmupSettingKey, e);
            } finally {
                lock.unlock();
            }
        }

        void shutdown() {
            lock.lock();
            try {
                runningFlag = false;
            } finally {
                lock.unlock();
            }
        }
    }
}
