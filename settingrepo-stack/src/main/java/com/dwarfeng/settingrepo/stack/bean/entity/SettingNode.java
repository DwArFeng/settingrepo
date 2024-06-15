package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.Date;

/**
 * 设置节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SettingNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = -8109769646978028850L;

    private StringIdKey key;

    /**
     * 设置节点的类型。
     *
     * <p>
     * int 枚举，可能的值为：
     * <ul>
     *     <li>文本</li>
     *     <li>长文本</li>
     *     <li>图片</li>
     *     <li>图片集</li>
     * </ul>
     * 详细值参考 sdk 模块的常量工具类。
     *
     * @since 2.0.0
     */
    private int type;

    /**
     * 节点最后一次修改的日期。
     *
     * @since 2.0.0
     */
    private Date lastModifiedDate;

    private String remark;

    public SettingNode() {
    }

    public SettingNode(StringIdKey key, int type, Date lastModifiedDate, String remark) {
        this.key = key;
        this.type = type;
        this.lastModifiedDate = lastModifiedDate;
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
        return "SettingNode{" +
                "key=" + key +
                ", type=" + type +
                ", lastModifiedDate=" + lastModifiedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
