package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 键值对节点查看信息。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeInspectInfo implements Dto {

    private static final long serialVersionUID = 2454252190823477608L;

    private String category;
    private String[] args;

    public KvNodeInspectInfo() {
    }

    public KvNodeInspectInfo(String category, String[] args) {
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
        return "KvNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
