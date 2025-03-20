package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.LongTextNodeTextDownloadInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 长文本节点文本下载信息。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class WebInputLongTextNodeTextDownloadInfo implements Dto {

    private static final long serialVersionUID = 7880791076081230295L;

    public static LongTextNodeTextDownloadInfo toStackBean(WebInputLongTextNodeTextDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new LongTextNodeTextDownloadInfo(
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

    public WebInputLongTextNodeTextDownloadInfo() {
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
        return "WebInputLongTextNodeTextDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
