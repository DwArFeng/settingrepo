package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 国际化节点地区不存在异常。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class IahnNodeLocaleNotExistsException extends HandlerException {

    @Serial
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
        return StackMessages.message(StackMessageKey.EXCEPTION_IAHN_NODE_LOCALE_NOT_EXISTS, iahnNodeLocaleKey);
    }
}
