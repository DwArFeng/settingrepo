package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.bean.key.FastJsonIahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMek;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 国际化节点 Mek。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class FastJsonIahnNodeMek implements Bean {

    private static final long serialVersionUID = 1870329586215755973L;

    public static FastJsonIahnNodeMek of(IahnNodeMek iahnNodeMek) {
        if (Objects.isNull(iahnNodeMek)) {
            return null;
        } else {
            return new FastJsonIahnNodeMek(
                    FastJsonIahnNodeMekKey.of(iahnNodeMek.getKey()),
                    iahnNodeMek.getLabel(),
                    iahnNodeMek.getDefaultMessage(),
                    iahnNodeMek.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonIahnNodeMekKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "default_message", ordinal = 3)
    private String defaultMessage;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonIahnNodeMek() {
    }

    public FastJsonIahnNodeMek(FastJsonIahnNodeMekKey key, String label, String defaultMessage, String remark) {
        this.key = key;
        this.label = label;
        this.defaultMessage = defaultMessage;
        this.remark = remark;
    }

    public FastJsonIahnNodeMekKey getKey() {
        return key;
    }

    public void setKey(FastJsonIahnNodeMekKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonIahnNodeMek{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", defaultMessage='" + defaultMessage + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
