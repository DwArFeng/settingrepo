package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 图片列表节点文件上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeFileUploadInfo implements Dto {

    private static final long serialVersionUID = 6993780445278041043L;

    private String category;
    private String[] args;

    /**
     * 上传的图片在列表中的索引。
     *
     * <p>
     * 该值不为 <code>null</code> 时代表向列表中的指定位置插入图片；该值为 <code>null</code> 时代表向列表的末尾插入图片。
     */
    private Integer index;

    private String originName;
    private byte[] content;

    public ImageListNodeFileUploadInfo() {
    }

    public ImageListNodeFileUploadInfo(
            String category, String[] args, Integer index, String originName, byte[] content
    ) {
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ImageListNodeFileUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
