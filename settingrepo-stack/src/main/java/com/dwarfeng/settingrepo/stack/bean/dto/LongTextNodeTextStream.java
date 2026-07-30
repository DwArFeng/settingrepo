package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.InputStream;
import java.io.Serial;

/**
 * 长文本节点文本流。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNodeTextStream implements Dto {

    @Serial
    private static final long serialVersionUID = 7495972727350670197L;

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
