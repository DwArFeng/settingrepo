package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.LongTextNodeInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 长文本节点查看结果。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class FastJsonLongTextNodeInspectResult implements Dto {

    private static final long serialVersionUID = -6167878452656956430L;

    public static FastJsonLongTextNodeInspectResult of(LongTextNodeInspectResult longTextNodeInspectResult) {
        if (Objects.isNull(longTextNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonLongTextNodeInspectResult(
                    longTextNodeInspectResult.isNullFlag(),
                    longTextNodeInspectResult.getPreview(),
                    longTextNodeInspectResult.getLength()
            );
        }
    }

    @JSONField(name = "null_flag", ordinal = 1)
    private boolean nullFlag;

    @JSONField(name = "preview", ordinal = 2)
    private String preview;

    @JSONField(name = "length", ordinal = 3)
    private long length;

    public FastJsonLongTextNodeInspectResult() {
    }

    public FastJsonLongTextNodeInspectResult(boolean nullFlag, String preview, long length) {
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
        return "FastJsonLongTextNodeInspectResult{" +
                "nullFlag=" + nullFlag +
                ", preview='" + preview + '\'' +
                ", length=" + length +
                '}';
    }
}
