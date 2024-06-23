package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 图片列表节点查看信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeInspectInfo implements Dto {

    private static final long serialVersionUID = 7650008331168470652L;

    private String category;
    private String[] args;

    public ImageListNodeInspectInfo() {
    }

    public ImageListNodeInspectInfo(String category, String[] args) {
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
        return "ImageListNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
