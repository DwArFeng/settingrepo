package com.dwarfeng.settingrepo.sdk.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 预热缓存类型条目。
 *
 * <p>
 * 该注解用于标记 {@link Constants} 中的预热缓存类型常量，使类型空间能够通过反射统一枚举。
 * 预热缓存类型与设置节点类型是两套相互独立的枚举空间，二者不得互相代替。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WarmupCacheTypeItem {
}
