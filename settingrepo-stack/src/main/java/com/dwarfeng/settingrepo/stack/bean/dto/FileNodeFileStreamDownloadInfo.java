package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 文件节点文件流下载信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileNodeFileStreamDownloadInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 3869569597827864359L;

    private String category;
    private String[] args;

    public FileNodeFileStreamDownloadInfo() {
    }

    public FileNodeFileStreamDownloadInfo(String category, String[] args) {
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
        return "FileNodeFileStreamDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
