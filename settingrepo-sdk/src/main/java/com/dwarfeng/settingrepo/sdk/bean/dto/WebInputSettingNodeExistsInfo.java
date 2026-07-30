package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeExistsInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 设置节点存在信息
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputSettingNodeExistsInfo implements Dto {

    @Serial
    private static final long serialVersionUID = 2478343530653239719L;

    public static SettingNodeExistsInfo toStackBean(WebInputSettingNodeExistsInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SettingNodeExistsInfo(
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

    public WebInputSettingNodeExistsInfo() {
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
        return "WebInputSettingNodeExistsInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
