package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.FileListNodeItem;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 文本列表项。
 *
 * @author liulx
 * @since 2.4.2
 */
public class JSFixedFastJsonFileListNodeItem implements Bean {

    private static final long serialVersionUID = -4897651733472148631L;

    public static JSFixedFastJsonFileListNodeItem of(FileListNodeItem fileListNodeItem) {
        if (Objects.isNull(fileListNodeItem)) {
            return null;
        } else {
            return new JSFixedFastJsonFileListNodeItem(
                    JSFixedFastJsonLongIdKey.of(fileListNodeItem.getKey()),
                    FastJsonStringIdKey.of(fileListNodeItem.getNodeKey()),
                    fileListNodeItem.getIndex(),
                    fileListNodeItem.isNullFlag(),
                    fileListNodeItem.getOriginName(),
                    fileListNodeItem.getStoreName(),
                    fileListNodeItem.getLength()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "node_key", ordinal = 2)
    private FastJsonStringIdKey nodeKey;

    @JSONField(name = "index", ordinal = 3)
    private int index;

    @JSONField(name = "null_flag", ordinal = 4)
    private boolean nullFlag;

    @JSONField(name = "origin_name", ordinal = 5)
    private String originName;

    @JSONField(name = "store_name", ordinal = 6)
    private String storeName;

    @JSONField(name = "length", ordinal = 7)
    private Long length;

    public JSFixedFastJsonFileListNodeItem() {
    }

    public JSFixedFastJsonFileListNodeItem(
            JSFixedFastJsonLongIdKey key, FastJsonStringIdKey nodeKey, int index, boolean nullFlag, String originName,
            String storeName, Long length
    ) {
        this.key = key;
        this.nodeKey = nodeKey;
        this.index = index;
        this.nullFlag = nullFlag;
        this.originName = originName;
        this.storeName = storeName;
        this.length = length;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
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
        return "JSFixedFastJsonFileListNodeItem{" +
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
