package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.TextListNodeInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dwarfeng.settingrepo.stack.bean.dto.TextListNodeInspectResult.Item;

/**
 * FastJson 文本列表节点查看结果。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class FastJsonTextListNodeInspectResult implements Dto {

    private static final long serialVersionUID = -4640155973036379265L;

    public static FastJsonTextListNodeInspectResult of(TextListNodeInspectResult textListNodeInspectResult) {
        if (Objects.isNull(textListNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonTextListNodeInspectResult(
                    Optional.ofNullable(textListNodeInspectResult.getItems()).map(
                            f -> f.stream().map(FastJsonItem::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "items", ordinal = 1)
    private List<FastJsonItem> items;

    public FastJsonTextListNodeInspectResult() {
    }

    public FastJsonTextListNodeInspectResult(List<FastJsonItem> items) {
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
        return "FastJsonTextListNodeInspectResult{" +
                "items=" + items +
                '}';
    }

    /**
     * 文本列表节点查看结果条目。
     *
     * <p>
     * 条目仅包含列表位置与空缺标记，不携带文本内容。
     *
     * @author DwArFeng
     * @since 3.2.0
     */
    public static class FastJsonItem {

        public static FastJsonItem of(Item item) {
            if (Objects.isNull(item)) {
                return null;
            } else {
                return new FastJsonItem(
                        item.getIndex(),
                        item.isNullFlag()
                );
            }
        }

        @JSONField(name = "index", ordinal = 1)
        private int index;

        @JSONField(name = "null_flag", ordinal = 2)
        private boolean nullFlag;

        public FastJsonItem() {
        }

        public FastJsonItem(int index, boolean nullFlag) {
            this.index = index;
            this.nullFlag = nullFlag;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public boolean isNullFlag() {
            return nullFlag;
        }

        public void setNullFlag(boolean nullFlag) {
            this.nullFlag = nullFlag;
        }

        @Override
        public String toString() {
            return "FastJsonItem{" +
                    "index=" + index +
                    ", nullFlag=" + nullFlag +
                    '}';
        }
    }
}
