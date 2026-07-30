package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.basic.stack.bean.key.LongIdKey;

import java.io.Serial;
import java.util.Arrays;

/**
 * 导航节点索引格式化信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeFormatIndexInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -7777444226116241262L;

    /**
     * 设置类别。
     */
    private String category;

    /**
     * 参数数组。
     */
    private String[] args;

    /**
     * 父条目主键（null 表示格式化根下的所有直接子条目）。
     */
    private LongIdKey parentItemKey;

    public NavigationNodeFormatIndexInfo() {
    }

    public NavigationNodeFormatIndexInfo(String category, String[] args, LongIdKey parentItemKey) {
        this.category = category;
        this.args = args;
        this.parentItemKey = parentItemKey;
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

    @Override
    public String toString() {
        return "NavigationNodeFormatIndexInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", parentItemKey=" + parentItemKey +
                '}';
    }
}
