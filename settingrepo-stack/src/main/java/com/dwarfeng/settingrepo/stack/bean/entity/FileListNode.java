package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 文件列表节点。
 *
 * @author liulx
 * @since 2.4.2
 */
public class FileListNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = -6437327921413918380L;

    private StringIdKey key;

    /**
     * 文件列表的数量。
     */
    private int size;

    public FileListNode() {
    }

    public FileListNode(
            StringIdKey key,
            int size
    ) {
        this.key = key;
        this.size = size;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
        this.key = key;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "FileListNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
