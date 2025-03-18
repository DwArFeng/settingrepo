package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 国际化节点 Mek 推入信息。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeMekPutInfo implements Dto {

    private static final long serialVersionUID = 1251089527362614701L;

    private String category;
    private String[] args;
    private String mekId;
    private String label;
    private String defaultMessage;
    private String remark;

    public IahnNodeMekPutInfo() {
    }

    public IahnNodeMekPutInfo(
            String category, String[] args, String mekId, String label, String defaultMessage, String remark
    ) {
        this.category = category;
        this.args = args;
        this.mekId = mekId;
        this.label = label;
        this.defaultMessage = defaultMessage;
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

    public String getMekId() {
        return mekId;
    }

    public void setMekId(String mekId) {
        this.mekId = mekId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "IahnNodeMekPutInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", mekId='" + mekId + '\'' +
                ", label='" + label + '\'' +
                ", defaultMessage='" + defaultMessage + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
