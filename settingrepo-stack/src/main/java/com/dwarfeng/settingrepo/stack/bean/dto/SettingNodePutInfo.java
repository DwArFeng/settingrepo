package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 设置节点推送信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class SettingNodePutInfo implements Dto {

    private static final long serialVersionUID = -4979575344450207333L;

    private String category;
    private String[] args;
    private String value;
    private String remark;

    public SettingNodePutInfo() {
    }

    public SettingNodePutInfo(String category, String[] args, String value, String remark) {
        this.category = category;
        this.args = args;
        this.value = value;
        this.remark = remark;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SettingNodePutInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
