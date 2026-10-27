package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverSupport;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 预热驱动器支持。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class FastJsonWarmupDriverSupport implements Bean {

    private static final long serialVersionUID = 8726478686887089834L;

    public static FastJsonWarmupDriverSupport of(WarmupDriverSupport warmupDriverSupport) {
        if (Objects.isNull(warmupDriverSupport)) {
            return null;
        } else {
            return new FastJsonWarmupDriverSupport(
                    FastJsonStringIdKey.of(warmupDriverSupport.getKey()),
                    warmupDriverSupport.getLabel(),
                    warmupDriverSupport.getDescription(),
                    warmupDriverSupport.getExampleParam()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "description", ordinal = 3)
    private String description;

    @JSONField(name = "example_param", ordinal = 4)
    private String exampleParam;

    public FastJsonWarmupDriverSupport() {
    }

    public FastJsonWarmupDriverSupport(
            FastJsonStringIdKey key, String label, String description, String exampleParam
    ) {
        this.key = key;
        this.label = label;
        this.description = description;
        this.exampleParam = exampleParam;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExampleParam() {
        return exampleParam;
    }

    public void setExampleParam(String exampleParam) {
        this.exampleParam = exampleParam;
    }

    @Override
    public String toString() {
        return "FastJsonWarmupDriverSupport{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", exampleParam='" + exampleParam + '\'' +
                '}';
    }
}
