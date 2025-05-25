package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.settingrepo.impl.handler.FtpPathResolver;
import com.dwarfeng.settingrepo.stack.bean.entity.LongTextNode;
import com.dwarfeng.settingrepo.stack.cache.LongTextNodeCache;
import com.dwarfeng.settingrepo.stack.dao.LongTextNodeDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LongTextNodeCrudOperation implements BatchCrudOperation<StringIdKey, LongTextNode> {

    private final LongTextNodeDao longTextNodeDao;
    private final LongTextNodeCache longTextNodeCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.long_text_node}")
    private long longTextNodeTimeout;

    public LongTextNodeCrudOperation(
            LongTextNodeDao longTextNodeDao,
            LongTextNodeCache longTextNodeCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.longTextNodeDao = longTextNodeDao;
        this.longTextNodeCache = longTextNodeCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return longTextNodeCache.exists(key) || longTextNodeDao.exists(key);
    }

    @Override
    public LongTextNode get(StringIdKey key) throws Exception {
        if (longTextNodeCache.exists(key)) {
            return longTextNodeCache.get(key);
        } else {
            if (!longTextNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            LongTextNode longTextNode = longTextNodeDao.get(key);
            longTextNodeCache.push(longTextNode, longTextNodeTimeout);
            return longTextNode;
        }
    }

    @Override
    public StringIdKey insert(LongTextNode longTextNode) throws Exception {
        longTextNodeCache.push(longTextNode, longTextNodeTimeout);
        return longTextNodeDao.insert(longTextNode);
    }

    @Override
    public void update(LongTextNode longTextNode) throws Exception {
        longTextNodeCache.push(longTextNode, longTextNodeTimeout);
        longTextNodeDao.update(longTextNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 获取文件名。
        String fileName = longTextNodeDao.get(key).getStoreName();

        // 如果存在工件长文本文件，则删除文件。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_LONG_TEXT_NODE_FILE), fileName
        )) {
            ftpHandler.deleteFile(ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_LONG_TEXT_NODE_FILE), fileName);
        }

        // 删除记录设置自身。
        longTextNodeDao.delete(key);
        longTextNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return longTextNodeCache.allExists(keys) || longTextNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return longTextNodeCache.nonExists(keys) && longTextNodeDao.nonExists(keys);
    }

    @Override
    public List<LongTextNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (longTextNodeCache.allExists(keys)) {
            return longTextNodeCache.batchGet(keys);
        } else {
            if (!longTextNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<LongTextNode> longTextNodes = longTextNodeDao.batchGet(keys);
            longTextNodeCache.batchPush(longTextNodes, longTextNodeTimeout);
            return longTextNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<LongTextNode> longTextNodes) throws Exception {
        longTextNodeCache.batchPush(longTextNodes, longTextNodeTimeout);
        return longTextNodeDao.batchInsert(longTextNodes);
    }

    @Override
    public void batchUpdate(List<LongTextNode> longTextNodes) throws Exception {
        longTextNodeCache.batchPush(longTextNodes, longTextNodeTimeout);
        longTextNodeDao.batchUpdate(longTextNodes);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
