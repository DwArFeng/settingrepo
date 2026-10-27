package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 预热驱动信息。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class FastJsonWarmupDriverInfo implements Bean {

    private static final long serialVersionUID = 5958087885291541930L;

    public static FastJsonWarmupDriverInfo of(WarmupDriverInfo warmupDriverInfo) {
        if (Objects.isNull(warmupDriverInfo)) {
            return null;
        } else {
            return new FastJsonWarmupDriverInfo(
                    FastJsonLongIdKey.of(warmupDriverInfo.getKey()),
                    FastJsonLongIdKey.of(warmupDriverInfo.getWarmupSettingKey()),
                    warmupDriverInfo.isEnabled(),
                    warmupDriverInfo.getType(),
                    warmupDriverInfo.getParam(),
                    warmupDriverInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "warmup_setting_key", ordinal = 2)
    private FastJsonLongIdKey warmupSettingKey;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "type", ordinal = 4)
    private String type;

    @JSONField(name = "param", ordinal = 5)
    private String param;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public FastJsonWarmupDriverInfo() {
    }

    public FastJsonWarmupDriverInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey warmupSettingKey, boolean enabled, String type, String param,
            String remark
    ) {
        this.key = key;
        this.warmupSettingKey = warmupSettingKey;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getWarmupSettingKey() {
        return warmupSettingKey;
    }

    public void setWarmupSettingKey(FastJsonLongIdKey warmupSettingKey) {
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
        return "FastJsonWarmupDriverInfo{" +
                "key=" + key +
                ", warmupSettingKey=" + warmupSettingKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
