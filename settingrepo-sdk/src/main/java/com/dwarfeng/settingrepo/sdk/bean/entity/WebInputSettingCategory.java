package com.dwarfeng.settingrepo.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.sdk.util.Constraints;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 设置类别。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class WebInputSettingCategory implements Bean {

    private static final long serialVersionUID = 4219337316980965582L;

    public static SettingCategory toStackBean(WebInputSettingCategory webInputSettingCategory) {
        if (Objects.isNull(webInputSettingCategory)) {
            return null;
        } else {
            return new SettingCategory(
                    WebInputStringIdKey.toStackBean(webInputSettingCategory.getKey()),
                    webInputSettingCategory.getFormatterUsing(), webInputSettingCategory.getFormatterParam(),
                    webInputSettingCategory.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    private WebInputStringIdKey key;

    @JSONField(name = "formatter_using")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_TYPE)
    private String formatterUsing;

    @JSONField(name = "formatter_param")
    private String formatterParam;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputSettingCategory() {
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
        this.key = key;
    }

    public String getFormatterUsing() {
        return formatterUsing;
    }

    public void setFormatterUsing(String formatterUsing) {
        this.formatterUsing = formatterUsing;
    }

    public String getFormatterParam() {
        return formatterParam;
    }

    public void setFormatterParam(String formatterParam) {
        this.formatterParam = formatterParam;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputSettingCategory{" +
                "key=" + key +
                ", formatterUsing='" + formatterUsing + '\'' +
                ", formatterParam='" + formatterParam + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
