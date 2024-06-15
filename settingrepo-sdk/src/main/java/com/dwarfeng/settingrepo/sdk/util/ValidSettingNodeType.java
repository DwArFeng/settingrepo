package com.dwarfeng.settingrepo.sdk.util;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 设置节点类型字段有效性验证注解。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Documented
@Constraint(
        validatedBy = {ValidSettingNodeType.InternalConstraintValidator.class}
)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSettingNodeType {

    String message() default "invalid data type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class InternalConstraintValidator implements ConstraintValidator<ValidSettingNodeType, Integer> {

        // 执行校验操作
        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            try {
                return Constants.settingNodeTypeSpace().contains(value);
            } catch (Exception e) {
                return false;
            }
        }
    }
}
