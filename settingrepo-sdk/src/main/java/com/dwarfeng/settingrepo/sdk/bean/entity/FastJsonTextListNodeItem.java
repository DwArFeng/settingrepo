package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.TextListNodeItem;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 文本列表节点条目。
 *
 * @author lihc
 * @since 3.2.0
 */
public class FastJsonTextListNodeItem implements Bean {

    private static final long serialVersionUID = 7911040574625567403L;

    public static FastJsonTextListNodeItem of(TextListNodeItem textListNodeItem) {
        if (Objects.isNull(textListNodeItem)) {
            return null;
        } else {
            return new FastJsonTextListNodeItem(
                    FastJsonLongIdKey.of(textListNodeItem.getKey()),
                    FastJsonStringIdKey.of(textListNodeItem.getNodeKey()),
                    textListNodeItem.getIndex(),
                    textListNodeItem.isNullFlag(),
                    textListNodeItem.getContent()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "node_key", ordinal = 2)
    private FastJsonStringIdKey nodeKey;

    @JSONField(name = "index", ordinal = 3)
    private int index;

    @JSONField(name = "null_flag", ordinal = 4)
    private boolean nullFlag;

    @JSONField(name = "content", ordinal = 5)
    private String content;

    public FastJsonTextListNodeItem() {
    }

    public FastJsonTextListNodeItem(
            FastJsonLongIdKey key, FastJsonStringIdKey nodeKey, int index, boolean nullFlag, String content
    ) {
        this.key = key;
        this.nodeKey = nodeKey;
        this.index = index;
        this.nullFlag = nullFlag;
        this.content = content;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonStringIdKey getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(FastJsonStringIdKey nodeKey) {
        this.nodeKey = nodeKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isNullFlag() {
        return nullFlag;
    }

    public void setNullFlag(boolean nullFlag) {
        this.nullFlag = nullFlag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FastJsonTextListNodeItem{" +
                "key=" + key +
                ", nodeKey=" + nodeKey +
                ", index=" + index +
                ", nullFlag=" + nullFlag +
                ", content='" + content + '\'' +
                '}';
    }
}
