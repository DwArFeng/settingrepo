package com.dwarfeng.settingrepo.stack.exception;

import java.io.Serial;

/**
 * 格式化器执行异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class FormatterExecutionException extends FormatterException {

    @Serial
    private static final long serialVersionUID = -4378992086774101567L;

    public FormatterExecutionException() {
    }

    public FormatterExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatterExecutionException(String message) {
        super(message);
    }

    public FormatterExecutionException(Throwable cause) {
        super(cause);
    }
}
