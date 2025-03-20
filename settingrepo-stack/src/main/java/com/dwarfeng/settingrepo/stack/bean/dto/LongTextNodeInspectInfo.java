package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 长文本节点查看信息。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNodeInspectInfo implements Dto {

    private static final long serialVersionUID = 7348405005298815810L;
    
    private String category;
    private String[] args;

    public LongTextNodeInspectInfo() {
    }

    public LongTextNodeInspectInfo(String category, String[] args) {
        this.category = category;
        this.args = args;
    }

    public String getCategory() {
        return category;
    }

    public LongTextNodeInspectInfo setCategory(String category) {
        this.category = category;
        return this;
    }

    public String[] getArgs() {
        return args;
    }

    public LongTextNodeInspectInfo setArgs(String[] args) {
        this.args = args;
        return this;
    }

    @Override
    public String toString() {
        return "LongTextNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
