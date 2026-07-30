package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 文件列表节点大小结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeSizeResult implements Dto {

    @Serial
    private static final long serialVersionUID = -3262057642757901010L;

    private int size;

    public FileListNodeSizeResult() {
    }

    public FileListNodeSizeResult(int size) {
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
        return "FileListNodeSizeResult{" +
                "size=" + size +
                '}';
    }
}
