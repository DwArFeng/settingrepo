package com.dwarfeng.settingrepo.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 国际化节点消息键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeMessageKey implements Key {

    private static final long serialVersionUID = -3163966520597124146L;

    private String nodeStringId;

    /**
     * 语言。
     *
     * <p>
     * 该值对应 <code>RFC 5646</code> 中的 <code>Language</code>。<br>
     * 其格式为：
     * <ul>
     *     <li> 2 位字母（如 zh 表示中文，en 表示英文）。</li>
     *     <li> 3 位字母（如 zho 表示中文，eng 表示英文）。</li>
     * </ul>
     */
    private String language;

    /**
     * 国家。
     *
     * <p>
     * 该值对应 <code>RFC 5646</code> 中的 <code>Region</code>。<br>
     * 其格式为：
     * <ul>
     *     <li> 2 位字母（如 CN 表示中国，US 表示美国）。</li>
     *     <li> 3 位数字（如 001 表示全球，142 表示亚洲）。</li>
     * </ul>
     */
    private String country;

    /**
     * 变体。
     *
     * <p>
     * 该值对应 <code>RFC 5646</code> 中的 <code>Variant</code>。<br>
     * 其格式为：
     * <ul>
     *     <li> 5-8 位数字或字母，首字符必须为字母（如 rozaj 表示 Resian 方言）。</li>
     * </ul>
     */
    private String variant;

    private String mekId;

    public IahnNodeMessageKey() {
    }

    public IahnNodeMessageKey(String nodeStringId, String language, String country, String variant, String mekId) {
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

        IahnNodeMessageKey that = (IahnNodeMessageKey) o;
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
        return "IahnNodeMessageKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                ", mekId='" + mekId + '\'' +
                '}';
    }
}
