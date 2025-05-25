package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNodeItem;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.exception.ImageListNodeIndexOutOfBoundException;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.ImageListNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.ImageListNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.ImageListNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Component
public class ImageListNodeOperateHandlerImpl implements ImageListNodeOperateHandler {

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final ImageListNodeMaintainService imageListNodeMaintainService;
    private final ImageListNodeItemMaintainService imageListNodeItemMaintainService;

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

    public ImageListNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            ImageListNodeMaintainService imageListNodeMaintainService,
            ImageListNodeItemMaintainService imageListNodeItemMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.imageListNodeMaintainService = imageListNodeMaintainService;
        this.imageListNodeItemMaintainService = imageListNodeItemMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ImageListNodeSizeResult size(ImageListNodeSizeInfo info) throws HandlerException {
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

            // 获取图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(imageListNode)) {
                imageListNode = new ImageListNode(settingNodeKey, 0);
                imageListNodeMaintainService.insertOrUpdate(imageListNode);
            }

            // 构造 ImageListNodeSizeResult 并返回。
            return new ImageListNodeSizeResult(imageListNode.getSize());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，此处不简化代码。
    @SuppressWarnings({"DuplicatedCode"})
    @Override
    public ImageListNodeInspectResult inspect(ImageListNodeInspectInfo info) throws HandlerException {
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

            // 获取图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(imageListNode)) {
                imageListNode = new ImageListNode(settingNodeKey, 0);
                imageListNodeMaintainService.insertOrUpdate(imageListNode);
            }

            // 获取图片节点列表实体对应的图片节点列表实体条目。
            List<ImageListNodeItem> imageListNodeItems = imageListNodeItemMaintainService.lookupAsList(
                    ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_ASC, new Object[]{settingNodeKey}
            );

