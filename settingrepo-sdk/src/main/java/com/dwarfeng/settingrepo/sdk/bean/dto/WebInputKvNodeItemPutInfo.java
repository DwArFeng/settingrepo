package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.dto.KvNodeItemPutInfo;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 键值对节点条目放入信息。
 *
 * @author wangxy
 * @since 2.5.0
 */
public class WebInputKvNodeItemPutInfo implements Bean {

    @Serial
    private static final long serialVersionUID = 4678326693881562455L;

    public static KvNodeItemPutInfo toStackBean(WebInputKvNodeItemPutInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new KvNodeItemPutInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getItemStringId(),
                    webInput.getValue()
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
    @Length(max = Constraints.LENGTH_KV_NODE_ITEM_ID)
    private String itemStringId;

    @JSONField(name = "value")
    private String value;

    public WebInputKvNodeItemPutInfo() {
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WebInputKvNodeItemPutInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", itemStringId='" + itemStringId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
