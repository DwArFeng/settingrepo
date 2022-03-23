package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeInspectInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 设置节点查看信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputSettingNodeInspectInfo implements Dto {

    private static final long serialVersionUID = 5989683822681749451L;

    public static SettingNodeInspectInfo toStackBean(WebInputSettingNodeInspectInfo webInputSettingNodeInspectInfo) {
        if (Objects.isNull(webInputSettingNodeInspectInfo)) {
            return null;
        } else {
            return new SettingNodeInspectInfo(
                    webInputSettingNodeInspectInfo.getCategory(), webInputSettingNodeInspectInfo.getArgs()
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

    public WebInputSettingNodeInspectInfo() {
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
        return "WebInputSettingNodeInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
