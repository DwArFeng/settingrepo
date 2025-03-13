package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeLocale;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeLocaleCache;
import com.dwarfeng.settingrepo.stack.cache.IahnNodeMessageCache;
import com.dwarfeng.settingrepo.stack.dao.IahnNodeLocaleDao;
import com.dwarfeng.settingrepo.stack.dao.IahnNodeMessageDao;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMessageMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IahnNodeLocaleCrudOperation implements BatchCrudOperation<IahnNodeLocaleKey, IahnNodeLocale> {

    private final IahnNodeLocaleDao iahnNodeLocaleDao;
    private final IahnNodeLocaleCache iahnNodeLocaleCache;

    private final IahnNodeMessageDao iahnNodeMessageDao;
    private final IahnNodeMessageCache iahnNodeMessageCache;

    @Value("${cache.timeout.entity.iahn_node_locale}")
    private long iahnNodeLocaleTimeout;

    public IahnNodeLocaleCrudOperation(
            IahnNodeLocaleDao iahnNodeLocaleDao,
            IahnNodeLocaleCache iahnNodeLocaleCache,
            IahnNodeMessageDao iahnNodeMessageDao,
            IahnNodeMessageCache iahnNodeMessageCache
    ) {
        this.iahnNodeLocaleDao = iahnNodeLocaleDao;
        this.iahnNodeLocaleCache = iahnNodeLocaleCache;
        this.iahnNodeMessageDao = iahnNodeMessageDao;
        this.iahnNodeMessageCache = iahnNodeMessageCache;
    }

    @Override
    public boolean exists(IahnNodeLocaleKey key) throws Exception {
        return iahnNodeLocaleCache.exists(key) || iahnNodeLocaleDao.exists(key);
    }

    @Override
    public IahnNodeLocale get(IahnNodeLocaleKey key) throws Exception {
        if (iahnNodeLocaleCache.exists(key)) {
            return iahnNodeLocaleCache.get(key);
        } else {
            if (!iahnNodeLocaleDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            IahnNodeLocale iahnNodeLocale = iahnNodeLocaleDao.get(key);
            iahnNodeLocaleCache.push(iahnNodeLocale, iahnNodeLocaleTimeout);
            return iahnNodeLocale;
        }
    }

    @Override
    public IahnNodeLocaleKey insert(IahnNodeLocale iahnNodeLocale) throws Exception {
        iahnNodeLocaleCache.push(iahnNodeLocale, iahnNodeLocaleTimeout);
        return iahnNodeLocaleDao.insert(iahnNodeLocale);
    }

    @Override
    public void update(IahnNodeLocale iahnNodeLocale) throws Exception {
        iahnNodeLocaleCache.push(iahnNodeLocale, iahnNodeLocaleTimeout);
        iahnNodeLocaleDao.update(iahnNodeLocale);
    }

    @Override
    public void delete(IahnNodeLocaleKey key) throws Exception {
        // 查找并删除所有相关的国际化节点消息。
        List<IahnNodeMessageKey> iahnNodeMessageKeys = iahnNodeMessageDao.lookup(
                IahnNodeMessageMaintainService.CHILD_FOR_LOCALE, new Object[]{key}
        ).stream().map(IahnNodeMessage::getKey).collect(Collectors.toList());
        iahnNodeMessageDao.batchDelete(iahnNodeMessageKeys);
        iahnNodeMessageCache.batchDelete(iahnNodeMessageKeys);

        // 删除记录设置自身。
        iahnNodeLocaleDao.delete(key);
        iahnNodeLocaleCache.delete(key);
    }

    @Override
    public boolean allExists(List<IahnNodeLocaleKey> keys) throws Exception {
        return iahnNodeLocaleCache.allExists(keys) || iahnNodeLocaleDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<IahnNodeLocaleKey> keys) throws Exception {
        return iahnNodeLocaleCache.nonExists(keys) && iahnNodeLocaleDao.nonExists(keys);
    }

    @Override
    public List<IahnNodeLocale> batchGet(List<IahnNodeLocaleKey> keys) throws Exception {
        if (iahnNodeLocaleCache.allExists(keys)) {
            return iahnNodeLocaleCache.batchGet(keys);
        } else {
            if (!iahnNodeLocaleDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<IahnNodeLocale> iahnNodeLocales = iahnNodeLocaleDao.batchGet(keys);
            iahnNodeLocaleCache.batchPush(iahnNodeLocales, iahnNodeLocaleTimeout);
            return iahnNodeLocales;
        }
    }

    @Override
    public List<IahnNodeLocaleKey> batchInsert(List<IahnNodeLocale> iahnNodeLocales) throws Exception {
        iahnNodeLocaleCache.batchPush(iahnNodeLocales, iahnNodeLocaleTimeout);
        return iahnNodeLocaleDao.batchInsert(iahnNodeLocales);
    }

    @Override
    public void batchUpdate(List<IahnNodeLocale> iahnNodeLocales) throws Exception {
        iahnNodeLocaleCache.batchPush(iahnNodeLocales, iahnNodeLocaleTimeout);
        iahnNodeLocaleDao.batchUpdate(iahnNodeLocales);
    }

    @Override
    public void batchDelete(List<IahnNodeLocaleKey> keys) throws Exception {
        for (IahnNodeLocaleKey key : keys) {
            delete(key);
        }
    }
}
