package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.InputStream;
import java.io.Serial;

/**
 * 文件节点文件流。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileNodeFileStream implements Dto {

    @Serial
    private static final long serialVersionUID = -5243481775152217221L;

    private String originName;
    private long length;
    private InputStream content;

    public FileNodeFileStream() {
    }

    public FileNodeFileStream(String originName, long length, InputStream content) {
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
        return "FileNodeFileStream{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
