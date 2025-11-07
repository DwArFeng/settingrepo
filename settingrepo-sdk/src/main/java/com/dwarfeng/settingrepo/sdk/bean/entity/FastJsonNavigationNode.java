package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 导航节点。
 *
 * @author Zhaofz
 * @since 2.4.2
 */
public class FastJsonNavigationNode implements Bean {

    private static final long serialVersionUID = 1571126023278952370L;

    public static FastJsonNavigationNode of(NavigationNode navigationNode) {
        if (Objects.isNull(navigationNode)) {
            return null;
        } else {
            return new FastJsonNavigationNode(
                    FastJsonStringIdKey.of(navigationNode.getKey()),
                    navigationNode.getSize()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "size", ordinal = 2)
    private int size;

    public FastJsonNavigationNode() {
    }

    public FastJsonNavigationNode(FastJsonStringIdKey key, int size) {
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
        return "FastJsonNavigationNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
