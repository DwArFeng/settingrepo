package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * FastJson 设置节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FastJsonSettingNode implements Bean {

    private static final long serialVersionUID = 8600379519907048459L;

    public static FastJsonSettingNode of(SettingNode settingNode) {
        if (Objects.isNull(settingNode)) {
            return null;
        } else {
            return new FastJsonSettingNode(
                    FastJsonStringIdKey.of(settingNode.getKey()),
                    settingNode.getType(),
                    settingNode.getLastModifiedDate(),
                    settingNode.getRemark(),
                    settingNode.isReachable(),
                    settingNode.getCategory(),
                    settingNode.getArgs()
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

    @JSONField(name = "reachable", ordinal = 5)
    private boolean reachable;

    @JSONField(name = "category", ordinal = 6)
    private String category;

    @JSONField(name = "args", ordinal = 7)
    private String[] args;

    public FastJsonSettingNode() {
    }

    public FastJsonSettingNode(
            FastJsonStringIdKey key, int type, Date lastModifiedDate, String remark, boolean reachable,
            String category, String[] args
    ) {
        this.key = key;
        this.type = type;
        this.lastModifiedDate = lastModifiedDate;
        this.remark = remark;
        this.reachable = reachable;
        this.category = category;
        this.args = args;
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

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "FastJsonSettingNode{" +
                "key=" + key +
                ", type=" + type +
                ", lastModifiedDate=" + lastModifiedDate +
                ", remark='" + remark + '\'' +
                ", reachable=" + reachable +
                ", category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
