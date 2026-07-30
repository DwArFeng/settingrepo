package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeFormatIndexInfo;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.WebInputLongIdKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 导航节点索引格式化信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class WebInputNavigationNodeFormatIndexInfo implements Bean {

    @Serial
    private static final long serialVersionUID = -3025913874325889645L;

    public static NavigationNodeFormatIndexInfo toStackBean(WebInputNavigationNodeFormatIndexInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NavigationNodeFormatIndexInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    WebInputLongIdKey.toStackBean(webInput.getParentItemKey())
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

    @JSONField(name = "parent_item_key")
    @Valid
    private WebInputLongIdKey parentItemKey;

    public WebInputNavigationNodeFormatIndexInfo() {
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

    public WebInputLongIdKey getParentItemKey() {
        return parentItemKey;
    }

    public void setParentItemKey(WebInputLongIdKey parentItemKey) {
        this.parentItemKey = parentItemKey;
    }

    @Override
    public String toString() {
        return "WebInputNavigationNodeFormatIndexInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", parentItemKey=" + parentItemKey +
                '}';
    }
}
