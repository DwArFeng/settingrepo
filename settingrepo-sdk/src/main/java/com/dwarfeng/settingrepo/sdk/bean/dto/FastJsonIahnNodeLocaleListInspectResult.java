package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeLocaleListInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeLocaleListInspectResult.Item;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FastJson 国际化节点地区列表查询结果。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class FastJsonIahnNodeLocaleListInspectResult implements Dto {

    private static final long serialVersionUID = -9024576942121702831L;

    public static FastJsonIahnNodeLocaleListInspectResult of(
            IahnNodeLocaleListInspectResult iahnNodeLocaleListInspectResult
    ) {
        if (Objects.isNull(iahnNodeLocaleListInspectResult)) {
            return null;
        } else {
            return new FastJsonIahnNodeLocaleListInspectResult(
                    Optional.ofNullable(iahnNodeLocaleListInspectResult.getItems()).map(
                            f -> f.stream().map(FastJsonItem::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    public static IahnNodeLocaleListInspectResult toStackBean(
            FastJsonIahnNodeLocaleListInspectResult fastJson
    ) {
        if (Objects.isNull(fastJson)) {
            return null;
        } else {
            return new IahnNodeLocaleListInspectResult(
                    Optional.ofNullable(fastJson.getItems()).map(
                            f -> f.stream().map(FastJsonItem::toStackBean).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "items", ordinal = 1)
    private List<FastJsonItem> items;

    public FastJsonIahnNodeLocaleListInspectResult() {
    }

    public FastJsonIahnNodeLocaleListInspectResult(List<FastJsonItem> items) {
        this.items = items;
    }

    public List<FastJsonItem> getItems() {
        return items;
    }

    public void setItems(List<FastJsonItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "FastJsonIahnNodeLocaleListInspectResult{" +
                "items=" + items +
                '}';
    }

    public static class FastJsonItem implements Dto {

        private static final long serialVersionUID = 5651062817569048282L;

        public static FastJsonItem of(Item item) {
            if (Objects.isNull(item)) {
                return null;
            } else {
                return new FastJsonItem(
                        item.getLanguage(),
                        item.getCountry(),
                        item.getVariant(),
                        item.getLabel(),
                        item.getRemark()
                );
            }
        }

        public static Item toStackBean(FastJsonItem fastJson) {
            if (Objects.isNull(fastJson)) {
                return null;
            } else {
                return new Item(
                        fastJson.getLanguage(),
                        fastJson.getCountry(),
                        fastJson.getVariant(),
                        fastJson.getLabel(),
                        fastJson.getRemark()
                );
            }
        }

        @JSONField(name = "language", ordinal = 1)
        private String language;

        @JSONField(name = "country", ordinal = 2)
        private String country;

        @JSONField(name = "variant", ordinal = 3)
        private String variant;

        @JSONField(name = "label", ordinal = 4)
        private String label;

        @JSONField(name = "remark", ordinal = 5)
        private String remark;

        public FastJsonItem() {
        }

        public FastJsonItem(String language, String country, String variant, String label, String remark) {
            this.language = language;
            this.country = country;
            this.variant = variant;
            this.label = label;
            this.remark = remark;
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
            return "FastJsonItem{" +
                    "language='" + language + '\'' +
                    ", country='" + country + '\'' +
                    ", variant='" + variant + '\'' +
                    ", label='" + label + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }
}
