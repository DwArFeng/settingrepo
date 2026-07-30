package com.dwarfeng.settingrepo.stack.exception;

import java.io.Serial;

/**
 * 格式化器构造异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FormatterMakeException extends FormatterException {

    @Serial
    private static final long serialVersionUID = 3278834776271914168L;

    public FormatterMakeException() {
    }

    public FormatterMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatterMakeException(String message) {
        super(message);
    }

    public FormatterMakeException(Throwable cause) {
        super(cause);
    }
}
