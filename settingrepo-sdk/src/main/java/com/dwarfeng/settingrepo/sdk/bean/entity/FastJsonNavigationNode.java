package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNode;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.FastJsonStringIdKey;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 导航节点。
 *
 * @author Zhaofz
 * @since 2.4.2
 */
public class FastJsonNavigationNode implements Bean {

    @Serial
    private static final long serialVersionUID = -4755004566202471271L;

    public static FastJsonNavigationNode of(NavigationNode navigationNode) {
        if (Objects.isNull(navigationNode)) {
            return null;
        } else {
            return new FastJsonNavigationNode(
                    FastJsonStringIdKey.of(navigationNode.getKey()),
                    navigationNode.getSize(),
                    navigationNode.getContent()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "size", ordinal = 2)
    private int size;

    @JSONField(name = "content", ordinal = 3)
    private String content;

    public FastJsonNavigationNode() {
    }

    public FastJsonNavigationNode(FastJsonStringIdKey key, int size, String content) {
        this.key = key;
        this.size = size;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FastJsonNavigationNode{" +
                "key=" + key +
                ", size=" + size +
                ", content='" + content + '\'' +
                '}';
    }
}
