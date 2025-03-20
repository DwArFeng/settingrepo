package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.LongTextNode;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 长文本节点。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class FastJsonLongTextNode implements Bean {

    private static final long serialVersionUID = 6484634678474731631L;

    public static FastJsonLongTextNode of(LongTextNode longTextNode) {
        if (Objects.isNull(longTextNode)) {
            return null;
        } else {
            return new FastJsonLongTextNode(
                    FastJsonStringIdKey.of(longTextNode.getKey()),
                    longTextNode.getPreview(),
                    longTextNode.getStoreName(),
                    longTextNode.getLength(),
                    longTextNode.isNullFlag()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "preview", ordinal = 2)
    private String preview;

    @JSONField(name = "store_name", ordinal = 3)
    private String storeName;

    @JSONField(name = "length", ordinal = 4)
    private Long length;

    @JSONField(name = "null_flag", ordinal = 5)
    private boolean nullFlag;

    public FastJsonLongTextNode() {
    }

    public FastJsonLongTextNode(
            FastJsonStringIdKey key, String preview, String storeName, Long length, boolean nullFlag
    ) {
        this.key = key;
        this.preview = preview;
        this.storeName = storeName;
        this.length = length;
        this.nullFlag = nullFlag;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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
        return "FastJsonLongTextNode{" +
                "key=" + key +
                ", preview='" + preview + '\'' +
                ", storeName='" + storeName + '\'' +
                ", length=" + length +
                ", nullFlag=" + nullFlag +
                '}';
    }
}
