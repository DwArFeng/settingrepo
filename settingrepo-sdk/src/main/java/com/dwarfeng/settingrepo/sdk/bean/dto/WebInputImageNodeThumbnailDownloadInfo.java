package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageNodeThumbnailDownloadInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 图片节点缩略图下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputImageNodeThumbnailDownloadInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -3477621187374447440L;

    public static ImageNodeThumbnailDownloadInfo toStackBean(WebInputImageNodeThumbnailDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new ImageNodeThumbnailDownloadInfo(
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

    public WebInputImageNodeThumbnailDownloadInfo() {
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
        return "WebInputImageNodeThumbnailDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
