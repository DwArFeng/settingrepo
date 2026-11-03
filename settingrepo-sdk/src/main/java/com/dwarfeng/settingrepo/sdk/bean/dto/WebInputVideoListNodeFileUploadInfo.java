package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.dto.VideoListNodeFileUploadInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 视频列表节点文件上传信息。
 *
 * @author fucl
 * @since 3.2.0
 */
public class WebInputVideoListNodeFileUploadInfo implements Dto {

    private static final long serialVersionUID = 5456266624083783128L;

    public static VideoListNodeFileUploadInfo toStackBean(WebInputVideoListNodeFileUploadInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VideoListNodeFileUploadInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getIndex(),
                    webInput.getOriginName(),
                    webInput.getContent(),
                    webInput.getCoverContent()
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
    private Integer index;

    @JSONField(name = "origin_name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String originName;

    @JSONField(name = "content")
    @NotNull
    private byte[] content;

    @JSONField(name = "cover_content")
    @NotNull
    private byte[] coverContent;

    public WebInputVideoListNodeFileUploadInfo() {
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

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
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

    public byte[] getCoverContent() {
        return coverContent;
    }

    public void setCoverContent(byte[] coverContent) {
        this.coverContent = coverContent;
    }

    @Override
    public String toString() {
        return "WebInputVideoListNodeFileUploadInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", index=" + index +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                ", coverContent=" + Arrays.toString(coverContent) +
                '}';
    }
}
