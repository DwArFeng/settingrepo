package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.FileListNodeSizeResult;

import java.util.Objects;

/**
 * FastJson 文件列表节点大小结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FastJsonFileListNodeSizeResult {

    public static FastJsonFileListNodeSizeResult of(FileListNodeSizeResult fileListNodeSizeResult) {
        if (Objects.isNull(fileListNodeSizeResult)) {
            return null;
        } else {
            return new FastJsonFileListNodeSizeResult(
                    fileListNodeSizeResult.getSize()
            );
        }
    }

    @JSONField(name = "size", ordinal = 1)
    private int size;

    public FastJsonFileListNodeSizeResult() {
    }

    public FastJsonFileListNodeSizeResult(int size) {
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
        return "FastJsonFileListNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
