package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.service.SettingCategoryMaintainService;
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
public class SettingCategoryMaintainServiceImplTest {

    @Autowired
    private SettingCategoryMaintainService settingCategoryMaintainService;

    private List<SettingCategory> settingCategories;

    @Before
    public void setUp() {
        settingCategories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SettingCategory settingCategory = new SettingCategory(
                    new StringIdKey("test.setting_category." + i), "formatterType", "formatterParam", "remark"
            );
            settingCategories.add(settingCategory);
        }
    }

    @After
    public void tearDown() {
        settingCategories.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (SettingCategory settingCategory : settingCategories) {
                settingCategoryMaintainService.insertOrUpdate(settingCategory);
                SettingCategory testSettingCategory = settingCategoryMaintainService.get(settingCategory.getKey());
                assertEquals(BeanUtils.describe(settingCategory), BeanUtils.describe(testSettingCategory));
                settingCategoryMaintainService.update(settingCategory);
                testSettingCategory = settingCategoryMaintainService.get(settingCategory.getKey());
                assertEquals(BeanUtils.describe(settingCategory), BeanUtils.describe(testSettingCategory));
            }
        } finally {
            for (SettingCategory settingCategory : settingCategories) {
                settingCategoryMaintainService.deleteIfExists(settingCategory.getKey());
            }
        }
    }
}
