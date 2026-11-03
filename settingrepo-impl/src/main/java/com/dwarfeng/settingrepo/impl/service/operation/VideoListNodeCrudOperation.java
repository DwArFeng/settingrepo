package com.dwarfeng.settingrepo.impl.service.operation;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNodeItem;
import com.dwarfeng.settingrepo.stack.cache.VideoListNodeCache;
import com.dwarfeng.settingrepo.stack.dao.VideoListNodeDao;
import com.dwarfeng.settingrepo.stack.dao.VideoListNodeItemDao;
import com.dwarfeng.settingrepo.stack.service.VideoListNodeItemMaintainService;
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

/**
 * 视频列表节点 CRUD 操作。
 *
 * @author fucl
 * @since 3.2.0
 */
@Component
public class VideoListNodeCrudOperation implements BatchCrudOperation<StringIdKey, VideoListNode> {

    private final VideoListNodeDao videoListNodeDao;
    private final VideoListNodeCache videoListNodeCache;

    private final VideoListNodeItemDao videoListNodeItemDao;
    private final VideoListNodeItemCrudOperation videoListNodeItemCrudOperation;

    @Value("${com.dwarfeng.settingrepo.cache.timeout.entity.video_list_node}")
    private long videoListNodeTimeout;

    public VideoListNodeCrudOperation(
            VideoListNodeDao videoListNodeDao,
            VideoListNodeCache videoListNodeCache,
            VideoListNodeItemDao videoListNodeItemDao,
            VideoListNodeItemCrudOperation videoListNodeItemCrudOperation
    ) {
        this.videoListNodeDao = videoListNodeDao;
        this.videoListNodeCache = videoListNodeCache;

        this.videoListNodeItemDao = videoListNodeItemDao;
        this.videoListNodeItemCrudOperation = videoListNodeItemCrudOperation;
    }

    @Override
    public boolean exists(StringIdKey key) throws Exception {
        return videoListNodeCache.exists(key) || videoListNodeDao.exists(key);
    }

    @Override
    public VideoListNode get(StringIdKey key) throws Exception {
        if (videoListNodeCache.exists(key)) {
            return videoListNodeCache.get(key);
        } else {
            if (!videoListNodeDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            VideoListNode videoListNode = videoListNodeDao.get(key);
            videoListNodeCache.push(videoListNode, videoListNodeTimeout);
            return videoListNode;
        }
    }

    @Override
    public StringIdKey insert(VideoListNode videoListNode) throws Exception {
        videoListNodeCache.push(videoListNode, videoListNodeTimeout);
        return videoListNodeDao.insert(videoListNode);
    }

    @Override
    public void update(VideoListNode videoListNode) throws Exception {
        videoListNodeCache.push(videoListNode, videoListNodeTimeout);
        videoListNodeDao.update(videoListNode);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 查找并删除所有相关的视频列表节点条目。
        List<LongIdKey> videoListNodeItemKeys = videoListNodeItemDao.lookup(
                VideoListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{key}
        ).stream().map(VideoListNodeItem::getKey).collect(Collectors.toList());
        videoListNodeItemCrudOperation.batchDelete(videoListNodeItemKeys);

        videoListNodeDao.delete(key);
        videoListNodeCache.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return videoListNodeCache.allExists(keys) || videoListNodeDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return videoListNodeCache.nonExists(keys) && videoListNodeDao.nonExists(keys);
    }

    @Override
    public List<VideoListNode> batchGet(List<StringIdKey> keys) throws Exception {
        if (videoListNodeCache.allExists(keys)) {
            return videoListNodeCache.batchGet(keys);
        } else {
            if (!videoListNodeDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<VideoListNode> videoListNodes = videoListNodeDao.batchGet(keys);
            videoListNodeCache.batchPush(videoListNodes, videoListNodeTimeout);
            return videoListNodes;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<VideoListNode> videoListNodes) throws Exception {
        List<StringIdKey> keys = new ArrayList<>();
        for (VideoListNode videoListNode : videoListNodes) {
            keys.add(insert(videoListNode));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<VideoListNode> videoListNodes) throws Exception {
        for (VideoListNode videoListNode : videoListNodes) {
            update(videoListNode);
        }
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
