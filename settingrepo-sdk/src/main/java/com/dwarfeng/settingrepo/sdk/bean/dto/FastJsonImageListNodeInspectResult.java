package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageListNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageListNodeInspectResult.Item;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FastJson 图片列表节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonImageListNodeInspectResult implements Dto {

    @Serial
    private static final long serialVersionUID = 5211259221371269135L;

    public static FastJsonImageListNodeInspectResult of(ImageListNodeInspectResult imageListNodeInspectResult) {
        if (Objects.isNull(imageListNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonImageListNodeInspectResult(
                    Optional.ofNullable(imageListNodeInspectResult.getItems()).map(
                            f -> f.stream().map(FastJsonItem::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "items", ordinal = 1)
    private List<FastJsonItem> items;

    public FastJsonImageListNodeInspectResult() {
    }

    public FastJsonImageListNodeInspectResult(List<FastJsonItem> items) {
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
        return "FastJsonImageListNodeInspectResult{" +
                "items=" + items +
                '}';
    }

    /**
     * 图片列表节点查看结果条目。
     *
     * @author DwArFeng
     * @since 2.0.0
     */
    public static class FastJsonItem implements Dto {

        @Serial
        private static final long serialVersionUID = 7820511900213122858L;

        public static FastJsonItem of(Item item) {
            if (Objects.isNull(item)) {
                return null;
            } else {
                return new FastJsonItem(
                        item.isNullFlag(),
                        item.getOriginName(),
                        item.getLength()
                );
            }
        }

        @JSONField(name = "null_flag", ordinal = 1)
        private boolean nullFlag;

        @JSONField(name = "origin_name", ordinal = 2)
        private String originName;

        @JSONField(name = "length", ordinal = 3)
        private Long length;

        public FastJsonItem() {
        }

        public FastJsonItem(boolean nullFlag, String originName, Long length) {
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
            return "FastJsonItem{" +
                    "nullFlag=" + nullFlag +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    '}';
        }
    }
}
