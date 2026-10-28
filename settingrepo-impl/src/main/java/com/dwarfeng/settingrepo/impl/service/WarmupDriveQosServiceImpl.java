package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.handler.WarmupDriveHandler;
import com.dwarfeng.settingrepo.stack.handler.WarmupLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.service.WarmupDriveQosService;
import com.dwarfeng.settingrepo.stack.struct.WarmupLocalCache;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

/**
 * 预热驱动 QOS 服务实现。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Service
public class WarmupDriveQosServiceImpl implements WarmupDriveQosService {

    private final WarmupDriveHandler warmupDriveHandler;
    private final WarmupLocalCacheHandler warmupLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public WarmupDriveQosServiceImpl(
            WarmupDriveHandler warmupDriveHandler,
            WarmupLocalCacheHandler warmupLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.warmupDriveHandler = warmupDriveHandler;
        this.warmupLocalCacheHandler = warmupLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public boolean isStarted() throws ServiceException {
        try {
            return warmupDriveHandler.isStarted();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断预热驱动处理器是否启动时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public boolean isWorking() throws ServiceException {
        try {
            return warmupDriveHandler.isStarted();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断预热驱动处理器是否工作时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public WarmupLocalCache getWarmupLocalCache(LongIdKey warmupSettingKey) throws ServiceException {
        try {
            return warmupLocalCacheHandler.get(warmupSettingKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取预热本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            warmupLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除预热本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
