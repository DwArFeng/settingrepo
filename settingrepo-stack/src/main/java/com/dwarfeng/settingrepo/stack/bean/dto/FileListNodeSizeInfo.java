package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文件列表节点大小信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeSizeInfo implements Dto {

    private static final long serialVersionUID = 242187300371960735L;

    private String category;
    private String[] args;

    public FileListNodeSizeInfo() {
    }

    public FileListNodeSizeInfo(String category, String[] args) {
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
        return "FileListNodeSizeInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
