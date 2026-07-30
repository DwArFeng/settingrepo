package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeRemoveInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 设置节点移除信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputSettingNodeRemoveInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -762444749184543024L;

    public static SettingNodeRemoveInfo toStackBean(WebInputSettingNodeRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SettingNodeRemoveInfo(
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

    public WebInputSettingNodeRemoveInfo() {
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
        return "WebInputSettingNodeRemoveInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
