package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.VideoListNodeSizeResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 视频列表节点大小结果。
 *
 * @author fucl
 * @since 3.2.0
 */
public class FastJsonVideoListNodeSizeResult implements Dto {

    private static final long serialVersionUID = -6237455516177200206L;

    public static FastJsonVideoListNodeSizeResult of(VideoListNodeSizeResult videoListNodeSizeResult) {
        if (Objects.isNull(videoListNodeSizeResult)) {
            return null;
        } else {
            return new FastJsonVideoListNodeSizeResult(
                    videoListNodeSizeResult.getSize()
            );
        }
    }

    @JSONField(name = "size", ordinal = 1)
    private int size;

    public FastJsonVideoListNodeSizeResult() {
    }

    public FastJsonVideoListNodeSizeResult(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FastJsonVideoListNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
