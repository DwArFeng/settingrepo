package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;

/**
 * 国际化节点地区列表查询结果。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class IahnNodeLocaleListInspectResult implements Dto {

    private static final long serialVersionUID = -2259917844640719856L;

    private List<Item> items;

    public IahnNodeLocaleListInspectResult() {
    }

    public IahnNodeLocaleListInspectResult(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "IahnNodeLocaleListInspectResult{" +
                "items=" + items +
                '}';
    }

    public static class Item implements Dto {

        private static final long serialVersionUID = -5815391213428133528L;

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

        private String label;
        private String remark;

        public Item() {
        }

        public Item(String language, String country, String variant, String label, String remark) {
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
            return "Item{" +
                    "language='" + language + '\'' +
                    ", country='" + country + '\'' +
                    ", variant='" + variant + '\'' +
                    ", label='" + label + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }
}
