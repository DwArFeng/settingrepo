package com.dwarfeng.settingrepo.sdk.bean;

import com.dwarfeng.settingrepo.sdk.bean.dto.*;
import com.dwarfeng.settingrepo.sdk.bean.entity.*;
import com.dwarfeng.settingrepo.sdk.bean.key.*;
import com.dwarfeng.settingrepo.stack.bean.dto.*;
import com.dwarfeng.settingrepo.stack.bean.entity.*;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>sdk</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 2.3.0
 */
@Mapper
public interface BeanMapper {

    // -----------------------------------------------------------Subgrade Key-----------------------------------------------------------
    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    JSFixedFastJsonLongIdKey longIdKeyToJSFixedFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromJSFixedFastJson(JSFixedFastJsonLongIdKey jSFixedFastJsonLongIdKey);

    WebInputStringIdKey stringIdKeyToWebInput(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromWebInput(WebInputStringIdKey webInputStringIdKey);

    // -----------------------------------------------------------Settingrepo Key-----------------------------------------------------------
    FastJsonIahnNodeLocaleKey iahnNodeLocaleKeyToFastJson(IahnNodeLocaleKey iahnNodeLocaleKey);

    @InheritInverseConfiguration
    IahnNodeLocaleKey iahnNodeLocaleKeyFromFastJson(FastJsonIahnNodeLocaleKey fastJsonIahnNodeLocaleKey);

    FastJsonIahnNodeMekKey iahnNodeMekKeyToFastJson(IahnNodeMekKey iahnNodeMekKey);

    @InheritInverseConfiguration
    IahnNodeMekKey iahnNodeMekKeyFromFastJson(FastJsonIahnNodeMekKey fastJsonIahnNodeMekKey);

    FastJsonIahnNodeMessageKey iahnNodeMessageKeyToFastJson(IahnNodeMessageKey iahnNodeMessageKey);

    @InheritInverseConfiguration
    IahnNodeMessageKey iahnNodeMessageKeyFromFastJson(FastJsonIahnNodeMessageKey fastJsonIahnNodeMessageKey);

    WebInputIahnNodeLocaleKey iahnNodeLocaleKeyToWebInput(IahnNodeLocaleKey iahnNodeLocaleKey);

    @InheritInverseConfiguration
    IahnNodeLocaleKey iahnNodeLocaleKeyFromWebInput(WebInputIahnNodeLocaleKey webInputIahnNodeLocaleKey);

    WebInputIahnNodeMekKey iahnNodeMekKeyToWebInput(IahnNodeMekKey iahnNodeMekKey);

    @InheritInverseConfiguration
    IahnNodeMekKey iahnNodeMekKeyFromWebInput(WebInputIahnNodeMekKey webInputIahnNodeMekKey);

    WebInputIahnNodeMessageKey iahnNodeMessageKeyToWebInput(IahnNodeMessageKey iahnNodeMessageKey);

    @InheritInverseConfiguration
    IahnNodeMessageKey iahnNodeMessageKeyFromWebInput(WebInputIahnNodeMessageKey webInputIahnNodeMessageKey);

    // -----------------------------------------------------------Settingrepo Entity-----------------------------------------------------------
    FastJsonFormatterSupport formatterSupportToFastJson(FormatterSupport formatterSupport);

    @InheritInverseConfiguration
    FormatterSupport formatterSupportFromFastJson(FastJsonFormatterSupport fastJsonFormatterSupport);

    FastJsonIahnNode iahnNodeToFastJson(IahnNode iahnNode);

    @InheritInverseConfiguration
    IahnNode iahnNodeFromFastJson(FastJsonIahnNode fastJsonIahnNode);

    FastJsonIahnNodeLocale iahnNodeLocaleToFastJson(IahnNodeLocale iahnNodeLocale);

    @InheritInverseConfiguration
    IahnNodeLocale iahnNodeLocaleFromFastJson(FastJsonIahnNodeLocale fastJsonIahnNodeLocale);

    FastJsonIahnNodeMek iahnNodeMekToFastJson(IahnNodeMek iahnNodeMek);

    @InheritInverseConfiguration
    IahnNodeMek iahnNodeMekFromFastJson(FastJsonIahnNodeMek fastJsonIahnNodeMek);

    FastJsonIahnNodeMessage iahnNodeMessageToFastJson(IahnNodeMessage iahnNodeMessage);

    @InheritInverseConfiguration
    IahnNodeMessage iahnNodeMessageFromFastJson(FastJsonIahnNodeMessage fastJsonIahnNodeMessage);

    FastJsonImageListNode imageListNodeToFastJson(ImageListNode imageListNode);

    @InheritInverseConfiguration
    ImageListNode imageListNodeFromFastJson(FastJsonImageListNode fastJsonImageListNode);

    FastJsonImageListNodeItem imageListNodeItemToFastJson(ImageListNodeItem imageListNodeItem);

    @InheritInverseConfiguration
    ImageListNodeItem imageListNodeItemFromFastJson(FastJsonImageListNodeItem fastJsonImageListNodeItem);

    FastJsonImageNode imageNodeToFastJson(ImageNode imageNode);

    @InheritInverseConfiguration
    ImageNode imageNodeFromFastJson(FastJsonImageNode fastJsonImageNode);

    FastJsonLongTextNode longTextNodeToFastJson(LongTextNode longTextNode);

    @InheritInverseConfiguration
    LongTextNode longTextNodeFromFastJson(FastJsonLongTextNode fastJsonLongTextNode);

    FastJsonSettingCategory settingCategoryToFastJson(SettingCategory settingCategory);

    @InheritInverseConfiguration
    SettingCategory settingCategoryFromFastJson(FastJsonSettingCategory fastJsonSettingCategory);

    FastJsonSettingNode settingNodeToFastJson(SettingNode settingNode);

    @InheritInverseConfiguration
    SettingNode settingNodeFromFastJson(FastJsonSettingNode fastJsonSettingNode);

    FastJsonTextNode textNodeToFastJson(TextNode textNode);

    @InheritInverseConfiguration
    TextNode textNodeFromFastJson(FastJsonTextNode fastJsonTextNode);

    JSFixedFastJsonImageListNodeItem imageListNodeItemToJSFixedFastJson(ImageListNodeItem imageListNodeItem);

    @InheritInverseConfiguration
    ImageListNodeItem imageListNodeItemFromJSFixedFastJson(JSFixedFastJsonImageListNodeItem jSFixedFastJsonImageListNodeItem);

    WebInputSettingCategory settingCategoryToWebInput(SettingCategory settingCategory);

    @InheritInverseConfiguration
    SettingCategory settingCategoryFromWebInput(WebInputSettingCategory webInputSettingCategory);

    FastJsonFileNode fileNodeToFastJson(FileNode fileNode);

    @InheritInverseConfiguration
    FileNode fileNodeFromFastJson(FastJsonFileNode fastJsonFileNode);

    FastJsonFileListNode fileListNodeToFastJson(FileListNode fileListNode);

    @InheritInverseConfiguration
    FileListNode fileListNodeFromFastJson(FastJsonFileListNode fastJsonFileListNode);

    FastJsonFileListNodeItem fileListNodeItemToFastJson(FileListNodeItem fileListNodeItem);

    @InheritInverseConfiguration
    FileListNodeItem fileListNodeItemFromFastJson(FastJsonFileListNodeItem fastJsonFileListNodeItem);

    JSFixedFastJsonFileListNodeItem fileListNodeItemToJSFixedFastJson(FileListNodeItem fileListNodeItem);

    @InheritInverseConfiguration
    FileListNodeItem fileListNodeItemFromJSFixedFastJson(JSFixedFastJsonFileListNodeItem fastJsonFileListNodeItem);

    // -----------------------------------------------------------Settingrepo DTO-----------------------------------------------------------
    FastJsonIahnNodeMessageInspectByLocaleResult iahnNodeMessageInspectByLocaleResultToFastJson(
            IahnNodeMessageInspectByLocaleResult iahnNodeMessageInspectByLocaleResult
    );

    @InheritInverseConfiguration
    IahnNodeMessageInspectByLocaleResult iahnNodeMessageInspectByLocaleResultFromFastJson(
            FastJsonIahnNodeMessageInspectByLocaleResult fastJsonIahnNodeMessageInspectByLocaleResult
    );

    FastJsonIahnNodeMessageInspectResult iahnNodeMessageInspectResultToFastJson(
            IahnNodeMessageInspectResult iahnNodeMessageInspectResult
    );

    @InheritInverseConfiguration
    IahnNodeMessageInspectResult iahnNodeMessageInspectResultFromFastJson(
            FastJsonIahnNodeMessageInspectResult fastJsonIahnNodeMessageInspectResult
    );

    FastJsonImageListNodeInspectResult imageListNodeInspectResultToFastJson(
            ImageListNodeInspectResult imageListNodeInspectResult
    );

    @InheritInverseConfiguration
    ImageListNodeInspectResult imageListNodeInspectResultFromFastJson(
            FastJsonImageListNodeInspectResult fastJsonImageListNodeInspectResult
    );

    FastJsonImageListNodeSizeResult imageListNodeSizeResultToFastJson(ImageListNodeSizeResult imageListNodeSizeResult);

    @InheritInverseConfiguration
    ImageListNodeSizeResult imageListNodeSizeResultFromFastJson(
            FastJsonImageListNodeSizeResult fastJsonImageListNodeSizeResult
    );

    FastJsonImageNodeInspectResult imageNodeInspectResultToFastJson(ImageNodeInspectResult imageNodeInspectResult);

    @InheritInverseConfiguration
    ImageNodeInspectResult imageNodeInspectResultFromFastJson(
            FastJsonImageNodeInspectResult fastJsonImageNodeInspectResult
    );

    FastJsonLongTextNodeInspectResult longTextNodeInspectResultToFastJson(
            LongTextNodeInspectResult longTextNodeInspectResult
    );

    @InheritInverseConfiguration
    LongTextNodeInspectResult longTextNodeInspectResultFromFastJson(
            FastJsonLongTextNodeInspectResult fastJsonLongTextNodeInspectResult
    );

    FastJsonSettingNodeExistsResult settingNodeExistsResultToFastJson(SettingNodeExistsResult settingNodeExistsResult);

    @InheritInverseConfiguration
    SettingNodeExistsResult settingNodeExistsResultFromFastJson(
            FastJsonSettingNodeExistsResult fastJsonSettingNodeExistsResult
    );

    FastJsonSettingNodeInspectResult settingNodeInspectResultToFastJson(
            SettingNodeInspectResult settingNodeInspectResult
    );

    @InheritInverseConfiguration
    SettingNodeInspectResult settingNodeInspectResultFromFastJson(
            FastJsonSettingNodeInspectResult fastJsonSettingNodeInspectResult
    );

    FastJsonTextNodeInspectResult textNodeInspectResultToFastJson(TextNodeInspectResult textNodeInspectResult);

    @InheritInverseConfiguration
    TextNodeInspectResult textNodeInspectResultFromFastJson(
            FastJsonTextNodeInspectResult fastJsonTextNodeInspectResult
    );

    WebInputIahnNodeLocalePutInfo iahnNodeLocalePutInfoToWebInput(IahnNodeLocalePutInfo iahnNodeLocalePutInfo);

    @InheritInverseConfiguration
    IahnNodeLocalePutInfo iahnNodeLocalePutInfoFromWebInput(
            WebInputIahnNodeLocalePutInfo webInputIahnNodeLocalePutInfo
    );

    WebInputIahnNodeLocaleRemoveInfo iahnNodeLocaleRemoveInfoToWebInput(
            IahnNodeLocaleRemoveInfo iahnNodeLocaleRemoveInfo
    );

    @InheritInverseConfiguration
    IahnNodeLocaleRemoveInfo iahnNodeLocaleRemoveInfoFromWebInput(
            WebInputIahnNodeLocaleRemoveInfo webInputIahnNodeLocaleRemoveInfo
    );

    WebInputIahnNodeMekPutInfo iahnNodeMekPutInfoToWebInput(IahnNodeMekPutInfo iahnNodeMekPutInfo);

    @InheritInverseConfiguration
    IahnNodeMekPutInfo iahnNodeMekPutInfoFromWebInput(WebInputIahnNodeMekPutInfo webInputIahnNodeMekPutInfo);

    WebInputIahnNodeMekRemoveInfo iahnNodeMekRemoveInfoToWebInput(IahnNodeMekRemoveInfo iahnNodeMekRemoveInfo);

    @InheritInverseConfiguration
    IahnNodeMekRemoveInfo iahnNodeMekRemoveInfoFromWebInput(
            WebInputIahnNodeMekRemoveInfo webInputIahnNodeMekRemoveInfo
    );

    WebInputIahnNodeMessageInspectByLocaleInfo iahnNodeMessageInspectByLocaleInfoToWebInput(
            IahnNodeMessageInspectByLocaleInfo iahnNodeMessageInspectByLocaleInfo
    );

    @InheritInverseConfiguration
    IahnNodeMessageInspectByLocaleInfo iahnNodeMessageInspectByLocaleInfoFromWebInput(
            WebInputIahnNodeMessageInspectByLocaleInfo webInputIahnNodeMessageInspectByLocaleInfo
    );

    WebInputIahnNodeMessageInspectInfo iahnNodeMessageInspectInfoToWebInput(
            IahnNodeMessageInspectInfo iahnNodeMessageInspectInfo
    );

    @InheritInverseConfiguration
    IahnNodeMessageInspectInfo iahnNodeMessageInspectInfoFromWebInput(
            WebInputIahnNodeMessageInspectInfo webInputIahnNodeMessageInspectInfo
    );

    WebInputIahnNodeMessageUpsertByLocaleInfo iahnNodeMessageUpsertByLocaleInfoToWebInput(
            IahnNodeMessageUpsertByLocaleInfo iahnNodeMessageUpsertByLocaleInfo
    );

    @InheritInverseConfiguration
    IahnNodeMessageUpsertByLocaleInfo iahnNodeMessageUpsertByLocaleInfoFromWebInput(
            WebInputIahnNodeMessageUpsertByLocaleInfo webInputIahnNodeMessageUpsertByLocaleInfo
    );

    WebInputIahnNodeMessageUpsertByMekInfo iahnNodeMessageUpsertByMekInfoToWebInput(
            IahnNodeMessageUpsertByMekInfo iahnNodeMessageUpsertByMekInfo
    );

    @InheritInverseConfiguration
    IahnNodeMessageUpsertByMekInfo iahnNodeMessageUpsertByMekInfoFromWebInput(
            WebInputIahnNodeMessageUpsertByMekInfo webInputIahnNodeMessageUpsertByMekInfo
    );

    WebInputIahnNodeMessageUpsertInfo iahnNodeMessageUpsertInfoToWebInput(
            IahnNodeMessageUpsertInfo iahnNodeMessageUpsertInfo
    );

    @InheritInverseConfiguration
    IahnNodeMessageUpsertInfo iahnNodeMessageUpsertInfoFromWebInput(
            WebInputIahnNodeMessageUpsertInfo webInputIahnNodeMessageUpsertInfo
    );

    WebInputImageListNodeChangeOrderInfo imageListNodeChangeOrderInfoToWebInput(
            ImageListNodeChangeOrderInfo imageListNodeChangeOrderInfo
    );

    @InheritInverseConfiguration
    ImageListNodeChangeOrderInfo imageListNodeChangeOrderInfoFromWebInput(
            WebInputImageListNodeChangeOrderInfo webInputImageListNodeChangeOrderInfo
    );

    WebInputImageListNodeFileDownloadInfo imageListNodeFileDownloadInfoToWebInput(
            ImageListNodeFileDownloadInfo imageListNodeFileDownloadInfo
    );

    @InheritInverseConfiguration
    ImageListNodeFileDownloadInfo imageListNodeFileDownloadInfoFromWebInput(
            WebInputImageListNodeFileDownloadInfo webInputImageListNodeFileDownloadInfo
    );

    WebInputImageListNodeFileStreamDownloadInfo imageListNodeFileStreamDownloadInfoToWebInput(
            ImageListNodeFileStreamDownloadInfo imageListNodeFileStreamDownloadInfo
    );

    @InheritInverseConfiguration
    ImageListNodeFileStreamDownloadInfo imageListNodeFileStreamDownloadInfoFromWebInput(
            WebInputImageListNodeFileStreamDownloadInfo webInputImageListNodeFileStreamDownloadInfo
    );

    WebInputImageListNodeFileUpdateInfo imageListNodeFileUpdateInfoToWebInput(
            ImageListNodeFileUpdateInfo imageListNodeFileUpdateInfo
    );

    @InheritInverseConfiguration
    ImageListNodeFileUpdateInfo imageListNodeFileUpdateInfoFromWebInput(
            WebInputImageListNodeFileUpdateInfo webInputImageListNodeFileUpdateInfo
    );

    WebInputImageListNodeFileUploadInfo imageListNodeFileUploadInfoToWebInput(
            ImageListNodeFileUploadInfo imageListNodeFileUploadInfo
    );

    @InheritInverseConfiguration
    ImageListNodeFileUploadInfo imageListNodeFileUploadInfoFromWebInput(
            WebInputImageListNodeFileUploadInfo webInputImageListNodeFileUploadInfo
    );

    WebInputImageListNodeInspectInfo imageListNodeInspectInfoToWebInput(
            ImageListNodeInspectInfo imageListNodeInspectInfo
    );

    @InheritInverseConfiguration
    ImageListNodeInspectInfo imageListNodeInspectInfoFromWebInput(
            WebInputImageListNodeInspectInfo webInputImageListNodeInspectInfo
    );

    WebInputImageListNodeRemoveInfo imageListNodeRemoveInfoToWebInput(
            ImageListNodeRemoveInfo imageListNodeRemoveInfo
    );

    @InheritInverseConfiguration
    ImageListNodeRemoveInfo imageListNodeRemoveInfoFromWebInput(
            WebInputImageListNodeRemoveInfo webInputImageListNodeRemoveInfo
    );

    WebInputImageListNodeSizeInfo imageListNodeSizeInfoToWebInput(ImageListNodeSizeInfo imageListNodeSizeInfo);

    @InheritInverseConfiguration
    ImageListNodeSizeInfo imageListNodeSizeInfoFromWebInput(
            WebInputImageListNodeSizeInfo webInputImageListNodeSizeInfo
    );

    WebInputImageListNodeThumbnailDownloadInfo imageListNodeThumbnailDownloadInfoToWebInput(
            ImageListNodeThumbnailDownloadInfo imageListNodeThumbnailDownloadInfo
    );

    @InheritInverseConfiguration
    ImageListNodeThumbnailDownloadInfo imageListNodeThumbnailDownloadInfoFromWebInput(
            WebInputImageListNodeThumbnailDownloadInfo webInputImageListNodeThumbnailDownloadInfo
    );

    WebInputImageNodeFileDownloadInfo imageNodeFileDownloadInfoToWebInput(
            ImageNodeFileDownloadInfo imageNodeFileDownloadInfo
    );

    @InheritInverseConfiguration
    ImageNodeFileDownloadInfo imageNodeFileDownloadInfoFromWebInput(
            WebInputImageNodeFileDownloadInfo webInputImageNodeFileDownloadInfo
    );

    WebInputImageNodeFileStreamDownloadInfo imageNodeFileStreamDownloadInfoToWebInput(
            ImageNodeFileStreamDownloadInfo imageNodeFileStreamDownloadInfo
    );

    @InheritInverseConfiguration
    ImageNodeFileStreamDownloadInfo imageNodeFileStreamDownloadInfoFromWebInput(
            WebInputImageNodeFileStreamDownloadInfo webInputImageNodeFileStreamDownloadInfo
    );

    WebInputImageNodeFileUploadInfo imageNodeFileUploadInfoToWebInput(ImageNodeFileUploadInfo imageNodeFileUploadInfo);

    @InheritInverseConfiguration
    ImageNodeFileUploadInfo imageNodeFileUploadInfoFromWebInput(
            WebInputImageNodeFileUploadInfo webInputImageNodeFileUploadInfo
    );

    WebInputImageNodeInspectInfo imageNodeInspectInfoToWebInput(ImageNodeInspectInfo imageNodeInspectInfo);

    @InheritInverseConfiguration
    ImageNodeInspectInfo imageNodeInspectInfoFromWebInput(WebInputImageNodeInspectInfo webInputImageNodeInspectInfo);

    WebInputImageNodeThumbnailDownloadInfo imageNodeThumbnailDownloadInfoToWebInput(
            ImageNodeThumbnailDownloadInfo imageNodeThumbnailDownloadInfo
    );

    @InheritInverseConfiguration
    ImageNodeThumbnailDownloadInfo imageNodeThumbnailDownloadInfoFromWebInput(
            WebInputImageNodeThumbnailDownloadInfo webInputImageNodeThumbnailDownloadInfo
    );

    WebInputLongTextNodeInspectInfo longTextNodeInspectInfoToWebInput(LongTextNodeInspectInfo longTextNodeInspectInfo);

    @InheritInverseConfiguration
    LongTextNodeInspectInfo longTextNodeInspectInfoFromWebInput(
            WebInputLongTextNodeInspectInfo webInputLongTextNodeInspectInfo
    );

    WebInputLongTextNodeTextDownloadInfo longTextNodeTextDownloadInfoToWebInput(
            LongTextNodeTextDownloadInfo longTextNodeTextDownloadInfo
    );

    @InheritInverseConfiguration
    LongTextNodeTextDownloadInfo longTextNodeTextDownloadInfoFromWebInput(
            WebInputLongTextNodeTextDownloadInfo webInputLongTextNodeTextDownloadInfo
    );

    WebInputLongTextNodeTextStreamDownloadInfo longTextNodeTextStreamDownloadInfoToWebInput(
            LongTextNodeTextStreamDownloadInfo longTextNodeTextStreamDownloadInfo
    );

    @InheritInverseConfiguration
    LongTextNodeTextStreamDownloadInfo longTextNodeTextStreamDownloadInfoFromWebInput(
            WebInputLongTextNodeTextStreamDownloadInfo webInputLongTextNodeTextStreamDownloadInfo
    );

    WebInputLongTextNodeTextUploadInfo longTextNodeTextUploadInfoToWebInput(
            LongTextNodeTextUploadInfo longTextNodeTextUploadInfo
    );

    @InheritInverseConfiguration
    LongTextNodeTextUploadInfo longTextNodeTextUploadInfoFromWebInput(
            WebInputLongTextNodeTextUploadInfo webInputLongTextNodeTextUploadInfo
    );

    WebInputSettingNodeExistsInfo settingNodeExistsInfoToWebInput(SettingNodeExistsInfo settingNodeExistsInfo);

    @InheritInverseConfiguration
    SettingNodeExistsInfo settingNodeExistsInfoFromWebInput(
            WebInputSettingNodeExistsInfo webInputSettingNodeExistsInfo
    );

    WebInputSettingNodeInitInfo settingNodeInitInfoToWebInput(SettingNodeInitInfo settingNodeInitInfo);

    @InheritInverseConfiguration
    SettingNodeInitInfo settingNodeInitInfoFromWebInput(WebInputSettingNodeInitInfo webInputSettingNodeInitInfo);

    WebInputSettingNodeInspectInfo settingNodeInspectInfoToWebInput(SettingNodeInspectInfo settingNodeInspectInfo);

    @InheritInverseConfiguration
    SettingNodeInspectInfo settingNodeInspectInfoFromWebInput(
            WebInputSettingNodeInspectInfo webInputSettingNodeInspectInfo
    );

    WebInputSettingNodeRemoveInfo settingNodeRemoveInfoToWebInput(SettingNodeRemoveInfo settingNodeRemoveInfo);

    @InheritInverseConfiguration
    SettingNodeRemoveInfo settingNodeRemoveInfoFromWebInput(
            WebInputSettingNodeRemoveInfo webInputSettingNodeRemoveInfo
    );

    WebInputTextNodeInspectInfo textNodeInspectInfoToWebInput(TextNodeInspectInfo textNodeInspectInfo);

    @InheritInverseConfiguration
    TextNodeInspectInfo textNodeInspectInfoFromWebInput(WebInputTextNodeInspectInfo webInputTextNodeInspectInfo);

    WebInputTextNodePutInfo textNodePutInfoToWebInput(TextNodePutInfo textNodePutInfo);

    @InheritInverseConfiguration
    TextNodePutInfo textNodePutInfoFromWebInput(WebInputTextNodePutInfo webInputTextNodePutInfo);

    WebInputIahnNodeLocaleListInspectInfo iahnNodeLocaleListInspectInfoToWebInput(
            IahnNodeLocaleListInspectInfo iahnNodeLocaleListInspectInfo
    );

    @InheritInverseConfiguration
    IahnNodeLocaleListInspectInfo iahnNodeLocaleListInspectInfoFromWebInput(
            WebInputIahnNodeLocaleListInspectInfo webInputIahnNodeLocaleListInspectInfo
    );

    FastJsonIahnNodeLocaleListInspectResult iahnNodeLocaleListInspectResultToFastJson(
            IahnNodeLocaleListInspectResult iahnNodeLocaleListInspectResult
    );

    @InheritInverseConfiguration
    IahnNodeLocaleListInspectResult iahnNodeLocaleListInspectResultFromFastJson(
            FastJsonIahnNodeLocaleListInspectResult fastJsonIahnNodeLocaleListInspectResult
    );

    FastJsonIahnNodeMekListInspectResult iahnNodeMekListInspectResultToFastJson(
            IahnNodeMekListInspectResult iahnNodeMekListInspectResult
    );

    @InheritInverseConfiguration
    IahnNodeMekListInspectResult iahnNodeMekListInspectResultFromFastJson(
            FastJsonIahnNodeMekListInspectResult fastJsonIahnNodeMekListInspectResult
    );

    FastJsonIahnNodeMessageTableInspectResult iahnNodeMessageTableInspectResultToFastJson(
            IahnNodeMessageTableInspectResult iahnNodeMessageTableInspectResult
    );

    @InheritInverseConfiguration
    IahnNodeMessageTableInspectResult iahnNodeMessageTableInspectResultFromFastJson(
            FastJsonIahnNodeMessageTableInspectResult fastJsonIahnNodeMessageTableInspectResult
    );

    WebInputIahnNodeMekListInspectInfo iahnNodeMekListInspectInfoToWebInput(
            IahnNodeMekListInspectInfo iahnNodeMekListInspectInfo
    );

    @InheritInverseConfiguration
    IahnNodeMekListInspectInfo iahnNodeMekListInspectInfoFromWebInput(
            WebInputIahnNodeMekListInspectInfo webInputIahnNodeMekListInspectInfo
    );

    WebInputIahnNodeMessageTableInspectInfo iahnNodeMessageTableInspectInfoToWebInput(
            IahnNodeMessageTableInspectInfo iahnNodeMessageTableInspectInfo
    );

    @InheritInverseConfiguration
    IahnNodeMessageTableInspectInfo iahnNodeMessageTableInspectInfoFromWebInput(
            WebInputIahnNodeMessageTableInspectInfo webInputIahnNodeMessageTableInspectInfo
    );
}
