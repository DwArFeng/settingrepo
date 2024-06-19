package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.settingrepo.impl.util.FtpConstants;
import com.dwarfeng.settingrepo.stack.bean.entity.ImageNode;
import com.dwarfeng.settingrepo.stack.cache.ImageNodeCache;
import com.dwarfeng.settingrepo.stack.dao.ImageNodeDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageNodeCrudOperation implements BatchCrudOperation<StringIdKey, ImageNode> {

    private final ImageNodeDao imageNodeDao;
    private final ImageNodeCache imageNodeCache;

    private final FtpHandler ftpHandler;

    @Value("${cache.timeout.entity.image_node}")
    private long imageNodeTimeout;

    public ImageNodeCrudOperation(
            ImageNodeDao imageNodeDao,
            ImageNodeCache imageNodeCache,
            FtpHandler ftpHandler
    ) {
        this.imageNodeDao = imageNodeDao;
        this.imageNodeCache = imageNodeCache;
        this.ftpHandler = ftpHandler;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return imageNodeCache.exists(key) || imageNodeDao.exists(key);
    }

    @Override
    public ImageNode get(StringIdKey key) throws Exception {
        if (imageNodeCache.exists(key)) {
            return imageNodeCache.get(key);
        } else {
            if (!imageNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            ImageNode imageNode = imageNodeDao.get(key);
            imageNodeCache.push(imageNode, imageNodeTimeout);
            return imageNode;
        }
    }

    @Override
    public StringIdKey insert(ImageNode imageNode) throws Exception {
        imageNodeCache.push(imageNode, imageNodeTimeout);
        return imageNodeDao.insert(imageNode);
    }

    @Override
    public void update(ImageNode imageNode) throws Exception {
        imageNodeCache.push(imageNode, imageNodeTimeout);
        imageNodeDao.update(imageNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 获取文件名。
        String fileName = imageNodeDao.get(key).getStoreName();

        // 如果存在工件图片文件，则删除文件。
        if (ftpHandler.existsFile(FtpConstants.PATH_IMAGE_NODE_FILE, fileName)) {
            ftpHandler.deleteFile(FtpConstants.PATH_IMAGE_NODE_FILE, fileName);
        }

        // 如果存在工件缩略图，则删除缩略图。
        if (ftpHandler.existsFile(FtpConstants.PATH_IMAGE_NODE_THUMBNAIL, fileName)) {
            ftpHandler.deleteFile(FtpConstants.PATH_IMAGE_NODE_THUMBNAIL, fileName);
        }

        // 删除记录设置自身。
        imageNodeDao.delete(key);
        imageNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return imageNodeCache.allExists(keys) || imageNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return imageNodeCache.nonExists(keys) && imageNodeDao.nonExists(keys);
    }

    @Override
    public List<ImageNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (imageNodeCache.allExists(keys)) {
            return imageNodeCache.batchGet(keys);
        } else {
            if (!imageNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<ImageNode> imageNodes = imageNodeDao.batchGet(keys);
            imageNodeCache.batchPush(imageNodes, imageNodeTimeout);
            return imageNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<ImageNode> imageNodes) throws Exception {
        imageNodeCache.batchPush(imageNodes, imageNodeTimeout);
        return imageNodeDao.batchInsert(imageNodes);
    }

    @Override
    public void batchUpdate(List<ImageNode> imageNodes) throws Exception {
        imageNodeCache.batchPush(imageNodes, imageNodeTimeout);
        imageNodeDao.batchUpdate(imageNodes);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
