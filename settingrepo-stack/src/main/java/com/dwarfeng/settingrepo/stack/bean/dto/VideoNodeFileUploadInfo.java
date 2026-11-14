package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频节点文件上传信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoNodeFileUploadInfo implements Dto {

    private static final long serialVersionUID = -3639705881463022964L;

    private String category;
    private String[] args;
    private String originName;
    private byte[] content;
    private byte[] coverContent;

    public VideoNodeFileUploadInfo() {
    }

    public VideoNodeFileUploadInfo(
            String category, String[] args, String originName, byte[] content, byte[] coverContent
    ) {
        this.category = category;
        this.args = args;
        this.originName = originName;
        this.content = content;
        this.coverContent = coverContent;
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

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getCoverContent() {
        return coverContent;
    }

    public void setCoverContent(byte[] coverContent) {
        this.coverContent = coverContent;
    }

    @Override
    public String toString() {
        return "VideoNodeFileUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                ", coverContent=" + Arrays.toString(coverContent) +
                '}';
    }
}
