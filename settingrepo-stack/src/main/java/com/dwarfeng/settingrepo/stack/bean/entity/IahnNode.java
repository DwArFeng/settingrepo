package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 国际化节点。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = 276740405699441271L;

    private StringIdKey key;

    public IahnNode() {
    }

    public IahnNode(StringIdKey key) {
        this.key = key;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "IahnNode{" +
                "key=" + key +
                '}';
    }
}
