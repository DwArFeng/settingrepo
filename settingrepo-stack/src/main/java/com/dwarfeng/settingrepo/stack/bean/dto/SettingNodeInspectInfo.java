package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 设置节点查看信息。
 *
 * @author DwArFeng
 * @since 2.0
 */
public class SettingNodeInspectInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 3154664881728328067L;

    private String category;
    private String[] args;

    public SettingNodeInspectInfo() {
    }

    public SettingNodeInspectInfo(String category, String[] args) {
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
        return "SettingNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
