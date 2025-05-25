package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNode;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeLocale;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMek;
import com.dwarfeng.settingrepo.stack.bean.entity.IahnNodeMessage;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeLocaleKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMekKey;
import com.dwarfeng.settingrepo.stack.bean.key.IahnNodeMessageKey;
import com.dwarfeng.settingrepo.stack.service.IahnNodeLocaleMaintainService;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMekMaintainService;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMessageMaintainService;
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
public class IahnNodeMessageMaintainServiceImplTest {

    private static final String SETTING_NODE_ID = "test.iahn_node";
    private static final String LANGUAGE = "xx";
    private static final String COUNTRY = "xx";
    @SuppressWarnings("SpellCheckingInspection")
    private static final String VARIANT = "xxxxx";
    private static final String MEK_ID = "test.mek";

    @Autowired
    private IahnNodeMessageMaintainService iahnNodeMessageMaintainService;
    @Autowired
    private IahnNodeMaintainService iahnNodeMaintainService;
    @Autowired
    private IahnNodeLocaleMaintainService iahnNodeLocaleMaintainService;
    @Autowired
    private IahnNodeMekMaintainService iahnNodeMekMaintainService;

    private IahnNodeMessage iahnNodeMessage;
    private IahnNode iahnNode;
    private IahnNodeLocale iahnNodeLocale;
    private IahnNodeMek iahnNodeMek;

    @Before
    public void setUp() {
        iahnNodeMessage = new IahnNodeMessage(
                new IahnNodeMessageKey(SETTING_NODE_ID, LANGUAGE, COUNTRY, VARIANT, MEK_ID), "message"
        );
        iahnNode = new IahnNode(new StringIdKey(SETTING_NODE_ID));
        iahnNodeLocale = new IahnNodeLocale(
                new IahnNodeLocaleKey(SETTING_NODE_ID, LANGUAGE, COUNTRY, VARIANT), "label", "remark"
        );
        iahnNodeMek = new IahnNodeMek(
                new IahnNodeMekKey(SETTING_NODE_ID, MEK_ID), "label", "defaultMessage", "remark"
        );
    }

