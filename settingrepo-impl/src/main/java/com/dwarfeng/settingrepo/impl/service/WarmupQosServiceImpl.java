package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.handler.WarmupHandler;
import com.dwarfeng.settingrepo.stack.service.WarmupQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

/**
 * 预热 QOS 服务实现。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Service
public class WarmupQosServiceImpl implements WarmupQosService {

    private final WarmupHandler warmupHandler;

    private final ServiceExceptionMapper sem;

    public WarmupQosServiceImpl(WarmupHandler warmupHandler, ServiceExceptionMapper sem) {
        this.warmupHandler = warmupHandler;
        this.sem = sem;
    }

    @Override
    public boolean isOnline() throws ServiceException {
        try {
            return warmupHandler.isOnline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断预热处理器是否上线时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void online() throws ServiceException {
        try {
            warmupHandler.online();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("上线预热处理器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void offline() throws ServiceException {
        try {
            warmupHandler.offline();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下线预热处理器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isLockHolding() throws ServiceException {
        try {
            return warmupHandler.isLockHolding();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断预热处理器是否持有锁时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isStarted() throws ServiceException {
        try {
            return warmupHandler.isStarted();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断预热处理器是否启动时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void start() throws ServiceException {
        try {
            warmupHandler.start();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("启动预热处理器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void stop() throws ServiceException {
        try {
            warmupHandler.stop();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("停止预热处理器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isWorking() throws ServiceException {
        try {
            return warmupHandler.isWorking();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断预热处理器是否工作时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
