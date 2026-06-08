package com.dwarfeng.settingrepo.sdk.util;

import com.dwarfeng.settingrepo.stack.exception.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 异常的帮助工具类。
 *
 * @author DwArFeng
 * @since 3.0.0
 */
public final class ServiceExceptionHelper {

    /**
     * 向指定的映射中添加 settingrepo 默认的目标映射。
     *
     * <p>
     * 该方法可以在配置类中快速的搭建目标映射。
     *
     * @param map 指定的映射，允许为 <code>null</code>。
     * @return 添加了默认目标的映射。
     */
    public static Map<Class<? extends Exception>, ServiceException.Code> putDefaultDestination(
            Map<Class<? extends Exception>, ServiceException.Code> map
    ) {
        if (Objects.isNull(map)) {
            map = new HashMap<>();
        }

        map.put(FormatterException.class, ServiceExceptionCodes.FORMATTER_FAILED);
        map.put(FormatterExecutionException.class, ServiceExceptionCodes.FORMATTER_EXECUTION_FAILED);
        map.put(FormatterMakeException.class, ServiceExceptionCodes.FORMATTER_MAKE_FAILED);
        map.put(UnsupportedFormatterTypeException.class, ServiceExceptionCodes.UNSUPPORTED_FORMATTER_TYPE);
        map.put(SettingCategoryNotExistsException.class, ServiceExceptionCodes.SETTING_CATEGORY_NOT_EXISTS);
        map.put(SettingNodeExistsException.class, ServiceExceptionCodes.SETTING_NODE_EXISTS);
        map.put(SettingNodeNotExistsException.class, ServiceExceptionCodes.SETTING_NODE_NOT_EXISTS);
        map.put(ImageListNodeIndexOutOfBoundException.class, ServiceExceptionCodes.IMAGE_LIST_NODE_INDEX_OUT_OF_BOUND);
        map.put(InvalidIahnNodeLocaleException.class, ServiceExceptionCodes.INVALID_IAHN_NODE_LOCALE);
        map.put(InvalidIahnNodeMekIdException.class, ServiceExceptionCodes.INVALID_IAHN_NODE_MEK_ID);
        map.put(IahnNodeLocaleNotExistsException.class, ServiceExceptionCodes.IAHN_NODE_LOCALE_NOT_EXISTS);
        map.put(IahnNodeMekNotExistsException.class, ServiceExceptionCodes.IAHN_NODE_MEK_NOT_EXISTS);
        map.put(FileListNodeIndexOutOfBoundException.class, ServiceExceptionCodes.FILE_LIST_NODE_INDEX_OUT_OF_BOUND);
        map.put(NavigationNodeItemIndexConflictException.class, ServiceExceptionCodes.NAVIGATION_NODE_ITEM_INDEX_CONFLICT);
        map.put(NavigationNodeItemMismatchedException.class, ServiceExceptionCodes.NAVIGATION_NODE_ITEM_MISMATCHED);
        map.put(NavigationNodeItemNotExistsException.class, ServiceExceptionCodes.NAVIGATION_NODE_ITEM_NOT_EXISTS);
        return map;
    }

    private ServiceExceptionHelper() {
        throw new IllegalStateException("禁止外部实例化");
    }
}
