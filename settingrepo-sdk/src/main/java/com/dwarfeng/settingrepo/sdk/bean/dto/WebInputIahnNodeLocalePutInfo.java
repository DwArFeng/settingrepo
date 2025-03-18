package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeLocalePutInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 国际化地区推入信息。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class WebInputIahnNodeLocalePutInfo implements Dto {

    private static final long serialVersionUID = -1018021530429573650L;

    public static IahnNodeLocalePutInfo toStackBean(WebInputIahnNodeLocalePutInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new IahnNodeLocalePutInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getLanguage(),
                    webInput.getCountry(),
                    webInput.getVariant(),
                    webInput.getLabel(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "category")
    @NotNull
    @NotEmpty
    private String category;

    @JSONField(name = "args")
    @NotNull
    private String[] args;

    @JSONField(name = "language")
    @NotNull
    @NotEmpty
    @Length(min = Constraints.LENGTH_IAHN_NODE_LANGUAGE_MIN, max = Constraints.LENGTH_IAHN_NODE_LANGUAGE_MAX)
    private String language;

    @JSONField(name = "country")
    @NotNull
    @Length(min = Constraints.LENGTH_IAHN_NODE_COUNTRY_MIN, max = Constraints.LENGTH_IAHN_NODE_COUNTRY_MAX)
    private String country;

    @JSONField(name = "variant")
    @NotNull
    @Length(min = Constraints.LENGTH_IAHN_NODE_VARIANT_MIN, max = Constraints.LENGTH_IAHN_NODE_VARIANT_MAX)
    private String variant;

    @JSONField(name = "label")
    @Length(max = Constraints.LENGTH_NAME)
    private String label;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputIahnNodeLocalePutInfo() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
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

    @Override
    public String toString() {
        return "WebInputIahnNodeLocalePutInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                ", label='" + label + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
