package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 导航节点条目。
 *
 * @author Zhaofz
 * @since 2.4.2
 */
public class NavigationNodeItem implements Entity<LongIdKey> {

    private static final long serialVersionUID = -6974897929313466769L;

    private LongIdKey key;
    private StringIdKey nodeKey;
    private LongIdKey parentKey;
    private int index;
    private String name;
    private String content;
    private String remark;

    public NavigationNodeItem() {
    }

    public NavigationNodeItem(
            LongIdKey key, StringIdKey nodeKey, LongIdKey parentKey, int index, String name, String content,
            String remark
    ) {
        this.key = key;
        this.nodeKey = nodeKey;
        this.parentKey = parentKey;
        this.index = index;
        this.name = name;
        this.content = content;
        this.remark = remark;
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

    public LongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(LongIdKey parentKey) {
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
        return "NavigationNodeItem{" +
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
