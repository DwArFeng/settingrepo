package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.KvNodeItemInspectResult;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 键值对节点条目查看结果。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class FastJsonKvNodeItemInspectResult implements Bean {

    private static final long serialVersionUID = 2010463673493731983L;

    public static FastJsonKvNodeItemInspectResult of(KvNodeItemInspectResult kvNodeItemInspectResult) {
        if (Objects.isNull(kvNodeItemInspectResult)) {
            return null;
        } else {
            return new FastJsonKvNodeItemInspectResult(
                    kvNodeItemInspectResult.getValue()
            );
        }
    }

    @JSONField(name = "value", ordinal = 1)
    private String value;

    public FastJsonKvNodeItemInspectResult() {
    }

    public FastJsonKvNodeItemInspectResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FastJsonKvNodeItemInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
