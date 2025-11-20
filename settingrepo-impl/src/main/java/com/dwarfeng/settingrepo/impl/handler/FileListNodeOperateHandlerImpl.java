package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.FileListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.FileListNodeItem;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.exception.FileListNodeIndexOutOfBoundException;
import com.dwarfeng.settingrepo.stack.handler.FileListNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.service.FileListNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.FileListNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Component
public class FileListNodeOperateHandlerImpl implements FileListNodeOperateHandler {

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final FileListNodeMaintainService fileListNodeMaintainService;
    private final FileListNodeItemMaintainService fileListNodeItemMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;
    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    private final HandlerValidator handlerValidator;

    public FileListNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            FileListNodeMaintainService fileListNodeMaintainService,
            FileListNodeItemMaintainService fileListNodeItemMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.fileListNodeMaintainService = fileListNodeMaintainService;
        this.fileListNodeItemMaintainService = fileListNodeItemMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public FileListNodeSizeResult size(FileListNodeSizeInfo info) throws HandlerException {
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

            // 获取文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(fileListNode)) {
                fileListNode = new FileListNode(settingNodeKey, 0);
                fileListNodeMaintainService.insertOrUpdate(fileListNode);
            }

            // 构造 FileListNodeSizeResult 并返回。
            return new FileListNodeSizeResult(fileListNode.getSize());
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，此处不简化代码。
    @SuppressWarnings({"DuplicatedCode"})
    @BehaviorAnalyse
    @Override
    public FileListNodeInspectResult inspect(FileListNodeInspectInfo info) throws HandlerException {
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

            // 获取文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则初始化，并插入或更新实体。
            if (Objects.isNull(fileListNode)) {
                fileListNode = new FileListNode(settingNodeKey, 0);
                fileListNodeMaintainService.insertOrUpdate(fileListNode);
            }

            // 获取文件列表节点条目。
            List<FileListNodeItem> fileListNodeItems = fileListNodeItemMaintainService.lookupAsList(
                    FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_ASC, new Object[]{settingNodeKey}
            );

            // 构造 FileListNodeInspectResult 并返回。
            List<FileListNodeInspectResult.Item> resultItems = new ArrayList<>();
            for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                FileListNodeInspectResult.Item resultItem = new FileListNodeInspectResult.Item(
                        fileListNodeItem.isNullFlag(),
                        fileListNodeItem.getOriginName(),
                        fileListNodeItem.getLength()
                );
                resultItems.add(resultItem);
            }
            return new FileListNodeInspectResult(resultItems);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public FileListNodeFile downloadFile(FileListNodeFileDownloadInfo info) throws HandlerException {
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

            // 获取文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则直接返回 null。
            if (Objects.isNull(fileListNode)) {
                return null;
            }

            // 获取指定索引对应的文件列表节点条目。
            FileListNodeItem fileListNodeItem = fileListNodeItemMaintainService.lookupFirst(
                    FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 如果条目不存在或 nullFlag 为 true，则直接返回 null。
            if (Objects.isNull(fileListNodeItem)) {
                return null;
            }
            if (fileListNodeItem.isNullFlag()) {
                return null;
            }

            // 下载文件。
            byte[] content = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_FILE_LIST_NODE_FILE),
                    fileListNodeItem.getStoreName()
            );

