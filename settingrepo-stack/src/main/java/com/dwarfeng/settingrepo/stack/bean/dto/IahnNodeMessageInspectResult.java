package com.dwarfeng.settingrepo.stack.bean.dto;

import com.dwarfeng.subgrade.basic.stack.bean.dto.Dto;

import java.io.Serial;

/**
 * 国际化节点消息查询结果。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeMessageInspectResult implements Dto {

    @Serial
    private static final long serialVersionUID = 1667494795169569821L;

    private String message;

    public IahnNodeMessageInspectResult() {
    }

    public IahnNodeMessageInspectResult(String message) {
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
        return "IahnNodeMessageInspectResult{" +
                "message='" + message + '\'' +
                '}';
    }
}
