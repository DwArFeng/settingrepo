package com.dwarfeng.settingrepo.sdk.util;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 预热缓存类型字段有效性验证注解。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@Documented
@Constraint(
        validatedBy = {ValidWarmupCacheType.InternalConstraintValidator.class}
)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidWarmupCacheType {

    String message() default "invalid warmup cache type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class InternalConstraintValidator implements ConstraintValidator<ValidWarmupCacheType, Integer> {

        // 执行校验操作
        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            try {
                return Constants.warmupCacheTypeSpace().contains(value);
            } catch (Exception e) {
                return false;
            }
        }
    }
}
