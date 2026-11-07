package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Arrays;
import java.util.Objects;

/**
 * JSFixed FastJson 预热设置。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class JSFixedFastJsonWarmupSetting implements Bean {

    private static final long serialVersionUID = -6139614992679832203L;

    public static JSFixedFastJsonWarmupSetting of(WarmupSetting warmupSetting) {
        if (Objects.isNull(warmupSetting)) {
            return null;
        } else {
            return new JSFixedFastJsonWarmupSetting(
                    JSFixedFastJsonLongIdKey.of(warmupSetting.getKey()),
                    warmupSetting.getType(),
                    warmupSetting.getTargetStringAlpha(),
                    warmupSetting.getTargetStringBravo(),
                    warmupSetting.getTargetStringCharlie(),
                    warmupSetting.getTargetStringDelta(),
                    warmupSetting.getTargetStringEcho(),
                    warmupSetting.getTargetStringArrayAlpha(),
                    warmupSetting.isEnabled(),
                    warmupSetting.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "type", ordinal = 2)
    private int type;

    @JSONField(name = "target_string_alpha", ordinal = 3)
    private String targetStringAlpha;

    @JSONField(name = "target_string_bravo", ordinal = 4)
    private String targetStringBravo;

    @JSONField(name = "target_string_charlie", ordinal = 5)
    private String targetStringCharlie;

    @JSONField(name = "target_string_delta", ordinal = 6)
    private String targetStringDelta;

    @JSONField(name = "target_string_echo", ordinal = 7)
    private String targetStringEcho;

    @JSONField(name = "target_string_array_alpha", ordinal = 8)
    private String[] targetStringArrayAlpha;

    @JSONField(name = "enabled", ordinal = 9)
    private boolean enabled;

    @JSONField(name = "remark", ordinal = 10)
    private String remark;

    public JSFixedFastJsonWarmupSetting() {
    }

    public JSFixedFastJsonWarmupSetting(
            JSFixedFastJsonLongIdKey key, int type, String targetStringAlpha,
            String targetStringBravo, String targetStringCharlie, String targetStringDelta, String targetStringEcho,
            String[] targetStringArrayAlpha, boolean enabled, String remark
    ) {
        this.key = key;
        this.type = type;
        this.targetStringAlpha = targetStringAlpha;
        this.targetStringBravo = targetStringBravo;
        this.targetStringCharlie = targetStringCharlie;
        this.targetStringDelta = targetStringDelta;
        this.targetStringEcho = targetStringEcho;
        this.targetStringArrayAlpha = targetStringArrayAlpha;
        this.enabled = enabled;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTargetStringAlpha() {
        return targetStringAlpha;
    }

    public void setTargetStringAlpha(String targetStringAlpha) {
        this.targetStringAlpha = targetStringAlpha;
    }

    public String getTargetStringBravo() {
        return targetStringBravo;
    }

    public void setTargetStringBravo(String targetStringBravo) {
        this.targetStringBravo = targetStringBravo;
    }

    public String getTargetStringCharlie() {
        return targetStringCharlie;
    }

    public void setTargetStringCharlie(String targetStringCharlie) {
        this.targetStringCharlie = targetStringCharlie;
    }

    public String getTargetStringDelta() {
        return targetStringDelta;
    }

    public void setTargetStringDelta(String targetStringDelta) {
        this.targetStringDelta = targetStringDelta;
    }

    public String getTargetStringEcho() {
        return targetStringEcho;
    }

    public void setTargetStringEcho(String targetStringEcho) {
        this.targetStringEcho = targetStringEcho;
    }

    public String[] getTargetStringArrayAlpha() {
        return targetStringArrayAlpha;
    }

    public void setTargetStringArrayAlpha(String[] targetStringArrayAlpha) {
        this.targetStringArrayAlpha = targetStringArrayAlpha;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonWarmupSetting{" +
                "key=" + key +
                ", type=" + type +
                ", targetStringAlpha='" + targetStringAlpha + '\'' +
                ", targetStringBravo='" + targetStringBravo + '\'' +
                ", targetStringCharlie='" + targetStringCharlie + '\'' +
                ", targetStringDelta='" + targetStringDelta + '\'' +
                ", targetStringEcho='" + targetStringEcho + '\'' +
                ", targetStringArrayAlpha=" + Arrays.toString(targetStringArrayAlpha) +
                ", enabled=" + enabled +
                ", remark='" + remark + '\'' +
                '}';
    }
}
