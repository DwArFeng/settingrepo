package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.KvNodeCountInfo;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 键值对节点数量信息。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class WebInputKvNodeCountInfo implements Bean {

    @Serial
    private static final long serialVersionUID = -1950642832837377177L;

    public static KvNodeCountInfo toStackBean(WebInputKvNodeCountInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new KvNodeCountInfo(
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

    public WebInputKvNodeCountInfo() {
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
        return "WebInputKvNodeCountInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
