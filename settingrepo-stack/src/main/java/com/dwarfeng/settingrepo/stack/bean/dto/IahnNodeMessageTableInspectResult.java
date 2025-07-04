package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;

/**
 * 国际化节点消息表查询结果。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class IahnNodeMessageTableInspectResult implements Dto {

    private static final long serialVersionUID = -4858521834311748731L;

    private List<Column> columns;
    private List<Row> rows;

    public IahnNodeMessageTableInspectResult() {
    }

    public IahnNodeMessageTableInspectResult(List<Column> columns, List<Row> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "IahnNodeMessageTableInspectResult{" +
                "columns=" + columns +
                ", rows=" + rows +
                '}';
    }

    public static class Column implements Dto {

        private static final long serialVersionUID = 766419378907398811L;

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

        public Column() {
        }

        public Column(String language, String country, String variant, String label, String remark) {
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
            return "Column{" +
                    "language='" + language + '\'' +
                    ", country='" + country + '\'' +
                    ", variant='" + variant + '\'' +
                    ", label='" + label + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    public static class Row implements Dto {

        private static final long serialVersionUID = -8707280150292419293L;

        private String mekId;
        private String label;
        private String defaultMessage;
        private String remark;
        private List<RowData> rowDatas;

        public Row() {
        }

        public Row(String mekId, String label, String defaultMessage, String remark, List<RowData> rowDatas) {
            this.mekId = mekId;
            this.label = label;
            this.defaultMessage = defaultMessage;
            this.remark = remark;
            this.rowDatas = rowDatas;
        }

        public String getMekId() {
            return mekId;
        }

        public void setMekId(String mekId) {
            this.mekId = mekId;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getDefaultMessage() {
            return defaultMessage;
        }

        public void setDefaultMessage(String defaultMessage) {
            this.defaultMessage = defaultMessage;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<RowData> getRowDatas() {
            return rowDatas;
        }

        public void setRowDatas(List<RowData> rowDatas) {
            this.rowDatas = rowDatas;
        }

        @Override
        public String toString() {
            return "Row{" +
                    "mekId='" + mekId + '\'' +
                    ", label='" + label + '\'' +
                    ", defaultMessage='" + defaultMessage + '\'' +
                    ", remark='" + remark + '\'' +
                    ", rowDatas=" + rowDatas +
                    '}';
        }
    }

    public static class RowData implements Dto {

        private static final long serialVersionUID = 4068057436578441062L;

        private String message;

        public RowData() {
        }

        public RowData(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "RowData{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }
}
