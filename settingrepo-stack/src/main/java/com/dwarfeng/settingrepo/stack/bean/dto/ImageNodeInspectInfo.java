package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 图片节点查看信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageNodeInspectInfo implements Dto {

    private static final long serialVersionUID = -4648571397686332486L;

    private String category;
    private String[] args;

    public ImageNodeInspectInfo() {
    }

    public ImageNodeInspectInfo(String category, String[] args) {
        this.category = category;
        this.args = args;
    }

    public String getCategory() {
        return category;
    }

    public ImageNodeInspectInfo setCategory(String category) {
        this.category = category;
        return this;
    }

    public String[] getArgs() {
        return args;
    }

    public ImageNodeInspectInfo setArgs(String[] args) {
        this.args = args;
        return this;
    }

    @Override
    public String toString() {
        return "ImageNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
