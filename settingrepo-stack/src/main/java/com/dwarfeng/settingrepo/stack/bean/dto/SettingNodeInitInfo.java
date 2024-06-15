package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 设置节点初始化信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class SettingNodeInitInfo implements Dto {

    private static final long serialVersionUID = -8019330711805081061L;
    
    private String category;
    private String[] args;
    private int type;
    private String remark;

    public SettingNodeInitInfo() {
    }

    public SettingNodeInitInfo(String category, String[] args, int type, String remark) {
        this.category = category;
        this.args = args;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SettingNodeInitInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", type=" + type +
                ", remark='" + remark + '\'' +
                '}';
    }
}
