package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 导航节点。
 *
 * @author zhaofz
 * @since 2.4.2
 */
public class NavigationNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = -4513840571719040829L;

    private StringIdKey key;
    private int size;

    public NavigationNode() {
    }

    public NavigationNode(StringIdKey key, int size) {
        this.key = key;
        this.size = size;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    public void setKey(StringIdKey key) {
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
        return "NavigationNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
