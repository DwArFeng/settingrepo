package com.dwarfeng.settingrepo.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 键值对条目节点 键。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class KvNodeItemKey implements Key {

    private static final long serialVersionUID = -3291240787630817424L;

    private String nodeStringId;
    private String itemStringId;

    public KvNodeItemKey() {
    }

    public KvNodeItemKey(String nodeStringId, String itemStringId) {
        this.nodeStringId = nodeStringId;
        this.itemStringId = itemStringId;
    }

    public String getNodeStringId() {
        return nodeStringId;
    }

    public void setNodeStringId(String nodeStringId) {
        this.nodeStringId = nodeStringId;
    }

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        KvNodeItemKey that = (KvNodeItemKey) o;
        return Objects.equals(nodeStringId, that.nodeStringId) && Objects.equals(itemStringId, that.itemStringId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeStringId, itemStringId);
    }

    @Override
    public String toString() {
        return "KvNodeItemKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
