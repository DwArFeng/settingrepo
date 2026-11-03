package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频列表节点缩略图。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoListNodeThumbnail implements Dto {

    private static final long serialVersionUID = 8944369555403517012L;

    private String originName;
    private byte[] content;

    public VideoListNodeThumbnail() {
    }

    public VideoListNodeThumbnail(String originName, byte[] content) {
        this.originName = originName;
        this.content = content;
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

    @Override
    public String toString() {
        return "VideoListNodeThumbnail{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
