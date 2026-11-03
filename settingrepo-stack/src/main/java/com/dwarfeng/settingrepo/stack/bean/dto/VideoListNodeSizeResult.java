package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 视频列表节点大小结果。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoListNodeSizeResult implements Dto {

    private static final long serialVersionUID = -4116057161211554522L;

    private int size;

    public VideoListNodeSizeResult() {
    }

    public VideoListNodeSizeResult(int size) {
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
        return "VideoListNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
