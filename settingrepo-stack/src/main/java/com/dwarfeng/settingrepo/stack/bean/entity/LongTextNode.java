package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 长文本节点。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = 5993536035258351289L;

    private StringIdKey key;
    private String preview;
    private String storeName;
    private Long length;
    private boolean nullFlag;

    public LongTextNode() {
    }

    public LongTextNode(StringIdKey key, String preview, String storeName, Long length, boolean nullFlag) {
        this.key = key;
        this.preview = preview;
        this.storeName = storeName;
        this.length = length;
        this.nullFlag = nullFlag;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
        this.key = key;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public boolean isNullFlag() {
        return nullFlag;
    }

    public void setNullFlag(boolean nullFlag) {
        this.nullFlag = nullFlag;
    }

    @Override
    public String toString() {
        return "LongTextNode{" +
                "key=" + key +
                ", preview='" + preview + '\'' +
                ", storeName='" + storeName + '\'' +
                ", length=" + length +
                ", nullFlag=" + nullFlag +
                '}';
    }
}
