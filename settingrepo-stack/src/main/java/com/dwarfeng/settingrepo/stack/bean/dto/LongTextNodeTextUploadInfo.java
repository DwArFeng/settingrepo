package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 长文本节点文本上传信息。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNodeTextUploadInfo implements Dto {

    private static final long serialVersionUID = -2510667183667086106L;
    
    private String category;
    private String[] args;
    private String content;

    public LongTextNodeTextUploadInfo() {
    }

    public LongTextNodeTextUploadInfo(String category, String[] args, String content) {
        this.category = category;
        this.args = args;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LongTextNodeTextUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", content='" + content + '\'' +
                '}';
    }
}
