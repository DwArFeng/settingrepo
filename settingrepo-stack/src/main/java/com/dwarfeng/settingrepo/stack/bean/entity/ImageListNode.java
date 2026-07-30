package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.basic.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;

import java.io.Serial;

/**
 * 图片列表节点。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNode implements Entity<StringIdKey> {

    @Serial
    private static final long serialVersionUID = 163358219631278755L;

    private StringIdKey key;

    /**
     * 图片列表的数量。
     */
    private int size;

    public ImageListNode() {
    }

    public ImageListNode(StringIdKey key, int size) {
        this.key = key;
        this.size = size;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
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
        return "ImageListNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
