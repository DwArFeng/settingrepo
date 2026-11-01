package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.TextListNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 文本列表节点。
 *
 * @author lihc
 * @since 3.2.0
 */
public class FastJsonTextListNode implements Bean {

    private static final long serialVersionUID = -5914658456355307220L;

    public static FastJsonTextListNode of(TextListNode textListNode) {
        if (Objects.isNull(textListNode)) {
            return null;
        } else {
            return new FastJsonTextListNode(
                    FastJsonStringIdKey.of(textListNode.getKey()),
                    textListNode.getSize()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "size", ordinal = 2)
    private int size;

    public FastJsonTextListNode() {
    }

    public FastJsonTextListNode(FastJsonStringIdKey key, int size) {
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
        return "FastJsonTextListNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
