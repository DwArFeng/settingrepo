package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageTableInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageTableInspectResult.Column;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageTableInspectResult.Row;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageTableInspectResult.RowData;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FastJson 国际化节点消息表查询结果。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class FastJsonIahnNodeMessageTableInspectResult implements Dto {

    private static final long serialVersionUID = 2736931849264518502L;

    public static FastJsonIahnNodeMessageTableInspectResult of(
            IahnNodeMessageTableInspectResult iahnNodeMessageTableInspectResult
    ) {
        if (Objects.isNull(iahnNodeMessageTableInspectResult)) {
            return null;
        } else {
            return new FastJsonIahnNodeMessageTableInspectResult(
                    Optional.ofNullable(iahnNodeMessageTableInspectResult.getColumns()).map(
                            f -> f.stream().map(FastJsonColumn::of).collect(Collectors.toList())
                    ).orElse(null),
                    Optional.ofNullable(iahnNodeMessageTableInspectResult.getRows()).map(
                            f -> f.stream().map(FastJsonRow::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "columns", ordinal = 1)
    private List<FastJsonColumn> columns;

    @JSONField(name = "rows", ordinal = 2)
    private List<FastJsonRow> rows;

    public FastJsonIahnNodeMessageTableInspectResult() {
    }

    public FastJsonIahnNodeMessageTableInspectResult(List<FastJsonColumn> columns, List<FastJsonRow> rows) {
        this.columns = columns;
        this.rows = rows;
    }

    public List<FastJsonColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<FastJsonColumn> columns) {
        this.columns = columns;
    }

    public List<FastJsonRow> getRows() {
        return rows;
    }

    public void setRows(List<FastJsonRow> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "FastJsonIahnNodeMessageTableInspectResult{" +
                "columns=" + columns +
                ", rows=" + rows +
                '}';
    }

    public static class FastJsonColumn implements Dto {

        private static final long serialVersionUID = 2732399126503321691L;

        public static FastJsonColumn of(Column column) {
            if (Objects.isNull(column)) {
                return null;
            } else {
                return new FastJsonColumn(
                        column.getLanguage(),
                        column.getCountry(),
                        column.getVariant(),
                        column.getLabel(),
                        column.getRemark()
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

        public FastJsonColumn() {
        }

        public FastJsonColumn(String language, String country, String variant, String label, String remark) {
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
            return "FastJsonColumn{" +
                    "language='" + language + '\'' +
                    ", country='" + country + '\'' +
                    ", variant='" + variant + '\'' +
                    ", label='" + label + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }

    public static class FastJsonRow implements Dto {

        private static final long serialVersionUID = -864384682524943876L;

        public static FastJsonRow of(Row row) {
            if (Objects.isNull(row)) {
                return null;
            } else {
                return new FastJsonRow(
                        row.getMekId(),
                        row.getLabel(),
                        row.getDefaultMessage(),
                        row.getRemark(),
                        Optional.ofNullable(row.getRowDatas()).map(
                                f -> f.stream().map(FastJsonRowData::of).collect(Collectors.toList())
                        ).orElse(null)
                );
            }
        }

        @JSONField(name = "mek_id", ordinal = 1)
        private String mekId;

        @JSONField(name = "label", ordinal = 2)
        private String label;

        @JSONField(name = "default_message", ordinal = 3)
        private String defaultMessage;

        @JSONField(name = "remark", ordinal = 4)
        private String remark;

        @JSONField(name = "row_datas", ordinal = 5)
        private List<FastJsonRowData> rowDatas;

        public FastJsonRow() {
        }

        public FastJsonRow(
                String mekId, String label, String defaultMessage, String remark, List<FastJsonRowData> rowDatas
        ) {
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

        public List<FastJsonRowData> getRowDatas() {
            return rowDatas;
        }

        public void setRowDatas(List<FastJsonRowData> rowDatas) {
            this.rowDatas = rowDatas;
        }

        @Override
        public String toString() {
            return "FastJsonRow{" +
                    "mekId='" + mekId + '\'' +
                    ", label='" + label + '\'' +
                    ", defaultMessage='" + defaultMessage + '\'' +
                    ", remark='" + remark + '\'' +
                    ", rowDatas=" + rowDatas +
                    '}';
        }
    }

    public static class FastJsonRowData implements Dto {

        private static final long serialVersionUID = 3694522375231822075L;

        public static FastJsonRowData of(RowData rowData) {
            if (Objects.isNull(rowData)) {
                return null;
            } else {
                return new FastJsonRowData(
                        rowData.getMessage()
                );
            }
        }

        @JSONField(name = "message", ordinal = 1)
        private String message;

        public FastJsonRowData() {
        }

        public FastJsonRowData(String message) {
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
            return "FastJsonRowData{" +
                    "message='" + message + '\'' +
                    '}';
        }
    }
}
