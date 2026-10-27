package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.WarmupSetting;
import com.dwarfeng.settingrepo.stack.service.WarmupDriverInfoMaintainService;
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
public class WarmupDriverInfoMaintainServiceImplTest {

    @Autowired
    private WarmupDriverInfoMaintainService warmupDriverInfoMaintainService;
    @Autowired
    private WarmupSettingMaintainService warmupSettingMaintainService;

    private List<WarmupDriverInfo> warmupDriverInfos;
    private WarmupSetting warmupSetting;

    @Before
    public void setUp() {
        warmupDriverInfos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WarmupDriverInfo warmupDriverInfo = new WarmupDriverInfo(
                    null, null, true, "type", "param", "remark"
            );
            warmupDriverInfos.add(warmupDriverInfo);
        }
        warmupSetting = new WarmupSetting(
                null, 0, "target", "item", "zh", "CN", "rozaj", new String[]{"argument"}, true, "remark"
        );
    }

    @After
    public void tearDown() {
        warmupDriverInfos.clear();
        warmupSetting = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            warmupSetting.setKey(warmupSettingMaintainService.insert(warmupSetting));
            for (WarmupDriverInfo warmupDriverInfo : warmupDriverInfos) {
                warmupDriverInfo.setWarmupSettingKey(warmupSetting.getKey());
                warmupDriverInfo.setKey(warmupDriverInfoMaintainService.insert(warmupDriverInfo));

                assertTrue(warmupDriverInfoMaintainService.exists(warmupDriverInfo.getKey()));
                WarmupDriverInfo testWarmupDriverInfo = warmupDriverInfoMaintainService.get(
                        warmupDriverInfo.getKey()
                );
                assertEquals(BeanUtils.describe(warmupDriverInfo), BeanUtils.describe(testWarmupDriverInfo));

                warmupDriverInfo.setRemark("test.remark.updated");
                warmupDriverInfoMaintainService.update(warmupDriverInfo);
                testWarmupDriverInfo = warmupDriverInfoMaintainService.get(warmupDriverInfo.getKey());
                assertEquals(BeanUtils.describe(warmupDriverInfo), BeanUtils.describe(testWarmupDriverInfo));

                warmupDriverInfoMaintainService.deleteIfExists(warmupDriverInfo.getKey());
                assertFalse(warmupDriverInfoMaintainService.exists(warmupDriverInfo.getKey()));
            }
        } finally {
            for (WarmupDriverInfo warmupDriverInfo : warmupDriverInfos) {
                if (Objects.isNull(warmupDriverInfo.getKey())) {
                    continue;
                }
                warmupDriverInfoMaintainService.deleteIfExists(warmupDriverInfo.getKey());
            }
            if (Objects.nonNull(warmupSetting.getKey())) {
                warmupSettingMaintainService.deleteIfExists(warmupSetting.getKey());
            }
        }
    }

    @Test
    public void testForWarmupSettingCascade() throws Exception {
        try {
            warmupSetting.setKey(warmupSettingMaintainService.insert(warmupSetting));
            for (WarmupDriverInfo warmupDriverInfo : warmupDriverInfos) {
                warmupDriverInfo.setWarmupSettingKey(warmupSetting.getKey());
                warmupDriverInfo.setKey(warmupDriverInfoMaintainService.insert(warmupDriverInfo));
            }

            assertEquals(
                    warmupDriverInfos.size(),
                    warmupDriverInfoMaintainService.lookupAsList(
                            WarmupDriverInfoMaintainService.CHILD_FOR_WARMUP_SETTING,
                            new Object[]{warmupSetting.getKey()}
                    ).size()
            );

            warmupSettingMaintainService.deleteIfExists(warmupSetting.getKey());

            assertEquals(
                    0,
                    warmupDriverInfoMaintainService.lookupAsList(
                            WarmupDriverInfoMaintainService.CHILD_FOR_WARMUP_SETTING,
                            new Object[]{warmupSetting.getKey()}
                    ).size()
            );

            for (WarmupDriverInfo warmupDriverInfo : warmupDriverInfos) {
                assertFalse(warmupDriverInfoMaintainService.exists(warmupDriverInfo.getKey()));
            }
        } finally {
            for (WarmupDriverInfo warmupDriverInfo : warmupDriverInfos) {
                if (Objects.isNull(warmupDriverInfo.getKey())) {
                    continue;
                }
                warmupDriverInfoMaintainService.deleteIfExists(warmupDriverInfo.getKey());
            }
            if (Objects.nonNull(warmupSetting.getKey())) {
                warmupSettingMaintainService.deleteIfExists(warmupSetting.getKey());
            }
        }
    }
}
