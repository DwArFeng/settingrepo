package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 长文本节点文本下载信息。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNodeTextDownloadInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -556501650146690375L;

    private String category;
    private String[] args;

    public LongTextNodeTextDownloadInfo() {
    }

    public LongTextNodeTextDownloadInfo(String category, String[] args) {
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
        return "LongTextNodeTextDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
