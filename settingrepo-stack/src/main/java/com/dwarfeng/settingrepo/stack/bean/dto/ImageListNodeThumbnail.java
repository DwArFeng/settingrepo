package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 图片列表节点缩略图。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeThumbnail implements Dto {

    private static final long serialVersionUID = -1604556628634339860L;

    private String originName;
    private byte[] content;

    public ImageListNodeThumbnail() {
    }

    public ImageListNodeThumbnail(String originName, byte[] content) {
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
        return "ImageListNodeThumbnail{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
