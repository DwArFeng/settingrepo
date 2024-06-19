package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 图片节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageNodeInspectResult implements Dto {

    private static final long serialVersionUID = 9109148065294865949L;

    private String originName;
    private long length;

    public ImageNodeInspectResult() {
    }

    public ImageNodeInspectResult(String originName, long length) {
        this.originName = originName;
        this.length = length;
    }

    public String getOriginName() {
        return originName;
    }

    public ImageNodeInspectResult setOriginName(String originName) {
        this.originName = originName;
        return this;
    }

    public long getLength() {
        return length;
    }

    public ImageNodeInspectResult setLength(long length) {
        this.length = length;
        return this;
    }

    @Override
    public String toString() {
        return "ImageNodeInspectResult{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                '}';
    }
}
