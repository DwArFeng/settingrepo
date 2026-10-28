package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.settingrepo.stack.handler.WarmupDriver;
import com.dwarfeng.settingrepo.stack.handler.WarmupDriverHandler;
import com.dwarfeng.settingrepo.stack.handler.WarmupLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.service.WarmupDriverInfoMaintainService;
import com.dwarfeng.settingrepo.stack.service.WarmupSettingMaintainService;
import com.dwarfeng.settingrepo.stack.struct.WarmupLocalCache;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class WarmupLocalCacheHandlerImpl implements WarmupLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, WarmupLocalCache> handler;

    public WarmupLocalCacheHandlerImpl(WarmupLocalCacheFetcher warmupLocalCacheFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(warmupLocalCacheFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public WarmupLocalCache get(LongIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(LongIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() {
        handler.clear();
    }

    @Component
    public static class WarmupLocalCacheFetcher implements Fetcher<LongIdKey, WarmupLocalCache> {

        private final WarmupSettingMaintainService warmupSettingMaintainService;
        private final WarmupDriverInfoMaintainService warmupDriverInfoMaintainService;
        private final WarmupDriverHandler warmupDriverHandler;

        public WarmupLocalCacheFetcher(
                WarmupSettingMaintainService warmupSettingMaintainService,
                WarmupDriverInfoMaintainService warmupDriverInfoMaintainService,
                WarmupDriverHandler warmupDriverHandler
        ) {
            this.warmupSettingMaintainService = warmupSettingMaintainService;
            this.warmupDriverInfoMaintainService = warmupDriverInfoMaintainService;
            this.warmupDriverHandler = warmupDriverHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            WarmupSetting warmupSetting = warmupSettingMaintainService.getIfExists(key);
            if (Objects.isNull(warmupSetting)) {
                return false;
            }
            return warmupSetting.isEnabled();
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public WarmupLocalCache fetch(LongIdKey key) throws Exception {
            WarmupSetting warmupSetting = warmupSettingMaintainService.get(key);
            List<WarmupDriverInfo> driverInfos = warmupDriverInfoMaintainService.lookupAsList(
                    WarmupDriverInfoMaintainService.CHILD_FOR_WARMUP_SETTING_ENABLED, new Object[]{key}
            );

            Map<WarmupDriverInfo, WarmupDriver> driverMap = new HashMap<>();
            for (WarmupDriverInfo driverInfo : driverInfos) {
                driverMap.put(driverInfo, warmupDriverHandler.find(driverInfo.getType()));
            }
            return new WarmupLocalCache(warmupSetting, driverMap);
        }
    }
}
