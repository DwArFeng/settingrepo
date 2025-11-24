package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 导航节点条目插入信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeItemInsertInfo implements Dto {

    private static final long serialVersionUID = -2194411145923658721L;

    /**
     * 设置类别。
     */
    private String category;

    /**
     * 参数数组。
     */
    private String[] args;

    /**
     * 父条目主键（null 表示根）。
     */
    private LongIdKey parentItemKey;

    /**
     * 索引值。
     */
    private int index;

    /**
     * 名称。
     */
    private String name;

    /**
     * 内容。
     */
    private String content;

    /**
     * 备注。
     */
    private String remark;

    public NavigationNodeItemInsertInfo() {
    }

    public NavigationNodeItemInsertInfo(
            String category, String[] args, LongIdKey parentItemKey, int index, String name, String content,
            String remark
    ) {
        this.category = category;
        this.args = args;
        this.parentItemKey = parentItemKey;
        this.index = index;
        this.name = name;
        this.content = content;
        this.remark = remark;
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

    public LongIdKey getParentItemKey() {
        return parentItemKey;
    }

    public void setParentItemKey(LongIdKey parentItemKey) {
        this.parentItemKey = parentItemKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "NavigationNodeItemInsertInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", parentItemKey=" + parentItemKey +
                ", index=" + index +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
