package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageListNodeRemoveInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 图片列表节点移除信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputImageListNodeRemoveInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -7703935051958122089L;

    public static ImageListNodeRemoveInfo toStackBean(WebInputImageListNodeRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new ImageListNodeRemoveInfo(
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

    public WebInputImageListNodeRemoveInfo() {
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
        return "WebInputImageListNodeRemoveInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                '}';
    }
}
