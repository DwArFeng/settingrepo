package com.dwarfeng.settingrepo.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.Bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Hibernate 国际化节点消息键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class HibernateIahnNodeMessageKey implements Bean, Serializable {

    private static final long serialVersionUID = -2303995621513680071L;

    private String nodeStringId;
    private String language;
    private String country;
    private String variant;
    private String mekId;

    public HibernateIahnNodeMessageKey() {
    }

    public HibernateIahnNodeMessageKey(
            String nodeStringId, String language, String country, String variant, String mekId
    ) {
        this.nodeStringId = nodeStringId;
        this.language = language;
        this.country = country;
        this.variant = variant;
        this.mekId = mekId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        HibernateIahnNodeMessageKey that = (HibernateIahnNodeMessageKey) o;
        return Objects.equals(nodeStringId, that.nodeStringId) && Objects.equals(language, that.language) &&
                Objects.equals(country, that.country) && Objects.equals(variant, that.variant) &&
                Objects.equals(mekId, that.mekId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(nodeStringId);
        result = 31 * result + Objects.hashCode(language);
        result = 31 * result + Objects.hashCode(country);
        result = 31 * result + Objects.hashCode(variant);
        result = 31 * result + Objects.hashCode(mekId);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateIahnNodeMessageKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                ", mekId='" + mekId + '\'' +
                '}';
    }
}
