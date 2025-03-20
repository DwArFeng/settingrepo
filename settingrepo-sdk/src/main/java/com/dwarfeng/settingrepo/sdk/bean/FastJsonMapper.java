package com.dwarfeng.settingrepo.sdk.bean;

import com.dwarfeng.settingrepo.sdk.bean.entity.*;
import com.dwarfeng.settingrepo.stack.bean.entity.*;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Mapper
public interface FastJsonMapper {

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonFormatterSupport formatterSupportToFastJson(FormatterSupport formatterSupport);

    @InheritInverseConfiguration
    FormatterSupport formatterSupportFromFastJson(FastJsonFormatterSupport fastJsonFormatterSupport);

    FastJsonSettingCategory settingCategoryToFastJson(SettingCategory settingCategory);

    @InheritInverseConfiguration
    SettingCategory settingCategoryFromFastJson(FastJsonSettingCategory fastJsonSettingCategory);

    FastJsonSettingNode settingNodeToFastJson(SettingNode settingNode);

    @InheritInverseConfiguration
    SettingNode settingNodeFromFastJson(FastJsonSettingNode fastJsonSettingNode);

    FastJsonTextNode textNodeToFastJson(TextNode textNode);

    @InheritInverseConfiguration
    TextNode textNodeFromFastJson(FastJsonTextNode fastJsonTextNode);

    FastJsonImageNode imageNodeToFastJson(ImageNode imageNode);

    @InheritInverseConfiguration
    ImageNode imageNodeFromFastJson(FastJsonImageNode fastJsonImageNode);

    FastJsonImageListNode imageListNodeToFastJson(ImageListNode imageListNode);

    @InheritInverseConfiguration
    ImageListNode imageListNodeFromFastJson(FastJsonImageListNode fastJsonImageListNode);

    FastJsonImageListNodeItem imageListNodeItemToFastJson(ImageListNodeItem imageListNodeItem);

    @InheritInverseConfiguration
    ImageListNodeItem imageListNodeItemFromFastJson(FastJsonImageListNodeItem fastJsonImageListNodeItem);

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

    FastJsonLongTextNode longTextNodeToFastJson(LongTextNode longTextNode);

    @InheritInverseConfiguration
    LongTextNode longTextNodeFromFastJson(FastJsonLongTextNode fastJsonLongTextNode);
}
