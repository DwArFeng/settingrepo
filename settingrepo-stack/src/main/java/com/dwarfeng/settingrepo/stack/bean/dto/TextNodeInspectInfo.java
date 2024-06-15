package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文本节点查看信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class TextNodeInspectInfo implements Dto {

    private static final long serialVersionUID = 5903370164672161994L;

    private String category;
    private String[] args;

    public TextNodeInspectInfo() {
    }

    public TextNodeInspectInfo(String category, String[] args) {
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
        return "TextNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
