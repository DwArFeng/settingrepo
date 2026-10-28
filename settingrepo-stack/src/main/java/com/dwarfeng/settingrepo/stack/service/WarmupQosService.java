package com.dwarfeng.settingrepo.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 预热 QOS 服务。
 *
 * <p>
 * 该服务对应集群唯一的预热门面，负责上线、下线、持锁和启停状态。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupQosService extends Service {

    /**
     * 预热处理器是否上线。
     *
     * @return 预热处理器是否上线。
     * @throws ServiceException 服务异常。
     */
    boolean isOnline() throws ServiceException;

    /**
     * 上线预热处理器。
     *
     * @throws ServiceException 服务异常。
     */
    void online() throws ServiceException;

    /**
     * 下线预热处理器。
     *
     * @throws ServiceException 服务异常。
     */
    void offline() throws ServiceException;

    /**
     * 预热处理器是否持锁。
     *
     * @return 预热处理器是否持锁。
     * @throws ServiceException 服务异常。
     */
    boolean isLockHolding() throws ServiceException;

    /**
     * 预热处理器是否启动。
     *
     * @return 预热处理器是否启动。
     * @throws ServiceException 服务异常。
     */
    boolean isStarted() throws ServiceException;

    /**
     * 启动预热处理器。
     *
     * @throws ServiceException 服务异常。
     */
    void start() throws ServiceException;

    /**
     * 停止预热处理器。
     *
     * @throws ServiceException 服务异常。
     */
    void stop() throws ServiceException;

    /**
     * 预热处理器是否工作。
     *
     * @return 预热处理器是否工作。
     * @throws ServiceException 服务异常。
     */
    boolean isWorking() throws ServiceException;
}
