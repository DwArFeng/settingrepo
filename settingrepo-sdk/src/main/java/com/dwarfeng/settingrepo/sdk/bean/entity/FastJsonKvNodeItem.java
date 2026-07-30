package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.bean.key.FastJsonKvNodeItemKey;
import com.dwarfeng.settingrepo.stack.bean.entity.KvNodeItem;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 键值对条目节点。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class FastJsonKvNodeItem implements Bean {

    @Serial
    private static final long serialVersionUID = 6359468707713656594L;

    public static FastJsonKvNodeItem of(KvNodeItem kvNodeItem) {
        if (Objects.isNull(kvNodeItem)) {
            return null;
        } else {
            return new FastJsonKvNodeItem(
                    FastJsonKvNodeItemKey.of(kvNodeItem.getKey()),
                    kvNodeItem.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonKvNodeItemKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public FastJsonKvNodeItem() {
    }

    public FastJsonKvNodeItem(FastJsonKvNodeItemKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public FastJsonKvNodeItemKey getKey() {
        return key;
    }

    public void setKey(FastJsonKvNodeItemKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FastJsonKvNodeItem{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
