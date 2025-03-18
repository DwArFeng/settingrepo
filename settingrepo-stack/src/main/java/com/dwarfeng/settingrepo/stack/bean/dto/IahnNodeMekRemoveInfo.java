package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 国际化节点 Mek 移除信息。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeMekRemoveInfo implements Dto {

    private static final long serialVersionUID = -9149077671159482577L;

    private String category;
    private String[] args;
    private String mekId;

    public IahnNodeMekRemoveInfo() {
    }

    public IahnNodeMekRemoveInfo(String category, String[] args, String mekId) {
        this.category = category;
        this.args = args;
        this.mekId = mekId;
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

    @Override
    public String toString() {
        return "IahnNodeMekRemoveInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", mekId='" + mekId + '\'' +
                '}';
    }
}
