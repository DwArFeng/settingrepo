package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.settingrepo.impl.handler.FtpPathResolver;
import com.dwarfeng.settingrepo.stack.bean.entity.FileListNodeItem;
import com.dwarfeng.settingrepo.stack.cache.FileListNodeItemCache;
import com.dwarfeng.settingrepo.stack.dao.FileListNodeItemDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class FileListNodeItemCrudOperation implements BatchCrudOperation<LongIdKey, FileListNodeItem> {

    private final FileListNodeItemDao fileListNodeItemDao;
    private final FileListNodeItemCache fileListNodeItemCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.file_list_node_item}")
    private long fileListNodeItemTimeout;

    public FileListNodeItemCrudOperation(
            FileListNodeItemDao fileListNodeItemDao,
            FileListNodeItemCache fileListNodeItemCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.fileListNodeItemDao = fileListNodeItemDao;
        this.fileListNodeItemCache = fileListNodeItemCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return fileListNodeItemCache.exists(key) || fileListNodeItemDao.exists(key);
    }

    @Override
    public FileListNodeItem get(LongIdKey key) throws Exception {
        if (fileListNodeItemCache.exists(key)) {
            return fileListNodeItemCache.get(key);
        } else {
            if (!fileListNodeItemDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            FileListNodeItem fileListNodeItem = fileListNodeItemDao.get(key);
            fileListNodeItemCache.push(fileListNodeItem, fileListNodeItemTimeout);
            return fileListNodeItem;
        }
    }

    @Override
    public LongIdKey insert(FileListNodeItem fileListNodeItem) throws Exception {
        fileListNodeItemCache.push(fileListNodeItem, fileListNodeItemTimeout);
        return fileListNodeItemDao.insert(fileListNodeItem);
    }

    @Override
    public void update(FileListNodeItem fileListNodeItem) throws Exception {
        fileListNodeItemCache.push(fileListNodeItem, fileListNodeItemTimeout);
        fileListNodeItemDao.update(fileListNodeItem);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 获取文件名。
        String fileName = fileListNodeItemDao.get(key).getStoreName();

        // 如果存在文件，则删除。
        boolean fileExistsFlag = Objects.nonNull(fileName) &&
                ftpHandler.existsFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_FILE_LIST_NODE_FILE), fileName
                );
        if (fileExistsFlag) {
            ftpHandler.deleteFile(ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_FILE_LIST_NODE_FILE), fileName);
        }

        // 删除记录设置自身。
        fileListNodeItemDao.delete(key);
        fileListNodeItemCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return fileListNodeItemCache.allExists(keys) || fileListNodeItemDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return fileListNodeItemCache.nonExists(keys) && fileListNodeItemDao.nonExists(keys);
    }

    @Override
    public List<FileListNodeItem> batchGet(List<LongIdKey> keys) throws Exception {
        if (fileListNodeItemCache.allExists(keys)) {
            return fileListNodeItemCache.batchGet(keys);
        } else {
            if (!fileListNodeItemDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<FileListNodeItem> fileListNodeItems = fileListNodeItemDao.batchGet(keys);
            fileListNodeItemCache.batchPush(fileListNodeItems, fileListNodeItemTimeout);
            return fileListNodeItems;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<FileListNodeItem> fileListNodeItems) throws Exception {
        fileListNodeItemCache.batchPush(fileListNodeItems, fileListNodeItemTimeout);
        return fileListNodeItemDao.batchInsert(fileListNodeItems);
    }

    @Override
    public void batchUpdate(List<FileListNodeItem> fileListNodeItems) throws Exception {
        fileListNodeItemCache.batchPush(fileListNodeItems, fileListNodeItemTimeout);
        fileListNodeItemDao.batchUpdate(fileListNodeItems);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
