package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.ImageNode;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.ImageNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.ImageNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
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
public class ImageNodeOperateHandlerImpl implements ImageNodeOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageNodeOperateHandlerImpl.class);

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final ImageNodeMaintainService imageNodeMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;
    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    private final HandlerValidator handlerValidator;

    @Value("${image_thumbnail.width}")
    private int thumbnailWidth;
    @Value("${image_thumbnail.height}")
    private int thumbnailHeight;
    @Value("${image_thumbnail.quality}")
    private double thumbnailQuality;
    @Value("${image_thumbnail.output_format}")
    private String thumbnailOutputFormat;

    public ImageNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            ImageNodeMaintainService imageNodeMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.imageNodeMaintainService = imageNodeMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ImageNodeInspectResult inspect(ImageNodeInspectInfo info) throws HandlerException {
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

            // 获取图片节点实体。
            ImageNode imageNode = imageNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点实体不存在，则直接返回 null。
            if (Objects.isNull(imageNode)) {
                return null;
            }

            // 构造 ImageNodeInspectResult 并返回。
            return new ImageNodeInspectResult(imageNode.getOriginName(), imageNode.getLength());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ImageNodeFile downloadFile(ImageNodeFileDownloadInfo info) throws HandlerException {
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

            // 获取图片节点实体。
            ImageNode imageNode = imageNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点实体不存在，则直接返回 null。
            if (Objects.isNull(imageNode)) {
                return null;
            }

            // 下载图片文件。
            byte[] content = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_NODE_FILE), imageNode.getStoreName()
            );
            // ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_NODE_FILE)

            // 构造 ImageNodeFile 并返回。
            return new ImageNodeFile(imageNode.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ImageNodeFileStream downloadFileStream(ImageNodeFileStreamDownloadInfo info) throws HandlerException {
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

            // 获取图片节点实体。
            ImageNode imageNode = imageNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点实体不存在，则直接返回 null。
            if (Objects.isNull(imageNode)) {
                return null;
            }

            // 下载图片文件流。
            InputStream content = ftpHandler.openInputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_NODE_FILE), imageNode.getStoreName()
            );

            // 构造 ImageNodeFileStream 并返回。
            return new ImageNodeFileStream(imageNode.getOriginName(), imageNode.getLength(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ImageNodeThumbnail downloadThumbnail(ImageNodeThumbnailDownloadInfo info) throws HandlerException {
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

            // 获取图片节点实体。
            ImageNode imageNode = imageNodeMaintainService.getIfExists(settingNodeKey);

            // 如果不存在证件的缩略图，则创建。
            boolean existsThumbnail = ftpHandler.existsFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_NODE_THUMBNAIL), imageNode.getStoreName()
            );
            if (!existsThumbnail) {
                LOGGER.info("图片节点 {} 的缩略图不存在, 将创建缩略图...", settingNodeKey);
                createThumbnail(imageNode.getStoreName());
            }

            // 下载缩略图。
            byte[] content = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_NODE_THUMBNAIL), imageNode.getStoreName()
            );

            // 构造 ImageNodeThumbnail 并返回。
            return new ImageNodeThumbnail(imageNode.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void uploadFile(ImageNodeFileUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String originName = info.getOriginName();
            byte[] content = info.getContent();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的图片节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IMAGE);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 定义存储名。
            String storeName;

            // 获取图片节点实体。
            ImageNode imageNode = imageNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点实体存在，则获取文件名；否则更新图片节点实体，并分配存储名。
            if (Objects.nonNull(imageNode)) {
                storeName = imageNode.getStoreName();
            } else {
                storeName = UUID.randomUUID().toString();
                imageNode = new ImageNode();
                imageNode.setKey(settingNodeKey);
                imageNode.setStoreName(storeName);
            }
            imageNode.setOriginName(originName);
            imageNode.setLength((long) content.length);

            // 上传文件。
            ftpHandler.storeFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_NODE_FILE), storeName, content
            );

            // 生成缩略图并存储（覆盖）。
            createThumbnail(storeName);

            // 插入或更新图片节点实体。
            imageNodeMaintainService.insertOrUpdate(imageNode);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void uploadFileStream(ImageNodeFileStreamUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String originName = info.getOriginName();
            long length = info.getLength();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的图片节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IMAGE);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 定义存储名。
            String storeName;

            // 获取图片节点实体。
            ImageNode imageNode = imageNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点实体存在，则获取文件名；否则更新图片节点实体，并分配存储名。
            if (Objects.nonNull(imageNode)) {
                storeName = imageNode.getStoreName();
            } else {
                storeName = UUID.randomUUID().toString();
                imageNode = new ImageNode();
                imageNode.setKey(settingNodeKey);
                imageNode.setStoreName(storeName);
            }
            imageNode.setOriginName(originName);
            imageNode.setLength(length);

            // 上传文件。
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_NODE_FILE), storeName
            )) {
                IOUtil.trans(cin, fout, 4096);
            }

            // 生成缩略图并存储（覆盖）。
            createThumbnail(storeName);

            // 插入或更新图片节点实体。
            imageNodeMaintainService.insertOrUpdate(imageNode);
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
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_IMAGE);
    }

    @SuppressWarnings("DuplicatedCode")
    private void createThumbnail(String fileName) throws Exception {
        // 定义临时变量，缩短代码长度。
        @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
        String[] pinf = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_NODE_FILE);
        @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
        String[] pint = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_NODE_THUMBNAIL);
        // 定义缩略图。
        byte[] thumbnailContent;
        // 打开原图的输入流，并在 try-with-resources 中创建缩略图。
        try (
                InputStream in = ftpHandler.openInputStream(pinf, fileName);
                ByteArrayOutputStream bout = new ByteArrayOutputStream()
        ) {
            Thumbnails.of(in).size(thumbnailWidth, thumbnailHeight).outputQuality(thumbnailQuality)
                    .outputFormat(thumbnailOutputFormat).toOutputStream(bout);
            thumbnailContent = bout.toByteArray();
        }
        // 打开缩略图的输出流，并在 try-with-resources 中写入缩略图。
        try (
                InputStream in = new ByteArrayInputStream(thumbnailContent);
                OutputStream out = ftpHandler.openOutputStream(pint, fileName)
        ) {
            IOUtil.trans(in, out, 4096);
        }
    }
}
