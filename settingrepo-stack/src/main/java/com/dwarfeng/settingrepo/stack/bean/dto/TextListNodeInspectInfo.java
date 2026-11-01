package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文本列表节点查看信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TextListNodeInspectInfo implements Dto {

    private static final long serialVersionUID = 1950014303192915258L;

    private String category;
    private String[] args;

    public TextListNodeInspectInfo() {
    }

    public TextListNodeInspectInfo(String category, String[] args) {
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
        return "TextListNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
