package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport;
import com.dwarfeng.settingrepo.stack.service.FormatterSupportMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class FormatterSupportMaintainServiceImplTest {

    @Autowired
    private FormatterSupportMaintainService formatterSupportMaintainService;

    private List<FormatterSupport> formatterSupports;

    @Before
    public void setUp() {
        formatterSupports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FormatterSupport formatterSupport = new FormatterSupport(
                    new StringIdKey("test.formatter_support." + i), "label", "description", "exampleParam"
            );
            formatterSupports.add(formatterSupport);
        }
    }

    @After
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
                formatterSupportMaintainService.deleteIfExists(formatterSupport.getKey());
            }
        }
    }
}
