package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.VideoNodeThumbnailDownloadInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 视频节点缩略图下载信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class WebInputVideoNodeThumbnailDownloadInfo implements Dto {

    private static final long serialVersionUID = -6825105050907758825L;

    public static VideoNodeThumbnailDownloadInfo toStackBean(WebInputVideoNodeThumbnailDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VideoNodeThumbnailDownloadInfo(
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

    public WebInputVideoNodeThumbnailDownloadInfo() {
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
        return "WebInputVideoNodeThumbnailDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
