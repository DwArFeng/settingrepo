package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频列表节点大小信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoListNodeSizeInfo implements Dto {

    private static final long serialVersionUID = -7914583668441518320L;

    private String category;
    private String[] args;

    public VideoListNodeSizeInfo() {
    }

    public VideoListNodeSizeInfo(String category, String[] args) {
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
        return "VideoListNodeSizeInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
