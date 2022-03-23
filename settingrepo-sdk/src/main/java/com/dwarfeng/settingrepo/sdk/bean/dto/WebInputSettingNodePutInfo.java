package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodePutInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 设置节点推送信息。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputSettingNodePutInfo implements Dto {

    private static final long serialVersionUID = -1523921755143460717L;

    public static SettingNodePutInfo toStackBean(WebInputSettingNodePutInfo webInputSettingNodePutInfo) {
        if (Objects.isNull(webInputSettingNodePutInfo)) {
            return null;
        } else {
            return new SettingNodePutInfo(
                    webInputSettingNodePutInfo.getCategory(), webInputSettingNodePutInfo.getArgs(),
                    webInputSettingNodePutInfo.getValue(), webInputSettingNodePutInfo.getRemark()
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

    @JSONField(name = "value")
    private String value;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputSettingNodePutInfo() {
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
        return "WebInputSettingNodePutInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
