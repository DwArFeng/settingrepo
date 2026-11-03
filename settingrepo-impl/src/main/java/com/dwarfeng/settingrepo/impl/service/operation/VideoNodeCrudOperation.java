package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.ftp.stack.handler.FtpHandler;
import com.dwarfeng.settingrepo.impl.handler.FtpPathResolver;
import com.dwarfeng.settingrepo.stack.bean.entity.VideoNode;
import com.dwarfeng.settingrepo.stack.cache.VideoNodeCache;
import com.dwarfeng.settingrepo.stack.dao.VideoNodeDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 视频节点 CRUD 操作。
 *
 * @author fucl
 * @since 3.2.0
 */
@Component
public class VideoNodeCrudOperation implements BatchCrudOperation<StringIdKey, VideoNode> {

    private final VideoNodeDao videoNodeDao;
    private final VideoNodeCache videoNodeCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${com.dwarfeng.settingrepo.cache.timeout.entity.video_node}")
    private long videoNodeTimeout;

    public VideoNodeCrudOperation(
            VideoNodeDao videoNodeDao,
            VideoNodeCache videoNodeCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.videoNodeDao = videoNodeDao;
        this.videoNodeCache = videoNodeCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return videoNodeCache.exists(key) || videoNodeDao.exists(key);
    }

    @Override
    public VideoNode get(StringIdKey key) throws Exception {
        if (videoNodeCache.exists(key)) {
            return videoNodeCache.get(key);
        } else {
            if (!videoNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            VideoNode videoNode = videoNodeDao.get(key);
            videoNodeCache.push(videoNode, videoNodeTimeout);
            return videoNode;
        }
    }

    @Override
    public StringIdKey insert(VideoNode videoNode) throws Exception {
        videoNodeCache.push(videoNode, videoNodeTimeout);
        return videoNodeDao.insert(videoNode);
    }

    @Override
    public void update(VideoNode videoNode) throws Exception {
        videoNodeCache.push(videoNode, videoNodeTimeout);
        videoNodeDao.update(videoNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 获取文件名。
        String fileName = videoNodeDao.get(key).getStoreName();

        // 如果存在工件视频文件，则删除文件。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_FILE), fileName
        )) {
            ftpHandler.deleteFile(ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_FILE), fileName);
        }

        // 如果存在工件缩略图，则删除缩略图。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_THUMBNAIL), fileName
        )) {
            ftpHandler.deleteFile(ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_THUMBNAIL), fileName);
        }

        // 删除记录设置自身。
        videoNodeDao.delete(key);
        videoNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return videoNodeCache.allExists(keys) || videoNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return videoNodeCache.nonExists(keys) && videoNodeDao.nonExists(keys);
    }

    @Override
    public List<VideoNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (videoNodeCache.allExists(keys)) {
            return videoNodeCache.batchGet(keys);
        } else {
            if (!videoNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<VideoNode> videoNodes = videoNodeDao.batchGet(keys);
            videoNodeCache.batchPush(videoNodes, videoNodeTimeout);
            return videoNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<VideoNode> videoNodes) throws Exception {
        videoNodeCache.batchPush(videoNodes, videoNodeTimeout);
        return videoNodeDao.batchInsert(videoNodes);
    }

    @Override
    public void batchUpdate(List<VideoNode> videoNodes) throws Exception {
        videoNodeCache.batchPush(videoNodes, videoNodeTimeout);
        videoNodeDao.batchUpdate(videoNodes);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
