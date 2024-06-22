package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNodeItem;
import com.dwarfeng.settingrepo.stack.cache.ImageListNodeCache;
import com.dwarfeng.settingrepo.stack.dao.ImageListNodeDao;
import com.dwarfeng.settingrepo.stack.dao.ImageListNodeItemDao;
import com.dwarfeng.settingrepo.stack.service.ImageListNodeItemMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageListNodeCrudOperation implements BatchCrudOperation<StringIdKey, ImageListNode> {

    private final ImageListNodeDao imageListNodeDao;
    private final ImageListNodeCache imageListNodeCache;

    private final ImageListNodeItemDao imageListNodeItemDao;
    private final ImageListNodeItemCrudOperation imageListNodeItemCrudOperation;

    @Value("${cache.timeout.entity.image_list_node}")
    private long imageListNodeTimeout;

    public ImageListNodeCrudOperation(
            ImageListNodeDao imageListNodeDao,
            ImageListNodeCache imageListNodeCache,
            ImageListNodeItemDao imageListNodeItemDao,
            ImageListNodeItemCrudOperation imageListNodeItemCrudOperation
    ) {
        this.imageListNodeDao = imageListNodeDao;
        this.imageListNodeCache = imageListNodeCache;

        this.imageListNodeItemDao = imageListNodeItemDao;
        this.imageListNodeItemCrudOperation = imageListNodeItemCrudOperation;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return imageListNodeCache.exists(key) || imageListNodeDao.exists(key);
    }

    @Override
    public ImageListNode get(StringIdKey key) throws Exception {
        if (imageListNodeCache.exists(key)) {
            return imageListNodeCache.get(key);
        } else {
            if (!imageListNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            ImageListNode imageListNode = imageListNodeDao.get(key);
            imageListNodeCache.push(imageListNode, imageListNodeTimeout);
            return imageListNode;
        }
    }

    @Override
    public StringIdKey insert(ImageListNode imageListNode) throws Exception {
        imageListNodeCache.push(imageListNode, imageListNodeTimeout);
        return imageListNodeDao.insert(imageListNode);
    }

    @Override
    public void update(ImageListNode imageListNode) throws Exception {
        imageListNodeCache.push(imageListNode, imageListNodeTimeout);
        imageListNodeDao.update(imageListNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 查找并删除所有相关的图片列表节点条目。
        List<LongIdKey> imageListNodeItemKeys = imageListNodeItemDao.lookup(
                ImageListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(ImageListNodeItem::getKey).collect(Collectors.toList());
        imageListNodeItemCrudOperation.batchDelete(imageListNodeItemKeys);

        imageListNodeDao.delete(key);
        imageListNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return imageListNodeCache.allExists(keys) || imageListNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return imageListNodeCache.nonExists(keys) && imageListNodeDao.nonExists(keys);
    }

    @Override
    public List<ImageListNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (imageListNodeCache.allExists(keys)) {
            return imageListNodeCache.batchGet(keys);
        } else {
            if (!imageListNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<ImageListNode> imageListNodes = imageListNodeDao.batchGet(keys);
            imageListNodeCache.batchPush(imageListNodes, imageListNodeTimeout);
            return imageListNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<ImageListNode> imageListNodes) throws Exception {
        List<StringIdKey> keys = new ArrayList<>();
        for (ImageListNode imageListNode : imageListNodes) {
            keys.add(insert(imageListNode));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<ImageListNode> imageListNodes) throws Exception {
        for (ImageListNode imageListNode : imageListNodes) {
            update(imageListNode);
        }
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
