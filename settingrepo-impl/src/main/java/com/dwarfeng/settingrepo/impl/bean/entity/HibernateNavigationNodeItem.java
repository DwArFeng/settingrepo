package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Optional;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_navigation_node_item")
public class HibernateNavigationNodeItem implements Bean {

    private static final long serialVersionUID = -5797505043800131488L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "node_id", length = Constraints.LENGTH_SETTING_NODE_ID)
    private String nodeStringId;

    @Column(name = "parent_id")
    private Long parentLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "column_index")
    private int index;

    @Column(name = "name", length = Constraints.LENGTH_NAVIGATION_NODE_NAME)
    private String name;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateNavigationNodeItem.class)
    @JoinColumns({ //
            @JoinColumn(name = "parent_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNavigationNodeItem parent;

    @ManyToOne(targetEntity = HibernateNavigationNode.class)
    @JoinColumns({ //
            @JoinColumn(name = "node_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateNavigationNode node;

    public HibernateNavigationNodeItem() {
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

    public void setNodeKey(HibernateStringIdKey nodeKey) {
        this.nodeStringId = Optional.ofNullable(nodeKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    public HibernateLongIdKey getParentKey() {
        return Optional.ofNullable(parentLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setParentKey(HibernateLongIdKey parentKey) {
        this.parentLongId = Optional.ofNullable(parentKey).map(HibernateLongIdKey::getLongId).orElse(null);
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

    public Long getParentLongId() {
        return parentLongId;
    }

    public void setParentLongId(Long parentLongId) {
        this.parentLongId = parentLongId;
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

    public HibernateNavigationNodeItem getParent() {
        return parent;
    }

    public void setParent(HibernateNavigationNodeItem parent) {
        this.parent = parent;
    }

    public HibernateNavigationNode getNode() {
        return node;
    }

    public void setNode(HibernateNavigationNode node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "nodeStringId = " + nodeStringId + ", " +
                "parentLongId = " + parentLongId + ", " +
                "index = " + index + ", " +
                "name = " + name + ", " +
                "content = " + content + ", " +
                "remark = " + remark + ", " +
                "parent = " + parent + ", " +
                "node = " + node + ")";
    }
}
