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
    public static final ServiceException.Code SETTING_NODE_EXISTS =
            new ServiceException.Code(offset(20), "setting node exists");
    public static final ServiceException.Code SETTING_NODE_NOT_EXISTS =
            new ServiceException.Code(offset(30), "setting node not exists");
    public static final ServiceException.Code IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND =
            new ServiceException.Code(offset(40), "image list node index out of bound");
    public static final ServiceException.Code INVALID_IAHN_NODE_LOCALE =
            new ServiceException.Code(offset(50), "invalid iahn node locale");
    public static final ServiceException.Code INVALID_IAHN_NODE_MEK_ID =
            new ServiceException.Code(offset(60), "invalid iahn node mek id");
    public static final ServiceException.Code IAHN_NODE_LOCALE_NOT_EXISTS =
            new ServiceException.Code(offset(70), "iahn node locale not exists");
    public static final ServiceException.Code IAHN_NODE_MEK_NOT_EXISTS =
            new ServiceException.Code(offset(80), "iahn node mek not exists");
    public static final ServiceException.Code FILE_LIST_NODE_INDEX_OUT_OF_BOUND =
            new ServiceException.Code(offset(90), "file list node index out of bound");
    public static final ServiceException.Code NAVIGATION_NODE_ITEM_INDEX_CONFLICT =
            new ServiceException.Code(offset(100), "navigation node item index conflict");
    public static final ServiceException.Code NAVIGATION_NODE_ITEM_MISMATCHED =
            new ServiceException.Code(offset(110), "navigation node item mismatched");
    public static final ServiceException.Code NAVIGATION_NODE_ITEM_NOT_EXISTS =
            new ServiceException.Code(offset(120), "navigation node item not exists");

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
        SETTING_NODE_EXISTS.setCode(offset(20));
        SETTING_NODE_NOT_EXISTS.setCode(offset(30));
        IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND.setCode(offset(40));
        INVALID_IAHN_NODE_LOCALE.setCode(offset(50));
        INVALID_IAHN_NODE_MEK_ID.setCode(offset(60));
        IAHN_NODE_LOCALE_NOT_EXISTS.setCode(offset(70));
        IAHN_NODE_MEK_NOT_EXISTS.setCode(offset(80));
        FILE_LIST_NODE_INDEX_OUT_OF_BOUND.setCode(offset(90));
        NAVIGATION_NODE_ITEM_INDEX_CONFLICT.setCode(offset(100));
        NAVIGATION_NODE_ITEM_MISMATCHED.setCode(offset(110));
        NAVIGATION_NODE_ITEM_NOT_EXISTS.setCode(offset(120));
    }

    private ServiceExceptionCodes() {
        throw new IllegalStateException("禁止实例化");
    }
}
