package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 文本列表节点。
 *
 * @author lihc
 * @since 3.2.0
 */
public class TextListNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = -1167142494825599577L;

    private StringIdKey key;

    /**
     * 文本列表的数量。
     */
    private int size;

    public TextListNode() {
    }

    public TextListNode(StringIdKey key, int size) {
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
        return "TextListNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
