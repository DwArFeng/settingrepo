package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageNodeFileStreamDownloadInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 图片节点文件流下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputImageNodeFileStreamDownloadInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 782294915894970935L;

    public static ImageNodeFileStreamDownloadInfo toStackBean(WebInputImageNodeFileStreamDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new ImageNodeFileStreamDownloadInfo(
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

    public WebInputImageNodeFileStreamDownloadInfo() {
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
        return "WebInputImageNodeFileStreamDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
