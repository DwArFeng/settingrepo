package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.List;

/**
 * 键值对节点查看结果。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeInspectResult implements Dto {

    @Serial
    private static final long serialVersionUID = 6019999587413758743L;

    private List<Item> items;

    public KvNodeInspectResult() {
    }

    public KvNodeInspectResult(List<Item> items) {
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
        return "KvNodeInspectResult{" +
                "items=" + items +
                '}';
    }

    /**
     * 键值对节点查看结果条目。
     *
     * @author DwArFeng
     * @since 2.5.0
     */
    public static class Item implements Dto {

        @Serial
        private static final long serialVersionUID = 2644265322226802992L;

        private String itemStringId;
        private String value;

        public Item() {
        }

        public Item(String itemStringId, String value) {
            this.itemStringId = itemStringId;
            this.value = value;
        }

        public String getItemStringId() {
            return itemStringId;
        }

        public void setItemStringId(String itemStringId) {
            this.itemStringId = itemStringId;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "itemStringId='" + itemStringId + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
