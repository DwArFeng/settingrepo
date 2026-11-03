package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.VideoNodeInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 视频节点查看结果。
 *
 * @author fucl
 * @since 3.2.0
 */
public class FastJsonVideoNodeInspectResult implements Dto {

    private static final long serialVersionUID = -417306089217973534L;

    public static FastJsonVideoNodeInspectResult of(VideoNodeInspectResult videoNodeInspectResult) {
        if (Objects.isNull(videoNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonVideoNodeInspectResult(
                    videoNodeInspectResult.getOriginName(),
                    videoNodeInspectResult.getLength()
            );
        }
    }

    @JSONField(name = "origin_name", ordinal = 1)
    private String originName;

    @JSONField(name = "length", ordinal = 2)
    private Long length;

    public FastJsonVideoNodeInspectResult() {
    }

    public FastJsonVideoNodeInspectResult(String originName, Long length) {
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
        return "FastJsonVideoNodeInspectResult{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                '}';
    }
}
