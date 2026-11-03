package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.ftp.stack.handler.FtpHandler;
import com.dwarfeng.settingrepo.impl.handler.FtpPathResolver;
import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNodeItem;
import com.dwarfeng.settingrepo.stack.cache.VideoListNodeItemCache;
import com.dwarfeng.settingrepo.stack.dao.VideoListNodeItemDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 视频列表节点条目 CRUD 操作。
 *
 * @author fucl
 * @since 3.2.0
 */
@Component
public class VideoListNodeItemCrudOperation implements BatchCrudOperation<LongIdKey, VideoListNodeItem> {

    private final VideoListNodeItemDao videoListNodeItemDao;
    private final VideoListNodeItemCache videoListNodeItemCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${com.dwarfeng.settingrepo.cache.timeout.entity.video_list_node_item}")
    private long videoListNodeItemTimeout;

    public VideoListNodeItemCrudOperation(
            VideoListNodeItemDao videoListNodeItemDao,
            VideoListNodeItemCache videoListNodeItemCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.videoListNodeItemDao = videoListNodeItemDao;
        this.videoListNodeItemCache = videoListNodeItemCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return videoListNodeItemCache.exists(key) || videoListNodeItemDao.exists(key);
    }

    @Override
    public VideoListNodeItem get(LongIdKey key) throws Exception {
        if (videoListNodeItemCache.exists(key)) {
            return videoListNodeItemCache.get(key);
        } else {
            if (!videoListNodeItemDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            VideoListNodeItem videoListNodeItem = videoListNodeItemDao.get(key);
            videoListNodeItemCache.push(videoListNodeItem, videoListNodeItemTimeout);
            return videoListNodeItem;
        }
    }

    @Override
    public LongIdKey insert(VideoListNodeItem videoListNodeItem) throws Exception {
        videoListNodeItemCache.push(videoListNodeItem, videoListNodeItemTimeout);
        return videoListNodeItemDao.insert(videoListNodeItem);
    }

    @Override
    public void update(VideoListNodeItem videoListNodeItem) throws Exception {
        videoListNodeItemCache.push(videoListNodeItem, videoListNodeItemTimeout);
        videoListNodeItemDao.update(videoListNodeItem);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 获取文件名。
        String fileName = videoListNodeItemDao.get(key).getStoreName();

        // 如果存在工件视频文件，则删除文件。
        boolean fileExistsFlag = Objects.nonNull(fileName) &&
                ftpHandler.existsFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_FILE), fileName
                );
        if (fileExistsFlag) {
            ftpHandler.deleteFile(ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_FILE), fileName);
        }

        // 如果存在工件缩略图，则删除缩略图。
        boolean thumbnailExistsFlag = Objects.nonNull(fileName) &&
                ftpHandler.existsFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_THUMBNAIL), fileName
                );
        if (thumbnailExistsFlag) {
            ftpHandler.deleteFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_THUMBNAIL), fileName
            );
        }

        // 删除记录设置自身。
        videoListNodeItemDao.delete(key);
        videoListNodeItemCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return videoListNodeItemCache.allExists(keys) || videoListNodeItemDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return videoListNodeItemCache.nonExists(keys) && videoListNodeItemDao.nonExists(keys);
    }

    @Override
    public List<VideoListNodeItem> batchGet(List<LongIdKey> keys) throws Exception {
        if (videoListNodeItemCache.allExists(keys)) {
            return videoListNodeItemCache.batchGet(keys);
        } else {
            if (!videoListNodeItemDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<VideoListNodeItem> videoListNodeItems = videoListNodeItemDao.batchGet(keys);
            videoListNodeItemCache.batchPush(videoListNodeItems, videoListNodeItemTimeout);
            return videoListNodeItems;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<VideoListNodeItem> videoListNodeItems) throws Exception {
        videoListNodeItemCache.batchPush(videoListNodeItems, videoListNodeItemTimeout);
        return videoListNodeItemDao.batchInsert(videoListNodeItems);
    }

    @Override
    public void batchUpdate(List<VideoListNodeItem> videoListNodeItems) throws Exception {
        videoListNodeItemCache.batchPush(videoListNodeItems, videoListNodeItemTimeout);
        videoListNodeItemDao.batchUpdate(videoListNodeItems);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
