package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.InputStream;
import java.io.Serial;

/**
 * 图片节点文件流。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageNodeFileStream implements Dto {

    @Serial
    private static final long serialVersionUID = 2188508823535235908L;

    private String originName;
    private long length;
    private InputStream content;

    public ImageNodeFileStream() {
    }

    public ImageNodeFileStream(String originName, long length, InputStream content) {
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
        return "ImageNodeFileStream{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
