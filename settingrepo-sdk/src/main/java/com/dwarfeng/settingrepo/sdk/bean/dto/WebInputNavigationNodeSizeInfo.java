package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeSizeInfo;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 导航节点大小信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class WebInputNavigationNodeSizeInfo implements Bean {

    @Serial
    private static final long serialVersionUID = 754338692771140852L;

    public static NavigationNodeSizeInfo toStackBean(WebInputNavigationNodeSizeInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NavigationNodeSizeInfo(
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

    public WebInputNavigationNodeSizeInfo() {
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
        return "WebInputNavigationNodeSizeInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
