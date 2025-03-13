package com.dwarfeng.settingrepo.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 国际化节点消息键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class FastJsonIahnNodeMessageKey implements Key {

    private static final long serialVersionUID = 6374385683522362159L;

    public static FastJsonIahnNodeMessageKey of(IahnNodeMessageKey iahnNodeMessageKey) {
        if (Objects.isNull(iahnNodeMessageKey)) {
            return null;
        } else {
            return new FastJsonIahnNodeMessageKey(
                    iahnNodeMessageKey.getNodeStringId(),
                    iahnNodeMessageKey.getLanguage(),
                    iahnNodeMessageKey.getCountry(),
                    iahnNodeMessageKey.getVariant(),
                    iahnNodeMessageKey.getMekId()
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

    @JSONField(name = "mek_id", ordinal = 5)
    private String mekId;

    public FastJsonIahnNodeMessageKey() {
    }

    public FastJsonIahnNodeMessageKey(
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

        FastJsonIahnNodeMessageKey that = (FastJsonIahnNodeMessageKey) o;
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
        return "FastJsonIahnNodeMessageKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                ", mekId='" + mekId + '\'' +
                '}';
    }
}
