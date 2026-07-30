package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.ImageListNodeFileDownloadInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 图片列表节点文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputImageListNodeFileDownloadInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 7915921399930517036L;

    public static ImageListNodeFileDownloadInfo toStackBean(WebInputImageListNodeFileDownloadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new ImageListNodeFileDownloadInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getIndex()
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

    @JSONField(name = "index")
    @PositiveOrZero
    private int index;

    public WebInputImageListNodeFileDownloadInfo() {
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "WebInputImageListNodeFileDownloadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                '}';
    }
}
