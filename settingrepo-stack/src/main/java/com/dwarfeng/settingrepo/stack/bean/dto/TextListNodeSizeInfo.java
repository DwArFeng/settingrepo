package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文本列表节点大小信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TextListNodeSizeInfo implements Dto {

    private static final long serialVersionUID = 1515881033581875848L;

    private String category;
    private String[] args;

    public TextListNodeSizeInfo() {
    }

    public TextListNodeSizeInfo(String category, String[] args) {
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
        return "TextListNodeSizeInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
