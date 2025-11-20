package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 文件列表节点文件更新信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeFileUpdateInfo implements Dto {

    private static final long serialVersionUID = -3271605603075471470L;

    private String category;
    private String[] args;

    /**
     * 上传的文件在列表中的索引。
     */
    private int index;

    private String originName;
    private byte[] content;

    public FileListNodeFileUpdateInfo() {
    }

    public FileListNodeFileUpdateInfo(String category, String[] args, int index, String originName, byte[] content) {
        this.category = category;
        this.args = args;
        this.index = index;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
        return "FileListNodeFileUpdateInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
