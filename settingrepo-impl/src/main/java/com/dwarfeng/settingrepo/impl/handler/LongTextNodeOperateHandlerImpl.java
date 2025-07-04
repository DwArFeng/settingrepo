package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringInputStream;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.settingrepo.sdk.util.Constants;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.LongTextNode;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.handler.FormatLocalCacheHandler;
import com.dwarfeng.settingrepo.stack.handler.LongTextNodeOperateHandler;
import com.dwarfeng.settingrepo.stack.service.LongTextNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Component
public class LongTextNodeOperateHandlerImpl implements LongTextNodeOperateHandler {

    private static final Charset TEXT_CHARSET = StandardCharsets.UTF_8;

    private final SettingNodeMaintainService settingNodeMaintainService;
    private final LongTextNodeMaintainService longTextNodeMaintainService;

    private final FormatLocalCacheHandler formatLocalCacheHandler;
    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    private final HandlerValidator handlerValidator;

    public LongTextNodeOperateHandlerImpl(
            SettingNodeMaintainService settingNodeMaintainService,
            LongTextNodeMaintainService longTextNodeMaintainService,
            FormatLocalCacheHandler formatLocalCacheHandler,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            HandlerValidator handlerValidator
    ) {
        this.settingNodeMaintainService = settingNodeMaintainService;
        this.longTextNodeMaintainService = longTextNodeMaintainService;
        this.formatLocalCacheHandler = formatLocalCacheHandler;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public LongTextNodeInspectResult inspect(LongTextNodeInspectInfo info) throws HandlerException {
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

            // 获取长文本节点实体。
            LongTextNode longTextNode = longTextNodeMaintainService.getIfExists(settingNodeKey);

            // 如果长文本节点实体不存在，则直接返回 null。
            if (Objects.isNull(longTextNode)) {
                return null;
            }

            // 构造 LongTextNodeInspectResult 并返回。
            return new LongTextNodeInspectResult(
                    longTextNode.isNullFlag(), longTextNode.getPreview(), longTextNode.getLength()
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public LongTextNodeText downloadText(LongTextNodeTextDownloadInfo info) throws HandlerException {
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

            // 获取长文本节点实体。
            LongTextNode longTextNode = longTextNodeMaintainService.getIfExists(settingNodeKey);

            // 如果长文本节点实体不存在，则直接返回 null。
            if (Objects.isNull(longTextNode)) {
                return null;
            }

            // 如果长文本内容为 null，则直接返回 null。
            if (longTextNode.isNullFlag()) {
                return null;
            }

            // 下载长文本文本的原始文件。
            byte[] rawContent = ftpHandler.retrieveFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_LONG_TEXT_NODE_FILE),
                    longTextNode.getStoreName()
            );

            // 原始文件转换为 String。
            String content = new String(rawContent, TEXT_CHARSET);

            // 构造 LongTextNodeText 并返回。
            return new LongTextNodeText(content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public LongTextNodeTextStream downloadTextStream(LongTextNodeTextStreamDownloadInfo info) throws HandlerException {
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

            // 获取长文本节点实体。
            LongTextNode longTextNode = longTextNodeMaintainService.getIfExists(settingNodeKey);

            // 如果长文本节点实体不存在，则直接返回 null。
            if (Objects.isNull(longTextNode)) {
                return null;
            }

            // 如果长文本内容为 null，则直接返回 null。
            if (longTextNode.isNullFlag()) {
                return null;
            }

            // 下载长文本文本流。
            InputStream content = ftpHandler.openInputStream(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_LONG_TEXT_NODE_FILE),
                    longTextNode.getStoreName()
            );

            // 构造 LongTextNodeTextStream 并返回。
            return new LongTextNodeTextStream(longTextNode.getLength(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void uploadText(LongTextNodeTextUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            String content = info.getContent();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的长文本节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_LONG_TEXT, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_LONG_TEXT);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 根据 content 内容调整参数。
            boolean nullFlag = false;
            if (Objects.isNull(content)) {
                nullFlag = true;
                content = StringUtils.EMPTY;
            }

            // 定义存储名。
            String storeName;

            // 获取长文本节点实体。
            LongTextNode longTextNode = longTextNodeMaintainService.getIfExists(settingNodeKey);

            // 如果长文本节点实体存在，则获取存储名；否则更新长文本节点实体，并分配存储名。
            if (Objects.nonNull(longTextNode)) {
                storeName = longTextNode.getStoreName();
            } else {
                storeName = UUID.randomUUID().toString();
                longTextNode = new LongTextNode();
                longTextNode.setKey(settingNodeKey);
                longTextNode.setStoreName(storeName);
            }
            longTextNode.setLength((long) content.length());
            longTextNode.setNullFlag(nullFlag);

            // 上传文本。
            ftpHandler.storeFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_LONG_TEXT_NODE_FILE),
                    storeName,
                    content.getBytes(TEXT_CHARSET)
            );

            // 解析预览。
            String preview = null;
            if (!nullFlag) {
                // 最多取 Constants.LONG_TEXT_NODE_PREVIEW_LENGTH 个字符，
                // 如果 长度小于等于 Constants.LONG_TEXT_NODE_PREVIEW_LENGTH，则直接使用 content。
                if (content.length() <= Constants.LONG_TEXT_NODE_PREVIEW_LENGTH) {
                    preview = content;
                } else {
                    preview = content.substring(0, Constants.LONG_TEXT_NODE_PREVIEW_LENGTH);
                }
            }

            // 设置长文本节点实体的预览和长度。
            longTextNode.setPreview(preview);

            // 插入或更新长文本节点实体。
            longTextNodeMaintainService.insertOrUpdate(longTextNode);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @BehaviorAnalyse
    @Override
    public void uploadTextStream(LongTextNodeTextStreamUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            String category = info.getCategory();
            String[] args = info.getArgs();
            long length = info.getLength();
            InputStream content = info.getContent();

            // 确认设置类别存在。
            StringIdKey settingCategoryKey = new StringIdKey(category);
            handlerValidator.makeSureSettingCategoryExists(settingCategoryKey);

            // 根据 category 以及 args 获取对应的设置节点主键。
            StringIdKey settingNodeKey = formatLocalCacheHandler.get(settingCategoryKey).format(args);

            // 获取设置节点实体。
            SettingNode settingNode = settingNodeMaintainService.getIfExists(settingNodeKey);

            // 如果节点设置不存在，则初始化节点设置实体。
            if (Objects.isNull(settingNode)) {
                String remark = "由 settingrepo 自动生成的长文本节点";
                settingNode = new SettingNode(
                        settingNodeKey, Constants.SETTING_NODE_TYPE_LONG_TEXT, new Date(), remark,
                        true, category, args
                );
            }
            // 否则更新属性。
            else {
                settingNode.setType(Constants.SETTING_NODE_TYPE_LONG_TEXT);
                settingNode.setLastModifiedDate(new Date());
            }

            // 插入或更新设置节点。
            settingNodeMaintainService.insertOrUpdate(settingNode);

            // 根据 content 内容调整参数。
            boolean nullFlag = false;
            if (Objects.isNull(content)) {
                nullFlag = true;
                content = new StringInputStream(StringUtils.EMPTY, TEXT_CHARSET);
                length = 0;
            }

            // 定义存储名。
            String storeName;

            // 获取长文本节点实体。
            LongTextNode longTextNode = longTextNodeMaintainService.getIfExists(settingNodeKey);

            // 如果长文本节点实体存在，则获取存储名；否则更新长文本节点实体，并分配存储名。
            if (Objects.nonNull(longTextNode)) {
                storeName = longTextNode.getStoreName();
            } else {
                storeName = UUID.randomUUID().toString();
                longTextNode = new LongTextNode();
                longTextNode.setKey(settingNodeKey);
                longTextNode.setStoreName(storeName);
            }
            longTextNode.setLength(length);
            longTextNode.setNullFlag(nullFlag);

            // 上传文本并收集预览。
            String preview;
            try (PreviewCollectOutputStream fout = new PreviewCollectOutputStream(
                    ftpHandler.openOutputStream(
                            ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_LONG_TEXT_NODE_FILE), storeName
                    ),
                    TEXT_CHARSET,
                    Constants.LONG_TEXT_NODE_PREVIEW_LENGTH
            )) {
                IOUtil.trans(content, fout, 4096);
                preview = fout.getPreview();
            }

            // 设置长文本节点实体的预览和长度。
            longTextNode.setPreview(preview);

            // 插入或更新长文本节点实体。
            longTextNodeMaintainService.insertOrUpdate(longTextNode);
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
        return Objects.equals(settingNode.getType(), Constants.SETTING_NODE_TYPE_LONG_TEXT);
    }

    private static class PreviewCollectOutputStream extends FilterOutputStream {

        private final CharsetDecoder decoder;
        private final CharBuffer previewBuffer;

        private boolean overflowFlag = false;

        public PreviewCollectOutputStream(OutputStream out, Charset charset, int previewLength) {
            super(out);
            this.decoder = charset.newDecoder();
            this.previewBuffer = CharBuffer.allocate(previewLength);
        }

        @Override
        public void write(int b) throws IOException {
            out.write(b);
            collectPreview(new byte[]{(byte) b}, 0, 1);
        }

        @Override
        public void write(@Nonnull byte[] b) throws IOException {
            out.write(b);
            collectPreview(b, 0, b.length);
        }

        @Override
        public void write(@Nonnull byte[] b, int off, int len) throws IOException {
            out.write(b, off, len);
            collectPreview(b, off, len);
        }

        private void collectPreview(byte[] b, int off, int len) {
            if (overflowFlag) {
                return;
            }
            ByteBuffer bb = ByteBuffer.wrap(b, off, len);
            CoderResult cr = decoder.decode(bb, previewBuffer, false);
            if (cr.isOverflow()) {
                overflowFlag = true;
            }
        }

        public String getPreview() {
            return previewBuffer.flip().toString();
        }
    }
}
