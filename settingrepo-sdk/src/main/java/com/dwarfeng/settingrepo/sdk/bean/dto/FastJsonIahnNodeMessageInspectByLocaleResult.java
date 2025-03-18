package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageInspectByLocaleResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageInspectByLocaleResult.Item;

/**
 * FastJson 国际化节点基于地区批量查询消息结果。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class FastJsonIahnNodeMessageInspectByLocaleResult implements Dto {

    private static final long serialVersionUID = 613525923547340563L;

    public static FastJsonIahnNodeMessageInspectByLocaleResult of(
            IahnNodeMessageInspectByLocaleResult iahnNodeMessageInspectByLocaleResult
    ) {
        if (Objects.isNull(iahnNodeMessageInspectByLocaleResult)) {
            return null;
        } else {
            return new FastJsonIahnNodeMessageInspectByLocaleResult(
                    Optional.ofNullable(iahnNodeMessageInspectByLocaleResult.getItems()).map(
                            f -> f.stream().map(FastJsonItem::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "items", ordinal = 1)
    private List<FastJsonItem> items;

    public FastJsonIahnNodeMessageInspectByLocaleResult() {
    }

    public FastJsonIahnNodeMessageInspectByLocaleResult(List<FastJsonItem> items) {
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
        return "FastJsonIahnNodeMessageInspectByLocaleResult{" +
                "items=" + items +
                '}';
    }

    public static class FastJsonItem implements Dto {

        private static final long serialVersionUID = -7119579550762057543L;

        public static FastJsonItem of(Item item) {
            if (Objects.isNull(item)) {
                return null;
            } else {
                return new FastJsonItem(
                        item.getMekId(),
                        item.getMessage()
                );
            }
        }

        @JSONField(name = "mek_id", ordinal = 1)
        private String mekId;

        @JSONField(name = "message", ordinal = 2)
        private String message;

        public FastJsonItem() {
        }

        public FastJsonItem(String mekId, String message) {
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
            return "FastJsonItem{" +
                    "mekId='" + mekId + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
