package com.dwarfeng.settingrepo.sdk.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置节点类型条目。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SettingNodeTypeItem {
}
