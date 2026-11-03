package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 视频列表节点变更顺序信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoListNodeChangeOrderInfo implements Dto {

    private static final long serialVersionUID = 1330785594790886047L;

    private String category;
    private String[] args;
    private int oldIndex;
    private int neoIndex;

    public VideoListNodeChangeOrderInfo() {
    }

    public VideoListNodeChangeOrderInfo(String category, String[] args, int oldIndex, int neoIndex) {
        this.category = category;
        this.args = args;
        this.oldIndex = oldIndex;
        this.neoIndex = neoIndex;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public int getOldIndex() {
        return oldIndex;
    }

    public void setOldIndex(int oldIndex) {
        this.oldIndex = oldIndex;
    }

    public int getNeoIndex() {
        return neoIndex;
    }

    public void setNeoIndex(int neoIndex) {
        this.neoIndex = neoIndex;
    }

    @Override
    public String toString() {
        return "VideoListNodeChangeOrderInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", oldIndex=" + oldIndex +
                ", neoIndex=" + neoIndex +
                '}';
    }
}
