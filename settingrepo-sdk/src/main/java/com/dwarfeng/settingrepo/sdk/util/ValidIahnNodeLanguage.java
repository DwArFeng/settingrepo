package com.dwarfeng.settingrepo.sdk.util;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 国际化节点语言字段有效性验证注解。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
@Documented
@Constraint(
        validatedBy = {ValidIahnNodeLanguage.InternalConstraintValidator.class}
)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIahnNodeLanguage {

    String message() default "invalid iahn language";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class InternalConstraintValidator implements ConstraintValidator<ValidIahnNodeLanguage, String> {

        // 执行校验操作
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            try {
                // 如果 value 为空，则返回 true。
                if (StringUtils.isEmpty(value)) {
                    return true;
                }
                // 否则，按照 RFC 5646 的规范进行校验：
                //   1. 最小长度为 Constraints.LENGTH_IAHN_NODE_LANGUAGE_MIN。
                //   2. 最大长度为 Constraints.LENGTH_IAHN_NODE_LANGUAGE_MAX。
                //   3. 必须是字母。
                return value.length() >= Constraints.LENGTH_IAHN_NODE_LANGUAGE_MIN &&
                        value.length() <= Constraints.LENGTH_IAHN_NODE_LANGUAGE_MAX &&
                        value.matches("[a-zA-Z]+");
            } catch (Exception e) {
                return false;
            }
        }
    }
}
