package com.dwarfeng.settingrepo.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.settingrepo.stack.bean.dto.IahnNodeMessageInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 国际化节点消息查询结果。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class FastJsonIahnNodeMessageInspectResult implements Dto {

    private static final long serialVersionUID = -3419278975581149979L;

    public static FastJsonIahnNodeMessageInspectResult of(IahnNodeMessageInspectResult iahnNodeMessageInspectResult) {
        if (Objects.isNull(iahnNodeMessageInspectResult)) {
            return null;
        } else {
            return new FastJsonIahnNodeMessageInspectResult(
                    iahnNodeMessageInspectResult.getMessage()
            );
        }
    }

    @JSONField(name = "message", ordinal = 1)
    private String message;

    public FastJsonIahnNodeMessageInspectResult() {
    }

    public FastJsonIahnNodeMessageInspectResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FastJsonIahnNodeMessageInspectResult{" +
                "message='" + message + '\'' +
                '}';
    }
}
