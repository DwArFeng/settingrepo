package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.sdk.util.ValidWarmupCacheType;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 预热设置。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class WebInputWarmupSetting implements Bean {

    private static final long serialVersionUID = -8222859359700421544L;

    public static WarmupSetting toStackBean(WebInputWarmupSetting webInputWarmupSetting) {
        if (Objects.isNull(webInputWarmupSetting)) {
            return null;
        } else {
            return new WarmupSetting(
                    WebInputLongIdKey.toStackBean(webInputWarmupSetting.getKey()),
                    webInputWarmupSetting.getType(),
                    webInputWarmupSetting.getTargetStringAlpha(),
                    webInputWarmupSetting.getTargetStringBravo(),
                    webInputWarmupSetting.getTargetStringCharlie(),
                    webInputWarmupSetting.getTargetStringDelta(),
                    webInputWarmupSetting.getTargetStringEcho(),
                    webInputWarmupSetting.getTargetStringArrayAlpha(),
                    webInputWarmupSetting.isEnabled(),
                    webInputWarmupSetting.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "type")
    @ValidWarmupCacheType
    private int type;

    @JSONField(name = "target_string_alpha")
    @NotNull
    @NotEmpty
    private String targetStringAlpha;

    @JSONField(name = "target_string_bravo")
    private String targetStringBravo;

    @JSONField(name = "target_string_charlie")
    private String targetStringCharlie;

    @JSONField(name = "target_string_delta")
    private String targetStringDelta;

    @JSONField(name = "target_string_echo")
    private String targetStringEcho;

    @JSONField(name = "target_string_array_alpha")
    @NotNull
    private String[] targetStringArrayAlpha;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputWarmupSetting() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
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
        return "WebInputWarmupSetting{" +
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
