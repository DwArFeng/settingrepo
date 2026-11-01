package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.TextListNodeRemoveInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 文本列表节点移除信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class WebInputTextListNodeRemoveInfo implements Dto {

    private static final long serialVersionUID = -3210346265418571792L;

    public static TextListNodeRemoveInfo toStackBean(WebInputTextListNodeRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TextListNodeRemoveInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getIndex()
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
    private int index;

    public WebInputTextListNodeRemoveInfo() {
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

    @Override
    public String toString() {
        return "WebInputTextListNodeRemoveInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                '}';
    }
}
