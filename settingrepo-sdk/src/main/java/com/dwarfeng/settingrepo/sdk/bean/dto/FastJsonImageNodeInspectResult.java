package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageNodeInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 图片节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonImageNodeInspectResult implements Dto {

    private static final long serialVersionUID = -1126448898462772738L;

    public static FastJsonImageNodeInspectResult of(ImageNodeInspectResult imageNodeInspectResult) {
        if (Objects.isNull(imageNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonImageNodeInspectResult(
                    imageNodeInspectResult.getOriginName(),
                    imageNodeInspectResult.getLength()
            );
        }
    }

    @JSONField(name = "origin_name", ordinal = 1)
    private String originName;

    @JSONField(name = "length", ordinal = 2)
    private Long length;

    public FastJsonImageNodeInspectResult() {
    }

    public FastJsonImageNodeInspectResult(String originName, Long length) {
        this.originName = originName;
        this.length = length;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "FastJsonImageNodeInspectResult{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                '}';
    }
}
