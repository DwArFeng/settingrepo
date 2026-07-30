package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeUpdateInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 导航节点更新信息。
 *
 * @author pengy
 * @since 2.0.1
 */
public class WebInputNavigationNodeUpdateInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 7224200180763995792L;

    public static NavigationNodeUpdateInfo toStackBean(WebInputNavigationNodeUpdateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NavigationNodeUpdateInfo(
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
    @NotNull
    private String content;

    public WebInputNavigationNodeUpdateInfo() {
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
        return "WebInputNavigationNodeUpdateInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", content='" + content + '\'' +
                '}';
    }
}
