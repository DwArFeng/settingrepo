package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频列表节点查看信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoListNodeInspectInfo implements Dto {

    private static final long serialVersionUID = 8675695972193252827L;

    private String category;
    private String[] args;

    public VideoListNodeInspectInfo() {
    }

    public VideoListNodeInspectInfo(String category, String[] args) {
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
        return "VideoListNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
