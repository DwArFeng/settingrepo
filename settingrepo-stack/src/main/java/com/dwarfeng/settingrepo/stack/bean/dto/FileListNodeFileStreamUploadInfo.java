package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;
import java.util.Arrays;

/**
 * 文件列表节点文件流上传信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeFileStreamUploadInfo implements Dto {

    private static final long serialVersionUID = 4885030647323859949L;

    private String category;
    private String[] args;

    /**
     * 上传的文件在列表中的索引。
     *
     * <p>
     * 该值不为 <code>null</code> 时代表向列表中的指定位置插入文件；该值为 <code>null</code> 时代表向列表的末尾插入文件。
     */
    private Integer index;

    private String originName;
    private long length;
    private InputStream content;

    public FileListNodeFileStreamUploadInfo() {
    }

    public FileListNodeFileStreamUploadInfo(
            String category, String[] args, Integer index, String originName, long length, InputStream content
    ) {
        this.category = category;
        this.args = args;
        this.index = index;
        this.originName = originName;
        this.length = length;
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileListNodeFileStreamUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
