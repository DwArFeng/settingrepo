package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.settingrepo.stack.exception.WarmupDriverException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 预热驱动器。
 *
 * <p>
 * 驱动器只负责按照驱动信息注册、取消调度，并在触发时通过上下文执行预热动作。
 * 它与预热缓存的具体实现解耦，因此同一种触发策略可以驱动任意受支持的缓存类型。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriver {

    /**
     * 初始化驱动器。
     *
     * <p>
     * 该方法会在驱动器初始化后调用。实现应保存上下文，供后续调度回调使用。
     *
     * @param context 驱动器上下文。
     */
    void init(Context context);

    /**
     * 注册指定的驱动器信息。
     *
     * @param driverInfo 指定的驱动器信息。
     * @throws WarmupDriverException 驱动器异常。
     */
    void register(WarmupDriverInfo driverInfo) throws WarmupDriverException;

    /**
     * 解除注册所有驱动器信息。
     *
     * @throws WarmupDriverException 驱动器异常。
     */
    void unregisterAll() throws WarmupDriverException;

    /**
     * 驱动器上下文。
     *
     * <p>
     * 上下文承载驱动器与业务层之间的唯一交互入口。驱动器不直接访问 Redis、数据库或业务处理器。
     *
     * @author DwArFeng
     * @since 3.1.0
     */
    interface Context {

        /**
         * 预热指定主键对应的预热设置。
         *
         * @param warmupSettingKey 预热设置主键。
         * @throws Exception 方法执行过程中发生的任何异常。
         */
        void warmup(LongIdKey warmupSettingKey) throws Exception;
    }
}
