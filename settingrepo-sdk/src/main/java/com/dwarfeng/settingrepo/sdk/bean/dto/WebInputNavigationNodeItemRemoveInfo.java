package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeItemRemoveInfo;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.WebInputLongIdKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 导航节点条目移除信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class WebInputNavigationNodeItemRemoveInfo implements Bean {

    @Serial
    private static final long serialVersionUID = -9096711189954064410L;

    public static NavigationNodeItemRemoveInfo toStackBean(WebInputNavigationNodeItemRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NavigationNodeItemRemoveInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    WebInputLongIdKey.toStackBean(webInput.getItemKey())
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

    @JSONField(name = "item_key")
    @NotNull
    @Valid
    private WebInputLongIdKey itemKey;

    public WebInputNavigationNodeItemRemoveInfo() {
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

    public WebInputLongIdKey getItemKey() {
        return itemKey;
    }

    public void setItemKey(WebInputLongIdKey itemKey) {
        this.itemKey = itemKey;
    }

    @Override
    public String toString() {
        return "WebInputNavigationNodeItemRemoveInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", itemKey=" + itemKey +
                '}';
    }
}
