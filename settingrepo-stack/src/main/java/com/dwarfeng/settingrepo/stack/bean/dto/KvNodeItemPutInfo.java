package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Arrays;

/**
 * 键值对节点条目放入信息。
 *
 * @author DwArFeng
 * @since 2.5.0
 */
public class KvNodeItemPutInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -996646441343107756L;

    private String category;
    private String[] args;
    private String itemStringId;
    private String value;

    public KvNodeItemPutInfo() {
    }

    public KvNodeItemPutInfo(String category, String[] args, String itemStringId, String value) {
        this.category = category;
        this.args = args;
        this.itemStringId = itemStringId;
        this.value = value;
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
        return "KvNodeItemPutInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", itemStringId='" + itemStringId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
