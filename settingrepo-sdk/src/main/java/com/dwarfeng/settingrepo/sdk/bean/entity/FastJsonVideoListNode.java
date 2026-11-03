package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 视频列表节点。
 *
 * @author fucl
 * @since 3.2.0
 */
public class FastJsonVideoListNode implements Bean {

    private static final long serialVersionUID = -8624138438636118199L;

    public static FastJsonVideoListNode of(VideoListNode videoListNode) {
        if (Objects.isNull(videoListNode)) {
            return null;
        } else {
            return new FastJsonVideoListNode(
                    FastJsonStringIdKey.of(videoListNode.getKey()),
                    videoListNode.getSize()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "size", ordinal = 2)
    private int size;

    public FastJsonVideoListNode() {
    }

    public FastJsonVideoListNode(FastJsonStringIdKey key, int size) {
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
        return "FastJsonVideoListNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
