package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 视频节点查看结果。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoNodeInspectResult implements Dto {

    private static final long serialVersionUID = 2391743131871382081L;

    private String originName;
    private long length;

    public VideoNodeInspectResult() {
    }

    public VideoNodeInspectResult(String originName, long length) {
        this.originName = originName;
        this.length = length;
    }

    public String getOriginName() {
        return originName;
    }

    public VideoNodeInspectResult setOriginName(String originName) {
        this.originName = originName;
        return this;
    }

    public long getLength() {
        return length;
    }

    public VideoNodeInspectResult setLength(long length) {
        this.length = length;
        return this;
    }

    @Override
    public String toString() {
        return "VideoNodeInspectResult{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                '}';
    }
}
