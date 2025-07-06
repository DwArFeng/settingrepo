package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeCountry;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeLanguage;
import com.dwarfeng.settingrepo.sdk.util.ValidIahnNodeVariant;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageUpsertByMekInfo;
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

import static com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageUpsertByMekInfo.Item;

/**
 * WebInput 国际化节点基于 Mek 批量插入或更新消息信息。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class WebInputIahnNodeMessageUpsertByMekInfo implements Dto {

    private static final long serialVersionUID = 5538580866745580813L;

    public static IahnNodeMessageUpsertByMekInfo toStackBean(WebInputIahnNodeMessageUpsertByMekInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new IahnNodeMessageUpsertByMekInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getMekId(),
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

    @JSONField(name = "mek_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_IAHN_NODE_MEK_ID)
    private String mekId;

    @JSONField(name = "items")
    @NotNull
    @Valid
    private List<WebInputItem> items;

    public WebInputIahnNodeMessageUpsertByMekInfo() {
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

    public String getMekId() {
        return mekId;
    }

    public void setMekId(String mekId) {
        this.mekId = mekId;
    }

    public List<WebInputItem> getItems() {
        return items;
    }

    public void setItems(List<WebInputItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "WebInputIahnNodeMessageUpsertByMekInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", mekId='" + mekId + '\'' +
                ", items=" + items +
                '}';
    }

    public static class WebInputItem implements Dto {

        private static final long serialVersionUID = 2044386978817104000L;

        public static Item toStackBean(WebInputItem webInput) {
            if (Objects.isNull(webInput)) {
                return null;
            } else {
                return new Item(
                        webInput.getLanguage(),
                        webInput.getCountry(),
                        webInput.getVariant(),
                        webInput.getMessage()
                );
            }
        }

        @JSONField(name = "language")
        @ValidIahnNodeLanguage
        private String language;

        @JSONField(name = "country")
        @ValidIahnNodeCountry
        private String country;

        @JSONField(name = "variant")
        @ValidIahnNodeVariant
        private String variant;

        @JSONField(name = "message")
        @NotNull
        @Length(max = Constraints.LENGTH_IAHN_NODE_MESSAGE)
        private String message;

        public WebInputItem() {
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

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "WebInputItem{" +
                    "language='" + language + '\'' +
                    ", country='" + country + '\'' +
                    ", variant='" + variant + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
