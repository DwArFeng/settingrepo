package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.KvNodeCountResult;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 键值对节点数量结果。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class FastJsonKvNodeCountResult implements Bean {

    private static final long serialVersionUID = 8470940555290637605L;

    public static FastJsonKvNodeCountResult of(KvNodeCountResult kvNodeCountResult) {
        if (Objects.isNull(kvNodeCountResult)) {
            return null;
        } else {
            return new FastJsonKvNodeCountResult(
                    kvNodeCountResult.getCount()
            );
        }
    }

    @JSONField(name = "count", ordinal = 1)
    private int count;

    public FastJsonKvNodeCountResult() {
    }

    public FastJsonKvNodeCountResult(int count) {
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
        return "FastJsonKvNodeCountResult{" +
                "count=" + count +
                '}';
    }
}
