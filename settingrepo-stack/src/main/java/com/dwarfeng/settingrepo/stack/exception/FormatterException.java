package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 格式化异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FormatterException extends HandlerException {

    private static final long serialVersionUID = 8453405276463294996L;

    public FormatterException() {
    }

    public FormatterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatterException(String message) {
        super(message);
    }

    public FormatterException(Throwable cause) {
        super(cause);
    }
}
