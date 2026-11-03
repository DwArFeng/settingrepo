package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频节点缩略图。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoNodeThumbnail implements Dto {

    private static final long serialVersionUID = 5964939006477709056L;

    private String originName;
    private byte[] content;

    public VideoNodeThumbnail() {
    }

    public VideoNodeThumbnail(String originName, byte[] content) {
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
        return "VideoNodeThumbnail{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
