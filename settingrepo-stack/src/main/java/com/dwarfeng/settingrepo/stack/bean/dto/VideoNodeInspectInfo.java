package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频节点查看信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoNodeInspectInfo implements Dto {

    private static final long serialVersionUID = -5230334666515098552L;

    private String category;
    private String[] args;

    public VideoNodeInspectInfo() {
    }

    public VideoNodeInspectInfo(String category, String[] args) {
        this.category = category;
        this.args = args;
    }

    public String getCategory() {
        return category;
    }

    public VideoNodeInspectInfo setCategory(String category) {
        this.category = category;
        return this;
    }

    public String[] getArgs() {
        return args;
    }

    public VideoNodeInspectInfo setArgs(String[] args) {
        this.args = args;
        return this;
    }

    @Override
    public String toString() {
        return "VideoNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
