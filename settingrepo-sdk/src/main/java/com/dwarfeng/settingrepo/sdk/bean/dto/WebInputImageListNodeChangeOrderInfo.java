package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageListNodeChangeOrderInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 图片列表节点变更顺序信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputImageListNodeChangeOrderInfo implements Dto {

    private static final long serialVersionUID = -7640615344883654934L;

    public static ImageListNodeChangeOrderInfo toStackBean(WebInputImageListNodeChangeOrderInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new ImageListNodeChangeOrderInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getOldIndex(),
                    webInput.getNeoIndex()
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

    @JSONField(name = "old_index")
    @PositiveOrZero
    private int oldIndex;

    @JSONField(name = "neo_index")
    @PositiveOrZero
    private int neoIndex;

    public WebInputImageListNodeChangeOrderInfo() {
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

    public int getOldIndex() {
        return oldIndex;
    }

    public void setOldIndex(int oldIndex) {
        this.oldIndex = oldIndex;
    }

    public int getNeoIndex() {
        return neoIndex;
    }

    public void setNeoIndex(int neoIndex) {
        this.neoIndex = neoIndex;
    }

    @Override
    public String toString() {
        return "WebInputImageListNodeChangeOrderInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", oldIndex=" + oldIndex +
                ", neoIndex=" + neoIndex +
                '}';
    }
}
