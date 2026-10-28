package com.dwarfeng.settingrepo.stack.exception;

/**
 * 不支持的预热驱动器类型异常。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class UnsupportedWarmupDriverTypeDriverException extends WarmupDriverException {

    private static final long serialVersionUID = -6876496291519777023L;

    private final String type;

    public UnsupportedWarmupDriverTypeDriverException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的预热驱动器类型: " + type;
    }
}
