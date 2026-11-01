package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 文本列表节点大小结果。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TextListNodeSizeResult implements Dto {

    private static final long serialVersionUID = 4233167425066601907L;

    private int size;

    public TextListNodeSizeResult() {
    }

    public TextListNodeSizeResult(int size) {
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
        return "TextListNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
