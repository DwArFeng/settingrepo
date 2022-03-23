package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.Formatter;
import com.dwarfeng.settingrepo.stack.handler.FormatterHandler;
import com.dwarfeng.settingrepo.stack.service.SettingCategoryMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Component
public class FormatLocalCacheHandlerImpl implements FormatLocalCacheHandler {

    @Autowired
    private FormatterFetcher formatterFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<StringIdKey, Formatter> formatterMap = new HashMap<>();
    private final Set<StringIdKey> notExistSettingCategories = new HashSet<>();

    @Override
    public Formatter getFormatter(StringIdKey settingCategoryKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (formatterMap.containsKey(settingCategoryKey)) {
                    return formatterMap.get(settingCategoryKey);
                }
                if (notExistSettingCategories.contains(settingCategoryKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (formatterMap.containsKey(settingCategoryKey)) {
                    return formatterMap.get(settingCategoryKey);
                }
                if (notExistSettingCategories.contains(settingCategoryKey)) {
                    return null;
                }
                Formatter formatter = formatterFetcher.fetchFormatter(settingCategoryKey);
                if (Objects.nonNull(formatter)) {
                    formatterMap.put(settingCategoryKey, formatter);
                    return formatter;
                }
                notExistSettingCategories.add(settingCategoryKey);
                return null;
            } finally {
                lock.writeLock().unlock();
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            formatterMap.clear();
            notExistSettingCategories.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Component
    public static class FormatterFetcher {

        @Autowired
        private SettingCategoryMaintainService settingCategoryMaintainService;

        @Autowired
        private FormatterHandler formatterHandler;

        @BehaviorAnalyse
        @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
        public Formatter fetchFormatter(StringIdKey settingCategoryKey) throws Exception {
            if (!settingCategoryMaintainService.exists(settingCategoryKey)) {
                return null;
            }
            SettingCategory settingCategory = settingCategoryMaintainService.get(settingCategoryKey);
            return formatterHandler.make(settingCategory);
        }
    }
}
