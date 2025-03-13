package com.dwarfeng.settingrepo.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 国际化节点地区键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class FastJsonIahnNodeLocaleKey implements Key {

    private static final long serialVersionUID = 4271955495700807160L;

    public static FastJsonIahnNodeLocaleKey of(IahnNodeLocaleKey iahnNodeLocaleKey) {
        if (Objects.isNull(iahnNodeLocaleKey)) {
            return null;
        } else {
            return new FastJsonIahnNodeLocaleKey(
                    iahnNodeLocaleKey.getNodeStringId(),
                    iahnNodeLocaleKey.getLanguage(),
                    iahnNodeLocaleKey.getCountry(),
                    iahnNodeLocaleKey.getVariant()
            );
        }
    }

    @JSONField(name = "node_string_id", ordinal = 1)
    private String nodeStringId;

    @JSONField(name = "language", ordinal = 2)
    private String language;

    @JSONField(name = "country", ordinal = 3)
    private String country;

    @JSONField(name = "variant", ordinal = 4)
    private String variant;

    public FastJsonIahnNodeLocaleKey() {
    }

    public FastJsonIahnNodeLocaleKey(String nodeStringId, String language, String country, String variant) {
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

        FastJsonIahnNodeLocaleKey that = (FastJsonIahnNodeLocaleKey) o;
        return Objects.equals(nodeStringId, that.nodeStringId) && Objects.equals(language, that.language) && Objects.equals(country, that.country) && Objects.equals(variant, that.variant);
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
        return "FastJsonIahnNodeLocaleKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                '}';
    }
}
