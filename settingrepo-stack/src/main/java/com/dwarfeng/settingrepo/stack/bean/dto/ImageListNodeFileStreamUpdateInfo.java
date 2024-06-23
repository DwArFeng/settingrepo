package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;
import java.util.Arrays;

/**
 * 图片列表节点文件流更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class ImageListNodeFileStreamUpdateInfo implements Dto {

    private static final long serialVersionUID = 8727519995248337171L;

    private String category;
    private String[] args;

    /**
     * 上传的图片在列表中的索引。
     */
    private int index;

    private String originName;
    private long length;
    private InputStream content;

    public ImageListNodeFileStreamUpdateInfo() {
    }

    public ImageListNodeFileStreamUpdateInfo(
            String category, String[] args, int index, String originName, long length, InputStream content
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
        return "ImageListNodeFileStreamUpdateInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
