package com.dwarfeng.settingrepo.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeCountry;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeLanguage;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeVariant;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 国际化节点地区键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class WebInputIahnNodeLocaleKey implements Key {

    private static final long serialVersionUID = 7369652489382839652L;

    public static IahnNodeLocaleKey toStackBean(WebInputIahnNodeLocaleKey webInputIahnNodeLocaleKey) {
        if (Objects.isNull(webInputIahnNodeLocaleKey)) {
            return null;
        } else {
            return new IahnNodeLocaleKey(
                    webInputIahnNodeLocaleKey.getNodeStringId(),
                    webInputIahnNodeLocaleKey.getLanguage(),
                    webInputIahnNodeLocaleKey.getCountry(),
                    webInputIahnNodeLocaleKey.getVariant()
            );
        }
    }

    @JSONField(name = "node_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_SETTING_NODE_ID)
    private String nodeStringId;

    @JSONField(name = "language")
    @ValidIahnNodeLanguage
    private String language;

    @JSONField(name = "country")
    @ValidIahnNodeCountry
    private String country;

    @JSONField(name = "variant")
    @ValidIahnNodeVariant
    private String variant;

    public WebInputIahnNodeLocaleKey() {
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

        WebInputIahnNodeLocaleKey that = (WebInputIahnNodeLocaleKey) o;
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
        return "WebInputIahnNodeLocaleKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                '}';
    }
}
