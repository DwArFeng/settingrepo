package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.impl.bean.key.HibernateIahnNodeLocaleKey;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@IdClass(HibernateIahnNodeLocaleKey.class)
@Table(name = "tbl_iahn_node_locale")
public class HibernateIahnNodeLocale implements Bean {

    private static final long serialVersionUID = -1640948406902130315L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "node_string_id", nullable = false, length = Constraints.LENGTH_SETTING_NODE_ID)
    private String nodeStringId;

    @Id
    @Column(name = "language", nullable = false, length = Constraints.LENGTH_IAHN_NODE_LANGUAGE_MAX)
    private String language;

    @Id
    @Column(name = "country", nullable = false, length = Constraints.LENGTH_IAHN_NODE_COUNTRY_MAX)
    private String country;

    @Id
    @Column(name = "variant", nullable = false, length = Constraints.LENGTH_IAHN_NODE_VARIANT_MAX)
    private String variant;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "label", length = Constraints.LENGTH_NAME)
    private String label;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateIahnNode.class)
    @JoinColumns({
            @JoinColumn(name = "node_string_id", referencedColumnName = "id", insertable = false, updatable = false),
    })
    private HibernateIahnNode node;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateIahnNodeMessage.class, mappedBy = "locale")
    private Set<HibernateIahnNodeMessage> messages = new HashSet<>();

    public HibernateIahnNodeLocale() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateIahnNodeLocaleKey getKey() {
        return new HibernateIahnNodeLocaleKey(nodeStringId, language, country, variant);
    }

    public void setKey(HibernateIahnNodeLocaleKey key) {
        if (Objects.isNull(key)) {
            this.nodeStringId = null;
            this.language = null;
            this.country = null;
            this.variant = null;
        } else {
            this.nodeStringId = key.getNodeStringId();
            this.language = key.getLanguage();
            this.country = key.getCountry();
            this.variant = key.getVariant();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public String getNodeStringId() {
        return nodeStringId;
    }

    public void setNodeStringId(String nodeStringId) {
        this.nodeStringId = nodeStringId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
                "language = " + language + ", " +
                "country = " + country + ", " +
                "variant = " + variant + ", " +
                "label = " + label + ", " +
                "remark = " + remark + ", " +
                "node = " + node + ")";
    }
}
