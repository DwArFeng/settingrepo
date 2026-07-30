package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 图片列表节点缩略图下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeThumbnailDownloadInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -5258863747639310456L;

    private String category;
    private String[] args;
    private int index;

    public ImageListNodeThumbnailDownloadInfo() {
    }

    public ImageListNodeThumbnailDownloadInfo(String category, String[] args, int index) {
        this.category = category;
        this.args = args;
        this.index = index;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "ImageListNodeThumbnailDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                '}';
    }
}
