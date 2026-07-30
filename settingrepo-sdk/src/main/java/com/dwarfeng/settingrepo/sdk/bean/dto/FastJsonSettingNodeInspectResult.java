package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeInspectResult;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;
import java.util.Date;
import java.util.Objects;

/**
 * FastJson 设置节点查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonSettingNodeInspectResult implements Dto {

    @Serial
    private static final long serialVersionUID = -1443938298030721235L;

    public static FastJsonSettingNodeInspectResult of(SettingNodeInspectResult settingNodeInspectResult) {
        if (Objects.isNull(settingNodeInspectResult)) {
            return null;
        } else {
            return new FastJsonSettingNodeInspectResult(
                    settingNodeInspectResult.getType(),
                    settingNodeInspectResult.getLastModifiedDate(),
                    settingNodeInspectResult.getRemark()
            );
        }
    }

    @JSONField(name = "type", ordinal = 1)
    private int type;

    @JSONField(name = "last_modified_date", ordinal = 2)
    private Date lastModifiedDate;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonSettingNodeInspectResult() {
    }

    public FastJsonSettingNodeInspectResult(int type, Date lastModifiedDate, String remark) {
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
        return "FastJsonSettingNodeInspectResult{" +
                "type=" + type +
                ", lastModifiedDate=" + lastModifiedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
