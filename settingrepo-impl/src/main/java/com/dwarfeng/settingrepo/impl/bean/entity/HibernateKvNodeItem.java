package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.impl.bean.key.HibernateKvNodeItemKey;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateKvNodeItemKey.class)
@Table(name = "tbl_kv_node_item")
public class HibernateKvNodeItem implements Bean {

    private static final long serialVersionUID = 180871182559860850L;

    // region 主键

    @Id
    @Column(name = "node_id", nullable = false, length = Constraints.LENGTH_SETTING_NODE_ID)
    private String nodeStringId;

    @Id
    @Column(name = "id", nullable = false, length = Constraints.LENGTH_KV_NODE_ITEM_ID)
    private String itemStringId;

    // endregion

    // region 主属性字段

    @Column(name = "value", columnDefinition = "TEXT")
    private String value;

    // endregion

    // region 多对一

    @ManyToOne(targetEntity = HibernateKvNode.class)
    @JoinColumns({ //
            @JoinColumn(name = "node_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateKvNode node;

    // endregion

    public HibernateKvNodeItem() {
    }

    // region 映射用属性区

    public HibernateKvNodeItemKey getKey() {
        return new HibernateKvNodeItemKey(nodeStringId, itemStringId);
    }

    public void setKey(HibernateKvNodeItemKey key) {
        if (Objects.isNull(key)) {
            this.itemStringId = null;
            this.nodeStringId = null;
        } else {
            this.nodeStringId = key.getNodeStringId();
            this.itemStringId = key.getItemStringId();
        }
    }

    // endregion

    // region 常规属性区

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public HibernateKvNode getNode() {
        return node;
    }

    public void setNode(HibernateKvNode node) {
        this.node = node;
    }

    // endregion

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nodeStringId = " + nodeStringId + ", " +
                "itemStringId = " + itemStringId + ", " +
                "value = " + value + ", " +
                "node = " + node + ")";
    }
}
