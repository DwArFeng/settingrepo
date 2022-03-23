package com.dwarfeng.settingrepo.stack.exception;

/**
 * @author DwArFeng
 * @since 1.0.0
 */
public class UnsupportedFormatterTypeException extends FormatterException {

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
        return "不支持的格式化器类型: " + type;
    }
}
