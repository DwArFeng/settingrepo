package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;
import com.dwarfeng.subgrade.basic.stack.exception.HandlerException;

import java.io.Serial;

/**
 * 无效的国际化节点地区异常。
 *
 * @author DwArFeng
 * @since 2.1.0
 */
public class InvalidIahnNodeLocaleException extends HandlerException {

    @Serial
    private static final long serialVersionUID = -2404856197358272385L;

    private final String language;
    private final String country;
    private final String variant;

    public InvalidIahnNodeLocaleException(String language, String country, String variant) {
        this.language = language;
        this.country = country;
        this.variant = variant;
    }

    public InvalidIahnNodeLocaleException(Throwable cause, String language, String country, String variant) {
        super(cause);
        this.language = language;
        this.country = country;
        this.variant = variant;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.INVALID_IAHN_NODE_LOCALE, language, country, variant);
    }
}
