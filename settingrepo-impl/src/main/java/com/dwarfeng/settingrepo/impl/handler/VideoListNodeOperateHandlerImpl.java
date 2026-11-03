package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.stack.handler.FtpHandler;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNodeItem;
import com.dwarfeng.settingrepo.stack.exception.VideoListNodeIndexOutOfBoundException;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.VideoListNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.VideoListNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.VideoListNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Component
public class VideoListNodeOperateHandlerImpl implements VideoListNodeOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoListNodeOperateHandlerImpl.class);

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final VideoListNodeMaintainService videoListNodeMaintainService;
    private final VideoListNodeItemMaintainService videoListNodeItemMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;
    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    private final HandlerValidator handlerValidator;

    @Value("${com.dwarfeng.settingrepo.video_thumbnail.width}")
    private int thumbnailWidth;
    @Value("${com.dwarfeng.settingrepo.video_thumbnail.height}")
    private int thumbnailHeight;
    @Value("${com.dwarfeng.settingrepo.video_thumbnail.quality}")
    private double thumbnailQuality;
    @Value("${com.dwarfeng.settingrepo.video_thumbnail.output_format}")
    private String thumbnailOutputFormat;

    public VideoListNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            VideoListNodeMaintainService videoListNodeMaintainService,
            VideoListNodeItemMaintainService videoListNodeItemMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.videoListNodeMaintainService = videoListNodeMaintainService;
        this.videoListNodeItemMaintainService = videoListNodeItemMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public VideoListNodeSizeResult size(VideoListNodeSizeInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 获取视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(videoListNode)) {
                videoListNode = new VideoListNode(settingNodeKey, 0);
                videoListNodeMaintainService.insertOrUpdate(videoListNode);
            }

            // 构造 VideoListNodeSizeResult 并返回。
            return new VideoListNodeSizeResult(videoListNode.getSize());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，此处不简化代码。
    @SuppressWarnings({"DuplicatedCode"})
    @BehaviorAnalyse
    @Override
    public VideoListNodeInspectResult inspect(VideoListNodeInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 获取视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(videoListNode)) {
                videoListNode = new VideoListNode(settingNodeKey, 0);
                videoListNodeMaintainService.insertOrUpdate(videoListNode);
            }

            // 获取视频节点列表实体对应的视频节点列表实体条目。
            List<VideoListNodeItem> videoListNodeItems = videoListNodeItemMaintainService.lookupAsList(
                    VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_ASC, new Object[]{settingNodeKey}
            );

            // 构造 VideoListNodeInspectResult 并返回。
            List<VideoListNodeInspectResult.Item> resultItems = new ArrayList<>();
            for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                VideoListNodeInspectResult.Item resultItem = new VideoListNodeInspectResult.Item(
                        videoListNodeItem.isNullFlag(),
                        videoListNodeItem.getOriginName(),
                        videoListNodeItem.getLength()
                );
                resultItems.add(resultItem);
            }
            return new VideoListNodeInspectResult(resultItems);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public VideoListNodeFile downloadFile(VideoListNodeFileDownloadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 获取视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则直接返回 null。
            if (Objects.isNull(videoListNode)) {
                return null;
            }

            // 获取指定索引对应的视频节点列表实体条目。
            VideoListNodeItem videoListNodeItem = videoListNodeItemMaintainService.lookupFirst(
                    VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 如果视频节点列表实体条目不存在，则直接返回 null。
            if (Objects.isNull(videoListNodeItem)) {
                return null;
            }

            // 如果视频节点列表实体条目的 nullFlag 为 true，则直接返回 null。
            if (videoListNodeItem.isNullFlag()) {
                return null;
            }

            // 下载视频文件。
            byte[] content = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_FILE),
                    videoListNodeItem.getStoreName()
            );

            // 构造 VideoListNodeFile 并返回。
            return new VideoListNodeFile(videoListNodeItem.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public VideoListNodeFileStream downloadFileStream(VideoListNodeFileStreamDownloadInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 获取视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则直接返回 null。
            if (Objects.isNull(videoListNode)) {
                return null;
            }

            // 获取指定索引对应的视频节点列表实体条目。
            VideoListNodeItem videoListNodeItem = videoListNodeItemMaintainService.lookupFirst(
                    VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 如果视频节点列表实体条目不存在，则直接返回 null。
            if (Objects.isNull(videoListNodeItem)) {
                return null;
            }

            // 如果视频节点列表实体条目的 nullFlag 为 true，则直接返回 null。
            if (videoListNodeItem.isNullFlag()) {
                return null;
            }

            // 下载视频文件流。
            InputStream content = ftpHandler.openInputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_FILE),
                    videoListNodeItem.getStoreName()
            );

            // 构造 VideoListNodeFileStream 并返回。
            return new VideoListNodeFileStream(
                    videoListNodeItem.getOriginName(), videoListNodeItem.getLength(), content
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public VideoListNodeThumbnail downloadThumbnail(VideoListNodeThumbnailDownloadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点不适配，则直接返回 null。
            if (!settingNodeMatching(settingNode)) {
                return null;
            }

            // 获取视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则直接返回 null。
            if (Objects.isNull(videoListNode)) {
                return null;
            }

            // 获取指定索引对应的视频节点列表实体条目。
            VideoListNodeItem videoListNodeItem = videoListNodeItemMaintainService.lookupFirst(
                    VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 如果视频节点列表实体条目不存在，则直接返回 null。
            if (Objects.isNull(videoListNodeItem)) {
                return null;
            }

            // 如果视频节点列表实体条目的 nullFlag 为 true，则直接返回 null。
            if (videoListNodeItem.isNullFlag()) {
                return null;
            }

            // 下载缩略图。
            byte[] content = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_THUMBNAIL),
                    videoListNodeItem.getStoreName()
            );

            // 构造 VideoListNodeThumbnail 并返回。
            return new VideoListNodeThumbnail(videoListNodeItem.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void uploadFile(VideoListNodeFileUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            Integer index = info.getIndex();
            String originName = info.getOriginName();
            byte[] content = info.getContent();
            byte[] coverContent = info.getCoverContent();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的视频列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_VIDEO_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_VIDEO_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则初始化视频节点列表实体。
            if (Objects.isNull(videoListNode)) {
                videoListNode = new VideoListNode(settingNodeKey, 0);
            }

            // 构造待更新的 VideoListNodeItem 列表。
            List<VideoListNodeItem> videoListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分三种情况讨论：
            // 如果 index 为 null。
            if (Objects.isNull(index)) {
                // 向视频列表的末尾插入新的视频。
                VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                        null, settingNodeKey, videoListNode.getSize(), false, originName, storeName,
                        (long) content.length
                );
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                // videoListNode 实体的 size 属性加一。
                videoListNode.setSize(videoListNode.getSize() + 1);
            }
            // 如果 index 小于 videoListNode.size()
            else if (index < videoListNode.getSize()) {
                // 获取 index 之后（包括当前索引）的所有视频节点列表实体条目，对所有的视频节点列表实体条目的 index 属性加一。
                List<VideoListNodeItem> videoListNodeItems = videoListNodeItemMaintainService.lookupAsList(
                        VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                        new Object[]{settingNodeKey, index}
                );
                for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                    videoListNodeItem.setIndex(videoListNodeItem.getIndex() + 1);
                    videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                }
                // 添加插入视频的 VideoListNodeItem。
                VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, (long) content.length
                );
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                // videoListNode 实体的 size 属性加一。
                videoListNode.setSize(videoListNode.getSize() + 1);
            }
            // 其余情况（index >= videoListNode.size()）
            else {
                // videoListNode.size() 到 index 之前填充 nullFlag = true 的 VideoListNodeItem。
                for (int i = videoListNode.getSize(); i < index; i++) {
                    VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                }
                // 插入新的视频。
                VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, (long) content.length
                );
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                // videoListNode 实体的 size 属性设置为 index + 1。
                videoListNode.setSize(index + 1);
            }

            // 使用封面图片生成缩略图内容。
            // 此步骤必须在写入文件之前完成：封面图片不合法时直接抛出异常，
            // 从而避免产生残留的文件，也避免覆盖已有的文件造成数据与文件不一致。
            byte[] thumbnailContent = buildThumbnailContent(coverContent);

            // 上传文件。
            ftpHandler.storeFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_FILE), storeName, content
            );

            // 存储缩略图（覆盖）。
            storeThumbnail(storeName, thumbnailContent);

            // 插入或更新视频节点列表实体。
            videoListNodeMaintainService.insertOrUpdate(videoListNode);

            // 批量插入或更新视频节点列表实体条目。
            videoListNodeItemMaintainService.batchInsertOrUpdate(videoListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void uploadFileStream(VideoListNodeFileStreamUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            Integer index = info.getIndex();
            String originName = info.getOriginName();
            long length = info.getLength();
            InputStream coverContent = info.getCoverContent();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的视频列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_VIDEO_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_VIDEO_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则初始化视频节点列表实体。
            if (Objects.isNull(videoListNode)) {
                videoListNode = new VideoListNode(settingNodeKey, 0);
            }

            // 构造待更新的 VideoListNodeItem 列表。
            List<VideoListNodeItem> videoListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分三种情况讨论：
            // 如果 index 为 null。
            if (Objects.isNull(index)) {
                // 向视频列表的末尾插入新的视频。
                VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                        null, settingNodeKey, videoListNode.getSize(), false, originName, storeName, length
                );
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                // videoListNode 实体的 size 属性加一。
                videoListNode.setSize(videoListNode.getSize() + 1);
            }
            // 如果 index 小于 videoListNode.size()
            else if (index < videoListNode.getSize()) {
                // 获取 index 之后（包括当前索引）的所有视频节点列表实体条目，对所有的视频节点列表实体条目的 index 属性加一。
                List<VideoListNodeItem> videoListNodeItems = videoListNodeItemMaintainService.lookupAsList(
                        VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                        new Object[]{settingNodeKey, index}
                );
                for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                    videoListNodeItem.setIndex(videoListNodeItem.getIndex() + 1);
                    videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                }
                // 添加插入视频的 VideoListNodeItem。
                VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, length
                );
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                // videoListNode 实体的 size 属性加一。
                videoListNode.setSize(videoListNode.getSize() + 1);
            }
            // 其余情况（index >= videoListNode.size()）
            else {
                // videoListNode.size() 到 index 之前填充 nullFlag = true 的 VideoListNodeItem。
                for (int i = videoListNode.getSize(); i < index; i++) {
                    VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                }
                // 插入新的视频。
                VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, length
                );
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                // videoListNode 实体的 size 属性设置为 index + 1。
                videoListNode.setSize(index + 1);
            }

            // 使用封面图片生成缩略图内容。
            // 此步骤必须在写入文件之前完成：封面图片不合法时直接抛出异常，
            // 从而避免产生残留的文件，也避免覆盖已有的文件造成数据与文件不一致。
            byte[] thumbnailContent = buildThumbnailContent(coverContent);

            // 上传文件。
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_FILE), storeName)
            ) {
                IOUtil.trans(cin, fout, 4096);
            }

            // 存储缩略图（覆盖）。
            storeThumbnail(storeName, thumbnailContent);

            // 插入或更新视频节点列表实体。
            videoListNodeMaintainService.insertOrUpdate(videoListNode);

            // 批量插入或更新视频节点列表实体条目。
            videoListNodeItemMaintainService.batchInsertOrUpdate(videoListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void updateFile(VideoListNodeFileUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();
            String originName = info.getOriginName();
            byte[] content = info.getContent();
            byte[] coverContent = info.getCoverContent();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的视频列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_VIDEO_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_VIDEO_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则初始化视频节点列表实体。
            if (Objects.isNull(videoListNode)) {
                videoListNode = new VideoListNode(settingNodeKey, 0);
            }

            // 构造待更新的 VideoListNodeItem 列表。
            List<VideoListNodeItem> videoListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分两种情况讨论：
            // 如果 index 小于 videoListNode.size()
            if (index < videoListNode.getSize()) {
                // 获取 index 对应的视频节点列表实体条目。
                VideoListNodeItem videoListNodeItem = videoListNodeItemMaintainService.lookupFirst(
                        VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, index}
                );
                // 如果视频节点列表实体条目不存在，则初始化视频节点列表实体条目。
                if (Objects.isNull(videoListNodeItem)) {
                    videoListNodeItem = new VideoListNodeItem(
                            null, settingNodeKey, index, false, originName, storeName, (long) content.length
                    );
                }
                // 更新视频节点列表实体条目。
                videoListNodeItem.setNullFlag(false);
                videoListNodeItem.setOriginName(originName);
                // 如果旧的 storeName 存在，则直接复用。
                if (Objects.nonNull(videoListNodeItem.getStoreName())) {
                    storeName = videoListNodeItem.getStoreName();
                } else {
                    videoListNodeItem.setStoreName(storeName);
                }
                videoListNodeItem.setLength((long) content.length);
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
            }
            // 其余情况（index >= videoListNode.size()）
            else {
                // videoListNode.size() 到 index 之前填充 nullFlag = true 的 VideoListNodeItem。
                for (int i = videoListNode.getSize(); i < index; i++) {
                    VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                }
                // 插入新的视频。
                VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, (long) content.length
                );
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                // videoListNode 实体的 size 属性设置为 index + 1。
                videoListNode.setSize(index + 1);
            }

            // 使用封面图片生成缩略图内容。
            // 此步骤必须在写入文件之前完成：封面图片不合法时直接抛出异常，
            // 从而避免产生残留的文件，也避免覆盖已有的文件造成数据与文件不一致。
            byte[] thumbnailContent = buildThumbnailContent(coverContent);

            // 上传文件（覆盖）。
            ftpHandler.storeFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_FILE), storeName, content
            );

            // 存储缩略图（覆盖）。
            storeThumbnail(storeName, thumbnailContent);

            // 插入或更新视频节点列表实体。
            videoListNodeMaintainService.insertOrUpdate(videoListNode);

            // 批量插入或更新视频节点列表实体条目。
            videoListNodeItemMaintainService.batchInsertOrUpdate(videoListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void updateFileStream(VideoListNodeFileStreamUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();
            String originName = info.getOriginName();
            long length = info.getLength();
            InputStream coverContent = info.getCoverContent();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的视频列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_VIDEO_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_VIDEO_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则初始化视频节点列表实体。
            if (Objects.isNull(videoListNode)) {
                videoListNode = new VideoListNode(settingNodeKey, 0);
            }

            // 构造待更新的 VideoListNodeItem 列表。
            List<VideoListNodeItem> videoListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分两种情况讨论：
            // 如果 index 小于 videoListNode.size()
            if (index < videoListNode.getSize()) {
                // 获取 index 对应的视频节点列表实体条目。
                VideoListNodeItem videoListNodeItem = videoListNodeItemMaintainService.lookupFirst(
                        VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, index}
                );
                // 如果视频节点列表实体条目不存在，则初始化视频节点列表实体条目。
                if (Objects.isNull(videoListNodeItem)) {
                    videoListNodeItem = new VideoListNodeItem(
                            null, settingNodeKey, index, false, originName, storeName, length
                    );
                }
                // 更新视频节点列表实体条目。
                videoListNodeItem.setNullFlag(false);
                videoListNodeItem.setOriginName(originName);
                // 如果旧的 storeName 存在，则直接复用。
                if (Objects.nonNull(videoListNodeItem.getStoreName())) {
                    storeName = videoListNodeItem.getStoreName();
                } else {
                    videoListNodeItem.setStoreName(storeName);
                }
                videoListNodeItem.setLength(length);
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
            }
            // 其余情况（index >= videoListNode.size()）
            else {
                // videoListNode.size() 到 index 之前填充 nullFlag = true 的 VideoListNodeItem。
                for (int i = videoListNode.getSize(); i < index; i++) {
                    VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                }
                // 插入新的视频。
                VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, length
                );
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                // videoListNode 实体的 size 属性设置为 index + 1。
                videoListNode.setSize(index + 1);
            }

            // 使用封面图片生成缩略图内容。
            // 此步骤必须在写入文件之前完成：封面图片不合法时直接抛出异常，
            // 从而避免产生残留的文件，也避免覆盖已有的文件造成数据与文件不一致。
            byte[] thumbnailContent = buildThumbnailContent(coverContent);

            // 上传文件（覆盖）。
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_FILE), storeName)
            ) {
                IOUtil.trans(cin, fout, 4096);
            }

            // 存储缩略图（覆盖）。
            storeThumbnail(storeName, thumbnailContent);

            // 插入或更新视频节点列表实体。
            videoListNodeMaintainService.insertOrUpdate(videoListNode);

            // 批量插入或更新视频节点列表实体条目。
            videoListNodeItemMaintainService.batchInsertOrUpdate(videoListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，此处不简化代码。
    @SuppressWarnings({"StatementWithEmptyBody", "DuplicatedCode"})
    @BehaviorAnalyse
    @Override
    public void changeOrder(VideoListNodeChangeOrderInfo info) throws HandlerException {
        try {
            // 展开参数
            String category = info.getCategory();
            String[] args = info.getArgs();
            int oldIndex = info.getOldIndex();
            int neoIndex = info.getNeoIndex();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的视频列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_VIDEO_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_VIDEO_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则初始化视频节点列表实体。
            if (Objects.isNull(videoListNode)) {
                videoListNode = new VideoListNode(settingNodeKey, 0);
            }

            // 确认 index 合法。
            if (oldIndex >= videoListNode.getSize()) {
                throw new VideoListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, videoListNode.getSize() - 1, oldIndex
                );
            }
            if (neoIndex >= videoListNode.getSize()) {
                throw new VideoListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, videoListNode.getSize() - 1, neoIndex
                );
            }

            // 构造待更新的 VideoListNodeItem 列表。
            List<VideoListNodeItem> videoListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 根据 oldIndex 与 neoIndex 分三种情况讨论：
            // 如果 oldIndex == neoIndex。
            if (oldIndex == neoIndex) {
                // 什么都不做。
            }
            // 如果 oldIndex < neoIndex。
            else if (oldIndex < neoIndex) {
                // 查询 oldIndex（不包含）到 neoIndex（包含）之间的所有视频节点列表实体条目。
                List<VideoListNodeItem> videoListNodeItems = videoListNodeItemMaintainService.lookupAsList(
                        VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GT_INDEX_LE,
                        new Object[]{settingNodeKey, oldIndex, neoIndex}
                );
                // 对所有的视频节点列表实体条目的 index 属性减一。
                for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                    videoListNodeItem.setIndex(videoListNodeItem.getIndex() - 1);
                    videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                }
                // 获取 oldIndex 对应的视频节点列表实体条目。
                VideoListNodeItem videoListNodeItem = videoListNodeItemMaintainService.lookupFirst(
                        VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, oldIndex}
                );
                // 如果 oldIndex 对应的视频节点列表实体条目不存在，则初始化。
                if (Objects.isNull(videoListNodeItem)) {
                    videoListNodeItem = new VideoListNodeItem(
                            null, settingNodeKey, oldIndex, true, null, null, null
                    );
                }
                // 更新 oldIndex 对应的视频节点列表实体条目。
                videoListNodeItem.setIndex(neoIndex);
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
            }
            // 其它情况（oldIndex > neoIndex）。
            else {
                // 查询 oldIndex（包含）到 neoIndex（不包含）之间的所有视频节点列表实体条目。
                List<VideoListNodeItem> videoListNodeItems = videoListNodeItemMaintainService.lookupAsList(
                        VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE_INDEX_LT,
                        new Object[]{settingNodeKey, neoIndex, oldIndex}
                );
                // 对所有的视频节点列表实体条目的 index 属性加一。
                for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                    videoListNodeItem.setIndex(videoListNodeItem.getIndex() + 1);
                    videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
                }
                // 获取 oldIndex 对应的视频节点列表实体条目。
                VideoListNodeItem videoListNodeItem = videoListNodeItemMaintainService.lookupFirst(
                        VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, oldIndex}
                );
                // 如果 oldIndex 对应的视频节点列表实体条目不存在，则初始化。
                if (Objects.isNull(videoListNodeItem)) {
                    videoListNodeItem = new VideoListNodeItem(
                            null, settingNodeKey, oldIndex, true, null, null, null
                    );
                }
                // 更新 oldIndex 对应的视频节点列表实体条目。
                videoListNodeItem.setIndex(neoIndex);
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
            }

            // 插入或更新视频节点列表实体。
            videoListNodeMaintainService.insertOrUpdate(videoListNode);

            // 批量插入或更新视频节点列表实体条目。
            videoListNodeItemMaintainService.batchInsertOrUpdate(videoListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void remove(VideoListNodeRemoveInfo info) throws HandlerException {
        try {
            // 展开参数
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的视频列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_VIDEO_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_VIDEO_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的视频节点列表实体。
            VideoListNode videoListNode = videoListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点列表实体不存在，则初始化视频节点列表实体。
            if (Objects.isNull(videoListNode)) {
                videoListNode = new VideoListNode(settingNodeKey, 0);
            }

            // 确认 index 合法。
            if (index >= videoListNode.getSize()) {
                throw new VideoListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, videoListNode.getSize() - 1, index
                );
            }

            // 查询待删除的视频节点列表实体条目。
            VideoListNodeItem videoListNodeItemToDelete = videoListNodeItemMaintainService.lookupFirst(
                    VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );
            // 如果待删除的视频节点列表实体条目存在，且存在文件，则删除其对应的文件。
            boolean deleteFileFlag = Objects.nonNull(videoListNodeItemToDelete) &&
                    Objects.nonNull(videoListNodeItemToDelete.getStoreName());
            if (deleteFileFlag) {
                ftpHandler.deleteFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_FILE),
                        videoListNodeItemToDelete.getStoreName()
                );
                ftpHandler.deleteFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_THUMBNAIL),
                        videoListNodeItemToDelete.getStoreName()
                );
            }
            // 如果待删除的视频节点列表实体条目存在，则删除对应的实体。
            boolean deleteEntityFlag = Objects.nonNull(videoListNodeItemToDelete);
            if (deleteEntityFlag) {
                videoListNodeItemMaintainService.delete(videoListNodeItemToDelete.getKey());
            }

            // 构造待更新的 VideoListNodeItem 列表。
            List<VideoListNodeItem> videoListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 获取 index 到 videoListNode.size() 之间的所有视频节点列表实体条目。
            List<VideoListNodeItem> videoListNodeItems = videoListNodeItemMaintainService.lookupAsList(
                    VideoListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                    new Object[]{settingNodeKey, index}
            );
            // 所有的视频节点列表实体条目的 index 属性减一。
            for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                videoListNodeItem.setIndex(videoListNodeItem.getIndex() - 1);
                videoListNodeItemsToInsertOrUpdate.add(videoListNodeItem);
            }

            // 视频节点列表实体的 size 属性减一。
            videoListNode.setSize(videoListNode.getSize() - 1);

            // 插入或更新视频节点列表实体。
            videoListNodeMaintainService.insertOrUpdate(videoListNode);

            // 批量插入或更新视频节点列表实体条目。
            videoListNodeItemMaintainService.batchInsertOrUpdate(videoListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，不简化此方法。
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean settingNodeMatching(SettingNode settingNode) {
        if (Objects.isNull(settingNode)) {
            return false;
        }
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_VIDEO_LIST);
    }

    /**
     * 使用上传的封面图片生成缩略图内容。
     *
     * <p>
     * 缩略图参数读取 {@code video-thumbnail.properties}。
     * 直接使用 Thumbnailator 对封面图片进行缩放；封面图片无法解析时异常向上抛出，
     * 由调用者导致整体上传流程失败。
     *
     * @param coverContent 上传的封面图片内容
     * @return 缩略图内容
     */
    private byte[] buildThumbnailContent(byte[] coverContent) throws Exception {
        // 使用封面图片生成缩略图内容；封面无法解析时异常向上抛出，导致上传失败。
        byte[] thumbnailContent;
        try (ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            Thumbnails.of(new ByteArrayInputStream(coverContent)).size(thumbnailWidth, thumbnailHeight)
                    .outputQuality(thumbnailQuality)
                    .outputFormat(thumbnailOutputFormat).toOutputStream(bout);
            thumbnailContent = bout.toByteArray();
        }
        return thumbnailContent;
    }

    /**
     * 使用上传的封面图片流生成缩略图内容。
     *
     * <p>
     * 缩略图参数读取 {@code video-thumbnail.properties}。
     * 直接使用 Thumbnailator 对封面图片进行缩放；封面图片无法解析时异常向上抛出，
     * 由调用者导致整体上传流程失败。
     *
     * @param coverContent 上传的封面图片流
     * @return 缩略图内容
     */
    private byte[] buildThumbnailContent(InputStream coverContent) throws Exception {
        // 使用封面图片流生成缩略图内容；封面无法解析时异常向上抛出，导致上传失败。
        byte[] thumbnailContent;
        try (ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            Thumbnails.of(coverContent).size(thumbnailWidth, thumbnailHeight)
                    .outputQuality(thumbnailQuality)
                    .outputFormat(thumbnailOutputFormat).toOutputStream(bout);
            thumbnailContent = bout.toByteArray();
        }
        return thumbnailContent;
    }

    /**
     * 将缩略图内容写入 FTP，覆盖同名的已有缩略图。
     *
     * @param fileName          FTP 存储名
     * @param thumbnailContent  缩略图内容
     */
    private void storeThumbnail(String fileName, byte[] thumbnailContent) throws Exception {
        @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
        String[] pint = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_LIST_NODE_THUMBNAIL);

        LOGGER.info("视频列表文件 {} 使用封面生成缩略图成功, thumbnail_size={}", fileName, thumbnailContent.length);

        // 将缩略图写入 FTP。
        try (
                InputStream in = new ByteArrayInputStream(thumbnailContent);
                OutputStream out = ftpHandler.openOutputStream(pint, fileName)
        ) {
            IOUtil.trans(in, out, 4096);
        }
    }
}
