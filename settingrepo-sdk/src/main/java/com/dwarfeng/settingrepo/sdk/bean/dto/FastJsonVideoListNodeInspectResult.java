package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.VideoListNodeInspectResult;
import com.dwarfeng.settingrepo.stack.bean.dto.VideoListNodeInspectResult.Item;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * FastJson 视频列表节点查看结果。
 *
 * @author fucl
 * @since 3.2.0
 */
public class FastJsonVideoListNodeInspectResult implements Dto {

    private static final long serialVersionUID = 5343042325272389088L;

    public static FastJsonVideoListNodeInspectResult of(VideoListNodeInspectResult videoListNodeInspectResult) {
        if (Objects.isNull(videoListNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonVideoListNodeInspectResult(
                    Optional.ofNullable(videoListNodeInspectResult.getItems()).map(
                            f -> f.stream().map(FastJsonItem::of).collect(Collectors.toList())
                    ).orElse(null)
            );
        }
    }

    @JSONField(name = "items", ordinal = 1)
    private List<FastJsonItem> items;

    public FastJsonVideoListNodeInspectResult() {
    }

    public FastJsonVideoListNodeInspectResult(List<FastJsonItem> items) {
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
        return "FastJsonVideoListNodeInspectResult{" +
                "items=" + items +
                '}';
    }

    /**
     * 视频列表节点查看结果条目。
     *
     * @author fucl
     * @since 3.2.0
     */
    public static class FastJsonItem implements Dto {

        private static final long serialVersionUID = -2576817554551549821L;

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
