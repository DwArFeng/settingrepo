package com.dwarfeng.settingrepo.sdk.util;

import org.apache.commons.lang3.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 国际化节点国家字段有效性验证注解。
 *
 * @author DwArFeng
 * @since 2.3.3
 */
@Documented
@Constraint(
        validatedBy = {ValidIahnNodeCountry.InternalConstraintValidator.class}
)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIahnNodeCountry {

    String message() default "invalid iahn country";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class InternalConstraintValidator implements ConstraintValidator<ValidIahnNodeCountry, String> {

        // 执行校验操作
        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            try {
                // 如果 value 为空，则返回 true。
                if (StringUtils.isEmpty(value)) {
                    return true;
                }
                // 否则，按照 RFC 5646 的规范进行校验：
                //   1. 最小长度为 Constraints.LENGTH_IAHN_NODE_COUNTRY_MIN。
                //   2. 最大长度为 Constraints.LENGTH_IAHN_NODE_COUNTRY_MAX。
                //   3. 必须是 2 位字母或 3 位数字。
                return value.length() >= Constraints.LENGTH_IAHN_NODE_COUNTRY_MIN &&
                        value.length() <= Constraints.LENGTH_IAHN_NODE_COUNTRY_MAX &&
                        (value.matches("[a-zA-Z]{2}") || value.matches("\\d{3}"));
            } catch (Exception e) {
                return false;
            }
        }
    }
}
