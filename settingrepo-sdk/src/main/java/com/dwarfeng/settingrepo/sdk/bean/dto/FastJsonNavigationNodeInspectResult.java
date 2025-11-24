package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeInspectResult.Item;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FastJson 导航节点查看结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FastJsonNavigationNodeInspectResult implements Bean {

    private static final long serialVersionUID = 2048019053006496430L;

    public static FastJsonNavigationNodeInspectResult of(NavigationNodeInspectResult navigationNodeInspectResult) {
        if (Objects.isNull(navigationNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonNavigationNodeInspectResult(
                    navigationNodeInspectResult.getCount(),
                    Optional.ofNullable(navigationNodeInspectResult.getChildren()).map(
                            f -> f.stream().map(FastJsonItem::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    public static NavigationNodeInspectResult toStackBean(FastJsonNavigationNodeInspectResult fastJson) {
        if (Objects.isNull(fastJson)) {
            return null;
        } else {
            return new NavigationNodeInspectResult(
                    fastJson.getCount(),
                    Optional.ofNullable(fastJson.getChildren()).map(
                            f -> f.stream().map(FastJsonItem::toStackBean).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "count", ordinal = 1)
    private int count;

    @JSONField(name = "children", ordinal = 2)
    private List<FastJsonItem> children;

    public FastJsonNavigationNodeInspectResult() {
    }

    public FastJsonNavigationNodeInspectResult(int count, List<FastJsonItem> children) {
        this.count = count;
        this.children = children;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<FastJsonItem> getChildren() {
        return children;
    }

    public void setChildren(List<FastJsonItem> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "FastJsonNavigationNodeInspectResult{" +
                "count=" + count +
                ", children=" + children +
                '}';
    }

    /**
     * FastJson 导航节点查看结果条目。
     *
     * @author DwArFeng
     * @since 2.4.2
     */
    public static class FastJsonItem implements Bean {

        private static final long serialVersionUID = 7675144224470540647L;

        public static FastJsonItem of(Item item) {
            if (Objects.isNull(item)) {
                return null;
            } else {
                return new FastJsonItem(
                        FastJsonLongIdKey.of(item.getKey()),
                        FastJsonLongIdKey.of(item.getParentItemKey()),
                        item.getIndex(),
                        item.getName(),
                        item.getContent(),
                        item.getRemark(),
                        Optional.ofNullable(item.getChildren()).map(
                                f -> f.stream().map(FastJsonItem::of).collect(Collectors.toList())
                        ).orElse(null)
                );
            }
        }

        public static Item toStackBean(FastJsonItem fastJson) {
            if (Objects.isNull(fastJson)) {
                return null;
            } else {
                return new Item(
                        FastJsonLongIdKey.toStackBean(fastJson.getKey()),
                        FastJsonLongIdKey.toStackBean(fastJson.getParentItemKey()),
                        fastJson.getIndex(),
                        fastJson.getName(),
                        fastJson.getContent(),
                        fastJson.getRemark(),
                        Optional.ofNullable(fastJson.getChildren()).map(
                                f -> f.stream().map(FastJsonItem::toStackBean).collect(Collectors.toList())
                        ).orElse(null)
                );
            }
        }

        @JSONField(name = "key", ordinal = 1)
        private FastJsonLongIdKey key;

        @JSONField(name = "parent_item_key", ordinal = 2)
        private FastJsonLongIdKey parentItemKey;

        @JSONField(name = "index", ordinal = 3)
        private int index;

        @JSONField(name = "name", ordinal = 4)
        private String name;

        @JSONField(name = "content", ordinal = 5)
        private String content;

        @JSONField(name = "remark", ordinal = 6)
        private String remark;

        @JSONField(name = "children", ordinal = 7)
        private List<FastJsonItem> children;

        public FastJsonItem() {
        }

        public FastJsonItem(
                FastJsonLongIdKey key, FastJsonLongIdKey parentItemKey, int index, String name, String content,
                String remark, List<FastJsonItem> children
        ) {
            this.key = key;
            this.parentItemKey = parentItemKey;
            this.index = index;
            this.name = name;
            this.content = content;
            this.remark = remark;
            this.children = children;
        }

        public FastJsonLongIdKey getKey() {
            return key;
        }

        public void setKey(FastJsonLongIdKey key) {
            this.key = key;
        }

        public FastJsonLongIdKey getParentItemKey() {
            return parentItemKey;
        }

        public void setParentItemKey(FastJsonLongIdKey parentItemKey) {
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

        public List<FastJsonItem> getChildren() {
            return children;
        }

        public void setChildren(List<FastJsonItem> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "FastJsonItem{" +
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
