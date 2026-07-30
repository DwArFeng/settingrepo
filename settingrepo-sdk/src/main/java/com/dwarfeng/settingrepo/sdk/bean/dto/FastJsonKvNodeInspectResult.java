package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.KvNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.KvNodeInspectResult.Item;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;

import java.io.Serial;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FastJson 键值对节点查看结果。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class FastJsonKvNodeInspectResult implements Bean {

    @Serial
    private static final long serialVersionUID = -3641310912673093348L;

    public static FastJsonKvNodeInspectResult of(KvNodeInspectResult kvNodeInspectResult) {
        if (Objects.isNull(kvNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonKvNodeInspectResult(
                    Optional.ofNullable(kvNodeInspectResult.getItems()).map(
                            f -> f.stream().map(FastJsonItem::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "items", ordinal = 1)
    private List<FastJsonItem> items;

    public FastJsonKvNodeInspectResult() {
    }

    public FastJsonKvNodeInspectResult(List<FastJsonItem> items) {
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
        return "FastJsonKvNodeInspectResult{" +
                "items=" + items +
                '}';
    }

    /**
     * FastJson 键值对节点查看结果条目。
     *
     * @author DwArFeng
     * @since 2.5.0
     */
    public static class FastJsonItem implements Bean {

        @Serial
        private static final long serialVersionUID = 4410906307489861455L;

        public static FastJsonItem of(Item item) {
            if (Objects.isNull(item)) {
                return null;
            } else {
                return new FastJsonItem(
                        item.getItemStringId(),
                        item.getValue()
                );
            }
        }

        @JSONField(name = "item_string_id", ordinal = 1)
        private String itemStringId;

        @JSONField(name = "value", ordinal = 2)
        private String value;

        public FastJsonItem() {
        }

        public FastJsonItem(String itemStringId, String value) {
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
            return "FastJsonItem{" +
                    "itemStringId='" + itemStringId + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }
}
