package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 长文本节点查看结果。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNodeInspectResult implements Dto {

    private static final long serialVersionUID = 7637610118153792604L;
    
    private boolean nullFlag;
    private String preview;
    private long length;

    public LongTextNodeInspectResult() {
    }

    public LongTextNodeInspectResult(boolean nullFlag, String preview, long length) {
        this.nullFlag = nullFlag;
        this.preview = preview;
        this.length = length;
    }

    public boolean isNullFlag() {
        return nullFlag;
    }

    public void setNullFlag(boolean nullFlag) {
        this.nullFlag = nullFlag;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "LongTextNodeInspectResult{" +
                "nullFlag=" + nullFlag +
                ", preview='" + preview + '\'' +
                ", length=" + length +
                '}';
    }
}
