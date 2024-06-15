package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文本节点推送信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class TextNodePutInfo implements Dto {

    private static final long serialVersionUID = -2782471676851624839L;

    private String category;
    private String[] args;
    private String value;

    public TextNodePutInfo() {
    }

    public TextNodePutInfo(String category, String[] args, String value) {
        this.category = category;
        this.args = args;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TextNodePutInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", value='" + value + '\'' +
                '}';
    }
}
