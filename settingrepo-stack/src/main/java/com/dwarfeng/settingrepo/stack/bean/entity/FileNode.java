package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.basic.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;

import java.io.Serial;

/**
 * 文件节点。
 *
 * @author WangT
 * @since 2.4.2
 */
public class FileNode implements Entity<StringIdKey> {

    @Serial
    private static final long serialVersionUID = 2075843061884066825L;

    private StringIdKey key;
    private String originName;
    private String storeName;
    private Long length;

    public FileNode() {
    }

    public FileNode(StringIdKey key, String originName, String storeName, Long length) {
        this.key = key;
        this.originName = originName;
        this.storeName = storeName;
        this.length = length;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
        this.key = key;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "FileNode{" +
                "key=" + key +
                ", originName='" + originName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", length=" + length +
                '}';
    }
}
