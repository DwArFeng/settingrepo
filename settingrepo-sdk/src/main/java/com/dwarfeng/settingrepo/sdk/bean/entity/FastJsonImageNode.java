package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.ImageNode;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.FastJsonStringIdKey;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 图片节点。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonImageNode implements Bean {

    @Serial
    private static final long serialVersionUID = 4385928167146812785L;

    public static FastJsonImageNode of(ImageNode imageNode) {
        if (Objects.isNull(imageNode)) {
            return null;
        } else {
            return new FastJsonImageNode(
                    FastJsonStringIdKey.of(imageNode.getKey()),
                    imageNode.getOriginName(),
                    imageNode.getStoreName(),
                    imageNode.getLength()
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

    public FastJsonImageNode() {
    }

    public FastJsonImageNode(FastJsonStringIdKey key, String originName, String storeName, Long length) {
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
        return "FastJsonImageNode{" +
                "key=" + key +
                ", originName='" + originName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", length=" + length +
                '}';
    }
}
