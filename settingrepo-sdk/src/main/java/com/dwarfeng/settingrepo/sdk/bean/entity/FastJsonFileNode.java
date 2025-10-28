package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.FileNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 文件节点。
 *
 * @author WangT
 * @since 2.4.2
 */
public class FastJsonFileNode implements Bean {

    private static final long serialVersionUID = -6485053991354119302L;

    public static FastJsonFileNode of(FileNode fileNode) {
        if (Objects.isNull(fileNode)) {
            return null;
        } else {
            return new FastJsonFileNode(
                    FastJsonStringIdKey.of(fileNode.getKey()),
                    fileNode.getOriginName(),
                    fileNode.getStoreName(),
                    fileNode.getLength()
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

    public FastJsonFileNode() {
    }

    public FastJsonFileNode(FastJsonStringIdKey key, String originName, String storeName, Long length) {
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
        return "FastJsonFileNode{" +
                "key=" + key +
                ", originName='" + originName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", length=" + length +
                '}';
    }
}
