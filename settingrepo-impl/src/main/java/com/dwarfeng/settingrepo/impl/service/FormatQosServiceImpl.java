package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.Formatter;
import com.dwarfeng.settingrepo.stack.service.FormatQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class FormatQosServiceImpl implements FormatQosService {

    private final FormatLocalCacheHandler formatLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    private final Lock lock = new ReentrantLock();

    public FormatQosServiceImpl(FormatLocalCacheHandler formatLocalCacheHandler, ServiceExceptionMapper sem) {
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public Formatter getFormatter(StringIdKey settingCategoryKey) throws ServiceException {
        lock.lock();
        try {
            return formatLocalCacheHandler.getFormatter(settingCategoryKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("获取格式化器时发生异常", LogLevel.WARN, sem, e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        lock.lock();
        try {
            formatLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("清除本地缓存时发生异常", LogLevel.WARN, sem, e);
        } finally {
            lock.unlock();
        }
    }
}
