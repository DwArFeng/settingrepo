package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 设置类别。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonSettingCategory implements Bean {

    private static final long serialVersionUID = 3742608095639169224L;

    public static FastJsonSettingCategory of(SettingCategory settingCategory) {
        if (Objects.isNull(settingCategory)) {
            return null;
        } else {
            return new FastJsonSettingCategory(
                    FastJsonStringIdKey.of(settingCategory.getKey()),
                    settingCategory.getFormatterUsing(), settingCategory.getFormatterParam(),
                    settingCategory.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "formatter_using", ordinal = 2)
    private String formatterUsing;

    @JSONField(name = "formatter_param", ordinal = 3)
    private String formatterParam;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonSettingCategory() {
    }

    public FastJsonSettingCategory(
            FastJsonStringIdKey key, String formatterUsing, String formatterParam, String remark
    ) {
        this.key = key;
        this.formatterUsing = formatterUsing;
        this.formatterParam = formatterParam;
        this.remark = remark;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getFormatterUsing() {
        return formatterUsing;
    }

    public void setFormatterUsing(String formatterUsing) {
        this.formatterUsing = formatterUsing;
    }

    public String getFormatterParam() {
        return formatterParam;
    }

    public void setFormatterParam(String formatterParam) {
        this.formatterParam = formatterParam;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonSettingCategory{" +
                "key=" + key +
                ", formatterUsing='" + formatterUsing + '\'' +
                ", formatterParam='" + formatterParam + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
