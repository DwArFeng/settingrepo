package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeItemInsertResult;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.JSFixedFastJsonLongIdKey;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * JSFixed FastJson 导航节点条目插入结果。
 *
 * @author DwArFeng
 * @since 2.4.0
 */
public class JSFixedFastJsonNavigationNodeItemInsertResult implements Bean {

    @Serial
    private static final long serialVersionUID = 1447451151077300161L;

    public static JSFixedFastJsonNavigationNodeItemInsertResult of(
            NavigationNodeItemInsertResult navigationNodeItemInsertResult
    ) {
        if (Objects.isNull(navigationNodeItemInsertResult)) {
            return null;
        } else {
            return new JSFixedFastJsonNavigationNodeItemInsertResult(
                    navigationNodeItemInsertResult.getCategory(),
                    navigationNodeItemInsertResult.getArgs(),
                    JSFixedFastJsonLongIdKey.of(navigationNodeItemInsertResult.getItemKey())
            );
        }
    }

    @JSONField(name = "category", ordinal = 1)
    private String category;

    @JSONField(name = "args", ordinal = 2)
    private String[] args;

    @JSONField(name = "item_key", ordinal = 3)
    private JSFixedFastJsonLongIdKey itemKey;

    public JSFixedFastJsonNavigationNodeItemInsertResult() {
    }

    public JSFixedFastJsonNavigationNodeItemInsertResult(
            String category, String[] args, JSFixedFastJsonLongIdKey itemKey
    ) {
        this.category = category;
        this.args = args;
        this.itemKey = itemKey;
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

    public JSFixedFastJsonLongIdKey getItemKey() {
        return itemKey;
    }

    public void setItemKey(JSFixedFastJsonLongIdKey itemKey) {
        this.itemKey = itemKey;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonNavigationNodeItemInsertResult{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", itemKey=" + itemKey +
                '}';
    }
}
