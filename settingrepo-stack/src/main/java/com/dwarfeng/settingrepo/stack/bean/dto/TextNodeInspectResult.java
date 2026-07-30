package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 文本节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class TextNodeInspectResult implements Dto {

    @Serial
    private static final long serialVersionUID = -6367996932432283475L;

    private String value;

    public TextNodeInspectResult() {
    }

    public TextNodeInspectResult(String value) {
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
        return "TextNodeInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
