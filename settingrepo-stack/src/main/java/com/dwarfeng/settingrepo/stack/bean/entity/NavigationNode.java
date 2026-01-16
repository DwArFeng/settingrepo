package com.dwarfeng.settingrepo.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 导航节点。
 *
 * @author zhaofz
 * @since 2.4.2
 */
public class NavigationNode implements Entity<StringIdKey> {

    private static final long serialVersionUID = -90938819676616476L;

    private StringIdKey key;
    private int size;

    /**
     * 内容。
     *
     * @since 2.4.5
     */
    private String content;

    public NavigationNode() {
    }

    public NavigationNode(StringIdKey key, int size, String content) {
        this.key = key;
        this.size = size;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NavigationNode{" +
                "key=" + key +
                ", size=" + size +
                ", content='" + content + '\'' +
                '}';
    }
}
