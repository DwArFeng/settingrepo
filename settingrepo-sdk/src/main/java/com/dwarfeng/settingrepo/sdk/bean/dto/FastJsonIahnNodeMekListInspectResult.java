package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMekListInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMekListInspectResult.Item;

/**
 * FastJson 国际化节点 Mek 列表查询结果。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class FastJsonIahnNodeMekListInspectResult implements Dto {

    private static final long serialVersionUID = 3852471955324015030L;

    public static FastJsonIahnNodeMekListInspectResult of(IahnNodeMekListInspectResult iahnNodeMekListInspectResult) {
        if (Objects.isNull(iahnNodeMekListInspectResult)) {
            return null;
        } else {
            return new FastJsonIahnNodeMekListInspectResult(
                    Optional.ofNullable(iahnNodeMekListInspectResult.getItems()).map(
                            f -> f.stream().map(FastJsonItem::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "items", ordinal = 1)
    private List<FastJsonItem> items;

    public FastJsonIahnNodeMekListInspectResult() {
    }

    public FastJsonIahnNodeMekListInspectResult(List<FastJsonItem> items) {
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
        return "FastJsonIahnNodeMekListInspectResult{" +
                "items=" + items +
                '}';
    }

    public static class FastJsonItem implements Dto {

        private static final long serialVersionUID = 8830581739508158992L;

        public static FastJsonItem of(Item item) {
            if (Objects.isNull(item)) {
                return null;
            } else {
                return new FastJsonItem(
                        item.getMekId(),
                        item.getLabel(),
                        item.getDefaultMessage(),
                        item.getRemark()
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

        public FastJsonItem() {
        }

        public FastJsonItem(String mekId, String label, String defaultMessage, String remark) {
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
            return "FastJsonItem{" +
                    "mekId='" + mekId + '\'' +
                    ", label='" + label + '\'' +
                    ", defaultMessage='" + defaultMessage + '\'' +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }
}
