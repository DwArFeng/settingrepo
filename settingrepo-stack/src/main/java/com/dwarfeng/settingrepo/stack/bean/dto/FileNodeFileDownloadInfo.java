package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 文件节点文件下载信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileNodeFileDownloadInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 8019462716821527802L;

    private String category;
    private String[] args;

    public FileNodeFileDownloadInfo() {
    }

    public FileNodeFileDownloadInfo(String category, String[] args) {
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
        return "FileNodeFileDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
