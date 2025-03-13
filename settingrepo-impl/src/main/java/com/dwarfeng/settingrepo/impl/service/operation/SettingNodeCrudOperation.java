package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.cache.SettingNodeCache;
import com.dwarfeng.settingrepo.stack.cache.TextNodeCache;
import com.dwarfeng.settingrepo.stack.dao.*;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SettingNodeCrudOperation implements BatchCrudOperation<StringIdKey, SettingNode> {

    private final SettingNodeDao settingNodeDao;
    private final SettingNodeCache settingNodeCache;

    private final TextNodeDao textNodeDao;
    private final TextNodeCache textNodeCache;

    private final ImageNodeDao imageNodeDao;
    private final ImageNodeCrudOperation imageNodeCrudOperation;

    private final ImageListNodeDao imageListNodeDao;
    private final ImageListNodeCrudOperation imageListNodeCrudOperation;

    private final IahnNodeDao iahnNodeDao;
    private final IahnNodeCrudOperation iahnNodeCrudOperation;

    @Value("${cache.timeout.entity.setting_node}")
    private long settingNodeTimeout;

    public SettingNodeCrudOperation(
            SettingNodeDao settingNodeDao,
            SettingNodeCache settingNodeCache,
            TextNodeDao textNodeDao,
            TextNodeCache textNodeCache,
            ImageNodeDao imageNodeDao,
            ImageNodeCrudOperation imageNodeCrudOperation,
            ImageListNodeDao imageListNodeDao,
            ImageListNodeCrudOperation imageListNodeCrudOperation,
            IahnNodeDao iahnNodeDao,
            IahnNodeCrudOperation iahnNodeCrudOperation
    ) {
        this.settingNodeDao = settingNodeDao;
        this.settingNodeCache = settingNodeCache;
        this.textNodeDao = textNodeDao;
        this.textNodeCache = textNodeCache;
        this.imageNodeDao = imageNodeDao;
        this.imageNodeCrudOperation = imageNodeCrudOperation;
        this.imageListNodeDao = imageListNodeDao;
        this.imageListNodeCrudOperation = imageListNodeCrudOperation;
        this.iahnNodeDao = iahnNodeDao;
        this.iahnNodeCrudOperation = iahnNodeCrudOperation;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return settingNodeCache.exists(key) || settingNodeDao.exists(key);
    }

    @Override
    public SettingNode get(StringIdKey key) throws Exception {
        if (settingNodeCache.exists(key)) {
            return settingNodeCache.get(key);
        } else {
            if (!settingNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            SettingNode settingNode = settingNodeDao.get(key);
            settingNodeCache.push(settingNode, settingNodeTimeout);
            return settingNode;
        }
    }

    @Override
    public StringIdKey insert(SettingNode settingNode) throws Exception {
        settingNodeCache.push(settingNode, settingNodeTimeout);
        return settingNodeDao.insert(settingNode);
    }

    @Override
    public void update(SettingNode settingNode) throws Exception {
        settingNodeCache.push(settingNode, settingNodeTimeout);
        settingNodeDao.update(settingNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 删除与该设置节点相关的文本节点。
        if (textNodeDao.exists(key)) {
            textNodeDao.delete(key);
            textNodeCache.delete(key);
        }

        // 删除与该设置节点相关的图片节点。
        if (imageNodeDao.exists(key)) {
            imageNodeCrudOperation.delete(key);
        }

        // 删除与该设置节点相关的图片列表节点。
        if (imageListNodeDao.exists(key)) {
            imageListNodeCrudOperation.delete(key);
        }

        // 删除与该设置节点相关的国际化节点。
        if (iahnNodeDao.exists(key)) {
            iahnNodeCrudOperation.delete(key);
        }

        settingNodeDao.delete(key);
        settingNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return settingNodeCache.allExists(keys) || settingNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return settingNodeCache.nonExists(keys) && settingNodeDao.nonExists(keys);
    }

    @Override
    public List<SettingNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (settingNodeCache.allExists(keys)) {
            return settingNodeCache.batchGet(keys);
        } else {
            if (!settingNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<SettingNode> settingNodes = settingNodeDao.batchGet(keys);
            settingNodeCache.batchPush(settingNodes, settingNodeTimeout);
            return settingNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<SettingNode> settingNodes) throws Exception {
        List<StringIdKey> keys = new ArrayList<>();
        for (SettingNode settingNode : settingNodes) {
            keys.add(insert(settingNode));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<SettingNode> settingNodes) throws Exception {
        for (SettingNode settingNode : settingNodes) {
            update(settingNode);
        }
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
