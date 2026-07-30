package com.dwarfeng.settingrepo.sdk.exception;

import com.dwarfeng.settingrepo.stack.exception.*;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Setting Repository 模块服务异常帮助类。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public final class ServiceExceptionHelper {

    /**
     * 向指定的映射中添加 Setting Repository 模块默认的目标映射。
     *
     * <p>
     * 该方法可以在配置类中快速搭建异常目标映射。映射保存异常代码供应器，调用方在映射异常时解析当前代码快照。
     *
     * @param map 指定的映射，允许为 null。
     * @return 添加了默认目标的映射。
     */
    public static Map<Class<? extends Exception>, Supplier<ServiceException.Code>> putDefaultDestination(
            Map<Class<? extends Exception>, Supplier<ServiceException.Code>> map
    ) {
        if (Objects.isNull(map)) {
            map = new HashMap<>();
        }

        map.put(FormatterException.class, ServiceExceptionCodeSuppliers.FORMATTER_FAILED);
        map.put(FormatterExecutionException.class, ServiceExceptionCodeSuppliers.FORMATTER_EXECUTION_FAILED);
        map.put(FormatterMakeException.class, ServiceExceptionCodeSuppliers.FORMATTER_MAKE_FAILED);
        map.put(UnsupportedFormatterTypeException.class, ServiceExceptionCodeSuppliers.UNSUPPORTED_FORMATTER_TYPE);
        map.put(SettingCategoryNotExistsException.class, ServiceExceptionCodeSuppliers.SETTING_CATEGORY_NOT_EXISTS);
        map.put(SettingNodeExistsException.class, ServiceExceptionCodeSuppliers.SETTING_NODE_EXISTS);
        map.put(SettingNodeNotExistsException.class, ServiceExceptionCodeSuppliers.SETTING_NODE_NOT_EXISTS);
        map.put(
                ImageListNodeIndexOutOfBoundException.class,
                ServiceExceptionCodeSuppliers.IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND
        );
        map.put(InvalidIahnNodeLocaleException.class, ServiceExceptionCodeSuppliers.INVALID_IAHN_NODE_LOCALE);
        map.put(InvalidIahnNodeMekIdException.class, ServiceExceptionCodeSuppliers.INVALID_IAHN_NODE_MEK_ID);
        map.put(IahnNodeLocaleNotExistsException.class, ServiceExceptionCodeSuppliers.IAHN_NODE_LOCALE_NOT_EXISTS);
        map.put(IahnNodeMekNotExistsException.class, ServiceExceptionCodeSuppliers.IAHN_NODE_MEK_NOT_EXISTS);
        map.put(
                FileListNodeIndexOutOfBoundException.class,
                ServiceExceptionCodeSuppliers.FILE_LIST_NODE_INDEX_OUT_OF_BOUND
        );
        map.put(
                NavigationNodeItemIndexConflictException.class,
                ServiceExceptionCodeSuppliers.NAVIGATION_NODE_ITEM_INDEX_CONFLICT
        );
        map.put(
                NavigationNodeItemMismatchedException.class,
                ServiceExceptionCodeSuppliers.NAVIGATION_NODE_ITEM_MISMATCHED
        );
        map.put(
                NavigationNodeItemNotExistsException.class,
                ServiceExceptionCodeSuppliers.NAVIGATION_NODE_ITEM_NOT_EXISTS
        );
        return map;
    }

    private ServiceExceptionHelper() {
        throw new IllegalStateException("禁止外部实例化");
    }
}
