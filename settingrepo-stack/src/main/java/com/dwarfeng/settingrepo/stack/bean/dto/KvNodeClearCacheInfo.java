package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 键值对节点缓存清理信息。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class KvNodeClearCacheInfo implements Dto {

    private static final long serialVersionUID = 2309375671414533887L;

    private String category;
    private String[] args;

    public KvNodeClearCacheInfo() {
    }

    public KvNodeClearCacheInfo(String category, String[] args) {
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
        return "KvNodeClearCacheInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
