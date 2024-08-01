package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 图片列表节点条目。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeItem implements Entity<LongIdKey> {

    private static final long serialVersionUID = -8516709234763178294L;

    private LongIdKey key;
    private StringIdKey nodeKey;

    /**
     * 该条目在列表节点中的索引。
     */
    private int index;

    /**
     * 标记该条目是否为 null。
     */
    private boolean nullFlag;

    private String originName;
    private String storeName;
    private Long length;

    public ImageListNodeItem() {
    }

    public ImageListNodeItem(
            LongIdKey key, StringIdKey nodeKey, int index, boolean nullFlag, String originName, String storeName,
            Long length
    ) {
        this.key = key;
        this.nodeKey = nodeKey;
        this.index = index;
        this.nullFlag = nullFlag;
        this.originName = originName;
        this.storeName = storeName;
        this.length = length;
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

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
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

    @Override
    public String toString() {
        return "ImageListNodeItem{" +
                "key=" + key +
                ", nodeKey=" + nodeKey +
                ", index=" + index +
                ", nullFlag=" + nullFlag +
                ", originName='" + originName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", length=" + length +
                '}';
    }
}
