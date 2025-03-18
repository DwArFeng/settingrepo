package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 国际化节点地区不存在异常。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeLocaleNotExistsException extends HandlerException {

    private static final long serialVersionUID = 5675858874401519682L;

    private final IahnNodeLocaleKey iahnNodeLocaleKey;

    public IahnNodeLocaleNotExistsException(IahnNodeLocaleKey iahnNodeLocaleKey) {
        this.iahnNodeLocaleKey = iahnNodeLocaleKey;
    }

    public IahnNodeLocaleNotExistsException(Throwable cause, IahnNodeLocaleKey iahnNodeLocaleKey) {
        super(cause);
        this.iahnNodeLocaleKey = iahnNodeLocaleKey;
    }

    @Override
    public String getMessage() {
        return "国际化节点地区 " + iahnNodeLocaleKey + " 不存在";
    }
}
