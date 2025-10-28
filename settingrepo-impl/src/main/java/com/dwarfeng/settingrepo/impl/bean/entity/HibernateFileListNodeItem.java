package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_file_list_node_item")
public class HibernateFileListNodeItem implements Bean {

    private static final long serialVersionUID = 1383404218177873026L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "node_id", length = Constraints.LENGTH_SETTING_NODE_ID)
    private String nodeStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "column_index", nullable = false)
    private int index;

    @Column(name = "null_flag", nullable = false)
    private boolean nullFlag;

    @Column(name = "origin_name", length = Constraints.LENGTH_NAME)
    private String originName;

    @Column(name = "store_name", length = Constraints.LENGTH_NAME)
    private String storeName;

    @Column(name = "length")
    private Long length;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateFileListNode.class)
    @JoinColumns({ //
            @JoinColumn(name = "node_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateFileListNode node;

    public HibernateFileListNodeItem() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateStringIdKey getNodeKey() {
        return Optional.ofNullable(nodeStringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setNodeKey(HibernateStringIdKey idKey) {
        this.nodeStringId = Optional.ofNullable(idKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getNodeStringId() {
        return nodeStringId;
    }

    public void setNodeStringId(String nodeStringId) {
        this.nodeStringId = nodeStringId;
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

    public HibernateFileListNode getNode() {
        return node;
    }

    public void setNode(HibernateFileListNode node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "nodeStringId = " + nodeStringId + ", " +
                "index = " + index + ", " +
                "nullFlag = " + nullFlag + ", " +
                "originName = " + originName + ", " +
                "storeName = " + storeName + ", " +
                "length = " + length + ", " +
                "node = " + node + ")";
    }
}
