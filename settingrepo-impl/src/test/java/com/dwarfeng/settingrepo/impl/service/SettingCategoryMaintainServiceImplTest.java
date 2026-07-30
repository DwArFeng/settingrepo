package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.service.SettingCategoryMaintainService;
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
public class SettingCategoryMaintainServiceImplTest {

    @Autowired
    private SettingCategoryMaintainService settingCategoryMaintainService;

    private List<SettingCategory> settingCategories;

    @BeforeEach
    public void setUp() {
        settingCategories = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SettingCategory settingCategory = new SettingCategory(
                    new StringIdKey("test.setting_category." + i), "formatterType", "formatterParam", "remark"
            );
            settingCategories.add(settingCategory);
        }
    }

    @AfterEach
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
                if (Objects.isNull(settingCategory.getKey())) {
                    continue;
                }
                settingCategoryMaintainService.deleteIfExists(settingCategory.getKey());
            }
        }
    }
}