            // 构造 ImageListNodeInspectResult 并返回。
            List<ImageListNodeInspectResult.Item> resultItems = new ArrayList<>();
            for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                ImageListNodeInspectResult.Item resultItem = new ImageListNodeInspectResult.Item(
                        imageListNodeItem.isNullFlag(),
                        imageListNodeItem.getOriginName(),
                        imageListNodeItem.getLength()
                );
                resultItems.add(resultItem);
            }
            return new ImageListNodeInspectResult(resultItems);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ImageListNodeFile downloadFile(ImageListNodeFileDownloadInfo info) throws HandlerException {
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

            // 获取图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则直接返回 null。
            if (Objects.isNull(imageListNode)) {
                return null;
            }

            // 获取指定索引对应的图片节点列表实体条目。
            ImageListNodeItem imageListNodeItem = imageListNodeItemMaintainService.lookupFirst(
                    ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 如果图片节点列表实体条目不存在，则直接返回 null。
            if (Objects.isNull(imageListNodeItem)) {
                return null;
            }

            // 如果图片节点列表实体条目的 nullFlag 为 true，则直接返回 null。
            if (imageListNodeItem.isNullFlag()) {
                return null;
            }

            // 下载图片文件。
            byte[] content = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE),
                    imageListNodeItem.getStoreName()
            );

            // 构造 ImageListNodeFile 并返回。
            return new ImageListNodeFile(imageListNodeItem.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ImageListNodeFileStream downloadFileStream(ImageListNodeFileStreamDownloadInfo info)
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

            // 获取图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则直接返回 null。
            if (Objects.isNull(imageListNode)) {
                return null;
            }

            // 获取指定索引对应的图片节点列表实体条目。
            ImageListNodeItem imageListNodeItem = imageListNodeItemMaintainService.lookupFirst(
                    ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 如果图片节点列表实体条目不存在，则直接返回 null。
            if (Objects.isNull(imageListNodeItem)) {
                return null;
            }

            // 如果图片节点列表实体条目的 nullFlag 为 true，则直接返回 null。
            if (imageListNodeItem.isNullFlag()) {
                return null;
            }

            // 下载图片文件流。
            InputStream content = ftpHandler.openInputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE),
                    imageListNodeItem.getStoreName()
            );

            // 构造 ImageListNodeFileStream 并返回。
            return new ImageListNodeFileStream(
                    imageListNodeItem.getOriginName(), imageListNodeItem.getLength(), content
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public ImageListNodeThumbnail downloadThumbnail(ImageListNodeThumbnailDownloadInfo info) throws HandlerException {
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

            // 获取图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则直接返回 null。
            if (Objects.isNull(imageListNode)) {
                return null;
            }

            // 获取指定索引对应的图片节点列表实体条目。
            ImageListNodeItem imageListNodeItem = imageListNodeItemMaintainService.lookupFirst(
                    ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 如果图片节点列表实体条目不存在，则直接返回 null。
            if (Objects.isNull(imageListNodeItem)) {
                return null;
            }

            // 如果图片节点列表实体条目的 nullFlag 为 true，则直接返回 null。
            if (imageListNodeItem.isNullFlag()) {
                return null;
            }

            // 下载缩略图。
            byte[] content = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_THUMBNAIL),
                    imageListNodeItem.getStoreName()
            );

            // 构造 ImageListNodeThumbnail 并返回。
            return new ImageListNodeThumbnail(imageListNodeItem.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void uploadFile(ImageListNodeFileUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            Integer index = info.getIndex();
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
                String remark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IMAGE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则初始化图片节点列表实体。
            if (Objects.isNull(imageListNode)) {
                imageListNode = new ImageListNode(settingNodeKey, 0);
            }

            // 构造待更新的 ImageListNodeItem 列表。
            List<ImageListNodeItem> imageListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分三种情况讨论：
            // 如果 index 为 null。
            if (Objects.isNull(index)) {
                // 向图片列表的末尾插入新的图片。
                ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                        null, settingNodeKey, imageListNode.getSize(), false, originName, storeName,
                        (long) content.length
                );
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                // imageListNode 实体的 size 属性加一。
                imageListNode.setSize(imageListNode.getSize() + 1);
            }
            // 如果 index 小于 imageListNode.size()
            else if (index < imageListNode.getSize()) {
                // 获取 index 之后（包括当前索引）的所有图片节点列表实体条目，对所有的图片节点列表实体条目的 index 属性加一。
                List<ImageListNodeItem> imageListNodeItems = imageListNodeItemMaintainService.lookupAsList(
                        ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                        new Object[]{settingNodeKey, index}
                );
                for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                    imageListNodeItem.setIndex(imageListNodeItem.getIndex() + 1);
                    imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                }
                // 添加插入图片的 ImageListNodeItem。
                ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, (long) content.length
                );
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                // imageListNode 实体的 size 属性加一。
                imageListNode.setSize(imageListNode.getSize() + 1);
            }
            // 其余情况（index >= imageListNode.size()）
            else {
                // imageListNode.size() 到 index 之前填充 nullFlag = true 的 ImageListNodeItem。
                for (int i = imageListNode.getSize(); i < index; i++) {
                    ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                }
                // 插入新的图片。
                ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, (long) content.length
                );
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                // imageListNode 实体的 size 属性设置为 index + 1。
                imageListNode.setSize(index + 1);
            }

            // 上传文件。
            ftpHandler.storeFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE), storeName, content
            );

            // 生成缩略图并存储（覆盖）。
            createThumbnail(storeName);

            // 插入或更新图片节点列表实体。
            imageListNodeMaintainService.insertOrUpdate(imageListNode);

            // 批量插入或更新图片节点列表实体条目。
            imageListNodeItemMaintainService.batchInsertOrUpdate(imageListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void uploadFileStream(ImageListNodeFileStreamUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            Integer index = info.getIndex();
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
                String remark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IMAGE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则初始化图片节点列表实体。
            if (Objects.isNull(imageListNode)) {
                imageListNode = new ImageListNode(settingNodeKey, 0);
            }

            // 构造待更新的 ImageListNodeItem 列表。
            List<ImageListNodeItem> imageListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分三种情况讨论：
            // 如果 index 为 null。
            if (Objects.isNull(index)) {
                // 向图片列表的末尾插入新的图片。
                ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                        null, settingNodeKey, imageListNode.getSize(), false, originName, storeName, length
                );
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                // imageListNode 实体的 size 属性加一。
                imageListNode.setSize(imageListNode.getSize() + 1);
            }
            // 如果 index 小于 imageListNode.size()
            else if (index < imageListNode.getSize()) {
                // 获取 index 之后（包括当前索引）的所有图片节点列表实体条目，对所有的图片节点列表实体条目的 index 属性加一。
                List<ImageListNodeItem> imageListNodeItems = imageListNodeItemMaintainService.lookupAsList(
                        ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                        new Object[]{settingNodeKey, index}
                );
                for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                    imageListNodeItem.setIndex(imageListNodeItem.getIndex() + 1);
                    imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                }
                // 添加插入图片的 ImageListNodeItem。
                ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, length
                );
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                // imageListNode 实体的 size 属性加一。
                imageListNode.setSize(imageListNode.getSize() + 1);
            }
            // 其余情况（index >= imageListNode.size()）
            else {
                // imageListNode.size() 到 index 之前填充 nullFlag = true 的 ImageListNodeItem。
                for (int i = imageListNode.getSize(); i < index; i++) {
                    ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                }
                // 插入新的图片。
                ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, length
                );
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                // imageListNode 实体的 size 属性设置为 index + 1。
                imageListNode.setSize(index + 1);
            }

            // 上传文件。
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE), storeName)
            ) {
                IOUtil.trans(cin, fout, 4096);
            }

            // 生成缩略图并存储（覆盖）。
            createThumbnail(storeName);

            // 插入或更新图片节点列表实体。
            imageListNodeMaintainService.insertOrUpdate(imageListNode);

            // 批量插入或更新图片节点列表实体条目。
            imageListNodeItemMaintainService.batchInsertOrUpdate(imageListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void updateFile(ImageListNodeFileUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();
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
                String remark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IMAGE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则初始化图片节点列表实体。
            if (Objects.isNull(imageListNode)) {
                imageListNode = new ImageListNode(settingNodeKey, 0);
            }

            // 构造待更新的 ImageListNodeItem 列表。
            List<ImageListNodeItem> imageListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分两种情况讨论：
            // 如果 index 小于 imageListNode.size()
            if (index < imageListNode.getSize()) {
                // 获取 index 对应的图片节点列表实体条目。
                ImageListNodeItem imageListNodeItem = imageListNodeItemMaintainService.lookupFirst(
                        ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, index}
                );
                // 如果图片节点列表实体条目不存在，则初始化图片节点列表实体条目。
                if (Objects.isNull(imageListNodeItem)) {
                    imageListNodeItem = new ImageListNodeItem(
                            null, settingNodeKey, index, false, originName, storeName, (long) content.length
                    );
                }
                // 更新图片节点列表实体条目。
                imageListNodeItem.setNullFlag(false);
                imageListNodeItem.setOriginName(originName);
                // 如果旧的 storeName 存在，则直接复用。
                if (Objects.nonNull(imageListNodeItem.getStoreName())) {
                    storeName = imageListNodeItem.getStoreName();
                } else {
                    imageListNodeItem.setStoreName(storeName);
                }
                imageListNodeItem.setLength((long) content.length);
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
            }
            // 其余情况（index >= imageListNode.size()）
            else {
                // imageListNode.size() 到 index 之前填充 nullFlag = true 的 ImageListNodeItem。
                for (int i = imageListNode.getSize(); i < index; i++) {
                    ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                }
                // 插入新的图片。
                ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, (long) content.length
                );
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                // imageListNode 实体的 size 属性设置为 index + 1。
                imageListNode.setSize(index + 1);
            }

            // 上传文件（覆盖）。
            ftpHandler.storeFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE), storeName, content
            );

            // 生成缩略图并存储（覆盖）。
            createThumbnail(storeName);

            // 插入或更新图片节点列表实体。
            imageListNodeMaintainService.insertOrUpdate(imageListNode);

            // 批量插入或更新图片节点列表实体条目。
            imageListNodeItemMaintainService.batchInsertOrUpdate(imageListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void updateFileStream(ImageListNodeFileStreamUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            int index = info.getIndex();
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
                String remark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IMAGE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则初始化图片节点列表实体。
            if (Objects.isNull(imageListNode)) {
                imageListNode = new ImageListNode(settingNodeKey, 0);
            }

            // 构造待更新的 ImageListNodeItem 列表。
            List<ImageListNodeItem> imageListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分两种情况讨论：
            // 如果 index 小于 imageListNode.size()
            if (index < imageListNode.getSize()) {
                // 获取 index 对应的图片节点列表实体条目。
                ImageListNodeItem imageListNodeItem = imageListNodeItemMaintainService.lookupFirst(
                        ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, index}
                );
                // 如果图片节点列表实体条目不存在，则初始化图片节点列表实体条目。
                if (Objects.isNull(imageListNodeItem)) {
                    imageListNodeItem = new ImageListNodeItem(
                            null, settingNodeKey, index, false, originName, storeName, length
                    );
                }
                // 更新图片节点列表实体条目。
                imageListNodeItem.setNullFlag(false);
                imageListNodeItem.setOriginName(originName);
                // 如果旧的 storeName 存在，则直接复用。
                if (Objects.nonNull(imageListNodeItem.getStoreName())) {
                    storeName = imageListNodeItem.getStoreName();
                } else {
                    imageListNodeItem.setStoreName(storeName);
                }
                imageListNodeItem.setLength(length);
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
            }
            // 其余情况（index >= imageListNode.size()）
            else {
                // imageListNode.size() 到 index 之前填充 nullFlag = true 的 ImageListNodeItem。
                for (int i = imageListNode.getSize(); i < index; i++) {
                    ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                }
                // 插入新的图片。
                ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, length
                );
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                // imageListNode 实体的 size 属性设置为 index + 1。
                imageListNode.setSize(index + 1);
            }

            // 上传文件（覆盖）。
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE), storeName)
            ) {
                IOUtil.trans(cin, fout, 4096);
            }

            // 生成缩略图并存储（覆盖）。
            createThumbnail(storeName);

            // 插入或更新图片节点列表实体。
            imageListNodeMaintainService.insertOrUpdate(imageListNode);

            // 批量插入或更新图片节点列表实体条目。
            imageListNodeItemMaintainService.batchInsertOrUpdate(imageListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，此处不简化代码。
    @SuppressWarnings({"StatementWithEmptyBody", "DuplicatedCode"})
    @Override
    public void changeOrder(ImageListNodeChangeOrderInfo info) throws HandlerException {
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
                String remark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IMAGE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则初始化图片节点列表实体。
            if (Objects.isNull(imageListNode)) {
                imageListNode = new ImageListNode(settingNodeKey, 0);
            }

            // 确认 index 合法。
            if (oldIndex >= imageListNode.getSize()) {
                throw new ImageListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, imageListNode.getSize() - 1, oldIndex
                );
            }
            if (neoIndex >= imageListNode.getSize()) {
                throw new ImageListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, imageListNode.getSize() - 1, neoIndex
                );
            }

            // 构造待更新的 ImageListNodeItem 列表。
            List<ImageListNodeItem> imageListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 根据 oldIndex 与 neoIndex 分三种情况讨论：
            // 如果 oldIndex == neoIndex。
            if (oldIndex == neoIndex) {
                // 什么都不做。
            }
            // 如果 oldIndex < neoIndex。
            else if (oldIndex < neoIndex) {
                // 查询 oldIndex（不包含）到 neoIndex（包含）之间的所有图片节点列表实体条目。
                List<ImageListNodeItem> imageListNodeItems = imageListNodeItemMaintainService.lookupAsList(
                        ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GT_INDEX_LE,
                        new Object[]{settingNodeKey, oldIndex, neoIndex}
                );
                // 对所有的图片节点列表实体条目的 index 属性减一。
                for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                    imageListNodeItem.setIndex(imageListNodeItem.getIndex() - 1);
                    imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                }
                // 获取 oldIndex 对应的图片节点列表实体条目。
                ImageListNodeItem imageListNodeItem = imageListNodeItemMaintainService.lookupFirst(
                        ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, oldIndex}
                );
                // 如果 oldIndex 对应的图片节点列表实体条目不存在，则初始化。
                if (Objects.isNull(imageListNodeItem)) {
                    imageListNodeItem = new ImageListNodeItem(
                            null, settingNodeKey, oldIndex, true, null, null, null
                    );
                }
                // 更新 oldIndex 对应的图片节点列表实体条目。
                imageListNodeItem.setIndex(neoIndex);
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
            }
            // 其它情况（oldIndex > neoIndex）。
            else {
                // 查询 oldIndex（包含）到 neoIndex（不包含）之间的所有图片节点列表实体条目。
                List<ImageListNodeItem> imageListNodeItems = imageListNodeItemMaintainService.lookupAsList(
                        ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE_INDEX_LT,
                        new Object[]{settingNodeKey, neoIndex, oldIndex}
                );
                // 对所有的图片节点列表实体条目的 index 属性加一。
                for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                    imageListNodeItem.setIndex(imageListNodeItem.getIndex() + 1);
                    imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
                }
                // 获取 oldIndex 对应的图片节点列表实体条目。
                ImageListNodeItem imageListNodeItem = imageListNodeItemMaintainService.lookupFirst(
                        ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, oldIndex}
                );
                // 如果 oldIndex 对应的图片节点列表实体条目不存在，则初始化。
                if (Objects.isNull(imageListNodeItem)) {
                    imageListNodeItem = new ImageListNodeItem(
                            null, settingNodeKey, oldIndex, true, null, null, null
                    );
                }
                // 更新 oldIndex 对应的图片节点列表实体条目。
                imageListNodeItem.setIndex(neoIndex);
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
            }

            // 插入或更新图片节点列表实体。
            imageListNodeMaintainService.insertOrUpdate(imageListNode);

            // 批量插入或更新图片节点列表实体条目。
            imageListNodeItemMaintainService.batchInsertOrUpdate(imageListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void remove(ImageListNodeRemoveInfo info) throws HandlerException {
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
                String remark = "由 settingrepo 自动生成的图片列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_IMAGE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_IMAGE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的图片节点列表实体。
            ImageListNode imageListNode = imageListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果图片节点列表实体不存在，则初始化图片节点列表实体。
            if (Objects.isNull(imageListNode)) {
                imageListNode = new ImageListNode(settingNodeKey, 0);
            }

            // 确认 index 合法。
            if (index >= imageListNode.getSize()) {
                throw new ImageListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, imageListNode.getSize() - 1, index
                );
            }

            // 查询待删除的图片节点列表实体条目。
            ImageListNodeItem imageListNodeItemToDelete = imageListNodeItemMaintainService.lookupFirst(
                    ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );
            // 如果待删除的图片节点列表实体条目存在，且存在文件，则删除其对应的文件。
            boolean deleteFileFlag = Objects.nonNull(imageListNodeItemToDelete) &&
                    Objects.nonNull(imageListNodeItemToDelete.getStoreName());
            if (deleteFileFlag) {
                ftpHandler.deleteFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE),
                        imageListNodeItemToDelete.getStoreName()
                );
                ftpHandler.deleteFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_THUMBNAIL),
                        imageListNodeItemToDelete.getStoreName()
                );
            }
            // 如果待删除的图片节点列表实体条目存在，则删除对应的实体。
            boolean deleteEntityFlag = Objects.nonNull(imageListNodeItemToDelete);
            if (deleteEntityFlag) {
                imageListNodeItemMaintainService.delete(imageListNodeItemToDelete.getKey());
            }

            // 构造待更新的 ImageListNodeItem 列表。
            List<ImageListNodeItem> imageListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 获取 index 到 imageListNode.size() 之间的所有图片节点列表实体条目。
            List<ImageListNodeItem> imageListNodeItems = imageListNodeItemMaintainService.lookupAsList(
                    ImageListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                    new Object[]{settingNodeKey, index}
            );
            // 所有的图片节点列表实体条目的 index 属性减一。
            for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                imageListNodeItem.setIndex(imageListNodeItem.getIndex() - 1);
                imageListNodeItemsToInsertOrUpdate.add(imageListNodeItem);
            }

            // 图片节点列表实体的 size 属性减一。
            imageListNode.setSize(imageListNode.getSize() - 1);

            // 插入或更新图片节点列表实体。
            imageListNodeMaintainService.insertOrUpdate(imageListNode);

            // 批量插入或更新图片节点列表实体条目。
            imageListNodeItemMaintainService.batchInsertOrUpdate(imageListNodeItemsToInsertOrUpdate);
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
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_IMAGE_LIST);
    }

    @SuppressWarnings("DuplicatedCode")
    private void createThumbnail(String fileName) throws Exception {
        // 定义临时变量，缩短代码长度。
        @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
        String[] pinf = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_FILE);
        @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
        String[] pint = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_IMAGE_LIST_NODE_THUMBNAIL);
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
