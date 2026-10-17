package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 导航节点缓存清理信息。
 *
 * @author DwArFeng
 * @since 3.0.4
 */
public class NavigationNodeClearCacheInfo implements Dto {

    private static final long serialVersionUID = -7904166267580749012L;

    private String category;
    private String[] args;

    public NavigationNodeClearCacheInfo() {
    }

    public NavigationNodeClearCacheInfo(String category, String[] args) {
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
        return "NavigationNodeClearCacheInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
