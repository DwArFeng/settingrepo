package com.dwarfeng.settingrepo.impl.bean.key;

import com.dwarfeng.subgrade.basic.stack.bean.Bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Hibernate 键值对条目节点 键。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class HibernateKvNodeItemKey implements Bean, Serializable {

    @Serial
    private static final long serialVersionUID = 4166511138158862951L;

    private String nodeStringId;
    private String itemStringId;

    public HibernateKvNodeItemKey() {
    }

    public HibernateKvNodeItemKey(String nodeStringId, String itemStringId) {
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

        HibernateKvNodeItemKey that = (HibernateKvNodeItemKey) o;
        return Objects.equals(nodeStringId, that.nodeStringId) && Objects.equals(itemStringId, that.itemStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(nodeStringId);
        result = 31 * result + Objects.hashCode(itemStringId);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateKvNodeItemKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
