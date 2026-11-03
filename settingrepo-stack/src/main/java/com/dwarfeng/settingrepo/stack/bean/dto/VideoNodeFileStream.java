package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;

/**
 * 视频节点文件流。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoNodeFileStream implements Dto {

    private static final long serialVersionUID = -3484603799686087067L;

    private String originName;
    private long length;
    private InputStream content;

    public VideoNodeFileStream() {
    }

    public VideoNodeFileStream(String originName, long length, InputStream content) {
        this.originName = originName;
        this.length = length;
        this.content = content;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "VideoNodeFileStream{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
