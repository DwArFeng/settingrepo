package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频列表节点文件。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoListNodeFile implements Dto {

    private static final long serialVersionUID = 880159129656113964L;

    private String originName;
    private byte[] content;

    public VideoListNodeFile() {
    }

    public VideoListNodeFile(String originName, byte[] content) {
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
        return "VideoListNodeFile{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
