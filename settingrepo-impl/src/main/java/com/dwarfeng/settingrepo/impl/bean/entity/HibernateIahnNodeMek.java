package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.impl.bean.key.HibernateIahnNodeMekKey;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@IdClass(HibernateIahnNodeMekKey.class)
@Table(name = "tbl_iahn_node_mek")
public class HibernateIahnNodeMek implements Bean {

    private static final long serialVersionUID = 6122776989262120715L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "node_string_id", nullable = false, length = Constraints.LENGTH_SETTING_NODE_ID)
    private String nodeStringId;

    @Id
    @Column(name = "mek_id", nullable = false, length = Constraints.LENGTH_IAHN_NODE_MEK_ID)
    private String mekId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "label")
    private String label;

    @Column(name = "default_message", length = Constraints.LENGTH_IAHN_NODE_MESSAGE)
    private String defaultMessage;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateIahnNode.class)
    @JoinColumns({
            @JoinColumn(name = "node_string_id", referencedColumnName = "id", insertable = false, updatable = false),
    })
    private HibernateIahnNode node;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateIahnNodeMessage.class, mappedBy = "mek")
    private Set<HibernateIahnNodeMessage> messages = new HashSet<>();

    public HibernateIahnNodeMek() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateIahnNodeMekKey getKey() {
        return new HibernateIahnNodeMekKey(nodeStringId, mekId);
    }

    public void setKey(HibernateIahnNodeMekKey key) {
        if (Objects.isNull(key)) {
            this.nodeStringId = null;
            this.mekId = null;
        } else {
            this.nodeStringId = key.getNodeStringId();
            this.mekId = key.getMekId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public String getNodeStringId() {
        return nodeStringId;
    }

    public void setNodeStringId(String nodeStringId) {
        this.nodeStringId = nodeStringId;
    }

    public String getMekId() {
        return mekId;
    }

    public void setMekId(String mekId) {
        this.mekId = mekId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateIahnNode getNode() {
        return node;
    }

    public void setNode(HibernateIahnNode node) {
        this.node = node;
    }

    public Set<HibernateIahnNodeMessage> getMessages() {
        return messages;
    }

    public void setMessages(Set<HibernateIahnNodeMessage> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nodeStringId = " + nodeStringId + ", " +
                "mekId = " + mekId + ", " +
                "label = " + label + ", " +
                "defaultMessage = " + defaultMessage + ", " +
                "remark = " + remark + ", " +
                "node = " + node + ")";
    }
}
