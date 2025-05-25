package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNode;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeLocale;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.service.IahnNodeLocaleMaintainService;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMaintainService;
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
public class IahnNodeLocaleMaintainServiceImplTest {

    private static final String SETTING_NODE_ID = "test.iahn_node";
    private static final String LANGUAGE = "xx";
    private static final String COUNTRY = "xx";
    @SuppressWarnings("SpellCheckingInspection")
    private static final String VARIANT = "xxxxx";

    @Autowired
    private IahnNodeLocaleMaintainService iahnNodeLocaleMaintainService;
    @Autowired
    private IahnNodeMaintainService iahnNodeMaintainService;

    private IahnNodeLocale iahnNodeLocale;
    private IahnNode iahnNode;

    @Before
    public void setUp() {
        iahnNodeLocale = new IahnNodeLocale(
                new IahnNodeLocaleKey(SETTING_NODE_ID, LANGUAGE, COUNTRY, VARIANT), "label", "remark"
        );
        iahnNode = new IahnNode(new StringIdKey(SETTING_NODE_ID));
    }

    @After
    public void tearDown() {
        iahnNodeLocale = null;
        iahnNode = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            iahnNodeMaintainService.insertOrUpdate(iahnNode);

            iahnNodeLocaleMaintainService.insertOrUpdate(iahnNodeLocale);
            IahnNodeLocale testIahnNodeLocale = iahnNodeLocaleMaintainService.get(iahnNodeLocale.getKey());
            assertEquals(BeanUtils.describe(iahnNodeLocale), BeanUtils.describe(testIahnNodeLocale));
            iahnNodeLocaleMaintainService.update(iahnNodeLocale);
            testIahnNodeLocale = iahnNodeLocaleMaintainService.get(iahnNodeLocale.getKey());
            assertEquals(BeanUtils.describe(iahnNodeLocale), BeanUtils.describe(testIahnNodeLocale));
        } finally {
            if (Objects.nonNull(iahnNodeLocale.getKey())) {
                iahnNodeLocaleMaintainService.deleteIfExists(iahnNodeLocale.getKey());
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

            iahnNodeLocale.setKey(iahnNodeLocaleMaintainService.insertOrUpdate(iahnNodeLocale));

            assertEquals(
                    1,
                    iahnNodeLocaleMaintainService.lookupAsList(
                            IahnNodeLocaleMaintainService.CHILD_FOR_NODE, new Object[]{iahnNode.getKey()}
                    ).size()
            );

            iahnNodeMaintainService.deleteIfExists(iahnNode.getKey());

            assertEquals(
                    0,
                    iahnNodeLocaleMaintainService.lookupAsList(
                            IahnNodeLocaleMaintainService.CHILD_FOR_NODE, new Object[]{iahnNode.getKey()}
                    ).size()
            );

            assertFalse(iahnNodeLocaleMaintainService.exists(iahnNodeLocale.getKey()));
        } finally {
            if (Objects.nonNull(iahnNodeLocale.getKey())) {
                iahnNodeLocaleMaintainService.deleteIfExists(iahnNodeLocale.getKey());
            }

            if (Objects.nonNull(iahnNode.getKey())) {
                iahnNodeMaintainService.deleteIfExists(iahnNode.getKey());
            }
        }
    }
}
