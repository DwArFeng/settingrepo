package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 键值对节点条目查看结果。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeItemInspectResult implements Dto {

    @Serial
    private static final long serialVersionUID = -1260340796002085350L;

    private String value;

    public KvNodeItemInspectResult() {
    }

    public KvNodeItemInspectResult(String value) {
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
        return "KvNodeItemInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
