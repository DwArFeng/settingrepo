package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.LongTextNodeTextStreamDownloadInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 长文本节点文本流下载信息。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class WebInputLongTextNodeTextStreamDownloadInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 5972088693023821312L;

    public static LongTextNodeTextStreamDownloadInfo toStackBean(WebInputLongTextNodeTextStreamDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new LongTextNodeTextStreamDownloadInfo(
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

    public WebInputLongTextNodeTextStreamDownloadInfo() {
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
        return "WebInputLongTextNodeTextStreamDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
