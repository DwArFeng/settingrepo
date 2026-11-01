package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.TextListNodeTextUpdateInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 文本列表节点文本更新信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class WebInputTextListNodeTextUpdateInfo implements Dto {

    private static final long serialVersionUID = -7322950162527464426L;

    public static TextListNodeTextUpdateInfo toStackBean(WebInputTextListNodeTextUpdateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TextListNodeTextUpdateInfo(
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

    /**
     * 更新的文本在列表中的索引。
     */
    @JSONField(name = "index")
    @PositiveOrZero
    private int index;

    @JSONField(name = "content")
    @NotNull
    private String content;

    public WebInputTextListNodeTextUpdateInfo() {
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
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
        return "WebInputTextListNodeTextUpdateInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", content='" + content + '\'' +
                '}';
    }
}
