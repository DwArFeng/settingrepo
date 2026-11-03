package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 视频节点。
 *
 * @author fucl
 * @since 3.2.0
 */
public class VideoNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = 308242642423337527L;

    private StringIdKey key;

    /**
     * 视频文件的原始名称。
     */
    private String originName;

    /**
     * 视频文件在 FTP 服务器上的存储名称。
     */
    private String storeName;

    /**
     * 视频文件的大小。
     *
     * <p>
     * 该字段可能为 <code>null</code>，表示视频文件的大小未知。
     */
    private Long length;

    public VideoNode() {
    }

    public VideoNode(StringIdKey key, String originName, String storeName, Long length) {
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
        return "VideoNode{" +
                "key=" + key +
                ", originName='" + originName + '\'' +
                ", storeName='" + storeName + '\'' +
                ", length=" + length +
                '}';
    }
}
