package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 预热驱动信息。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class WebInputWarmupDriverInfo implements Bean {

    private static final long serialVersionUID = -7938819614526137420L;

    public static WarmupDriverInfo toStackBean(WebInputWarmupDriverInfo webInputWarmupDriverInfo) {
        if (Objects.isNull(webInputWarmupDriverInfo)) {
            return null;
        } else {
            return new WarmupDriverInfo(
                    WebInputLongIdKey.toStackBean(webInputWarmupDriverInfo.getKey()),
                    WebInputLongIdKey.toStackBean(webInputWarmupDriverInfo.getWarmupSettingKey()),
                    webInputWarmupDriverInfo.isEnabled(),
                    webInputWarmupDriverInfo.getType(),
                    webInputWarmupDriverInfo.getParam(),
                    webInputWarmupDriverInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "warmup_setting_key")
    @Valid
    private WebInputLongIdKey warmupSettingKey;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "type")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_TYPE)
    private String type;

    @JSONField(name = "param")
    private String param;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputWarmupDriverInfo() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getWarmupSettingKey() {
        return warmupSettingKey;
    }

    public void setWarmupSettingKey(WebInputLongIdKey warmupSettingKey) {
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
        return "WebInputWarmupDriverInfo{" +
                "key=" + key +
                ", warmupSettingKey=" + warmupSettingKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
