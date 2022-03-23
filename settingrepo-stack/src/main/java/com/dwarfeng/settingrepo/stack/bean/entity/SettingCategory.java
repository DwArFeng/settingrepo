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

    private static final long serialVersionUID = -2037886562008953343L;

    private StringIdKey key;
    private String formatterType;
    private String formatterParam;
    private String remark;

    public SettingCategory() {
    }

    public SettingCategory(StringIdKey key, String formatterType, String formatterParam, String remark) {
        this.key = key;
        this.formatterType = formatterType;
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

    public String getFormatterType() {
        return formatterType;
    }

    public void setFormatterType(String formatterType) {
        this.formatterType = formatterType;
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
                ", formatterType='" + formatterType + '\'' +
                ", formatterParam='" + formatterParam + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
