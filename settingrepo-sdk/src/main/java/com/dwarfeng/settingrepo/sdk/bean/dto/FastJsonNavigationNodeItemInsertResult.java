package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.NavigationNodeItemInsertResult;
import com.dwarfeng.subgrade.basic.stack.bean.Bean;
import com.dwarfeng.subgrade.web.sdk.bean.key.FastJsonLongIdKey;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * FastJson 导航节点条目插入结果。
 *
 * @author DwArFeng
 * @since 2.4.0
 */
public class FastJsonNavigationNodeItemInsertResult implements Bean {

    @Serial
    private static final long serialVersionUID = 8334187368087466090L;

    public static FastJsonNavigationNodeItemInsertResult of(
            NavigationNodeItemInsertResult navigationNodeItemInsertResult
    ) {
        if (Objects.isNull(navigationNodeItemInsertResult)) {
            return null;
        } else {
            return new FastJsonNavigationNodeItemInsertResult(
                    navigationNodeItemInsertResult.getCategory(),
                    navigationNodeItemInsertResult.getArgs(),
                    FastJsonLongIdKey.of(navigationNodeItemInsertResult.getItemKey())
            );
        }
    }

    @JSONField(name = "category", ordinal = 1)
    private String category;

    @JSONField(name = "args", ordinal = 2)
    private String[] args;

    @JSONField(name = "item_key", ordinal = 3)
    private FastJsonLongIdKey itemKey;

    public FastJsonNavigationNodeItemInsertResult() {
    }

    public FastJsonNavigationNodeItemInsertResult(String category, String[] args, FastJsonLongIdKey itemKey) {
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

    public FastJsonLongIdKey getItemKey() {
        return itemKey;
    }

    public void setItemKey(FastJsonLongIdKey itemKey) {
        this.itemKey = itemKey;
    }

    @Override
    public String toString() {
        return "FastJsonNavigationNodeItemInsertResult{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", itemKey=" + itemKey +
                '}';
    }
}
