package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeLocaleListInspectInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 国际化节点地区列表查询信息。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
public class WebInputIahnNodeLocaleListInspectInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -5708885972265903016L;

    public static IahnNodeLocaleListInspectInfo toStackBean(WebInputIahnNodeLocaleListInspectInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new IahnNodeLocaleListInspectInfo(
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

    public WebInputIahnNodeLocaleListInspectInfo() {
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
        return "WebInputIahnNodeLocaleListInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
