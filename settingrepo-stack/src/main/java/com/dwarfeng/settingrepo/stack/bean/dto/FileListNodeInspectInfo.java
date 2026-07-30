package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 文件列表节点查看信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeInspectInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 2137584980750685642L;

    private String category;
    private String[] args;

    public FileListNodeInspectInfo() {
    }

    public FileListNodeInspectInfo(String category, String[] args) {
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
        return "FileListNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
