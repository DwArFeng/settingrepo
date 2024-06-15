package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 设置节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonSettingNode implements Bean {

    private static final long serialVersionUID = -4546049175785067703L;

    public static FastJsonSettingNode of(SettingNode settingNode) {
        if (Objects.isNull(settingNode)) {
            return null;
        } else {
            return new FastJsonSettingNode(
                    FastJsonStringIdKey.of(settingNode.getKey()),
                    settingNode.getType(),
                    settingNode.getLastModifiedDate(),
                    settingNode.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "type", ordinal = 2)
    private int type;

    @JSONField(name = "last_modified_date", ordinal = 3)
    private Date lastModifiedDate;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonSettingNode() {
    }

    public FastJsonSettingNode(FastJsonStringIdKey key, int type, Date lastModifiedDate, String remark) {
        this.key = key;
        this.type = type;
        this.lastModifiedDate = lastModifiedDate;
        this.remark = remark;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
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
                ", type=" + type +
                ", lastModifiedDate=" + lastModifiedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
