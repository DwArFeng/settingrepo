package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeCountry;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeLanguage;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeVariant;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageInspectByLocaleInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 国际化节点基于地区批量查询消息信息。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class WebInputIahnNodeMessageInspectByLocaleInfo implements Dto {

    private static final long serialVersionUID = -6251025315885031399L;

    public static IahnNodeMessageInspectByLocaleInfo toStackBean(WebInputIahnNodeMessageInspectByLocaleInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new IahnNodeMessageInspectByLocaleInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getLanguage(),
                    webInput.getCountry(),
                    webInput.getVariant()
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
    @ValidIahnNodeLanguage
    private String language;

    @JSONField(name = "country")
    @ValidIahnNodeCountry
    private String country;

    @JSONField(name = "variant")
    @ValidIahnNodeVariant
    private String variant;

    public WebInputIahnNodeMessageInspectByLocaleInfo() {
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

    @Override
    public String toString() {
        return "WebInputIahnNodeMessageInspectByLocaleInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                '}';
    }
}
