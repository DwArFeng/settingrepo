package com.dwarfeng.settingrepo.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 键值对条目节点 键。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class FastJsonKvNodeItemKey implements Key {

    private static final long serialVersionUID = 735247462787452878L;

    public static FastJsonKvNodeItemKey of(KvNodeItemKey kvNodeItemKey) {
        if (Objects.isNull(kvNodeItemKey)) {
            return null;
        } else {
            return new FastJsonKvNodeItemKey(
                    kvNodeItemKey.getNodeStringId(),
                    kvNodeItemKey.getItemStringId()
            );
        }
    }

    @JSONField(name = "node_string_id", ordinal = 1)
    private String nodeStringId;

    @JSONField(name = "item_string_id", ordinal = 2)
    private String itemStringId;

    public FastJsonKvNodeItemKey() {
    }

    public FastJsonKvNodeItemKey(String nodeStringId, String itemStringId) {
        this.nodeStringId = nodeStringId;
        this.itemStringId = itemStringId;
    }

    public String getNodeStringId() {
        return nodeStringId;
    }

    public void setNodeStringId(String nodeStringId) {
        this.nodeStringId = nodeStringId;
    }

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        FastJsonKvNodeItemKey that = (FastJsonKvNodeItemKey) o;
        return Objects.equals(nodeStringId, that.nodeStringId) && Objects.equals(itemStringId, that.itemStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(nodeStringId);
        result = 31 * result + Objects.hashCode(itemStringId);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonKvNodeItemKey{" +
                "nodeStringId='" + nodeStringId + '\'' +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
