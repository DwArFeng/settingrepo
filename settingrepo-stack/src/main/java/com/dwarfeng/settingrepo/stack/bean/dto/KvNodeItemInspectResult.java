package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 键值对节点条目查看结果。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeItemInspectResult implements Dto {

    private static final long serialVersionUID = -534255578334562888L;

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
