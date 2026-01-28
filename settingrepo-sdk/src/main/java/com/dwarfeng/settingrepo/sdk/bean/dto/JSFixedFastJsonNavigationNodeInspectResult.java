package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeInspectResult.Item;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * JSFixed FastJson 导航节点查看结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class JSFixedFastJsonNavigationNodeInspectResult implements Bean {

    private static final long serialVersionUID = 717591849167078619L;

    public static JSFixedFastJsonNavigationNodeInspectResult of(
            NavigationNodeInspectResult navigationNodeInspectResult
    ) {
        if (Objects.isNull(navigationNodeInspectResult)) {
            return null;
        } else {
            return new JSFixedFastJsonNavigationNodeInspectResult(
                    navigationNodeInspectResult.getCount(),
                    navigationNodeInspectResult.getContent(),
                    Optional.ofNullable(navigationNodeInspectResult.getChildren()).map(
                            f -> f.stream().map(JSFixedFastJsonItem::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "count", ordinal = 1)
    private int count;

    @JSONField(name = "content", ordinal = 2)
    private String content;

    @JSONField(name = "children", ordinal = 3)
    private List<JSFixedFastJsonItem> children;

    public JSFixedFastJsonNavigationNodeInspectResult() {
    }

    public JSFixedFastJsonNavigationNodeInspectResult(int count, String content, List<JSFixedFastJsonItem> children) {
        this.count = count;
        this.content = content;
        this.children = children;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<JSFixedFastJsonItem> getChildren() {
        return children;
    }

    public void setChildren(List<JSFixedFastJsonItem> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonNavigationNodeInspectResult{" +
                "count=" + count +
                ", content='" + content + '\'' +
                ", children=" + children +
                '}';
    }

    /**
     * JSFixed FastJson 导航节点查看结果条目。
     *
     * @author DwArFeng
     * @since 2.4.2
     */
    public static class JSFixedFastJsonItem implements Bean {

        private static final long serialVersionUID = -7458442234882016966L;

        public static JSFixedFastJsonItem of(Item item) {
            if (Objects.isNull(item)) {
                return null;
            } else {
                return new JSFixedFastJsonItem(
                        JSFixedFastJsonLongIdKey.of(item.getKey()),
                        JSFixedFastJsonLongIdKey.of(item.getParentItemKey()),
                        item.getIndex(),
                        item.getName(),
                        item.getContent(),
                        item.getRemark(),
                        Optional.ofNullable(item.getChildren()).map(
                                f -> f.stream().map(JSFixedFastJsonItem::of).collect(Collectors.toList())
                        ).orElse(null)
                );
            }
        }

        @JSONField(name = "key", ordinal = 1)
        private JSFixedFastJsonLongIdKey key;

        @JSONField(name = "parent_item_key", ordinal = 2)
        private JSFixedFastJsonLongIdKey parentItemKey;

        @JSONField(name = "index", ordinal = 3)
        private int index;

        @JSONField(name = "name", ordinal = 4)
        private String name;

        @JSONField(name = "content", ordinal = 5)
        private String content;

        @JSONField(name = "remark", ordinal = 6)
        private String remark;

        @JSONField(name = "children", ordinal = 7)
        private List<JSFixedFastJsonItem> children;

        public JSFixedFastJsonItem() {
        }

        public JSFixedFastJsonItem(
                JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey parentItemKey, int index, String name,
                String content, String remark, List<JSFixedFastJsonItem> children
        ) {
            this.key = key;
            this.parentItemKey = parentItemKey;
            this.index = index;
            this.name = name;
            this.content = content;
            this.remark = remark;
            this.children = children;
        }

        public JSFixedFastJsonLongIdKey getKey() {
            return key;
        }

        public void setKey(JSFixedFastJsonLongIdKey key) {
            this.key = key;
        }

        public JSFixedFastJsonLongIdKey getParentItemKey() {
            return parentItemKey;
        }

        public void setParentItemKey(JSFixedFastJsonLongIdKey parentItemKey) {
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

        public List<JSFixedFastJsonItem> getChildren() {
            return children;
        }

        public void setChildren(List<JSFixedFastJsonItem> children) {
            this.children = children;
        }

        @Override
        public String toString() {
            return "JSFixedFastJsonItem{" +
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
