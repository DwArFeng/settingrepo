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
 * <p>
 * 预热缓存类型采用独立于设置节点类型的枚举空间，并从 0 开始按节点类别划分十位段：
 * <ul>
 *     <li>KvNode：0-9</li>
 *     <li>NavigationNode：10-19</li>
 *     <li>IahnNode：20-29</li>
 * </ul>
 * 段内余量用于同类节点后续新增缓存，不得改为全局连续追加。
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

    /**
     * @since 2.4.2
     */
    @SettingNodeTypeItem
    public static final int SETTING_NODE_TYPE_FILE = 5;

    /**
     * @since 2.4.2
     */
    @SettingNodeTypeItem
    public static final int SETTING_NODE_TYPE_FILE_LIST = 6;

    /**
     * @since 2.4.2
     */
    @SettingNodeTypeItem
    public static final int SETTING_NODE_TYPE_NAVIGATION = 7;

    /**
     * @since 2.5.0
     */
    @SettingNodeTypeItem
    public static final int SETTING_NODE_TYPE_KV = 8;

    /**
     * 键值对节点条目查看结果缓存。
     *
     * @since 3.1.0
     */
    @WarmupCacheTypeItem
    public static final int WARMUP_CACHE_TYPE_KV_NODE_INSPECT_RESULT = 0;

    /**
     * 导航节点查看结果缓存。
     *
     * @since 3.1.0
     */
    @WarmupCacheTypeItem
    public static final int WARMUP_CACHE_TYPE_NAVIGATION_NODE_INSPECT_RESULT = 10;

    /**
     * 国际化节点地区映射缓存。
     *
     * @since 3.1.0
     */
    @WarmupCacheTypeItem
    public static final int WARMUP_CACHE_TYPE_IAHN_NODE_LOCALE_MAP = 20;

    /**
     * 国际化节点消息映射缓存。
     *
     * @since 3.1.0
     */
    @WarmupCacheTypeItem
    public static final int WARMUP_CACHE_TYPE_IAHN_NODE_MESSAGE_MAP = 21;

    /**
     * 国际化节点地区列表缓存。
     *
     * @since 3.1.0
     */
    @WarmupCacheTypeItem
    public static final int WARMUP_CACHE_TYPE_IAHN_NODE_LOCALE_LIST = 22;

    /**
     * 国际化节点 Mek 列表缓存。
     *
     * @since 3.1.0
     */
    @WarmupCacheTypeItem
    public static final int WARMUP_CACHE_TYPE_IAHN_NODE_MEK_LIST = 23;

    /**
     * 国际化节点消息表缓存。
     *
     * @since 3.1.0
     */
    @WarmupCacheTypeItem
    public static final int WARMUP_CACHE_TYPE_IAHN_NODE_MESSAGE_TABLE = 24;

    public static final int LONG_TEXT_NODE_PREVIEW_LENGTH = 1000;

    private static final Lock LOCK = new ReentrantLock();

    private static List<Integer> settingNodeTypeSpace = null;
    private static List<Integer> warmupCacheTypeSpace = null;

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

    /**
     * 预热缓存类型空间。
     *
     * @return 预热缓存类型空间。
     * @since 3.1.0
     */
    public static List<Integer> warmupCacheTypeSpace() {
        if (Objects.nonNull(warmupCacheTypeSpace)) {
            return warmupCacheTypeSpace;
        }
        // 基于线程安全的懒加载初始化结果列表。
        LOCK.lock();
        try {
            if (Objects.nonNull(warmupCacheTypeSpace)) {
                return warmupCacheTypeSpace;
            }
            initWarmupCacheTypeSpace();
            return warmupCacheTypeSpace;
        } finally {
            LOCK.unlock();
        }
    }

    private static void initWarmupCacheTypeSpace() {
        List<Integer> result = new ArrayList<>();

        Field[] declaredFields = Constants.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (!declaredField.isAnnotationPresent(WarmupCacheTypeItem.class)) {
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

        warmupCacheTypeSpace = Collections.unmodifiableList(result);
    }

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
