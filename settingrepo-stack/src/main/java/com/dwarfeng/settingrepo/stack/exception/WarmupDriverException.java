package com.dwarfeng.settingrepo.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 预热驱动器异常。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public class WarmupDriverException extends HandlerException {

    private static final long serialVersionUID = -8129168950884099929L;

    public WarmupDriverException() {
    }

    public WarmupDriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public WarmupDriverException(String message) {
        super(message);
    }

    public WarmupDriverException(Throwable cause) {
        super(cause);
    }
}
