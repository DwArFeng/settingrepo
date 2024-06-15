package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.TextNodePutInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 文本节点推送信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputTextNodePutInfo implements Dto {

    private static final long serialVersionUID = -5971158452589559916L;

    public static TextNodePutInfo toStackBean(WebInputTextNodePutInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TextNodePutInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getValue()
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

    @JSONField(name = "value")
    private String value;

    public WebInputTextNodePutInfo() {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WebInputTextNodePutInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", value='" + value + '\'' +
                '}';
    }
}
