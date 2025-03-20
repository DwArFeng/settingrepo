package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.LongTextNodeTextUploadInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 长文本节点文本上传信息。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class WebInputLongTextNodeTextUploadInfo implements Dto {

    private static final long serialVersionUID = -2781757875035931815L;

    public static LongTextNodeTextUploadInfo toStackBean(WebInputLongTextNodeTextUploadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new LongTextNodeTextUploadInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
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

    @JSONField(name = "content")
    private String content;

    public WebInputLongTextNodeTextUploadInfo() {
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WebInputLongTextNodeTextUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", content='" + content + '\'' +
                '}';
    }
}
