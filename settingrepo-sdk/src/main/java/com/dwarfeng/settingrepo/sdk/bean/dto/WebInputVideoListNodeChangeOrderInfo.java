package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.VideoListNodeChangeOrderInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 视频列表节点变更顺序信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class WebInputVideoListNodeChangeOrderInfo implements Dto {

    private static final long serialVersionUID = -2475693063694853056L;

    public static VideoListNodeChangeOrderInfo toStackBean(WebInputVideoListNodeChangeOrderInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VideoListNodeChangeOrderInfo(
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

    public WebInputVideoListNodeChangeOrderInfo() {
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
        return "WebInputVideoListNodeChangeOrderInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", oldIndex=" + oldIndex +
                ", neoIndex=" + neoIndex +
                '}';
    }
}
