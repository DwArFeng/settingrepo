package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.VideoNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 视频节点。
 *
 * @author fucl
 * @since 3.2.0
 */
public class FastJsonVideoNode implements Bean {

    private static final long serialVersionUID = -3822353546824959031L;

    public static FastJsonVideoNode of(VideoNode videoNode) {
        if (Objects.isNull(videoNode)) {
            return null;
        } else {
            return new FastJsonVideoNode(
                    FastJsonStringIdKey.of(videoNode.getKey()),
                    videoNode.getOriginName(),
                    videoNode.getStoreName(),
                    videoNode.getLength()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "origin_name", ordinal = 2)
    private String originName;

    @JSONField(name = "store_name", ordinal = 3)
    private String storeName;

    @JSONField(name = "length", ordinal = 4)
    private Long length;

    public FastJsonVideoNode() {
    }

    public FastJsonVideoNode(FastJsonStringIdKey key, String originName, String storeName, Long length) {
        this.key = key;
        this.originName = originName;
        this.storeName = storeName;
        this.length = length;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "FastJsonVideoNode{" +
                "key=" + key +
                ", originName='" + originName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", length=" + length +
                '}';
    }
}
