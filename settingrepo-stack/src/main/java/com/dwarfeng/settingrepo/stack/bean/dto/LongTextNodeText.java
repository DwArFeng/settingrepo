package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 长文本节点文本。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class LongTextNodeText implements Dto {

    private static final long serialVersionUID = 3683187033686779403L;
    
    private String content;

    public LongTextNodeText() {
    }

    public LongTextNodeText(String content) {
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
        return "LongTextNodeText{" +
                "content='" + content + '\'' +
                '}';
    }
}
