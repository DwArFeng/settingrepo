package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNode;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMek;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMekMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class IahnNodeMekMaintainServiceImplTest {

    private static final String SETTING_NODE_ID = "test.iahn_node";
    private static final String MEK_ID = "test.mek";

    @Autowired
    private IahnNodeMekMaintainService iahnNodeMekMaintainService;
    @Autowired
    private IahnNodeMaintainService iahnNodeMaintainService;

    private IahnNodeMek iahnNodeMek;
    private IahnNode iahnNode;

    @Before
    public void setUp() {
        iahnNodeMek = new IahnNodeMek(
                new IahnNodeMekKey(SETTING_NODE_ID, MEK_ID), "label", "defaultMessage", "remark"
        );
        iahnNode = new IahnNode(new StringIdKey(SETTING_NODE_ID));
    }

    @After
    public void tearDown() {
        iahnNodeMek = null;
        iahnNode = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            iahnNodeMaintainService.insertOrUpdate(iahnNode);

            iahnNodeMekMaintainService.insertOrUpdate(iahnNodeMek);
            IahnNodeMek testIahnNodeMek = iahnNodeMekMaintainService.get(iahnNodeMek.getKey());
            assertEquals(BeanUtils.describe(iahnNodeMek), BeanUtils.describe(testIahnNodeMek));
            iahnNodeMekMaintainService.update(iahnNodeMek);
            testIahnNodeMek = iahnNodeMekMaintainService.get(iahnNodeMek.getKey());
            assertEquals(BeanUtils.describe(iahnNodeMek), BeanUtils.describe(testIahnNodeMek));
        } finally {
            if (Objects.nonNull(iahnNodeMek.getKey())) {
                iahnNodeMekMaintainService.deleteIfExists(iahnNodeMek.getKey());
            }

            if (Objects.nonNull(iahnNode.getKey())) {
                iahnNodeMaintainService.deleteIfExists(iahnNode.getKey());
            }
        }
    }

    @Test
    public void testForIahnNodeCascade() throws Exception {
        try {
            iahnNode.setKey(iahnNodeMaintainService.insertOrUpdate(iahnNode));

            iahnNodeMek.setKey(iahnNodeMekMaintainService.insertOrUpdate(iahnNodeMek));

            assertEquals(
                    1,
                    iahnNodeMekMaintainService.lookupAsList(
                            IahnNodeMekMaintainService.CHILD_FOR_NODE, new Object[]{iahnNode.getKey()}
                    ).size()
            );

            iahnNodeMaintainService.deleteIfExists(iahnNode.getKey());

            assertEquals(
                    0,
                    iahnNodeMekMaintainService.lookupAsList(
                            IahnNodeMekMaintainService.CHILD_FOR_NODE, new Object[]{iahnNode.getKey()}
                    ).size()
            );

            assertFalse(iahnNodeMekMaintainService.exists(iahnNodeMek.getKey()));
        } finally {
            if (Objects.nonNull(iahnNodeMek.getKey())) {
                iahnNodeMekMaintainService.deleteIfExists(iahnNodeMek.getKey());
            }

            if (Objects.nonNull(iahnNode.getKey())) {
                iahnNodeMaintainService.deleteIfExists(iahnNode.getKey());
            }
        }
    }
}
