package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;

/**
 * 文本列表节点查看结果。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TextListNodeInspectResult implements Dto {

    private static final long serialVersionUID = 6276317432255513047L;

    private List<Item> items;

    public TextListNodeInspectResult() {
    }

    public TextListNodeInspectResult(List<Item> items) {
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
        return "TextListNodeInspectResult{" +
                "items=" + items +
                '}';
    }

    /**
     * 文本列表节点查看结果条目。
     *
     * <p>
     * 条目仅描述列表结构和位置，不包含文本内容；文本内容通过索引单独获取。
     *
     * @author DwArFeng
     * @since 3.2.0
     */
    public static class Item implements Dto {

        private static final long serialVersionUID = -6610367546491375756L;

        private int index;
        private boolean nullFlag;

        public Item() {
        }

        public Item(int index, boolean nullFlag) {
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
            return "Item{" +
                    "index=" + index +
                    ", nullFlag=" + nullFlag +
                    '}';
        }
    }
}
