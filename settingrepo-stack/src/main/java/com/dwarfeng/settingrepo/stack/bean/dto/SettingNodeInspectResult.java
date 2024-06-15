package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Date;

/**
 * 设置节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class SettingNodeInspectResult implements Dto {

    private static final long serialVersionUID = 8408125690249895375L;

    private int type;
    private Date lastModifiedDate;
    private String remark;

    public SettingNodeInspectResult() {
    }

    public SettingNodeInspectResult(int type, Date lastModifiedDate, String remark) {
        this.type = type;
        this.lastModifiedDate = lastModifiedDate;
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SettingNodeInspectResult{" +
                "type=" + type +
                ", lastModifiedDate=" + lastModifiedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
