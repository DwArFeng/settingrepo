package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 键值对节点数量结果。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeCountResult implements Dto {

    @Serial
    private static final long serialVersionUID = -7147930577392465410L;

    private int count;

    public KvNodeCountResult() {
    }

    public KvNodeCountResult(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "KvNodeCountResult{" +
                "count=" + count +
                '}';
    }
}
