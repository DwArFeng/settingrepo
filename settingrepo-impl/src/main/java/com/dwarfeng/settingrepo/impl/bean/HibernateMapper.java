package com.dwarfeng.settingrepo.impl.bean;

import com.dwarfeng.settingrepo.impl.bean.entity.HibernateFormatterSupport;
import com.dwarfeng.settingrepo.impl.bean.entity.HibernateSettingCategory;
import com.dwarfeng.settingrepo.impl.bean.entity.HibernateSettingNode;
import com.dwarfeng.settingrepo.impl.bean.entity.HibernateTextNode;
import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
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
}
