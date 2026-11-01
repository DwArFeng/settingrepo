package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 文本列表节点文本拉取结果。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TextListNodeTextFetchResult implements Dto {

    private static final long serialVersionUID = -713423710872993581L;

    private String content;

    public TextListNodeTextFetchResult() {
    }

    public TextListNodeTextFetchResult(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextListNodeTextFetchResult{" +
                "content='" + content + '\'' +
                '}';
    }
}
