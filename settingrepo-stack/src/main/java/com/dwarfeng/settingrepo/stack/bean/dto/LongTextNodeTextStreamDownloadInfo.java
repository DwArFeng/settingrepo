package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 长文本节点文本流下载信息。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNodeTextStreamDownloadInfo implements Dto {

    private static final long serialVersionUID = 545961540309613942L;
    
    private String category;
    private String[] args;

    public LongTextNodeTextStreamDownloadInfo() {
    }

    public LongTextNodeTextStreamDownloadInfo(String category, String[] args) {
        this.category = category;
        this.args = args;
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

    @Override
    public String toString() {
        return "LongTextNodeTextStreamDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
