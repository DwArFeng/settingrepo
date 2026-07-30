package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 导航节点大小信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeSizeInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 523778539369044584L;

    /**
     * 设置类别。
     */
    private String category;

    /**
     * 参数数组。
     */
    private String[] args;

    public NavigationNodeSizeInfo() {
    }

    public NavigationNodeSizeInfo(String category, String[] args) {
        this.category = category;
        this.args = args;
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
        return "NavigationNodeSizeInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
