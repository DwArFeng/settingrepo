package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 图片列表节点大小信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeSizeInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 7547152524171532070L;

    private String category;
    private String[] args;

    public ImageListNodeSizeInfo() {
    }

    public ImageListNodeSizeInfo(String category, String[] args) {
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
        return "ImageListNodeSizeInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
