package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeCountry;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeLanguage;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeVariant;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageUpsertByLocaleInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageUpsertByLocaleInfo.Item;

/**
 * WebInput 国际化节点基于地区批量插入或更新消息信息。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class WebInputIahnNodeMessageUpsertByLocaleInfo implements Dto {

    private static final long serialVersionUID = -8846595784119244499L;

    public static IahnNodeMessageUpsertByLocaleInfo toStackBean(WebInputIahnNodeMessageUpsertByLocaleInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new IahnNodeMessageUpsertByLocaleInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getLanguage(),
                    webInput.getCountry(),
                    webInput.getVariant(),
                    Optional.ofNullable(webInput.getItems()).map(
                            f -> f.stream().map(WebInputItem::toStackBean).collect(Collectors.toList())
                    ).orElse(null)
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

    @JSONField(name = "items")
    @NotNull
    @Valid
    private List<WebInputItem> items;

    public WebInputIahnNodeMessageUpsertByLocaleInfo() {
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

    public List<WebInputItem> getItems() {
        return items;
    }

    public void setItems(List<WebInputItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "WebInputIahnNodeMessageUpsertByLocaleInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", variant='" + variant + '\'' +
                ", items=" + items +
                '}';
    }

    public static class WebInputItem implements Dto {

        private static final long serialVersionUID = 2336064879979288811L;

        public static Item toStackBean(WebInputItem webInput) {
            if (Objects.isNull(webInput)) {
                return null;
            } else {
                return new Item(
                        webInput.getMekId(),
                        webInput.getMessage()
                );
            }
        }

        @JSONField(name = "mek_id")
        @NotNull
        @NotEmpty
        @Length(max = Constraints.LENGTH_IAHN_NODE_MEK_ID)
        private String mekId;

        @JSONField(name = "message")
        @NotNull
        @Length(max = Constraints.LENGTH_IAHN_NODE_MESSAGE)
        private String message;

        public WebInputItem() {
        }

        public WebInputItem(String mekId, String message) {
            this.mekId = mekId;
            this.message = message;
        }

        public String getMekId() {
            return mekId;
        }

        public void setMekId(String mekId) {
            this.mekId = mekId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "WebInputItem{" +
                    "mekId='" + mekId + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
