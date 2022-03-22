package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 设置节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonSettingNode implements Bean {

    private static final long serialVersionUID = -8440846072414473223L;

    public static FastJsonSettingNode of(SettingNode settingNode) {
        if (Objects.isNull(settingNode)) {
            return null;
        } else {
            return new FastJsonSettingNode(
                    FastJsonStringIdKey.of(settingNode.getKey()),
                    settingNode.getValue(), settingNode.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonSettingNode() {
    }

    public FastJsonSettingNode(FastJsonStringIdKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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
        return "FastJsonSettingNode{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
