package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;

/**
 * 长文本节点文本流。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNodeTextStream implements Dto {

    private static final long serialVersionUID = 2517514725072155089L;
    
    private long length;
    private InputStream content;

    public LongTextNodeTextStream() {
    }

    public LongTextNodeTextStream(long length, InputStream content) {
        this.length = length;
        this.content = content;
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
        return "LongTextNodeTextStream{" +
                "length=" + length +
                ", content=" + content +
                '}';
    }
}
