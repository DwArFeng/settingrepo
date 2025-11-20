package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文件节点文件上传信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileNodeFileUploadInfo implements Dto {

    private static final long serialVersionUID = -8124879321756710176L;

    private String category;
    private String[] args;
    private String originName;
    private byte[] content;

    public FileNodeFileUploadInfo() {
    }

    public FileNodeFileUploadInfo(String category, String[] args, String originName, byte[] content) {
        this.category = category;
        this.args = args;
        this.originName = originName;
        this.content = content;
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

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileNodeFileUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
