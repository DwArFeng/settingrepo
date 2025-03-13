package com.dwarfeng.settingrepo.impl.bean;

import com.dwarfeng.settingrepo.impl.bean.entity.*;
import com.dwarfeng.settingrepo.impl.bean.key.HibernateIahnNodeLocaleKey;
import com.dwarfeng.settingrepo.impl.bean.key.HibernateIahnNodeMekKey;
import com.dwarfeng.settingrepo.impl.bean.key.HibernateIahnNodeMessageKey;
import com.dwarfeng.settingrepo.stack.bean.entity.*;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Hibernate Bean 映射器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Mapper
public interface HibernateMapper {

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateIahnNodeLocaleKey localeKeyToHibernate(IahnNodeLocaleKey iahnNodeLocaleKey);

    @InheritInverseConfiguration
    IahnNodeLocaleKey localeKeyFromHibernate(HibernateIahnNodeLocaleKey hibernateIahnNodeLocaleKey);

    HibernateIahnNodeMekKey mekKeyToHibernate(IahnNodeMekKey iahnNodeMekKey);

    @InheritInverseConfiguration
    IahnNodeMekKey mekKeyFromHibernate(HibernateIahnNodeMekKey hibernateIahnNodeMekKey);

    HibernateIahnNodeMessageKey messageKeyToHibernate(IahnNodeMessageKey iahnNodeMessageKey);

    @InheritInverseConfiguration
    IahnNodeMessageKey messageKeyFromHibernate(HibernateIahnNodeMessageKey hibernateIahnNodeMessageKey);

    @Mapping(target = "stringId", ignore = true)
    HibernateFormatterSupport formatterSupportToHibernate(FormatterSupport formatterSupport);

    @InheritInverseConfiguration
    FormatterSupport formatterSupportFromHibernate(HibernateFormatterSupport hibernateFormatterSupport);

    @Mapping(target = "stringId", ignore = true)
    HibernateSettingCategory settingCategoryToHibernate(SettingCategory settingCategory);

    @InheritInverseConfiguration
    SettingCategory settingCategoryFromHibernate(HibernateSettingCategory hibernateSettingCategory);

    @Mapping(target = "stringId", ignore = true)
    HibernateSettingNode settingNodeToHibernate(SettingNode settingNode);

    @InheritInverseConfiguration
    SettingNode settingNodeFromHibernate(HibernateSettingNode hibernateSettingNode);

    @Mapping(target = "stringId", ignore = true)
    HibernateTextNode textNodeToHibernate(TextNode textNode);

    @InheritInverseConfiguration
    TextNode textNodeFromHibernate(HibernateTextNode hibernateTextNode);

    @Mapping(target = "stringId", ignore = true)
    HibernateImageNode imageNodeToHibernate(ImageNode imageNode);

    @InheritInverseConfiguration
    ImageNode imageNodeFromHibernate(HibernateImageNode hibernateImageNode);

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "stringId", ignore = true)
    HibernateImageListNode imageListNodeToHibernate(ImageListNode imageListNode);

    @InheritInverseConfiguration
    ImageListNode imageListNodeFromHibernate(HibernateImageListNode hibernateImageListNode);

    @Mapping(target = "nodeStringId", ignore = true)
    @Mapping(target = "node", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateImageListNodeItem imageListNodeItemToHibernate(ImageListNodeItem imageListNodeItem);

    @InheritInverseConfiguration
    ImageListNodeItem imageListNodeItemFromHibernate(HibernateImageListNodeItem hibernateImageListNodeItem);

    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "meks", ignore = true)
    @Mapping(target = "locales", ignore = true)
    HibernateIahnNode iahnNodeToHibernate(IahnNode iahnNode);

    @InheritInverseConfiguration
    IahnNode iahnNodeFromHibernate(HibernateIahnNode hibernateIahnNode);

    @Mapping(target = "variant", ignore = true)
    @Mapping(target = "nodeStringId", ignore = true)
    @Mapping(target = "node", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "country", ignore = true)
    HibernateIahnNodeLocale iahnNodeLocaleToHibernate(IahnNodeLocale iahnNodeLocale);

    @InheritInverseConfiguration
    IahnNodeLocale iahnNodeLocaleFromHibernate(HibernateIahnNodeLocale hibernateIahnNodeLocale);

    @Mapping(target = "nodeStringId", ignore = true)
    @Mapping(target = "node", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "mekId", ignore = true)
    HibernateIahnNodeMek iahnNodeMekToHibernate(IahnNodeMek iahnNodeMek);

    @InheritInverseConfiguration
    IahnNodeMek iahnNodeMekFromHibernate(HibernateIahnNodeMek hibernateIahnNodeMek);

    @Mapping(target = "variant", ignore = true)
    @Mapping(target = "nodeStringId", ignore = true)
    @Mapping(target = "node", ignore = true)
    @Mapping(target = "mekId", ignore = true)
    @Mapping(target = "mek", ignore = true)
    @Mapping(target = "locale", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "country", ignore = true)
    HibernateIahnNodeMessage iahnNodeMessageToHibernate(IahnNodeMessage iahnNodeMessage);

    @InheritInverseConfiguration
    IahnNodeMessage iahnNodeMessageFromHibernate(HibernateIahnNodeMessage hibernateIahnNodeMessage);
}
