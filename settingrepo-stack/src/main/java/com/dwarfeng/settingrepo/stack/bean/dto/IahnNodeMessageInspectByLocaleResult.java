package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.List;

/**
 * 国际化节点基于地区批量查询消息结果。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeMessageInspectByLocaleResult implements Dto {

    @Serial
    private static final long serialVersionUID = 8953738290232058729L;

    private List<Item> items;

    public IahnNodeMessageInspectByLocaleResult() {
    }

    public IahnNodeMessageInspectByLocaleResult(List<Item> items) {
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
        return "IahnNodeMessageInspectByLocaleResult{" +
                "items=" + items +
                '}';
    }

    public static class Item implements Dto {

        @Serial
        private static final long serialVersionUID = 1878406404737966196L;

        private String mekId;
        private String message;

        public Item() {
        }

        public Item(String mekId, String message) {
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
            return "Item{" +
                    "mekId='" + mekId + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
