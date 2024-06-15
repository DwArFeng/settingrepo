package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.sdk.util.ValidSettingNodeType;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeInitInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 设置节点初始化信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputSettingNodeInitInfo implements Dto {

    private static final long serialVersionUID = 2882223257075802263L;

    public static SettingNodeInitInfo toStackBean(WebInputSettingNodeInitInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SettingNodeInitInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getType(),
                    webInput.getRemark()
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

    @JSONField(name = "type")
    @ValidSettingNodeType
    private int type;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputSettingNodeInitInfo() {
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
        return "WebInputSettingNodeInitInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", type=" + type +
                ", remark='" + remark + '\'' +
                '}';
    }
}
