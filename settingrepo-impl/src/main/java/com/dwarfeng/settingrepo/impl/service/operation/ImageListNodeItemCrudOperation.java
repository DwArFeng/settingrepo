package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.settingrepo.impl.handler.FtpPathResolver;
import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNodeItem;
import com.dwarfeng.settingrepo.stack.cache.ImageListNodeItemCache;
import com.dwarfeng.settingrepo.stack.dao.ImageListNodeItemDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class ImageListNodeItemCrudOperation implements BatchCrudOperation<LongIdKey, ImageListNodeItem> {

    private final ImageListNodeItemDao imageListNodeItemDao;
    private final ImageListNodeItemCache imageListNodeItemCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.image_list_node_item}")
    private long imageListNodeItemTimeout;

    public ImageListNodeItemCrudOperation(
            ImageListNodeItemDao imageListNodeItemDao,
            ImageListNodeItemCache imageListNodeItemCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.imageListNodeItemDao = imageListNodeItemDao;
        this.imageListNodeItemCache = imageListNodeItemCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return imageListNodeItemCache.exists(key) || imageListNodeItemDao.exists(key);
    }

    @Override
    public ImageListNodeItem get(LongIdKey key) throws Exception {
        if (imageListNodeItemCache.exists(key)) {
            return imageListNodeItemCache.get(key);
        } else {
            if (!imageListNodeItemDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            ImageListNodeItem imageListNodeItem = imageListNodeItemDao.get(key);
            imageListNodeItemCache.push(imageListNodeItem, imageListNodeItemTimeout);
            return imageListNodeItem;
        }
    }

    @Override
    public LongIdKey insert(ImageListNodeItem imageListNodeItem) throws Exception {
        imageListNodeItemCache.push(imageListNodeItem, imageListNodeItemTimeout);
        return imageListNodeItemDao.insert(imageListNodeItem);
    }

    @Override
    public void update(ImageListNodeItem imageListNodeItem) throws Exception {
        imageListNodeItemCache.push(imageListNodeItem, imageListNodeItemTimeout);
        imageListNodeItemDao.update(imageListNodeItem);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 获取文件名。
        String fileName = imageListNodeItemDao.get(key).getStoreName();

        // 如果存在工件图片文件，则删除文件。
        boolean fileExistsFlag = Objects.nonNull(fileName) &&
                ftpHandler.existsFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE), fileName
                );
        if (fileExistsFlag) {
            ftpHandler.deleteFile(ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE), fileName);
        }

        // 如果存在工件缩略图，则删除缩略图。
        boolean thumbnailExistsFlag = Objects.nonNull(fileName) &&
                ftpHandler.existsFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_THUMBNAIL), fileName
                );
        if (thumbnailExistsFlag) {
            ftpHandler.deleteFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_THUMBNAIL), fileName
            );
        }

        // 删除记录设置自身。
        imageListNodeItemDao.delete(key);
        imageListNodeItemCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return imageListNodeItemCache.allExists(keys) || imageListNodeItemDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return imageListNodeItemCache.nonExists(keys) && imageListNodeItemDao.nonExists(keys);
    }

    @Override
    public List<ImageListNodeItem> batchGet(List<LongIdKey> keys) throws Exception {
        if (imageListNodeItemCache.allExists(keys)) {
            return imageListNodeItemCache.batchGet(keys);
        } else {
            if (!imageListNodeItemDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<ImageListNodeItem> imageListNodeItems = imageListNodeItemDao.batchGet(keys);
            imageListNodeItemCache.batchPush(imageListNodeItems, imageListNodeItemTimeout);
            return imageListNodeItems;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<ImageListNodeItem> imageListNodeItems) throws Exception {
        imageListNodeItemCache.batchPush(imageListNodeItems, imageListNodeItemTimeout);
        return imageListNodeItemDao.batchInsert(imageListNodeItems);
    }

    @Override
    public void batchUpdate(List<ImageListNodeItem> imageListNodeItems) throws Exception {
        imageListNodeItemCache.batchPush(imageListNodeItems, imageListNodeItemTimeout);
        imageListNodeItemDao.batchUpdate(imageListNodeItems);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
