package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文本列表节点文本拉取信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TextListNodeTextFetchInfo implements Dto {

    private static final long serialVersionUID = 9022245768350124511L;

    private String category;
    private String[] args;

    /**
     * 拉取的文本在列表中的索引。
     */
    private int index;

    public TextListNodeTextFetchInfo() {
    }

    public TextListNodeTextFetchInfo(String category, String[] args, int index) {
        this.category = category;
        this.args = args;
        this.index = index;
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

    @Override
    public String toString() {
        return "TextListNodeTextFetchInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                '}';
    }
}
