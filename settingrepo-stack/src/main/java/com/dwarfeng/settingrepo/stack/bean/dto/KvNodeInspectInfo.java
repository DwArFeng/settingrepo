package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 键值对节点查看信息。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeInspectInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -8154506486958830421L;

    private String category;
    private String[] args;

    public KvNodeInspectInfo() {
    }

    public KvNodeInspectInfo(String category, String[] args) {
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
        return "KvNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
