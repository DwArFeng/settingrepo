package com.dwarfeng.settingrepo.sdk.exception;

import com.dwarfeng.settingrepo.base.sdk.i18n.MessageContext;
import com.dwarfeng.subgrade.basic.stack.exception.ServiceException;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * 服务异常代码供应器测试。
 *
 * @author DwArFeng
 * @since 4.0.0
 */
public class ServiceExceptionCodeSuppliersTest {

    @Test
    public void shouldResolveLocaleAndCreateIndependentCodeSnapshots() {
        int originalOffset = ServiceExceptionCodeSuppliers.getExceptionCodeOffset();
        try {
            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(5500);

            ServiceException.Code englishCode = MessageContext.call(
                    Locale.ENGLISH, ServiceExceptionCodeSuppliers.FORMATTER_FAILED::get
            );
            ServiceException.Code chineseCode = MessageContext.call(
                    Locale.SIMPLIFIED_CHINESE, ServiceExceptionCodeSuppliers.FORMATTER_FAILED::get
            );

            assertEquals(5500, englishCode.getCode());
            assertEquals("formatter failed", englishCode.getTip());
            assertEquals(5500, chineseCode.getCode());
            assertEquals("格式化器失败", chineseCode.getTip());
            assertNotSame(englishCode, chineseCode);

            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(6500);
            ServiceException.Code updatedCode = ServiceExceptionCodeSuppliers.FORMATTER_FAILED.get();

            assertEquals(5500, englishCode.getCode());
            assertEquals(6500, updatedCode.getCode());
            assertNotSame(englishCode, updatedCode);
        } finally {
            ServiceExceptionCodeSuppliers.setExceptionCodeOffset(originalOffset);
        }
    }
}
