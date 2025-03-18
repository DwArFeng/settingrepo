package com.dwarfeng.settingrepo.sdk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public final class Constants {

    private static final Logger LOGGER = LoggerFactory.getLogger(Constants.class);

    @SettingNodeTypeItem
    public static final int SETTING_NODE_TYPE_TEXT = 0;

    @SettingNodeTypeItem
    public static final int SETTING_NODE_TYPE_LONG_TEXT = 1;

    @SettingNodeTypeItem
    public static final int SETTING_NODE_TYPE_IMAGE = 2;

    @SettingNodeTypeItem
    public static final int SETTING_NODE_TYPE_IMAGE_LIST = 3;

    @SettingNodeTypeItem
    public static final int SETTING_NODE_TYPE_IAHN = 4;

    private static final Lock LOCK = new ReentrantLock();

    private static List<Integer> settingNodeTypeSpace = null;

    /**
     * 设置节点类型空间。
     *
     * @return 设置节点类型空间。
     */
    public static List<Integer> settingNodeTypeSpace() {
        if (Objects.nonNull(settingNodeTypeSpace)) {
            return settingNodeTypeSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(settingNodeTypeSpace)) {
                return settingNodeTypeSpace;
            }
            initSettingNodeTypeSpace();
            return settingNodeTypeSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initSettingNodeTypeSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(SettingNodeTypeItem.class)) {
                continue;
            }
            Integer value;
            try {
                value = (Integer) declaredField.get(null);
                result.add(value);
            } catch (Exception e) {
                LOGGER.error("初始化异常, 请检查代码, 信息如下: ", e);
            }
        }

        settingNodeTypeSpace = Collections.unmodifiableList(result);
    }

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
