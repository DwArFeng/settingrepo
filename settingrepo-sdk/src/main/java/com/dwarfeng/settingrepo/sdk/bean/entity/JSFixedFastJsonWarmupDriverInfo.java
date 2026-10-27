package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 预热驱动信息。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class JSFixedFastJsonWarmupDriverInfo implements Bean {

    private static final long serialVersionUID = -7255226233334771199L;

    public static JSFixedFastJsonWarmupDriverInfo of(WarmupDriverInfo warmupDriverInfo) {
        if (Objects.isNull(warmupDriverInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonWarmupDriverInfo(
                    JSFixedFastJsonLongIdKey.of(warmupDriverInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(warmupDriverInfo.getWarmupSettingKey()),
                    warmupDriverInfo.isEnabled(),
                    warmupDriverInfo.getType(),
                    warmupDriverInfo.getParam(),
                    warmupDriverInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "warmup_setting_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey warmupSettingKey;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "type", ordinal = 4)
    private String type;

    @JSONField(name = "param", ordinal = 5)
    private String param;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public JSFixedFastJsonWarmupDriverInfo() {
    }

    public JSFixedFastJsonWarmupDriverInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey warmupSettingKey, boolean enabled, String type,
            String param, String remark
    ) {
        this.key = key;
        this.warmupSettingKey = warmupSettingKey;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getWarmupSettingKey() {
        return warmupSettingKey;
    }

    public void setWarmupSettingKey(JSFixedFastJsonLongIdKey warmupSettingKey) {
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
        return "JSFixedFastJsonWarmupDriverInfo{" +
                "key=" + key +
                ", warmupSettingKey=" + warmupSettingKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
