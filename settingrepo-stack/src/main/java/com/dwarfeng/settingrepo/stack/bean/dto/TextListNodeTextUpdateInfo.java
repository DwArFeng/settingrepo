package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文本列表节点文本更新信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TextListNodeTextUpdateInfo implements Dto {

    private static final long serialVersionUID = 4782364043637812319L;

    private String category;
    private String[] args;

    /**
     * 更新的文本在列表中的索引。
     */
    private int index;

    private String content;

    public TextListNodeTextUpdateInfo() {
    }

    public TextListNodeTextUpdateInfo(String category, String[] args, int index, String content) {
        this.category = category;
        this.args = args;
        this.index = index;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextListNodeTextUpdateInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", content='" + content + '\'' +
                '}';
    }
}
