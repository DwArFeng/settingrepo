package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.settingrepo.stack.service.FormatterSupportMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:com/dwarfeng/settingrepo/impl/spring/application-context*.xml")
public class FormatterSupportMaintainServiceImplTest {

    @Autowired
    private FormatterSupportMaintainService formatterSupportMaintainService;

    private List<FormatterSupport> formatterSupports;

    @BeforeEach
    public void setUp() {
        formatterSupports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FormatterSupport formatterSupport = new FormatterSupport(
                    new StringIdKey("test.formatter_support." + i), "label", "description", "exampleParam"
            );
            formatterSupports.add(formatterSupport);
        }
    }

    @AfterEach
    public void tearDown() {
        formatterSupports.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (FormatterSupport formatterSupport : formatterSupports) {
                formatterSupportMaintainService.insertOrUpdate(formatterSupport);
                FormatterSupport testFormatterSupport = formatterSupportMaintainService.get(formatterSupport.getKey());
                assertEquals(BeanUtils.describe(formatterSupport), BeanUtils.describe(testFormatterSupport));
                formatterSupportMaintainService.update(formatterSupport);
                testFormatterSupport = formatterSupportMaintainService.get(formatterSupport.getKey());
                assertEquals(BeanUtils.describe(formatterSupport), BeanUtils.describe(testFormatterSupport));
            }
        } finally {
            for (FormatterSupport formatterSupport : formatterSupports) {
                if (Objects.isNull(formatterSupport.getKey())) {
                    continue;
                }
                formatterSupportMaintainService.deleteIfExists(formatterSupport.getKey());
            }
        }
    }
}
