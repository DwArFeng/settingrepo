package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.FileNodeInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 文件节点查看结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FastJsonFileNodeInspectResult implements Dto {

    private static final long serialVersionUID = -7886509818851873139L;

    public static FastJsonFileNodeInspectResult of(FileNodeInspectResult fileNodeInspectResult) {
        if (Objects.isNull(fileNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonFileNodeInspectResult(
                    fileNodeInspectResult.getOriginName(),
                    fileNodeInspectResult.getLength()
            );
        }
    }

    @JSONField(name = "origin_name", ordinal = 1)
    private String originName;

    @JSONField(name = "length", ordinal = 2)
    private Long length;

    public FastJsonFileNodeInspectResult() {
    }

    public FastJsonFileNodeInspectResult(String originName, Long length) {
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
        return "FastJsonFileNodeInspectResult{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                '}';
    }
}
