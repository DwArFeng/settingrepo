package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.WarmupDriverSupport;
import com.dwarfeng.settingrepo.stack.service.WarmupDriverSupportMaintainService;
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
import java.util.Objects;

import static org.junit.Assert.assertEquals;

/**
 * 预热驱动器支持维护服务测试。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class WarmupDriverSupportMaintainServiceImplTest {

    @Autowired
    private WarmupDriverSupportMaintainService warmupDriverSupportMaintainService;

    private List<WarmupDriverSupport> warmupDriverSupports;

    @Before
    public void setUp() {
        warmupDriverSupports = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            WarmupDriverSupport warmupDriverSupport = new WarmupDriverSupport(
                    new StringIdKey("test.warmup_driver_support." + i), "label", "description", "exampleParam"
            );
            warmupDriverSupports.add(warmupDriverSupport);
        }
    }

    @After
    public void tearDown() {
        warmupDriverSupports.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (WarmupDriverSupport warmupDriverSupport : warmupDriverSupports) {
                warmupDriverSupportMaintainService.insertOrUpdate(warmupDriverSupport);
                WarmupDriverSupport testWarmupDriverSupport =
                        warmupDriverSupportMaintainService.get(warmupDriverSupport.getKey());
                assertEquals(
                        BeanUtils.describe(warmupDriverSupport), BeanUtils.describe(testWarmupDriverSupport)
                );
                warmupDriverSupport.setDescription("updated");
                warmupDriverSupportMaintainService.update(warmupDriverSupport);
                testWarmupDriverSupport = warmupDriverSupportMaintainService.get(warmupDriverSupport.getKey());
                assertEquals(
                        BeanUtils.describe(warmupDriverSupport), BeanUtils.describe(testWarmupDriverSupport)
                );
            }
        } finally {
            for (WarmupDriverSupport warmupDriverSupport : warmupDriverSupports) {
                if (Objects.isNull(warmupDriverSupport.getKey())) {
                    continue;
                }
                warmupDriverSupportMaintainService.deleteIfExists(warmupDriverSupport.getKey());
            }
        }
    }
}
