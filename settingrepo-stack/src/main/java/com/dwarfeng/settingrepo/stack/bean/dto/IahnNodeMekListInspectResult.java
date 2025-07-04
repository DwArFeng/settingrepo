package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;

/**
 * 国际化节点 Mek 列表查询结果。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class IahnNodeMekListInspectResult implements Dto {

    private static final long serialVersionUID = 4564127871356444078L;

    private List<Item> items;

    public IahnNodeMekListInspectResult() {
    }

    public IahnNodeMekListInspectResult(List<Item> items) {
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
        return "IahnNodeMekListInspectResult{" +
                "items=" + items +
                '}';
    }

    public static class Item implements Dto {

        private static final long serialVersionUID = 8665069976405039437L;

        private String mekId;
        private String label;
        private String defaultMessage;
        private String remark;

        public Item() {
        }

        public Item(String mekId, String label, String defaultMessage, String remark) {
            this.mekId = mekId;
            this.label = label;
            this.defaultMessage = defaultMessage;
            this.remark = remark;
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

        @Override
        public String toString() {
            return "Item{" +
                    "mekId='" + mekId + '\'' +
                    ", label='" + label + '\'' +
                    ", defaultMessage='" + defaultMessage + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }
}
