package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 图片节点缩略图下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageNodeThumbnailDownloadInfo implements Dto {

    private static final long serialVersionUID = -7368912614123235274L;

    private String category;
    private String[] args;

    public ImageNodeThumbnailDownloadInfo() {
    }

    public ImageNodeThumbnailDownloadInfo(String category, String[] args) {
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
        return "ImageNodeThumbnailDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
