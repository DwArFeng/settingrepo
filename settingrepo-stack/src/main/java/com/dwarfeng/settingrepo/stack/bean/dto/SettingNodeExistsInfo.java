package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 设置节点存在信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class SettingNodeExistsInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -2206343874880663095L;

    private String category;
    private String[] args;

    public SettingNodeExistsInfo() {
    }

    public SettingNodeExistsInfo(String category, String[] args) {
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
        return "SettingNodeExistsInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
