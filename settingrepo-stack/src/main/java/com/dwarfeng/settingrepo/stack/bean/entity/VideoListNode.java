package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 视频列表节点。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoListNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = 7412313410959247591L;

    private StringIdKey key;

    /**
     * 视频列表的数量。
     */
    private int size;

    public VideoListNode() {
    }

    public VideoListNode(StringIdKey key, int size) {
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
        return "VideoListNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
