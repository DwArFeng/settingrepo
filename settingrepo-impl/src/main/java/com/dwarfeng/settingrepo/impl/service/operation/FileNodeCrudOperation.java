package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.settingrepo.impl.handler.FtpPathResolver;
import com.dwarfeng.settingrepo.stack.bean.entity.FileNode;
import com.dwarfeng.settingrepo.stack.cache.FileNodeCache;
import com.dwarfeng.settingrepo.stack.dao.FileNodeDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileNodeCrudOperation implements BatchCrudOperation<StringIdKey, FileNode> {

    private final FileNodeDao fileNodeDao;
    private final FileNodeCache fileNodeCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.file_node}")
    private long fileNodeTimeout;

    public FileNodeCrudOperation(
            FileNodeDao fileNodeDao,
            FileNodeCache fileNodeCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.fileNodeDao = fileNodeDao;
        this.fileNodeCache = fileNodeCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return fileNodeCache.exists(key) || fileNodeDao.exists(key);
    }

    @Override
    public FileNode get(StringIdKey key) throws Exception {
        if (fileNodeCache.exists(key)) {
            return fileNodeCache.get(key);
        } else {
            if (!fileNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            FileNode fileNode = fileNodeDao.get(key);
            fileNodeCache.push(fileNode, fileNodeTimeout);
            return fileNode;
        }
    }

    @Override
    public StringIdKey insert(FileNode fileNode) throws Exception {
        fileNodeCache.push(fileNode, fileNodeTimeout);
        return fileNodeDao.insert(fileNode);
    }

    @Override
    public void update(FileNode fileNode) throws Exception {
        fileNodeCache.push(fileNode, fileNodeTimeout);
        fileNodeDao.update(fileNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {

        // 删除记录设置自身。
        fileNodeDao.delete(key);
        fileNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return fileNodeCache.allExists(keys) || fileNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return fileNodeCache.nonExists(keys) && fileNodeDao.nonExists(keys);
    }

    @Override
    public List<FileNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (fileNodeCache.allExists(keys)) {
            return fileNodeCache.batchGet(keys);
        } else {
            if (!fileNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<FileNode> fileNodes = fileNodeDao.batchGet(keys);
            fileNodeCache.batchPush(fileNodes, fileNodeTimeout);
            return fileNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<FileNode> fileNodes) throws Exception {
        fileNodeCache.batchPush(fileNodes, fileNodeTimeout);
        return fileNodeDao.batchInsert(fileNodes);
    }

    @Override
    public void batchUpdate(List<FileNode> fileNodes) throws Exception {
        fileNodeCache.batchPush(fileNodes, fileNodeTimeout);
        fileNodeDao.batchUpdate(fileNodes);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
