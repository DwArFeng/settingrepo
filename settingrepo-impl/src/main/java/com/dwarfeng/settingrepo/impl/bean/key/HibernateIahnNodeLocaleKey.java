package com.dwarfeng.settingrepo.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.Bean;

import java.io.Serializable;
import java.util.Objects;

/**
 * Hibernate 国际化节点地区键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class HibernateIahnNodeLocaleKey implements Bean, Serializable {

    private static final long serialVersionUID = -4810929816505755894L;

    private String nodeStringId;
    private String language;
    private String country;
    private String variant;

    public HibernateIahnNodeLocaleKey() {
    }

    public HibernateIahnNodeLocaleKey(String nodeStringId, String language, String country, String variant) {
        this.nodeStringId = nodeStringId;
        this.language = language;
        this.country = country;
        this.variant = variant;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        HibernateIahnNodeLocaleKey that = (HibernateIahnNodeLocaleKey) o;
        return Objects.equals(nodeStringId, that.nodeStringId) && Objects.equals(language, that.language) &&
                Objects.equals(country, that.country) && Objects.equals(variant, that.variant);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(nodeStringId);
        result = 31 * result + Objects.hashCode(language);
        result = 31 * result + Objects.hashCode(country);
        result = 31 * result + Objects.hashCode(variant);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateIahnNodeLocaleKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                '}';
    }
}
