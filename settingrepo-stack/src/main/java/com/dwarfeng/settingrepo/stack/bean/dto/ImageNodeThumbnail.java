package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 图片节点缩略图。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageNodeThumbnail implements Dto {

    @Serial
    private static final long serialVersionUID = 4098750601083282126L;

    private String originName;
    private byte[] content;

    public ImageNodeThumbnail() {
    }

    public ImageNodeThumbnail(String originName, byte[] content) {
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
        return "ImageNodeThumbnail{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
