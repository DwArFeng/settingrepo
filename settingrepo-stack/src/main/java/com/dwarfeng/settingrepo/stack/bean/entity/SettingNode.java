package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.Arrays;
import java.util.Date;

/**
 * 设置节点。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SettingNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = -7463247247008588989L;

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

    /**
     * 用于标识设置节点是否可以被访问。
     *
     * @since 2.0.0
     */
    private boolean reachable;

    /**
     * 用于记录设置节点的分类。
     *
     * @since 2.0.0
     */
    private String category;

    /**
     * 用于记录设置节点的参数。
     *
     * @since 2.0.0
     */
    private String[] args;

    public SettingNode() {
    }

    public SettingNode(
            StringIdKey key, int type, Date lastModifiedDate, String remark, boolean reachable, String category,
            String[] args
    ) {
        this.key = key;
        this.type = type;
        this.lastModifiedDate = lastModifiedDate;
        this.remark = remark;
        this.reachable = reachable;
        this.category = category;
        this.args = args;
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
        return "SettingNode{" +
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
