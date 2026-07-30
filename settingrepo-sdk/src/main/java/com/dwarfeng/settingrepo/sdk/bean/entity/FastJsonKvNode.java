package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.KvNode;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.FastJsonStringIdKey;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 键值对节点。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class FastJsonKvNode implements Bean {

    @Serial
    private static final long serialVersionUID = -2595521832445177062L;

    public static FastJsonKvNode of(KvNode kvNode) {
        if (Objects.isNull(kvNode)) {
            return null;
        } else {
            return new FastJsonKvNode(
                    FastJsonStringIdKey.of(kvNode.getKey()),
                    kvNode.getCount()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "count", ordinal = 2)
    private int count;

    public FastJsonKvNode() {
    }

    public FastJsonKvNode(FastJsonStringIdKey key, int count) {
        this.key = key;
        this.count = count;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "FastJsonKvNode{" +
                "key=" + key +
                ", count=" + count +
                '}';
    }
}
