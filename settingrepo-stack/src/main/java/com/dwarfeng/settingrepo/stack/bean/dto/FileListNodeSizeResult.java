package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 文件列表节点大小结果。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class FileListNodeSizeResult implements Dto {

    private static final long serialVersionUID = -9091647946465822984L;

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
