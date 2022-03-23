package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 设置节点移除信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SettingNodeRemoveInfo implements Dto {

    private static final long serialVersionUID = -3705101384792711477L;

    private String category;
    private String[] args;

    public SettingNodeRemoveInfo() {
    }

    public SettingNodeRemoveInfo(String category, String[] args) {
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
        return "SettingNodeRemoveInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
