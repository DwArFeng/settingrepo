package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 文本节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class TextNodeInspectResult implements Dto {

    private static final long serialVersionUID = 510506848395089L;

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
