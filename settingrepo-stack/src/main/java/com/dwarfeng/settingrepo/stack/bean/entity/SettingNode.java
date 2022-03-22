package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 设置节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SettingNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = -2052258912837334808L;

    private StringIdKey key;
    private String value;
    private String remark;

    public SettingNode() {
    }

    public SettingNode(StringIdKey key, String value, String remark) {
        this.key = key;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SettingNode{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
