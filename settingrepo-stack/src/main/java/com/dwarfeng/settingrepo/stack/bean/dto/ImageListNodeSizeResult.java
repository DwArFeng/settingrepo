package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 图片列表节点大小结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeSizeResult implements Dto {

    @Serial
    private static final long serialVersionUID = 2931781373981619436L;

    private int size;

    public ImageListNodeSizeResult() {
    }

    public ImageListNodeSizeResult(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ImageListNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
