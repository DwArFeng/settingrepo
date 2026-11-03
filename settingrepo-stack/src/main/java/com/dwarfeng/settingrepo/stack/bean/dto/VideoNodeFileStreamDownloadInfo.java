package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频节点文件流下载信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoNodeFileStreamDownloadInfo implements Dto {

    private static final long serialVersionUID = -4598671936185001694L;

    private String category;
    private String[] args;

    public VideoNodeFileStreamDownloadInfo() {
    }

    public VideoNodeFileStreamDownloadInfo(String category, String[] args) {
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
        return "VideoNodeFileStreamDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
