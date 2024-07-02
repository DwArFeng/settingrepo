package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.cache.SettingCategoryCache;
import com.dwarfeng.settingrepo.stack.cache.SettingNodeCache;
import com.dwarfeng.settingrepo.stack.dao.SettingCategoryDao;
import com.dwarfeng.settingrepo.stack.dao.SettingNodeDao;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SettingCategoryCrudOperation implements BatchCrudOperation<StringIdKey, SettingCategory> {

    private final SettingCategoryDao settingCategoryDao;
    private final SettingCategoryCache settingCategoryCache;

    private final SettingNodeDao settingNodeDao;
    private final SettingNodeCache settingNodeCache;

    @Value("${cache.timeout.entity.setting_category}")
    private long settingCategoryTimeout;

    public SettingCategoryCrudOperation(
            SettingCategoryDao settingCategoryDao,
            SettingCategoryCache settingCategoryCache,
            SettingNodeDao settingNodeDao,
            SettingNodeCache settingNodeCache
    ) {
        this.settingCategoryDao = settingCategoryDao;
        this.settingCategoryCache = settingCategoryCache;
        this.settingNodeDao = settingNodeDao;
        this.settingNodeCache = settingNodeCache;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return settingCategoryCache.exists(key) || settingCategoryDao.exists(key);
    }

    @Override
    public SettingCategory get(StringIdKey key) throws Exception {
        if (settingCategoryCache.exists(key)) {
            return settingCategoryCache.get(key);
        } else {
            if (!settingCategoryDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            SettingCategory settingCategory = settingCategoryDao.get(key);
            settingCategoryCache.push(settingCategory, settingCategoryTimeout);
            return settingCategory;
        }
    }

    @Override
    public StringIdKey insert(SettingCategory settingCategory) throws Exception {
        settingCategoryCache.push(settingCategory, settingCategoryTimeout);
        return settingCategoryDao.insert(settingCategory);
    }

    @Override
    public void update(SettingCategory settingCategory) throws Exception {
        settingCategoryCache.push(settingCategory, settingCategoryTimeout);
        settingCategoryDao.update(settingCategory);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 将设置类别下所属的所有设置节点元数据设置为不可访问。
        List<SettingNode> settingNodes = settingNodeDao.lookup(
                SettingNodeMaintainService.CATEGORY_EQUALS, new Object[]{key.getStringId()}
        );
        settingNodes.forEach(e -> e.setReachable(false));
        settingNodeCache.batchDelete(
                settingNodes.stream().map(SettingNode::getKey).collect(Collectors.toList())
        );
        settingNodeDao.batchUpdate(settingNodes);

        // 删除设置类别自身。
        settingCategoryDao.delete(key);
        settingCategoryCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return settingCategoryCache.allExists(keys) || settingCategoryDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return settingCategoryCache.nonExists(keys) && settingCategoryDao.nonExists(keys);
    }

    @Override
    public List<SettingCategory> batchGet(List<StringIdKey> keys) throws Exception {
        if (settingCategoryCache.allExists(keys)) {
            return settingCategoryCache.batchGet(keys);
        } else {
            if (!settingCategoryDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<SettingCategory> settingCategories = settingCategoryDao.batchGet(keys);
            settingCategoryCache.batchPush(settingCategories, settingCategoryTimeout);
            return settingCategories;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<SettingCategory> settingCategories) throws Exception {
        List<StringIdKey> keys = new ArrayList<>();
        for (SettingCategory settingCategory : settingCategories) {
            keys.add(insert(settingCategory));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<SettingCategory> settingCategories) throws Exception {
        for (SettingCategory settingCategory : settingCategories) {
            update(settingCategory);
        }
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
