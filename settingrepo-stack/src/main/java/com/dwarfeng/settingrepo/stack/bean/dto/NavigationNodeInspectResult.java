package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.List;

/**
 * 导航节点查看结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class NavigationNodeInspectResult implements Dto {

    private static final long serialVersionUID = -7731743565787053033L;

    /**
     * 导航节点的数量。
     */
    private int count;

    /**
     * 导航节点条目列表（树形嵌套结构，同级节点按 index 升序）。
     */
    private List<Item> children;

    public NavigationNodeInspectResult() {
    }

    public NavigationNodeInspectResult(int count, List<Item> children) {
        this.count = count;
        this.children = children;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Item> getChildren() {
        return children;
    }

    public void setChildren(List<Item> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "NavigationNodeInspectResult{" +
                "count=" + count +
                ", children=" + children +
                '}';
    }

    /**
     * 导航节点查看结果条目。
     *
     * @author DwArFeng
     * @since 2.4.2
     */
    public static class Item implements Dto {

        private static final long serialVersionUID = -7179048417214123643L;

        /**
         * 节点条目主键。
         */
        private LongIdKey key;

        /**
         * 节点父条目主键。
         */
        private LongIdKey parentItemKey;

        /**
         * 索引值。
         */
        private int index;

        /**
         * 名称。
         */
        private String name;

        /**
         * 内容。
         */
        private String content;

        /**
         * 备注。
         */
        private String remark;

        /**
         * 子节点列表（按 index 升序）。
         */
        private List<Item> children;

        public Item() {
        }

        public Item(
                LongIdKey key, LongIdKey parentItemKey, int index, String name, String content, String remark,
                List<Item> children
        ) {
            this.key = key;
            this.parentItemKey = parentItemKey;
            this.index = index;
            this.name = name;
            this.content = content;
            this.remark = remark;
            this.children = children;
        }

        public LongIdKey getKey() {
            return key;
        }

        public void setKey(LongIdKey key) {
            this.key = key;
        }

        public LongIdKey getParentItemKey() {
            return parentItemKey;
        }

        public void setParentItemKey(LongIdKey parentItemKey) {
            this.parentItemKey = parentItemKey;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public List<Item> getChildren() {
            return children;
        }

        public void setChildren(List<Item> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "key=" + key +
                    ", parentItemKey=" + parentItemKey +
                    ", index=" + index +
                    ", name='" + name + '\'' +
                    ", content='" + content + '\'' +
                    ", remark='" + remark + '\'' +
                    ", children=" + children +
                    '}';
        }
    }
}
