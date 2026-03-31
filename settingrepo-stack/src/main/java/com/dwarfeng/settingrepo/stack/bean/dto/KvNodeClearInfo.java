package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 键值对节点清空信息。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeClearInfo implements Dto {

    private static final long serialVersionUID = 8189851561063454033L;

    private String category;
    private String[] args;

    public KvNodeClearInfo() {
    }

    public KvNodeClearInfo(String category, String[] args) {
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
        return "KvNodeClearInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
