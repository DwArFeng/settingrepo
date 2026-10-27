package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.settingrepo.stack.service.WarmupSettingMaintainService;
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
import java.util.Objects;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class WarmupSettingMaintainServiceImplTest {

    @Autowired
    private WarmupSettingMaintainService warmupSettingMaintainService;

    private List<WarmupSetting> warmupSettings;

    @Before
    public void setUp() {
        warmupSettings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WarmupSetting warmupSetting = new WarmupSetting(
                    null, 0, "target", "item", "zh", "CN", "rozaj", new String[]{"argument"}, true, "remark"
            );
            warmupSettings.add(warmupSetting);
        }
    }

    @After
    public void tearDown() {
        warmupSettings.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (WarmupSetting warmupSetting : warmupSettings) {
                warmupSetting.setKey(warmupSettingMaintainService.insert(warmupSetting));

                assertTrue(warmupSettingMaintainService.exists(warmupSetting.getKey()));
                WarmupSetting testWarmupSetting = warmupSettingMaintainService.get(warmupSetting.getKey());
                assertEquals(BeanUtils.describe(warmupSetting), BeanUtils.describe(testWarmupSetting));

                warmupSetting.setRemark("test.remark.updated");
                warmupSettingMaintainService.update(warmupSetting);
                testWarmupSetting = warmupSettingMaintainService.get(warmupSetting.getKey());
                assertEquals(BeanUtils.describe(warmupSetting), BeanUtils.describe(testWarmupSetting));

                warmupSettingMaintainService.deleteIfExists(warmupSetting.getKey());
                assertFalse(warmupSettingMaintainService.exists(warmupSetting.getKey()));
            }
        } finally {
            for (WarmupSetting warmupSetting : warmupSettings) {
                if (Objects.isNull(warmupSetting.getKey())) {
                    continue;
                }
                warmupSettingMaintainService.deleteIfExists(warmupSetting.getKey());
            }
        }
    }
}
