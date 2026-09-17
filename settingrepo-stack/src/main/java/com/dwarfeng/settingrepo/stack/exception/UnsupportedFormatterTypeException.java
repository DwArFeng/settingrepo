package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessageKey;
import com.dwarfeng.settingrepo.stack.internal.i18n.StackMessages;

import java.io.Serial;

/**
 * @author DwArFeng
 * @since 1.0.0
 */
public class UnsupportedFormatterTypeException extends FormatterException {

    @Serial
    private static final long serialVersionUID = 230153361991622499L;

    private final String type;

    public UnsupportedFormatterTypeException(String type) {
        this.type = type;
    }

    public UnsupportedFormatterTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return StackMessages.message(StackMessageKey.EXCEPTION_UNSUPPORTED_FORMATTER_TYPE, type);
    }
}
