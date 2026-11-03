package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频列表节点缩略图下载信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoListNodeThumbnailDownloadInfo implements Dto {

    private static final long serialVersionUID = -5133879795538022216L;

    private String category;
    private String[] args;
    private int index;

    public VideoListNodeThumbnailDownloadInfo() {
    }

    public VideoListNodeThumbnailDownloadInfo(String category, String[] args, int index) {
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
        return "VideoListNodeThumbnailDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                '}';
    }
}
