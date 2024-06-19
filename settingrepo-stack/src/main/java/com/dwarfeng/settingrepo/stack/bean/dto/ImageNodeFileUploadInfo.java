package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 图片节点文件上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageNodeFileUploadInfo implements Dto {

    private static final long serialVersionUID = -8029979076662212585L;

    private String category;
    private String[] args;
    private String originName;
    private byte[] content;

    public ImageNodeFileUploadInfo() {
    }

    public ImageNodeFileUploadInfo(String category, String[] args, String originName, byte[] content) {
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
        return "ImageNodeFileUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
