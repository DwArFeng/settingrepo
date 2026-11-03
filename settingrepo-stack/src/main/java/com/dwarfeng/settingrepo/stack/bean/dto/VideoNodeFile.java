package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频节点文件。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoNodeFile implements Dto {

    private static final long serialVersionUID = 5969329336069195271L;

    private String originName;
    private byte[] content;

    public VideoNodeFile() {
    }

    public VideoNodeFile(String originName, byte[] content) {
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
        return "VideoNodeFile{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
