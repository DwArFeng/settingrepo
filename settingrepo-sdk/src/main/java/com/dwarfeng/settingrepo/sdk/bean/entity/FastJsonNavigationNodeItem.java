package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNodeItem;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 导航节点条目。
 *
 * @author Zhaofz
 * @since 2.4.2
 */
public class FastJsonNavigationNodeItem implements Bean {

    private static final long serialVersionUID = -7723091851965895672L;

    public static FastJsonNavigationNodeItem of(NavigationNodeItem navigationNodeItem) {
        if (Objects.isNull(navigationNodeItem)) {
            return null;
        } else {
            return new FastJsonNavigationNodeItem(
                    FastJsonLongIdKey.of(navigationNodeItem.getKey()),
                    FastJsonStringIdKey.of(navigationNodeItem.getNodeKey()),
                    FastJsonLongIdKey.of(navigationNodeItem.getParentKey()),
                    navigationNodeItem.getIndex(),
                    navigationNodeItem.getName(),
                    navigationNodeItem.getContent(),
                    navigationNodeItem.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "node_key", ordinal = 2)
    private FastJsonStringIdKey nodeKey;

    @JSONField(name = "parent_key", ordinal = 3)
    private FastJsonLongIdKey parentKey;

    @JSONField(name = "index", ordinal = 4)
    private int index;

    @JSONField(name = "name", ordinal = 5)
    private String name;

    @JSONField(name = "content", ordinal = 6)
    private String content;

    @JSONField(name = "remark", ordinal = 7)
    private String remark;

    public FastJsonNavigationNodeItem() {
    }

    public FastJsonNavigationNodeItem(
            FastJsonLongIdKey key, FastJsonStringIdKey nodeKey, FastJsonLongIdKey parentKey, int index, String name,
            String content, String remark
    ) {
        this.key = key;
        this.nodeKey = nodeKey;
        this.parentKey = parentKey;
        this.index = index;
        this.name = name;
        this.content = content;
        this.remark = remark;
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

    public FastJsonLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(FastJsonLongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonNavigationNodeItem{" +
                "key=" + key +
                ", nodeKey=" + nodeKey +
                ", parentKey=" + parentKey +
                ", index=" + index +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