            // 构造 FileListNodeFile 并返回。
            return new FileListNodeFile(fileListNodeItem.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public FileListNodeFileStream downloadFileStream(FileListNodeFileStreamDownloadInfo info) throws HandlerException {
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

            // 获取文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则直接返回 null。
            if (Objects.isNull(fileListNode)) {
                return null;
            }

            // 获取指定索引对应的文件列表节点条目。
            FileListNodeItem fileListNodeItem = fileListNodeItemMaintainService.lookupFirst(
                    FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 如果条目不存在或 nullFlag 为 true，则直接返回 null。
            if (Objects.isNull(fileListNodeItem)) {
                return null;
            }
            if (fileListNodeItem.isNullFlag()) {
                return null;
            }

            // 下载文件流。
            InputStream content = ftpHandler.openInputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_FILE_LIST_NODE_FILE),
                    fileListNodeItem.getStoreName()
            );

            // 构造 FileListNodeFileStream 并返回。
            return new FileListNodeFileStream(
                    fileListNodeItem.getOriginName(), fileListNodeItem.getLength(), content
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void uploadFile(FileListNodeFileUploadInfo info) throws HandlerException {
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
                String remark = "由 settingrepo 自动生成的文件列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_FILE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_FILE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则初始化实体。
            if (Objects.isNull(fileListNode)) {
                fileListNode = new FileListNode(settingNodeKey, 0);
            }

            // 构造待更新的 FileListNodeItem 列表。
            List<FileListNodeItem> fileListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分三种情况讨论。
            if (Objects.isNull(index)) {
                FileListNodeItem fileListNodeItem = new FileListNodeItem(
                        null, settingNodeKey, fileListNode.getSize(), false, originName, storeName,
                        (long) content.length
                );
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                fileListNode.setSize(fileListNode.getSize() + 1);
            } else if (index < fileListNode.getSize()) {
                List<FileListNodeItem> fileListNodeItems = fileListNodeItemMaintainService.lookupAsList(
                        FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                        new Object[]{settingNodeKey, index}
                );
                for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                    fileListNodeItem.setIndex(fileListNodeItem.getIndex() + 1);
                    fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                }
                FileListNodeItem fileListNodeItem = new FileListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, (long) content.length
                );
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                fileListNode.setSize(fileListNode.getSize() + 1);
            } else {
                for (int i = fileListNode.getSize(); i < index; i++) {
                    FileListNodeItem fileListNodeItem = new FileListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                }
                FileListNodeItem fileListNodeItem = new FileListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, (long) content.length
                );
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                fileListNode.setSize(index + 1);
            }

