package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.settingrepo.stack.cache.WarmupDriverInfoCache;
import com.dwarfeng.settingrepo.stack.cache.WarmupSettingCache;
import com.dwarfeng.settingrepo.stack.dao.WarmupDriverInfoDao;
import com.dwarfeng.settingrepo.stack.dao.WarmupSettingDao;
import com.dwarfeng.settingrepo.stack.service.WarmupDriverInfoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 预热设置 CRUD 操作。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Component
public class WarmupSettingCrudOperation implements BatchCrudOperation<LongIdKey, WarmupSetting> {

    private final WarmupSettingDao warmupSettingDao;
    private final WarmupSettingCache warmupSettingCache;

    private final WarmupDriverInfoDao warmupDriverInfoDao;
    private final WarmupDriverInfoCache warmupDriverInfoCache;

    @Value("${com.dwarfeng.settingrepo.cache.timeout.entity.warmup_setting}")
    private long warmupSettingTimeout;

    public WarmupSettingCrudOperation(
            WarmupSettingDao warmupSettingDao,
            WarmupSettingCache warmupSettingCache,
            WarmupDriverInfoDao warmupDriverInfoDao,
            WarmupDriverInfoCache warmupDriverInfoCache
    ) {
        this.warmupSettingDao = warmupSettingDao;
        this.warmupSettingCache = warmupSettingCache;

        this.warmupDriverInfoDao = warmupDriverInfoDao;
        this.warmupDriverInfoCache = warmupDriverInfoCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return warmupSettingCache.exists(key) || warmupSettingDao.exists(key);
    }

    @Override
    public WarmupSetting get(LongIdKey key) throws Exception {
        if (warmupSettingCache.exists(key)) {
            return warmupSettingCache.get(key);
        } else {
            if (!warmupSettingDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            WarmupSetting warmupSetting = warmupSettingDao.get(key);
            warmupSettingCache.push(warmupSetting, warmupSettingTimeout);
            return warmupSetting;
        }
    }

    @Override
    public LongIdKey insert(WarmupSetting warmupSetting) throws Exception {
        warmupSettingCache.push(warmupSetting, warmupSettingTimeout);
        return warmupSettingDao.insert(warmupSetting);
    }

    @Override
    public void update(WarmupSetting warmupSetting) throws Exception {
        warmupSettingCache.push(warmupSetting, warmupSettingTimeout);
        warmupSettingDao.update(warmupSetting);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 查找并删除所有相关的预热驱动信息。
        List<WarmupDriverInfo> warmupDriverInfos = warmupDriverInfoDao.lookup(
                WarmupDriverInfoMaintainService.CHILD_FOR_WARMUP_SETTING, new Object[]{key}
        );
        List<LongIdKey> warmupDriverInfoKeys = warmupDriverInfos.stream()
                .map(WarmupDriverInfo::getKey).collect(Collectors.toList());
        warmupDriverInfoCache.batchDelete(warmupDriverInfoKeys);
        warmupDriverInfoDao.batchDelete(warmupDriverInfoKeys);

        // 删除 预热设置 自身。
        warmupSettingDao.delete(key);
        warmupSettingCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return warmupSettingCache.allExists(keys) || warmupSettingDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return warmupSettingCache.nonExists(keys) && warmupSettingDao.nonExists(keys);
    }

    @Override
    public List<WarmupSetting> batchGet(List<LongIdKey> keys) throws Exception {
        if (warmupSettingCache.allExists(keys)) {
            return warmupSettingCache.batchGet(keys);
        } else {
            if (!warmupSettingDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<WarmupSetting> warmupSettings = warmupSettingDao.batchGet(keys);
            warmupSettingCache.batchPush(warmupSettings, warmupSettingTimeout);
            return warmupSettings;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<WarmupSetting> warmupSettings) throws Exception {
        warmupSettingCache.batchPush(warmupSettings, warmupSettingTimeout);
        return warmupSettingDao.batchInsert(warmupSettings);
    }

    @Override
    public void batchUpdate(List<WarmupSetting> warmupSettings) throws Exception {
        warmupSettingCache.batchPush(warmupSettings, warmupSettingTimeout);
        warmupSettingDao.batchUpdate(warmupSettings);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
