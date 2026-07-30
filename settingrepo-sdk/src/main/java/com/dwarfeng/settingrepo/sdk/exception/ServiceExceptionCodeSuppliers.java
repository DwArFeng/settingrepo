package com.dwarfeng.settingrepo.sdk.exception;

import com.dwarfeng.settingrepo.sdk.internal.i18n.SdkMessageKey;
import com.dwarfeng.settingrepo.sdk.internal.i18n.SdkMessages;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;

import java.util.function.Supplier;

/**
 * Setting Repository 模块服务异常代码供应器。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public final class ServiceExceptionCodeSuppliers {

    private static volatile int EXCEPTION_CODE_OFFSET = 10000;

    /**
     * 格式化器失败。
     */
    public static final Supplier<ServiceException.Code> FORMATTER_FAILED =
            () -> new ServiceException.Code(
                    offset(0), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_FORMATTER_FAILED)
            );

    /**
     * 格式化器执行失败。
     */
    public static final Supplier<ServiceException.Code> FORMATTER_EXECUTION_FAILED =
            () -> new ServiceException.Code(
                    offset(1), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_FORMATTER_EXECUTION_FAILED)
            );

    /**
     * 格式化器构造失败。
     */
    public static final Supplier<ServiceException.Code> FORMATTER_MAKE_FAILED =
            () -> new ServiceException.Code(
                    offset(2), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_FORMATTER_MAKE_FAILED)
            );

    /**
     * 不支持的格式化器类型。
     */
    public static final Supplier<ServiceException.Code> UNSUPPORTED_FORMATTER_TYPE =
            () -> new ServiceException.Code(
                    offset(3), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_UNSUPPORTED_FORMATTER_TYPE)
            );

    /**
     * 设置类别不存在。
     */
    public static final Supplier<ServiceException.Code> SETTING_CATEGORY_NOT_EXISTS =
            () -> new ServiceException.Code(
                    offset(10), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_SETTING_CATEGORY_NOT_EXISTS)
            );

    /**
     * 设置节点已存在。
     */
    public static final Supplier<ServiceException.Code> SETTING_NODE_EXISTS =
            () -> new ServiceException.Code(
                    offset(20), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_SETTING_NODE_EXISTS)
            );

    /**
     * 设置节点不存在。
     */
    public static final Supplier<ServiceException.Code> SETTING_NODE_NOT_EXISTS =
            () -> new ServiceException.Code(
                    offset(30), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_SETTING_NODE_NOT_EXISTS)
            );

    /**
     * 图片列表节点索引越界。
     */
    public static final Supplier<ServiceException.Code> IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND =
            () -> new ServiceException.Code(
                    offset(40),
                    SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND)
            );

    /**
     * 国际化节点语言环境无效。
     */
    public static final Supplier<ServiceException.Code> INVALID_IAHN_NODE_LOCALE =
            () -> new ServiceException.Code(
                    offset(50), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_INVALID_IAHN_NODE_LOCALE)
            );

    /**
     * 国际化节点消息键 ID 无效。
     */
    public static final Supplier<ServiceException.Code> INVALID_IAHN_NODE_MEK_ID =
            () -> new ServiceException.Code(
                    offset(60), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_INVALID_IAHN_NODE_MEK_ID)
            );

    /**
     * 国际化节点语言环境不存在。
     */
    public static final Supplier<ServiceException.Code> IAHN_NODE_LOCALE_NOT_EXISTS =
            () -> new ServiceException.Code(
                    offset(70), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_IAHN_NODE_LOCALE_NOT_EXISTS)
            );

    /**
     * 国际化节点消息键不存在。
     */
    public static final Supplier<ServiceException.Code> IAHN_NODE_MEK_NOT_EXISTS =
            () -> new ServiceException.Code(
                    offset(80), SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_IAHN_NODE_MEK_NOT_EXISTS)
            );

    /**
     * 文件列表节点索引越界。
     */
    public static final Supplier<ServiceException.Code> FILE_LIST_NODE_INDEX_OUT_OF_BOUND =
            () -> new ServiceException.Code(
                    offset(90),
                    SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_FILE_LIST_NODE_INDEX_OUT_OF_BOUND)
            );

    /**
     * 导航节点项目索引冲突。
     */
    public static final Supplier<ServiceException.Code> NAVIGATION_NODE_ITEM_INDEX_CONFLICT =
            () -> new ServiceException.Code(
                    offset(100),
                    SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_NAVIGATION_NODE_ITEM_INDEX_CONFLICT)
            );

    /**
     * 导航节点项目不匹配。
     */
    public static final Supplier<ServiceException.Code> NAVIGATION_NODE_ITEM_MISMATCHED =
            () -> new ServiceException.Code(
                    offset(110),
                    SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_NAVIGATION_NODE_ITEM_MISMATCHED)
            );

    /**
     * 导航节点项目不存在。
     */
    public static final Supplier<ServiceException.Code> NAVIGATION_NODE_ITEM_NOT_EXISTS =
            () -> new ServiceException.Code(
                    offset(120),
                    SdkMessages.message(SdkMessageKey.SERVICE_EXCEPTION_NAVIGATION_NODE_ITEM_NOT_EXISTS)
            );

    // 为了程序的可扩展性，此处不做代码简化。
    @SuppressWarnings("SameParameterValue")
    private static int offset(int code) {
        return EXCEPTION_CODE_OFFSET + code;
    }

    /**
     * 获取异常代码的偏移量。
     *
     * @return 异常代码的偏移量。
     */
    public static int getExceptionCodeOffset() {
        return EXCEPTION_CODE_OFFSET;
    }

    /**
     * 设置异常代码的偏移量。
     *
     * <p>
     * 该方法只更新后续生成异常代码所使用的偏移量，已经创建的异常代码保持不变。
     *
     * @param exceptionCodeOffset 指定的异常代码偏移量。
     */
    public static void setExceptionCodeOffset(int exceptionCodeOffset) {
        EXCEPTION_CODE_OFFSET = exceptionCodeOffset;
    }

    private ServiceExceptionCodeSuppliers() {
        throw new IllegalStateException("禁止实例化");
    }
}
