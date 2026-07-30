package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 图片节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageNodeInspectResult implements Dto {

    @Serial
    private static final long serialVersionUID = -9114288111610560367L;

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
