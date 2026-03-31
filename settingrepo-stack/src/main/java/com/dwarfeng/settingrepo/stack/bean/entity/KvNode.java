package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 键值对节点。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class KvNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = -1259140830547025320L;

    private StringIdKey key;
    private int count;

    public KvNode() {
    }

    public KvNode(StringIdKey key, int count) {
        this.key = key;
        this.count = count;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
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
        return "KvNode{" +
                "key=" + key +
                ", count=" + count +
                '}';
    }
}
