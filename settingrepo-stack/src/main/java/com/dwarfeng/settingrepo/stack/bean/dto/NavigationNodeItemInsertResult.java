package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 导航节点条目插入结果。
 *
 * @author DwArFeng
 * @since 2.4.0
 */
public class NavigationNodeItemInsertResult implements Dto {

    private static final long serialVersionUID = 1730366344237288212L;

    /**
     * 设置类别。
     */
    private String category;

    /**
     * 参数数组。
     */
    private String[] args;

    /**
     * 生成的条目主键。
     */
    private LongIdKey itemKey;

    public NavigationNodeItemInsertResult() {
    }

    public NavigationNodeItemInsertResult(String category, String[] args, LongIdKey itemKey) {
        this.category = category;
        this.args = args;
        this.itemKey = itemKey;
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

    public LongIdKey getItemKey() {
        return itemKey;
    }

    public void setItemKey(LongIdKey itemKey) {
        this.itemKey = itemKey;
    }

    @Override
    public String toString() {
        return "NavigationNodeItemInsertResult{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", itemKey=" + itemKey +
                '}';
    }
}
