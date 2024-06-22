package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 图片列表节点。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = 2769456600347694948L;

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
