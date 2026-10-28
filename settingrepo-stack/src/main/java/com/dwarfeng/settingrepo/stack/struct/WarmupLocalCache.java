package com.dwarfeng.settingrepo.stack.struct;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.settingrepo.stack.handler.WarmupDriver;

import java.util.Map;

/**
 * 预热本地缓存。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public final class WarmupLocalCache {

    private final WarmupSetting warmupSetting;
    private final Map<WarmupDriverInfo, WarmupDriver> driverMap;

    public WarmupLocalCache(WarmupSetting warmupSetting, Map<WarmupDriverInfo, WarmupDriver> driverMap) {
        this.warmupSetting = warmupSetting;
        this.driverMap = driverMap;
    }

    public WarmupSetting getWarmupSetting() {
        return warmupSetting;
    }

    public Map<WarmupDriverInfo, WarmupDriver> getDriverMap() {
        return driverMap;
    }

    @Override
    public String toString() {
        return "WarmupLocalCache{" +
                "warmupSetting=" + warmupSetting +
                ", driverMap=" + driverMap +
                '}';
    }
}
