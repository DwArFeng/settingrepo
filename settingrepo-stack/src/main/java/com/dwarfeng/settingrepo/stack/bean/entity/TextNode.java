package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 文本节点。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class TextNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = -1395000876881261223L;

    private StringIdKey key;
    private String value;

    public TextNode() {
    }

    public TextNode(StringIdKey key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
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
        return "TextNode{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
