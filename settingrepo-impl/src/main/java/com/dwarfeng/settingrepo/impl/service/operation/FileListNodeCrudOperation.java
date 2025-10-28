package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.FileListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.FileListNodeItem;
import com.dwarfeng.settingrepo.stack.cache.FileListNodeCache;
import com.dwarfeng.settingrepo.stack.dao.FileListNodeDao;
import com.dwarfeng.settingrepo.stack.dao.FileListNodeItemDao;
import com.dwarfeng.settingrepo.stack.service.FileListNodeItemMaintainService;
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
public class FileListNodeCrudOperation implements BatchCrudOperation<StringIdKey, FileListNode> {

    private final FileListNodeDao fileListNodeDao;
    private final FileListNodeCache fileListNodeCache;

    private final FileListNodeItemDao fileListNodeItemDao;
    private final FileListNodeItemCrudOperation fileListNodeItemCrudOperation;

    @Value("${cache.timeout.entity.file_list_node}")
    private long fileListNodeTimeout;

    public FileListNodeCrudOperation(
            FileListNodeDao fileListNodeDao,
            FileListNodeCache fileListNodeCache,
            FileListNodeItemDao fileListNodeItemDao,
            FileListNodeItemCrudOperation fileListNodeItemCrudOperation
    ) {
        this.fileListNodeDao = fileListNodeDao;
        this.fileListNodeCache = fileListNodeCache;

        this.fileListNodeItemDao = fileListNodeItemDao;
        this.fileListNodeItemCrudOperation = fileListNodeItemCrudOperation;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return fileListNodeCache.exists(key) || fileListNodeDao.exists(key);
    }

    @Override
    public FileListNode get(StringIdKey key) throws Exception {
        if (fileListNodeCache.exists(key)) {
            return fileListNodeCache.get(key);
        } else {
            if (!fileListNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            FileListNode fileListNode = fileListNodeDao.get(key);
            fileListNodeCache.push(fileListNode, fileListNodeTimeout);
            return fileListNode;
        }
    }

    @Override
    public StringIdKey insert(FileListNode fileListNode) throws Exception {
        fileListNodeCache.push(fileListNode, fileListNodeTimeout);
        return fileListNodeDao.insert(fileListNode);
    }

    @Override
    public void update(FileListNode fileListNode) throws Exception {
        fileListNodeCache.push(fileListNode, fileListNodeTimeout);
        fileListNodeDao.update(fileListNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 查找并删除所有相关的文件列表节点条目。
        List<LongIdKey> fileListNodeItemKeys = fileListNodeItemDao.lookup(
                FileListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(FileListNodeItem::getKey).collect(Collectors.toList());
        fileListNodeItemCrudOperation.batchDelete(fileListNodeItemKeys);

        fileListNodeDao.delete(key);
        fileListNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return fileListNodeCache.allExists(keys) || fileListNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return fileListNodeCache.nonExists(keys) && fileListNodeDao.nonExists(keys);
    }

    @Override
    public List<FileListNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (fileListNodeCache.allExists(keys)) {
            return fileListNodeCache.batchGet(keys);
        } else {
            if (!fileListNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<FileListNode> fileListNodes = fileListNodeDao.batchGet(keys);
            fileListNodeCache.batchPush(fileListNodes, fileListNodeTimeout);
            return fileListNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<FileListNode> fileListNodes) throws Exception {
        List<StringIdKey> keys = new ArrayList<>();
        for (FileListNode fileListNode : fileListNodes) {
            keys.add(insert(fileListNode));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<FileListNode> fileListNodes) throws Exception {
        for (FileListNode fileListNode : fileListNodes) {
            update(fileListNode);
        }
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
