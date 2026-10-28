package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.settingrepo.stack.exception.WarmupDriverException;
import com.dwarfeng.settingrepo.stack.handler.WarmupDriveHandler;
import com.dwarfeng.settingrepo.stack.handler.WarmupDriver;
import com.dwarfeng.settingrepo.stack.handler.WarmupLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.service.WarmupSettingMaintainService;
import com.dwarfeng.settingrepo.stack.struct.WarmupLocalCache;
import com.dwarfeng.subgrade.impl.handler.GeneralStartableHandler;
import com.dwarfeng.subgrade.impl.handler.Worker;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WarmupDriveHandlerImpl implements WarmupDriveHandler {

    private final GeneralStartableHandler handler;

    public WarmupDriveHandlerImpl(WarmupDriveWorker warmupDriveWorker) {
        handler = new GeneralStartableHandler(warmupDriveWorker);
    }

    @BehaviorAnalyse
    @Override
    public boolean isStarted() {
        return handler.isStarted();
    }

    @BehaviorAnalyse
    @Override
    public void start() throws HandlerException {
        handler.start();
    }

    @BehaviorAnalyse
    @Override
    public void stop() throws HandlerException {
        handler.stop();
    }

    @Component
    public static class WarmupDriveWorker implements Worker {

        private static final Logger LOGGER = LoggerFactory.getLogger(WarmupDriveWorker.class);

        private final WarmupSettingMaintainService warmupSettingMaintainService;

        private final WarmupLocalCacheHandler warmupLocalCacheHandler;

        private final Set<WarmupDriver> usedDrivers = new HashSet<>();

        public WarmupDriveWorker(
                WarmupSettingMaintainService warmupSettingMaintainService,
                WarmupLocalCacheHandler warmupLocalCacheHandler
        ) {
            this.warmupSettingMaintainService = warmupSettingMaintainService;
            this.warmupLocalCacheHandler = warmupLocalCacheHandler;
        }

        @Override
        public void work() throws Exception {
            // 记录日志。
            LOGGER.info("预热驱动器开始工作...");

            List<WarmupSetting> warmupSettings = warmupSettingMaintainService.lookupAsList(
                    WarmupSettingMaintainService.ENABLED, new Object[]{}
            );
            // 注册所有驱动成功标志。
            boolean successFlag = true;
            // 获取所有驱动器信息。
            for (WarmupSetting warmupSetting : warmupSettings) {
                WarmupLocalCache warmupLocalCache = warmupLocalCacheHandler.get(warmupSetting.getKey());
                if (Objects.isNull(warmupLocalCache)) {
                    throw new WarmupDriverException("无法在本地缓存中找到有效的预热上下文: " + warmupSetting.getKey());
                }
                if (!registerDrivers(warmupLocalCache)) {
                    successFlag = false;
                }
            }
            if (successFlag) {
                LOGGER.info("所有预热驱动信息注册成功");
            } else {
                LOGGER.warn("至少一条预热驱动信息注册失败，请查看警报日志以了解详细原因");
            }
        }

        private boolean registerDrivers(WarmupLocalCache warmupLocalCache) {
            boolean successFlag = true;
            Map<WarmupDriverInfo, WarmupDriver> driverMap = warmupLocalCache.getDriverMap();
            for (Map.Entry<WarmupDriverInfo, WarmupDriver> entry : driverMap.entrySet()) {
                WarmupDriverInfo warmupDriverInfo = entry.getKey();
                WarmupDriver warmupDriver = entry.getValue();
                try {
                    warmupDriver.register(warmupDriverInfo);
                    usedDrivers.add(warmupDriver);
                } catch (Exception e) {
                    successFlag = false;
                    LOGGER.warn("预热驱动信息 {} 注册失败，将忽略此条注册信息", warmupDriverInfo, e);
                }
            }
            return successFlag;
        }

        @Override
        public void rest() throws Exception {
            // 记录日志。
            LOGGER.info("预热驱动器停止工作...");

            for (Iterator<WarmupDriver> iterator = usedDrivers.iterator(); iterator.hasNext(); ) {
                WarmupDriver warmupDriver = iterator.next();
                warmupDriver.unregisterAll();
                iterator.remove();
            }
        }
    }
}