    @After
    public void tearDown() {
        iahnNodeMessage = null;
        iahnNode = null;
        iahnNodeLocale = null;
        iahnNodeMek = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            iahnNodeMaintainService.insertOrUpdate(iahnNode);
            iahnNodeLocaleMaintainService.insertOrUpdate(iahnNodeLocale);
            iahnNodeMekMaintainService.insertOrUpdate(iahnNodeMek);

            iahnNodeMessageMaintainService.insertOrUpdate(iahnNodeMessage);
            IahnNodeMessage testIahnNodeMessage = iahnNodeMessageMaintainService.get(iahnNodeMessage.getKey());
            assertEquals(BeanUtils.describe(iahnNodeMessage), BeanUtils.describe(testIahnNodeMessage));
            iahnNodeMessageMaintainService.update(iahnNodeMessage);
            testIahnNodeMessage = iahnNodeMessageMaintainService.get(iahnNodeMessage.getKey());
            assertEquals(BeanUtils.describe(iahnNodeMessage), BeanUtils.describe(testIahnNodeMessage));
        } finally {
            if (Objects.nonNull(iahnNodeMessage.getKey())) {
                iahnNodeMessageMaintainService.deleteIfExists(iahnNodeMessage.getKey());
            }

            if (Objects.nonNull(iahnNodeMek.getKey())) {
                iahnNodeMekMaintainService.deleteIfExists(iahnNodeMek.getKey());
            }
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
            iahnNodeMaintainService.insertOrUpdate(iahnNode);
            iahnNodeLocaleMaintainService.insertOrUpdate(iahnNodeLocale);
            iahnNodeMekMaintainService.insertOrUpdate(iahnNodeMek);

            iahnNodeMessage.setKey(iahnNodeMessageMaintainService.insertOrUpdate(iahnNodeMessage));

            assertEquals(
                    1,
                    iahnNodeMessageMaintainService.lookupAsList(
                            IahnNodeMessageMaintainService.CHILD_FOR_NODE, new Object[]{iahnNode.getKey()}
                    ).size()
            );

            iahnNodeMaintainService.deleteIfExists(iahnNode.getKey());

            assertEquals(
                    0,
                    iahnNodeMessageMaintainService.lookupAsList(
                            IahnNodeMessageMaintainService.CHILD_FOR_NODE, new Object[]{iahnNode.getKey()}
                    ).size()
            );

            assertFalse(iahnNodeMessageMaintainService.exists(iahnNodeMessage.getKey()));
        } finally {
            if (Objects.nonNull(iahnNodeMessage.getKey())) {
                iahnNodeMessageMaintainService.deleteIfExists(iahnNodeMessage.getKey());
            }

            if (Objects.nonNull(iahnNodeMek.getKey())) {
                iahnNodeMekMaintainService.deleteIfExists(iahnNodeMek.getKey());
            }
            if (Objects.nonNull(iahnNodeLocale.getKey())) {
                iahnNodeLocaleMaintainService.deleteIfExists(iahnNodeLocale.getKey());
            }
            if (Objects.nonNull(iahnNode.getKey())) {
                iahnNodeMaintainService.deleteIfExists(iahnNode.getKey());
            }
        }
    }

    @Test
    public void testForIahnNodeLocaleCascade() throws Exception {
        try {
            iahnNodeMaintainService.insertOrUpdate(iahnNode);
            iahnNodeLocaleMaintainService.insertOrUpdate(iahnNodeLocale);
            iahnNodeMekMaintainService.insertOrUpdate(iahnNodeMek);

            iahnNodeMessage.setKey(iahnNodeMessageMaintainService.insertOrUpdate(iahnNodeMessage));

            assertEquals(
                    1,
                    iahnNodeMessageMaintainService.lookupAsList(
                            IahnNodeMessageMaintainService.CHILD_FOR_LOCALE, new Object[]{iahnNodeLocale.getKey()}
                    ).size()
            );

            iahnNodeLocaleMaintainService.deleteIfExists(iahnNodeLocale.getKey());

            assertEquals(
                    0,
                    iahnNodeMessageMaintainService.lookupAsList(
                            IahnNodeMessageMaintainService.CHILD_FOR_LOCALE, new Object[]{iahnNodeLocale.getKey()}
                    ).size()
            );

            assertFalse(iahnNodeMessageMaintainService.exists(iahnNodeMessage.getKey()));
        } finally {
            if (Objects.nonNull(iahnNodeMessage.getKey())) {
                iahnNodeMessageMaintainService.deleteIfExists(iahnNodeMessage.getKey());
            }

            if (Objects.nonNull(iahnNodeMek.getKey())) {
                iahnNodeMekMaintainService.deleteIfExists(iahnNodeMek.getKey());
            }
            if (Objects.nonNull(iahnNodeLocale.getKey())) {
                iahnNodeLocaleMaintainService.deleteIfExists(iahnNodeLocale.getKey());
            }
            if (Objects.nonNull(iahnNode.getKey())) {
                iahnNodeMaintainService.deleteIfExists(iahnNode.getKey());
            }
        }
    }

    @Test
    public void testForIahnNodeMekCascade() throws Exception {
        try {
            iahnNodeMaintainService.insertOrUpdate(iahnNode);
            iahnNodeLocaleMaintainService.insertOrUpdate(iahnNodeLocale);
            iahnNodeMekMaintainService.insertOrUpdate(iahnNodeMek);

            iahnNodeMessage.setKey(iahnNodeMessageMaintainService.insertOrUpdate(iahnNodeMessage));

            assertEquals(
                    1,
                    iahnNodeMessageMaintainService.lookupAsList(
                            IahnNodeMessageMaintainService.CHILD_FOR_MEK, new Object[]{iahnNodeMek.getKey()}
                    ).size()
            );

            iahnNodeMekMaintainService.deleteIfExists(iahnNodeMek.getKey());

            assertEquals(
                    0,
                    iahnNodeMessageMaintainService.lookupAsList(
                            IahnNodeMessageMaintainService.CHILD_FOR_MEK, new Object[]{iahnNodeMek.getKey()}
                    ).size()
            );

            assertFalse(iahnNodeMessageMaintainService.exists(iahnNodeMessage.getKey()));
        } finally {
            if (Objects.nonNull(iahnNodeMessage.getKey())) {
                iahnNodeMessageMaintainService.deleteIfExists(iahnNodeMessage.getKey());
            }

            if (Objects.nonNull(iahnNodeMek.getKey())) {
                iahnNodeMekMaintainService.deleteIfExists(iahnNodeMek.getKey());
            }
            if (Objects.nonNull(iahnNodeLocale.getKey())) {
                iahnNodeLocaleMaintainService.deleteIfExists(iahnNodeLocale.getKey());
            }
            if (Objects.nonNull(iahnNode.getKey())) {
                iahnNodeMaintainService.deleteIfExists(iahnNode.getKey());
            }
        }
    }
}
