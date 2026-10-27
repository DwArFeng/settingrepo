package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 预热驱动信息。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class WarmupDriverInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = -7890738167269360709L;

    private LongIdKey key;

    /**
     * 所属预热设置的主键。
     */
    private LongIdKey warmupSettingKey;

    /**
     * 是否启用。
     */
    private boolean enabled;

    /**
     * 驱动类型，取值由驱动提供器决定。
     */
    private String type;

    /**
     * 驱动参数，格式由 type 决定，由程序负责解析。
     */
    private String param;

    /**
     * 备注。
     */
    private String remark;

    public WarmupDriverInfo() {
    }

    public WarmupDriverInfo(
            LongIdKey key, LongIdKey warmupSettingKey, boolean enabled, String type, String param, String remark
    ) {
        this.key = key;
        this.warmupSettingKey = warmupSettingKey;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getWarmupSettingKey() {
        return warmupSettingKey;
    }

    public void setWarmupSettingKey(LongIdKey warmupSettingKey) {
        this.warmupSettingKey = warmupSettingKey;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WarmupDriverInfo{" +
                "key=" + key +
                ", warmupSettingKey=" + warmupSettingKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
