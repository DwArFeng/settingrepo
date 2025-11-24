package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeItemRemoveInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 导航节点条目移除信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class WebInputNavigationNodeItemRemoveInfo implements Bean {

    private static final long serialVersionUID = -8719617966320665338L;

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
