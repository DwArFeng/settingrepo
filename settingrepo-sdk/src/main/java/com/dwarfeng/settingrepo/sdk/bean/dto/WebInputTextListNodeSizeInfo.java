package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.TextListNodeSizeInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 文本列表节点大小信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class WebInputTextListNodeSizeInfo implements Dto {

    private static final long serialVersionUID = 6635038715527425155L;

    public static TextListNodeSizeInfo toStackBean(WebInputTextListNodeSizeInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TextListNodeSizeInfo(
                    webInput.getCategory(),
                    webInput.getArgs()
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

    public WebInputTextListNodeSizeInfo() {
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

    @Override
    public String toString() {
        return "WebInputTextListNodeSizeInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
