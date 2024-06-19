package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;
import java.util.Arrays;

/**
 * 图片节点文件流上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageNodeFileStreamUploadInfo implements Dto {

    private static final long serialVersionUID = 1720633117381346632L;

    private String category;
    private String[] args;
    private String originName;
    private long length;
    private InputStream content;

    public ImageNodeFileStreamUploadInfo() {
    }

    public ImageNodeFileStreamUploadInfo(
            String category, String[] args, String originName, long length, InputStream content
    ) {
        this.category = category;
        this.args = args;
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
        return "ImageNodeFileStreamUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
