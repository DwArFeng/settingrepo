package com.dwarfeng.settingrepo.impl.bean.entity;

import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
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
}
