package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.entity.FileListNode;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.FastJsonStringIdKey;

import java.io.Serial;
import java.util.Objects;

/**
 * FastJson 文件列表节点。
 *
 * @author liulx
 * @since 2.4.2
 */
public class FastJsonFileListNode implements Bean {

    @Serial
    private static final long serialVersionUID = -311086933037180441L;

    public static FastJsonFileListNode of(FileListNode fileListNode) {
        if (Objects.isNull(fileListNode)) {
            return null;
        } else {
            return new FastJsonFileListNode(
                    FastJsonStringIdKey.of(fileListNode.getKey()),
                    fileListNode.getSize()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "size", ordinal = 2)
    private int size;

    public FastJsonFileListNode() {
    }

    public FastJsonFileListNode(
            FastJsonStringIdKey key,
            int size) {
        this.key = key;
        this.size = size;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
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
        return "FastJsonFileListNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
