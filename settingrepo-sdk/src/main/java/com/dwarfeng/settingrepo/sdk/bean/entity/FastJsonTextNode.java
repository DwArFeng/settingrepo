package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 文本节点。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonTextNode implements Bean {

    private static final long serialVersionUID = 2367599749782015524L;

    public static FastJsonTextNode of(TextNode textNode) {
        if (Objects.isNull(textNode)) {
            return null;
        } else {
            return new FastJsonTextNode(
                    FastJsonStringIdKey.of(textNode.getKey()),
                    textNode.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public FastJsonTextNode() {
    }

    public FastJsonTextNode(FastJsonStringIdKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FastJsonTextNode{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
