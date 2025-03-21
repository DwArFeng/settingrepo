package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMekPutInfo;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 国际化节点 Mek 推入信息。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class WebInputIahnNodeMekPutInfo implements Dto {

    private static final long serialVersionUID = -3951215790001796932L;

    public static IahnNodeMekPutInfo toStackBean(WebInputIahnNodeMekPutInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new IahnNodeMekPutInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getMekId(),
                    webInput.getLabel(),
                    webInput.getDefaultMessage(),
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

    @JSONField(name = "mek_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_IAHN_NODE_MEK_ID)
    private String mekId;

    @JSONField(name = "label")
    @Length(max = Constraints.LENGTH_NAME)
    private String label;

    @JSONField(name = "default_message")
    @NotNull
    @Length(max = Constraints.LENGTH_IAHN_NODE_MESSAGE)
    private String defaultMessage;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputIahnNodeMekPutInfo() {
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
        return "WebInputIahnNodeMekPutInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", mekId='" + mekId + '\'' +
                ", label='" + label + '\'' +
                ", defaultMessage='" + defaultMessage + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
