package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson2.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMekRemoveInfo;
import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.util.Arrays;
import java.util.Objects;

/**
 * WebInput 国际化节点 Mek 移除信息。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class WebInputIahnNodeMekRemoveInfo implements Dto {

    @Serial
    private static final long serialVersionUID = -1414061061347434050L;

    public static IahnNodeMekRemoveInfo toStackBean(WebInputIahnNodeMekRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new IahnNodeMekRemoveInfo(
                    webInput.getCategory(),
                    webInput.getArgs(),
                    webInput.getMekId()
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

    public WebInputIahnNodeMekRemoveInfo() {
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
        return "WebInputIahnNodeMekRemoveInfo{" +
                "category='" + category + '\'' +
                ", args=" + Arrays.toString(args) +
                ", mekId='" + mekId + '\'' +
                '}';
    }
}
