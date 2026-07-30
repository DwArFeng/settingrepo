package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.KvNodeItemInspectInfo;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 键值对节点条目查看信息。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class WebInputKvNodeItemInspectInfo implements Bean {

    @Serial
    private static final long serialVersionUID = -7465600614607284177L;

    public static KvNodeItemInspectInfo toStackBean(WebInputKvNodeItemInspectInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new KvNodeItemInspectInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getItemStringId()
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

    @JSONField(name = "item_string_id")
    @NotNull
    @NotEmpty
    private String itemStringId;

    public WebInputKvNodeItemInspectInfo() {
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

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    @Override
    public String toString() {
        return "WebInputKvNodeItemInspectInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
