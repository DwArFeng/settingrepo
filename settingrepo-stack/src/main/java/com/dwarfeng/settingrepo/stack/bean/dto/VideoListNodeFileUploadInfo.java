package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频列表节点文件上传信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoListNodeFileUploadInfo implements Dto {

    private static final long serialVersionUID = -2777461290841717364L;

    private String category;
    private String[] args;

    /**
     * 上传的视频在列表中的索引。
     *
     * <p>
     * 该值不为 <code>null</code> 时代表向列表中的指定位置插入视频；该值为 <code>null</code> 时代表向列表的末尾插入视频。
     */
    private Integer index;

    private String originName;
    private byte[] content;
    private byte[] coverContent;

    public VideoListNodeFileUploadInfo() {
    }

    public VideoListNodeFileUploadInfo(
            String category, String[] args, Integer index, String originName, byte[] content, byte[] coverContent
    ) {
        this.category = category;
        this.args = args;
        this.index = index;
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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
        return "VideoListNodeFileUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                ", coverContent=" + Arrays.toString(coverContent) +
                '}';
    }
}
