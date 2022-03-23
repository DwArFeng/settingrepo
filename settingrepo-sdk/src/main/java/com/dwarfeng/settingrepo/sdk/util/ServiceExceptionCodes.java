package com.dwarfeng.settingrepo.sdk.util;

import com.dwarfeng.subgrade.stack.exception.ServiceException;

/**
 * 服务异常代码。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public final class ServiceExceptionCodes {

    private static int EXCEPTION_CODE_OFFSET = 10000;

    public static final ServiceException.Code FORMATTER_FAILED =
            new ServiceException.Code(offset(0), "formatter failed");
    public static final ServiceException.Code FORMATTER_EXECUTION_FAILED =
            new ServiceException.Code(offset(1), "formatter execution failed");
    public static final ServiceException.Code FORMATTER_MAKE_FAILED =
            new ServiceException.Code(offset(2), "formatter make failed");
    public static final ServiceException.Code UNSUPPORTED_FORMATTER_TYPE =
            new ServiceException.Code(offset(3), "unsupported formatter type");
    public static final ServiceException.Code SETTING_CATEGORY_NOT_EXISTS =
            new ServiceException.Code(offset(10), "setting category not exists");

    private static int offset(int i) {
        return EXCEPTION_CODE_OFFSET + i;
    }

    /**
     * 获取异常代号的偏移量。
     *
     * @return 异常代号的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代号的偏移量。
     *
     * @param exceptionCodeOffset 指定的异常代号的偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        // 设置 EXCEPTION_CODE_OFFSET 的值。
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;

        // 以新的 EXCEPTION_CODE_OFFSET 为基准，更新异常代码的值。
        FORMATTER_FAILED.setCode(offset(0));
        FORMATTER_EXECUTION_FAILED.setCode(offset(1));
        FORMATTER_MAKE_FAILED.setCode(offset(2));
        UNSUPPORTED_FORMATTER_TYPE.setCode(offset(3));
        SETTING_CATEGORY_NOT_EXISTS.setCode(offset(10));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
