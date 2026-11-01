package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文本列表节点移除信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TextListNodeRemoveInfo implements Dto {

    private static final long serialVersionUID = -2507959032288562735L;

    private String category;
    private String[] args;
    private int index;

    public TextListNodeRemoveInfo() {
    }

    public TextListNodeRemoveInfo(String category, String[] args, int index) {
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
        return "TextListNodeRemoveInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                '}';
    }
}
