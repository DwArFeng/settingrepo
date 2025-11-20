package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;

/**
 * 文件列表节点查看结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeInspectResult implements Dto {

    private static final long serialVersionUID = 993838744580415269L;

    private List<Item> items;

    public FileListNodeInspectResult() {
    }

    public FileListNodeInspectResult(List<Item> items) {
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
        return "FileListNodeInspectResult{" +
                "items=" + items +
                '}';
    }

    /**
     * 文件列表节点查看结果条目。
     *
     * @author DwArFeng
     * @since 2.4.2
     */
    public static class Item implements Dto {

        private static final long serialVersionUID = 583878758905613109L;

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
