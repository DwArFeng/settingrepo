package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 键值对节点数量结果。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeCountResult implements Dto {

    private static final long serialVersionUID = 7694120255718793802L;

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
