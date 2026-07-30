package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.subgrade.basic.stack.bean.entity.Entity;

import java.io.Serial;

/**
 * 键值对条目节点。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class KvNodeItem implements Entity<KvNodeItemKey> {

    @Serial
    private static final long serialVersionUID = -1879497813848236184L;

    private KvNodeItemKey key;
    private String value;

    public KvNodeItem() {
    }

    public KvNodeItem(KvNodeItemKey key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public KvNodeItemKey getKey() {
        return key;
    }

    @Override
    public void setKey(KvNodeItemKey key) {
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
        return "KvNodeItem{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
