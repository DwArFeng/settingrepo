package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateStringIdKey.class)
@Table(name = "tbl_iahn_node")
public class HibernateIahnNode implements Bean {

    private static final long serialVersionUID = 2942638746339182849L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", length = Constraints.LENGTH_SETTING_NODE_ID, nullable = false, unique = true)
    private String stringId;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateIahnNodeLocale.class, mappedBy = "node")
    private Set<HibernateIahnNodeLocale> locales = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateIahnNodeMek.class, mappedBy = "node")
    private Set<HibernateIahnNodeMek> meks = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateIahnNodeMessage.class, mappedBy = "node")
    private Set<HibernateIahnNodeMessage> messages = new HashSet<>();

    public HibernateIahnNode() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateStringIdKey getKey() {
        return Optional.ofNullable(stringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setKey(HibernateStringIdKey idKey) {
        this.stringId = Optional.ofNullable(idKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    public Set<HibernateIahnNodeLocale> getLocales() {
        return locales;
    }

    public void setLocales(Set<HibernateIahnNodeLocale> locales) {
        this.locales = locales;
    }

    public Set<HibernateIahnNodeMek> getMeks() {
        return meks;
    }

    public void setMeks(Set<HibernateIahnNodeMek> meks) {
        this.meks = meks;
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
                "stringId = " + stringId + ")";
    }
}
