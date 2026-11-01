package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 文本列表节点条目。
 *
 * @author lihc
 * @since 3.2.0
 */
public class TextListNodeItem implements Entity<LongIdKey> {

    private static final long serialVersionUID = 2549994444320216480L;

    private LongIdKey key;
    private StringIdKey nodeKey;
    private int index;
    /**
     * 标记该条目是否为 null。
     */
    private boolean nullFlag;
    private String content;

    public TextListNodeItem() {
    }

    public TextListNodeItem(LongIdKey key, StringIdKey nodeKey, int index, boolean nullFlag, String content) {
        this.key = key;
        this.nodeKey = nodeKey;
        this.index = index;
        this.nullFlag = nullFlag;
        this.content = content;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public StringIdKey getNodeKey() {
        return nodeKey;
    }

    public void setNodeKey(StringIdKey nodeKey) {
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
        return "TextListNodeItem{" +
                "key=" + key +
                ", nodeKey=" + nodeKey +
                ", index=" + index +
                ", nullFlag=" + nullFlag +
                ", content='" + content + '\'' +
                '}';
    }
}
