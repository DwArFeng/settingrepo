package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;
import java.util.Arrays;

/**
 * 长文本节点文本流上传信息。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNodeTextStreamUploadInfo implements Dto {

    private static final long serialVersionUID = -1237361294112403404L;
    
    private String category;
    private String[] args;
    private long length;

    /**
     * 文本流。
     *
     * <p>
     * 该字段允许为 <code>null</code>，代表该长文本节点的文本内容为 <code>null</code>。<br>
     * 该字段为 <code>null</code> 时，length 字段将被忽略。<br>
     * 该字段为 <code>null</code> 时，调用者无需考虑关闭该流。
     */
    private InputStream content;

    public LongTextNodeTextStreamUploadInfo() {
    }

    public LongTextNodeTextStreamUploadInfo(String category, String[] args, long length, InputStream content) {
        this.category = category;
        this.args = args;
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
        return "LongTextNodeTextStreamUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
