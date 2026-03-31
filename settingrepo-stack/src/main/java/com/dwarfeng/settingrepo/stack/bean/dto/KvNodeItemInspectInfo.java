package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 键值对节点条目查看信息。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeItemInspectInfo implements Dto {

    private static final long serialVersionUID = -1295552012870229003L;

    private String category;
    private String[] args;
    private String itemStringId;

    public KvNodeItemInspectInfo() {
    }

    public KvNodeItemInspectInfo(String category, String[] args, String itemStringId) {
        this.category = category;
        this.args = args;
        this.itemStringId = itemStringId;
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

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    @Override
    public String toString() {
        return "KvNodeItemInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