            // 上传文件。
            ftpHandler.storeFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_FILE_LIST_NODE_FILE), storeName, content
            );

            // 插入或更新文件列表节点实体。
            fileListNodeMaintainService.insertOrUpdate(fileListNode);

            // 批量插入或更新文件列表节点条目。
            fileListNodeItemMaintainService.batchInsertOrUpdate(fileListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void uploadFileStream(FileListNodeFileStreamUploadInfo info) throws HandlerException {
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
                String remark = "由 settingrepo 自动生成的文件列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_FILE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_FILE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则初始化实体。
            if (Objects.isNull(fileListNode)) {
                fileListNode = new FileListNode(settingNodeKey, 0);
            }

            // 构造待更新的 FileListNodeItem 列表。
            List<FileListNodeItem> fileListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分三种情况讨论。
            if (Objects.isNull(index)) {
                FileListNodeItem fileListNodeItem = new FileListNodeItem(
                        null, settingNodeKey, fileListNode.getSize(), false, originName, storeName, length
                );
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                fileListNode.setSize(fileListNode.getSize() + 1);
            } else if (index < fileListNode.getSize()) {
                List<FileListNodeItem> fileListNodeItems = fileListNodeItemMaintainService.lookupAsList(
                        FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                        new Object[]{settingNodeKey, index}
                );
                for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                    fileListNodeItem.setIndex(fileListNodeItem.getIndex() + 1);
                    fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                }
                FileListNodeItem fileListNodeItem = new FileListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, length
                );
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                fileListNode.setSize(fileListNode.getSize() + 1);
            } else {
                for (int i = fileListNode.getSize(); i < index; i++) {
                    FileListNodeItem fileListNodeItem = new FileListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                }
                FileListNodeItem fileListNodeItem = new FileListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, length
                );
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                fileListNode.setSize(index + 1);
            }

            // 上传文件。
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_FILE_LIST_NODE_FILE), storeName)
            ) {
                IOUtil.trans(cin, fout, 4096);
            }

            // 插入或更新文件列表节点实体。
            fileListNodeMaintainService.insertOrUpdate(fileListNode);

            // 批量插入或更新文件列表节点条目。
            fileListNodeItemMaintainService.batchInsertOrUpdate(fileListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void updateFile(FileListNodeFileUpdateInfo info) throws HandlerException {
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
                String remark = "由 settingrepo 自动生成的文件列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_FILE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_FILE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则初始化实体。
            if (Objects.isNull(fileListNode)) {
                fileListNode = new FileListNode(settingNodeKey, 0);
            }

            // 构造待更新的 FileListNodeItem 列表。
            List<FileListNodeItem> fileListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分两种情况讨论。
            if (index < fileListNode.getSize()) {
                FileListNodeItem fileListNodeItem = fileListNodeItemMaintainService.lookupFirst(
                        FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, index}
                );
                if (Objects.isNull(fileListNodeItem)) {
                    fileListNodeItem = new FileListNodeItem(
                            null, settingNodeKey, index, false, originName, storeName, (long) content.length
                    );
                }
                fileListNodeItem.setNullFlag(false);
                fileListNodeItem.setOriginName(originName);
                if (Objects.nonNull(fileListNodeItem.getStoreName())) {
                    storeName = fileListNodeItem.getStoreName();
                } else {
                    fileListNodeItem.setStoreName(storeName);
                }
                fileListNodeItem.setLength((long) content.length);
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
            } else {
                for (int i = fileListNode.getSize(); i < index; i++) {
                    FileListNodeItem fileListNodeItem = new FileListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                }
                FileListNodeItem fileListNodeItem = new FileListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, (long) content.length
                );
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                fileListNode.setSize(index + 1);
            }

            // 上传文件（覆盖）。
            ftpHandler.storeFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_FILE_LIST_NODE_FILE), storeName, content
            );

            // 插入或更新文件列表节点实体。
            fileListNodeMaintainService.insertOrUpdate(fileListNode);

            // 批量插入或更新文件列表节点条目。
            fileListNodeItemMaintainService.batchInsertOrUpdate(fileListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void updateFileStream(FileListNodeFileStreamUpdateInfo info) throws HandlerException {
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
                String remark = "由 settingrepo 自动生成的文件列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_FILE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_FILE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则初始化实体。
            if (Objects.isNull(fileListNode)) {
                fileListNode = new FileListNode(settingNodeKey, 0);
            }

            // 构造待更新的 FileListNodeItem 列表。
            List<FileListNodeItem> fileListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 分配文件名。
            String storeName = UUID.randomUUID().toString();

            // 根据 index 分两种情况讨论。
            if (index < fileListNode.getSize()) {
                FileListNodeItem fileListNodeItem = fileListNodeItemMaintainService.lookupFirst(
                        FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, index}
                );
                if (Objects.isNull(fileListNodeItem)) {
                    fileListNodeItem = new FileListNodeItem(
                            null, settingNodeKey, index, false, originName, storeName, length
                    );
                }
                fileListNodeItem.setNullFlag(false);
                fileListNodeItem.setOriginName(originName);
                if (Objects.nonNull(fileListNodeItem.getStoreName())) {
                    storeName = fileListNodeItem.getStoreName();
                } else {
                    fileListNodeItem.setStoreName(storeName);
                }
                fileListNodeItem.setLength(length);
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
            } else {
                for (int i = fileListNode.getSize(); i < index; i++) {
                    FileListNodeItem fileListNodeItem = new FileListNodeItem(
                            null, settingNodeKey, i, true, null, null, null
                    );
                    fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                }
                FileListNodeItem fileListNodeItem = new FileListNodeItem(
                        null, settingNodeKey, index, false, originName, storeName, length
                );
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                fileListNode.setSize(index + 1);
            }

            // 上传文件（覆盖）。
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_FILE_LIST_NODE_FILE), storeName)
            ) {
                IOUtil.trans(cin, fout, 4096);
            }

            // 插入或更新文件列表节点实体。
            fileListNodeMaintainService.insertOrUpdate(fileListNode);

            // 批量插入或更新文件列表节点条目。
            fileListNodeItemMaintainService.batchInsertOrUpdate(fileListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可读性，此处不简化代码。
    @SuppressWarnings({"StatementWithEmptyBody", "DuplicatedCode"})
    @BehaviorAnalyse
    @Override
    public void changeOrder(FileListNodeChangeOrderInfo info) throws HandlerException {
        try {
            // 展开参数。
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
                String remark = "由 settingrepo 自动生成的文件列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_FILE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_FILE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则初始化实体。
            if (Objects.isNull(fileListNode)) {
                fileListNode = new FileListNode(settingNodeKey, 0);
            }

            // 确认 index 合法。
            if (oldIndex >= fileListNode.getSize()) {
                throw new FileListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, fileListNode.getSize() - 1, oldIndex
                );
            }
            if (neoIndex >= fileListNode.getSize()) {
                throw new FileListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, fileListNode.getSize() - 1, neoIndex
                );
            }

            // 构造待更新的 FileListNodeItem 列表。
            List<FileListNodeItem> fileListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 根据 oldIndex 与 neoIndex 分三种情况讨论。
            if (oldIndex == neoIndex) {
                // 什么都不做。
            } else if (oldIndex < neoIndex) {
                List<FileListNodeItem> fileListNodeItems = fileListNodeItemMaintainService.lookupAsList(
                        FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GT_INDEX_LE,
                        new Object[]{settingNodeKey, oldIndex, neoIndex}
                );
                for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                    fileListNodeItem.setIndex(fileListNodeItem.getIndex() - 1);
                    fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                }
                FileListNodeItem fileListNodeItem = fileListNodeItemMaintainService.lookupFirst(
                        FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, oldIndex}
                );
                if (Objects.isNull(fileListNodeItem)) {
                    fileListNodeItem = new FileListNodeItem(
                            null, settingNodeKey, oldIndex, true, null, null, null
                    );
                }
                fileListNodeItem.setIndex(neoIndex);
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
            } else {
                List<FileListNodeItem> fileListNodeItems = fileListNodeItemMaintainService.lookupAsList(
                        FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE_INDEX_LT,
                        new Object[]{settingNodeKey, neoIndex, oldIndex}
                );
                for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                    fileListNodeItem.setIndex(fileListNodeItem.getIndex() + 1);
                    fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
                }
                FileListNodeItem fileListNodeItem = fileListNodeItemMaintainService.lookupFirst(
                        FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                        new Object[]{settingNodeKey, oldIndex}
                );
                if (Objects.isNull(fileListNodeItem)) {
                    fileListNodeItem = new FileListNodeItem(
                            null, settingNodeKey, oldIndex, true, null, null, null
                    );
                }
                fileListNodeItem.setIndex(neoIndex);
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
            }

            // 插入或更新文件列表节点实体。
            fileListNodeMaintainService.insertOrUpdate(fileListNode);

            // 批量插入或更新文件列表节点条目。
            fileListNodeItemMaintainService.batchInsertOrUpdate(fileListNodeItemsToInsertOrUpdate);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void remove(FileListNodeRemoveInfo info) throws HandlerException {
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

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的文件列表节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_FILE_LIST, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_FILE_LIST);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 获取对应的文件列表节点实体。
            FileListNode fileListNode = fileListNodeMaintainService.getIfExists(settingNodeKey);

            // 如果文件列表节点实体不存在，则初始化实体。
            if (Objects.isNull(fileListNode)) {
                fileListNode = new FileListNode(settingNodeKey, 0);
            }

            // 确认 index 合法。
            if (index >= fileListNode.getSize()) {
                throw new FileListNodeIndexOutOfBoundException(
                        settingNodeKey, 0, fileListNode.getSize() - 1, index
                );
            }

            // 查询待删除的文件列表节点条目。
            FileListNodeItem fileListNodeItemToDelete = fileListNodeItemMaintainService.lookupFirst(
                    FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_EQ,
                    new Object[]{settingNodeKey, index}
            );

            // 删除文件。
            boolean deleteFileFlag = Objects.nonNull(fileListNodeItemToDelete) &&
                    Objects.nonNull(fileListNodeItemToDelete.getStoreName());
            if (deleteFileFlag) {
                ftpHandler.deleteFile(
                        ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_FILE_LIST_NODE_FILE),
                        fileListNodeItemToDelete.getStoreName()
                );
            }

            // 删除条目实体。
            boolean deleteEntityFlag = Objects.nonNull(fileListNodeItemToDelete);
            if (deleteEntityFlag) {
                fileListNodeItemMaintainService.delete(fileListNodeItemToDelete.getKey());
            }

            // 构造待更新的 FileListNodeItem 列表。
            List<FileListNodeItem> fileListNodeItemsToInsertOrUpdate = new ArrayList<>();

            // 调整索引。
            List<FileListNodeItem> fileListNodeItems = fileListNodeItemMaintainService.lookupAsList(
                    FileListNodeItemMaintainService.CHILD_FOR_NODE_INDEX_GE,
                    new Object[]{settingNodeKey, index}
            );
            for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                fileListNodeItem.setIndex(fileListNodeItem.getIndex() - 1);
                fileListNodeItemsToInsertOrUpdate.add(fileListNodeItem);
            }

            // 文件列表节点大小减一。
            fileListNode.setSize(fileListNode.getSize() - 1);

            // 插入或更新文件列表节点实体。
            fileListNodeMaintainService.insertOrUpdate(fileListNode);

            // 批量插入或更新文件列表节点条目。
            fileListNodeItemMaintainService.batchInsertOrUpdate(fileListNodeItemsToInsertOrUpdate);
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
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_FILE_LIST);
    }
}
