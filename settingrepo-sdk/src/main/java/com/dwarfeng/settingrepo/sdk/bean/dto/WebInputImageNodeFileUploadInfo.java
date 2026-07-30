package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageNodeFileUploadInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 图片节点文件上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputImageNodeFileUploadInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 2884526877317217205L;

    public static ImageNodeFileUploadInfo toStackBean(WebInputImageNodeFileUploadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new ImageNodeFileUploadInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getOriginName(),
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

    @JSONField(name = "origin_name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String originName;

    @JSONField(name = "content")
    @NotNull
    private byte[] content;

    public WebInputImageNodeFileUploadInfo() {
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

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WebInputImageNodeFileUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
