package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageListNodeSizeResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 图片列表节点大小结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonImageListNodeSizeResult implements Dto {

    private static final long serialVersionUID = 8546047769527336572L;

    public static FastJsonImageListNodeSizeResult of(ImageListNodeSizeResult imageListNodeSizeResult) {
        if (Objects.isNull(imageListNodeSizeResult)) {
            return null;
        } else {
            return new FastJsonImageListNodeSizeResult(
                    imageListNodeSizeResult.getSize()
            );
        }
    }

    @JSONField(name = "size", ordinal = 1)
    private int size;

    public FastJsonImageListNodeSizeResult() {
    }

    public FastJsonImageListNodeSizeResult(int size) {
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
        return "FastJsonImageListNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
