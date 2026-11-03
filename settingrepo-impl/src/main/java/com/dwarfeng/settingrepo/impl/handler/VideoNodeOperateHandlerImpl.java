package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.stack.handler.FtpHandler;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.bean.entity.VideoNode;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.VideoNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.VideoNodeMaintainService;
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
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Component
public class VideoNodeOperateHandlerImpl implements VideoNodeOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoNodeOperateHandlerImpl.class);

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final VideoNodeMaintainService videoNodeMaintainService;

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

    public VideoNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            VideoNodeMaintainService videoNodeMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.videoNodeMaintainService = videoNodeMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public VideoNodeInspectResult inspect(VideoNodeInspectInfo info) throws HandlerException {
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

            // 获取视频节点实体。
            VideoNode videoNode = videoNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点实体不存在，则直接返回 null。
            if (Objects.isNull(videoNode)) {
                return null;
            }

            // 构造 VideoNodeInspectResult 并返回。
            return new VideoNodeInspectResult(videoNode.getOriginName(), videoNode.getLength());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public VideoNodeFile downloadFile(VideoNodeFileDownloadInfo info) throws HandlerException {
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

            // 获取视频节点实体。
            VideoNode videoNode = videoNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点实体不存在，则直接返回 null。
            if (Objects.isNull(videoNode)) {
                return null;
            }

            // 下载视频文件。
            byte[] content = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_FILE), videoNode.getStoreName()
            );

            // 构造 VideoNodeFile 并返回。
            return new VideoNodeFile(videoNode.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public VideoNodeFileStream downloadFileStream(VideoNodeFileStreamDownloadInfo info) throws HandlerException {
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

            // 获取视频节点实体。
            VideoNode videoNode = videoNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点实体不存在，则直接返回 null。
            if (Objects.isNull(videoNode)) {
                return null;
            }

            // 下载视频文件流。
            InputStream content = ftpHandler.openInputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_FILE), videoNode.getStoreName()
            );

            // 构造 VideoNodeFileStream 并返回。
            return new VideoNodeFileStream(videoNode.getOriginName(), videoNode.getLength(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public VideoNodeThumbnail downloadThumbnail(VideoNodeThumbnailDownloadInfo info) throws HandlerException {
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

            // 获取视频节点实体。
            VideoNode videoNode = videoNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点实体不存在，则直接返回 null。
            if (Objects.isNull(videoNode)) {
                return null;
            }

            // 如果不存在缩略图，则直接返回 null。
            boolean existsThumbnail = ftpHandler.existsFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_THUMBNAIL), videoNode.getStoreName()
            );
            if (!existsThumbnail) {
                return null;
            }

            // 下载缩略图。
            byte[] content = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_THUMBNAIL), videoNode.getStoreName()
            );

            // 构造 VideoNodeThumbnail 并返回。
            return new VideoNodeThumbnail(videoNode.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void uploadFile(VideoNodeFileUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
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
                String remark = "由 settingrepo 自动生成的视频节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_VIDEO, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_VIDEO);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 定义存储名。
            String storeName;

            // 获取视频节点实体。
            VideoNode videoNode = videoNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点实体存在，则获取文件名；否则更新视频节点实体，并分配存储名。
            if (Objects.nonNull(videoNode)) {
                storeName = videoNode.getStoreName();
            } else {
                storeName = UUID.randomUUID().toString();
                videoNode = new VideoNode();
                videoNode.setKey(settingNodeKey);
                videoNode.setStoreName(storeName);
            }
            videoNode.setOriginName(originName);
            videoNode.setLength((long) content.length);

            // 使用封面图片生成缩略图内容。
            // 此步骤必须在上传文件之前完成：封面图片不合法时直接抛出异常，
            // 从而避免产生残留的视频文件，也避免覆盖已有的视频文件造成数据与文件不一致。
            byte[] thumbnailContent = buildThumbnailContent(coverContent);

            // 上传文件。
            ftpHandler.storeFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_FILE), storeName, content
            );

            // 存储缩略图（覆盖）。
            storeThumbnail(storeName, thumbnailContent);

            // 插入或更新视频节点实体。
            videoNodeMaintainService.insertOrUpdate(videoNode);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void uploadFileStream(VideoNodeFileStreamUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
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
                String remark = "由 settingrepo 自动生成的视频节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_VIDEO, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_VIDEO);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 定义存储名。
            String storeName;

            // 获取视频节点实体。
            VideoNode videoNode = videoNodeMaintainService.getIfExists(settingNodeKey);

            // 如果视频节点实体存在，则获取文件名；否则更新视频节点实体，并分配存储名。
            if (Objects.nonNull(videoNode)) {
                storeName = videoNode.getStoreName();
            } else {
                storeName = UUID.randomUUID().toString();
                videoNode = new VideoNode();
                videoNode.setKey(settingNodeKey);
                videoNode.setStoreName(storeName);
            }
            videoNode.setOriginName(originName);
            videoNode.setLength(length);

            // 使用封面图片生成缩略图内容。
            // 此步骤必须在上传文件之前完成：封面图片不合法时直接抛出异常，
            // 从而避免产生残留的视频文件，也避免覆盖已有的视频文件造成数据与文件不一致。
            byte[] thumbnailContent = buildThumbnailContent(coverContent);

            // 上传文件。
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_FILE), storeName
            )) {
                IOUtil.trans(cin, fout, 4096);
            }

            // 存储缩略图（覆盖）。
            storeThumbnail(storeName, thumbnailContent);

            // 插入或更新视频节点实体。
            videoNodeMaintainService.insertOrUpdate(videoNode);
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
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_VIDEO);
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
        String[] pint = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_VIDEO_NODE_THUMBNAIL);

        LOGGER.info("视频文件 {} 使用封面生成缩略图成功, thumbnail_size={}", fileName, thumbnailContent.length);

        // 将缩略图写入 FTP。
        try (
                InputStream in = new ByteArrayInputStream(thumbnailContent);
                OutputStream out = ftpHandler.openOutputStream(pint, fileName)
        ) {
            IOUtil.trans(in, out, 4096);
        }
    }
}
