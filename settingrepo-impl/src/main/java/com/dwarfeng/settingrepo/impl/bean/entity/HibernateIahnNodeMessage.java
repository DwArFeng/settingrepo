package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.impl.bean.key.HibernateIahnNodeMessageKey;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(HibernateIahnNodeMessageKey.class)
@Table(name = "tbl_iahn_node_message")
public class HibernateIahnNodeMessage implements Bean {

    private static final long serialVersionUID = -8313653148934662532L;

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

    @Id
    @Column(name = "mek_id", nullable = false, length = Constraints.LENGTH_IAHN_NODE_MEK_ID)
    private String mekId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "message", nullable = false, length = Constraints.LENGTH_IAHN_NODE_MESSAGE)
    private String message;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateIahnNode.class)
    @JoinColumns({
            @JoinColumn(name = "node_string_id", referencedColumnName = "id", insertable = false, updatable = false),
    })
    private HibernateIahnNode node;

    @ManyToOne(targetEntity = HibernateIahnNodeLocale.class)
    @JoinColumns({
            @JoinColumn(name = "node_string_id", referencedColumnName = "node_string_id", insertable = false, updatable = false),
            @JoinColumn(name = "language", referencedColumnName = "language", insertable = false, updatable = false),
            @JoinColumn(name = "country", referencedColumnName = "country", insertable = false, updatable = false),
            @JoinColumn(name = "variant", referencedColumnName = "variant", insertable = false, updatable = false),
    })
    private HibernateIahnNodeLocale locale;

    @ManyToOne(targetEntity = HibernateIahnNodeMek.class)
    @JoinColumns({
            @JoinColumn(name = "node_string_id", referencedColumnName = "node_string_id", insertable = false, updatable = false),
            @JoinColumn(name = "mek_id", referencedColumnName = "mek_id", insertable = false, updatable = false),
    })
    private HibernateIahnNodeMek mek;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    public HibernateIahnNodeMessage() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateIahnNodeMessageKey getKey() {
        return new HibernateIahnNodeMessageKey(nodeStringId, language, country, variant, mekId);
    }

    public void setKey(HibernateIahnNodeMessageKey key) {
        if (Objects.isNull(key)) {
            this.nodeStringId = null;
            this.language = null;
            this.country = null;
            this.variant = null;
            this.mekId = null;
        } else {
            this.nodeStringId = key.getNodeStringId();
            this.language = key.getLanguage();
            this.country = key.getCountry();
            this.variant = key.getVariant();
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

    public String getMekId() {
        return mekId;
    }

    public void setMekId(String mekId) {
        this.mekId = mekId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HibernateIahnNode getNode() {
        return node;
    }

    public void setNode(HibernateIahnNode node) {
        this.node = node;
    }

    public HibernateIahnNodeLocale getLocale() {
        return locale;
    }

    public void setLocale(HibernateIahnNodeLocale locale) {
        this.locale = locale;
    }

    public HibernateIahnNodeMek getMek() {
        return mek;
    }

    public void setMek(HibernateIahnNodeMek mek) {
        this.mek = mek;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nodeStringId = " + nodeStringId + ", " +
                "language = " + language + ", " +
                "country = " + country + ", " +
                "variant = " + variant + ", " +
                "mekId = " + mekId + ", " +
                "message = " + message + ", " +
                "node = " + node + ", " +
                "locale = " + locale + ", " +
                "mek = " + mek + ")";
    }
}
