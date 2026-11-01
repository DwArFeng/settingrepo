package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文本列表节点文本插入信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TextListNodeTextInsertInfo implements Dto {

    private static final long serialVersionUID = 7077906972314774954L;

    private String category;
    private String[] args;

    /**
     * 插入的文本在列表中的索引。
     *
     * <p>
     * 该值不为 <code>null</code> 时代表向列表中的指定位置插入文本；该值为 <code>null</code> 时代表向列表的末尾插入文本。
     */
    private Integer index;

    private String content;

    public TextListNodeTextInsertInfo() {
    }

    public TextListNodeTextInsertInfo(String category, String[] args, Integer index, String content) {
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
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
        return "TextListNodeTextInsertInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", content='" + content + '\'' +
                '}';
    }
}
