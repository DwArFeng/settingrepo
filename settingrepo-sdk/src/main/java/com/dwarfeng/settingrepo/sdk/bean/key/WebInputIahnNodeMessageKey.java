package com.dwarfeng.settingrepo.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeCountry;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeLanguage;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeVariant;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 国际化节点消息键。
 *
 * @author mooyuan
 * @author DwArFeng
 * @since 2.1.0
 */
public class WebInputIahnNodeMessageKey implements Key {

    private static final long serialVersionUID = -6387528343969647832L;

    public static IahnNodeMessageKey toStackBean(WebInputIahnNodeMessageKey webInputIahnNodeMessageKey) {
        if (Objects.isNull(webInputIahnNodeMessageKey)) {
            return null;
        } else {
            return new IahnNodeMessageKey(
                    webInputIahnNodeMessageKey.getNodeStringId(),
                    webInputIahnNodeMessageKey.getLanguage(),
                    webInputIahnNodeMessageKey.getCountry(),
                    webInputIahnNodeMessageKey.getVariant(),
                    webInputIahnNodeMessageKey.getMekId()
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

    @JSONField(name = "mek_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_IAHN_NODE_MEK_ID)
    private String mekId;

    public WebInputIahnNodeMessageKey() {
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

        WebInputIahnNodeMessageKey that = (WebInputIahnNodeMessageKey) o;
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
        return "WebInputIahnNodeMessageKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                ", mekId='" + mekId + '\'' +
                '}';
    }
}
