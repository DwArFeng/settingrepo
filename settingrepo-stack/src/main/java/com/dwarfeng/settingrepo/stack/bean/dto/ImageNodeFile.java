package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 图片节点文件。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageNodeFile implements Dto {

    @Serial
    private static final long serialVersionUID = 74548982247282675L;

    private String originName;
    private byte[] content;

    public ImageNodeFile() {
    }

    public ImageNodeFile(String originName, byte[] content) {
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
        return "ImageNodeFile{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
