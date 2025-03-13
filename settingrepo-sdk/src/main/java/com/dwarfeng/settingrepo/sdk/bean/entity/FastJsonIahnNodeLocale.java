package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.bean.key.FastJsonIahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeLocale;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 国际化节点地区。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class FastJsonIahnNodeLocale implements Bean {

    private static final long serialVersionUID = -6297777758958759768L;

    public static FastJsonIahnNodeLocale of(IahnNodeLocale iahnNodeLocale) {
        if (Objects.isNull(iahnNodeLocale)) {
            return null;
        } else {
            return new FastJsonIahnNodeLocale(
                    FastJsonIahnNodeLocaleKey.of(iahnNodeLocale.getKey()),
                    iahnNodeLocale.getLabel(),
                    iahnNodeLocale.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonIahnNodeLocaleKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonIahnNodeLocale() {
    }

    public FastJsonIahnNodeLocale(FastJsonIahnNodeLocaleKey key, String label, String remark) {
        this.key = key;
        this.label = label;
        this.remark = remark;
    }

    public FastJsonIahnNodeLocaleKey getKey() {
        return key;
    }

    public void setKey(FastJsonIahnNodeLocaleKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonIahnNodeLocale{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
