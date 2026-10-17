package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 国际化节点缓存清理信息。
 *
 * @author DwArFeng
 * @since 3.0.4
 */
public class IahnNodeClearCacheInfo implements Dto {

    private static final long serialVersionUID = 9137208317883834394L;

    private String category;
    private String[] args;

    public IahnNodeClearCacheInfo() {
    }

    public IahnNodeClearCacheInfo(String category, String[] args) {
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
        return "IahnNodeClearCacheInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
