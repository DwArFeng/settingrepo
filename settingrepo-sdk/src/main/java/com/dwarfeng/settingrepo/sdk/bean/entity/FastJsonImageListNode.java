package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 图片列表节点。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonImageListNode implements Bean {

    private static final long serialVersionUID = 5134975731996238367L;

    public static FastJsonImageListNode of(ImageListNode imageListNode) {
        if (Objects.isNull(imageListNode)) {
            return null;
        } else {
            return new FastJsonImageListNode(
                    FastJsonStringIdKey.of(imageListNode.getKey()),
                    imageListNode.getSize()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "size", ordinal = 2)
    private int size;

    public FastJsonImageListNode() {
    }

    public FastJsonImageListNode(FastJsonStringIdKey key, int size) {
        this.key = key;
        this.size = size;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FastJsonImageListNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
