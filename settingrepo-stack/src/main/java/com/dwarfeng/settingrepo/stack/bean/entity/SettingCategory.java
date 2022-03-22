package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 设置类别。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SettingCategory implements Entity<StringIdKey> {

    private static final long serialVersionUID = -3983781748313933519L;

    private StringIdKey key;
    private String formatterUsing;
    private String formatterParam;
    private String remark;

    public SettingCategory() {
    }

    public SettingCategory(StringIdKey key, String formatterUsing, String formatterParam, String remark) {
        this.key = key;
        this.formatterUsing = formatterUsing;
        this.formatterParam = formatterParam;
        this.remark = remark;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
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
        return "SettingCategory{" +
                "key=" + key +
                ", formatterUsing='" + formatterUsing + '\'' +
                ", formatterParam='" + formatterParam + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
