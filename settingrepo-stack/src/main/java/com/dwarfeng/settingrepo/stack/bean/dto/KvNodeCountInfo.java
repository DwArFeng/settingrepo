package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 键值对节点数量信息。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeCountInfo implements Dto {

    private static final long serialVersionUID = 6357547871421988830L;

    private String category;
    private String[] args;

    public KvNodeCountInfo() {
    }

    public KvNodeCountInfo(String category, String[] args) {
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
        return "KvNodeCountInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
