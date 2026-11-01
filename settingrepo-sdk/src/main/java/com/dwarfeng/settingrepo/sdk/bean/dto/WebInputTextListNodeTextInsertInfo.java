package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.TextListNodeTextInsertInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 文本列表节点文本插入信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class WebInputTextListNodeTextInsertInfo implements Dto {

    private static final long serialVersionUID = -1295088756596929824L;

    public static TextListNodeTextInsertInfo toStackBean(WebInputTextListNodeTextInsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TextListNodeTextInsertInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getIndex(),
                    webInput.getContent()
            );
        }
    }

    @JSONField(name = "category")
    @NotNull
    @NotEmpty
    private String category;

    @JSONField(name = "args")
    @NotNull
    private String[] args;

    @JSONField(name = "index")
    @PositiveOrZero
    private Integer index;

    @JSONField(name = "content")
    @NotNull
    private String content;

    public WebInputTextListNodeTextInsertInfo() {
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WebInputTextListNodeTextInsertInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", content='" + content + '\'' +
                '}';
    }
}
