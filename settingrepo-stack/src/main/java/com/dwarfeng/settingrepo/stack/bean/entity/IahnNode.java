package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.basic.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;

import java.io.Serial;

/**
 * 国际化节点。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNode implements Entity<StringIdKey> {

    @Serial
    private static final long serialVersionUID = -8475998199605692304L;

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
