package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 导航节点更新信息。
 *
 * @author pengy
 * @since 2.4.2
 */
public class NavigationNodeUpdateInfo implements Dto {

    private static final long serialVersionUID = 4310348204611224418L;

    private String category;
    private String[] args;

    /**
     * 导航节点的内容。
     *
     * @since 2.4.5
     */
    private String content;

    public NavigationNodeUpdateInfo() {
    }

    public NavigationNodeUpdateInfo(String category, String[] args, String content) {
        this.category = category;
        this.args = args;
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NavigationNodeUpdateInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", content='" + content + '\'' +
                '}';
    }
}
