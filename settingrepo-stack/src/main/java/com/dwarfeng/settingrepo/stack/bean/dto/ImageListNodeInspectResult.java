package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;

/**
 * 图片列表节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeInspectResult implements Dto {

    private static final long serialVersionUID = -736868678879487633L;

    private List<Item> items;

    public ImageListNodeInspectResult() {
    }

    public ImageListNodeInspectResult(List<Item> items) {
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
        return "ImageListNodeInspectResult{" +
                "items=" + items +
                '}';
    }

    /**
     * 图片列表节点查看结果条目。
     *
     * @author DwArFeng
     * @since 2.0.0
     */
    public static class Item implements Dto {

        private static final long serialVersionUID = -1610935291935138615L;

        private boolean nullFlag;
        private String originName;
        private Long length;

        public Item() {
        }

        public Item(boolean nullFlag, String originName, Long length) {
            this.nullFlag = nullFlag;
            this.originName = originName;
            this.length = length;
        }

        public boolean isNullFlag() {
            return nullFlag;
        }

        public void setNullFlag(boolean nullFlag) {
            this.nullFlag = nullFlag;
        }

        public String getOriginName() {
            return originName;
        }

        public void setOriginName(String originName) {
            this.originName = originName;
        }

        public Long getLength() {
            return length;
        }

        public void setLength(Long length) {
            this.length = length;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "nullFlag=" + nullFlag +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    '}';
        }
    }
}
