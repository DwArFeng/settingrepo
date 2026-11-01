package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.TextListNodeSizeResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 文本列表节点大小结果。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class FastJsonTextListNodeSizeResult implements Dto {

    private static final long serialVersionUID = 8059748839158230713L;

    public static FastJsonTextListNodeSizeResult of(TextListNodeSizeResult textListNodeSizeResult) {
        if (Objects.isNull(textListNodeSizeResult)) {
            return null;
        } else {
            return new FastJsonTextListNodeSizeResult(
                    textListNodeSizeResult.getSize()
            );
        }
    }

    @JSONField(name = "size", ordinal = 1)
    private int size;

    public FastJsonTextListNodeSizeResult() {
    }

    public FastJsonTextListNodeSizeResult(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FastJsonTextListNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
