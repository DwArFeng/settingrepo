package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文件列表节点文件流下载信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeFileStreamDownloadInfo implements Dto {

    private static final long serialVersionUID = 7833962173400210328L;

    private String category;
    private String[] args;
    private int index;

    public FileListNodeFileStreamDownloadInfo() {
    }

    public FileListNodeFileStreamDownloadInfo(String category, String[] args, int index) {
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
        return "FileListNodeFileStreamDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                '}';
    }
}
