package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodeInspectResult;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 文本节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonTextNodeInspectResult implements Dto {

    @Serial
    private static final long serialVersionUID = 5323831344241085687L;

    public static FastJsonTextNodeInspectResult of(TextNodeInspectResult textNodeInspectResult) {
        if (Objects.isNull(textNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonTextNodeInspectResult(
                    textNodeInspectResult.getValue()
            );
        }
    }

    @JSONField(name = "value", ordinal = 1)
    private String value;

    public FastJsonTextNodeInspectResult() {
    }

    public FastJsonTextNodeInspectResult(String value) {
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
        return "FastJsonTextNodeInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
