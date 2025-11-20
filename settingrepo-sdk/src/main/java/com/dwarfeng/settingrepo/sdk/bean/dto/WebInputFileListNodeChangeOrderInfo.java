package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.FileListNodeChangeOrderInfo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 文件列表节点变更顺序信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class WebInputFileListNodeChangeOrderInfo {

    public static FileListNodeChangeOrderInfo toStackBean(WebInputFileListNodeChangeOrderInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new FileListNodeChangeOrderInfo(
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

    public WebInputFileListNodeChangeOrderInfo() {
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
        return "WebInputFileListNodeChangeOrderInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", oldIndex=" + oldIndex +
                ", neoIndex=" + neoIndex +
                '}';
    }
}
