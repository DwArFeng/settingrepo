package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeItemUpdateInfo;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.WebInputLongIdKey;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 导航节点条目更新信息。
 *
 * @author DwArFeng
 * @since 2.4.2
 */
public class WebInputNavigationNodeItemUpdateInfo implements Bean {

    @Serial
    private static final long serialVersionUID = 8166434304083811546L;

    public static NavigationNodeItemUpdateInfo toStackBean(WebInputNavigationNodeItemUpdateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new NavigationNodeItemUpdateInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    WebInputLongIdKey.toStackBean(webInput.getItemKey()),
                    WebInputLongIdKey.toStackBean(webInput.getParentItemKey()),
                    webInput.getIndex(),
                    webInput.getName(),
                    webInput.getContent(),
                    webInput.getRemark()
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

    @JSONField(name = "parent_item_key")
    @Valid
    private WebInputLongIdKey parentItemKey;

    @JSONField(name = "index")
    @PositiveOrZero
    private int index;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAVIGATION_NODE_NAME)
    private String name;

    @JSONField(name = "content")
    @NotNull
    private String content;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputNavigationNodeItemUpdateInfo() {
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

    public WebInputLongIdKey getParentItemKey() {
        return parentItemKey;
    }

    public void setParentItemKey(WebInputLongIdKey parentItemKey) {
        this.parentItemKey = parentItemKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputNavigationNodeItemUpdateInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", itemKey=" + itemKey +
                ", parentItemKey=" + parentItemKey +
                ", index=" + index +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
