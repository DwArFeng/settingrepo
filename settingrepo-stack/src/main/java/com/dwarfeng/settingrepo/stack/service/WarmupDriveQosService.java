package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.settingrepo.stack.struct.WarmupLocalCache;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 预热驱动 QOS 服务。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupDriveQosService extends Service {

    /**
     * 驱动处理器是否启动。
     *
     * @return 驱动处理器是否启动。
     * @throws ServiceException 服务异常。
     */
    boolean isStarted() throws ServiceException;

    /**
     * 驱动处理器是否工作。
     *
     * <p>
     * 该状态用于区分驱动处理器已启动与驱动已经完成注册并真正运行两种情况。
     *
     * @return 驱动处理器是否工作。
     * @throws ServiceException 服务异常。
     * @since 3.1.0
     */
    boolean isWorking() throws ServiceException;

    /**
     * 获取指定预热设置的本地缓存。
     *
     * @param warmupSettingKey 预热设置主键。
     * @return 对应的本地缓存。
     * @throws ServiceException 服务异常。
     */
    WarmupLocalCache getWarmupLocalCache(LongIdKey warmupSettingKey) throws ServiceException;

    /**
     * 清除预热本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
