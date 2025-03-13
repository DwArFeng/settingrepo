package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 国际化节点。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class FastJsonIahnNode implements Bean {

    private static final long serialVersionUID = 2765305716847675600L;

    public static FastJsonIahnNode of(IahnNode iahnNode) {
        if (Objects.isNull(iahnNode)) {
            return null;
        } else {
            return new FastJsonIahnNode(
                    FastJsonStringIdKey.of(iahnNode.getKey())
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    public FastJsonIahnNode() {
    }

    public FastJsonIahnNode(FastJsonStringIdKey key) {
        this.key = key;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "FastJsonIahnNode{" +
                "key=" + key +
                '}';
    }
}
