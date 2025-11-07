package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNode;
import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNodeItem;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeMaintainService;
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
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class NavigationNodeItemMaintainServiceImplTest {

    @Autowired
    private NavigationNodeItemMaintainService navigationNodeItemMaintainService;
    @Autowired
    private NavigationNodeMaintainService navigationNodeMaintainService;

    private List<NavigationNodeItem> navigationNodeItems;
    private NavigationNode navigationNode;
    private NavigationNodeItem parent;

    @Before
    public void setUp() {
        navigationNodeItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NavigationNodeItem navigationNodeItem = new NavigationNodeItem(
                    null, null, null, 0, "content", "name", "remark"
            );
            navigationNodeItems.add(navigationNodeItem);
        }
        navigationNode = new NavigationNode(new StringIdKey("test.navigation_id"), 0);
        parent = new NavigationNodeItem(null, navigationNode.getKey(), null, 0, "content", "name", "remark");
    }

    @After
    public void tearDown() {
        navigationNodeItems.clear();
        navigationNode = null;
        parent = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            navigationNodeMaintainService.insertOrUpdate(navigationNode);
            for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
                navigationNodeItem.setNodeKey(navigationNode.getKey());
                navigationNodeItem.setKey(navigationNodeItemMaintainService.insert(navigationNodeItem));

                NavigationNodeItem testNavigationNodeItem = navigationNodeItemMaintainService.get(
                        navigationNodeItem.getKey()
                );
                assertEquals(BeanUtils.describe(navigationNodeItem), BeanUtils.describe(testNavigationNodeItem));
                navigationNodeItemMaintainService.update(navigationNodeItem);
                testNavigationNodeItem = navigationNodeItemMaintainService.get(navigationNodeItem.getKey());
                assertEquals(BeanUtils.describe(navigationNodeItem), BeanUtils.describe(testNavigationNodeItem));
            }
        } finally {
            for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
                if (Objects.isNull(navigationNodeItem.getKey())) {
                    continue;
                }
                navigationNodeItemMaintainService.deleteIfExists(navigationNodeItem.getKey());
            }
            if (Objects.nonNull(navigationNode.getKey())) {
                navigationNodeMaintainService.deleteIfExists(navigationNode.getKey());
            }
        }
    }

    @Test
    public void testForNodeCascade() throws Exception {
        try {
            navigationNodeMaintainService.insertOrUpdate(navigationNode);
            for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
                navigationNodeItem.setNodeKey(navigationNode.getKey());
                navigationNodeItem.setKey(navigationNodeItemMaintainService.insert(navigationNodeItem));
            }

            assertEquals(
                    navigationNodeItems.size(),
                    navigationNodeItemMaintainService.lookupAsList(
                            NavigationNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{navigationNode.getKey()}
                    ).size()
            );

            navigationNodeMaintainService.deleteIfExists(navigationNode.getKey());

            assertEquals(
                    0,
                    navigationNodeItemMaintainService.lookupAsList(
                            NavigationNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{navigationNode.getKey()}
                    ).size()
            );

            for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
                assertFalse(navigationNodeItemMaintainService.exists(navigationNodeItem.getKey()));
            }
        } finally {
            for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
                if (Objects.isNull(navigationNodeItem.getKey())) {
                    continue;
                }
                navigationNodeItemMaintainService.deleteIfExists(navigationNodeItem.getKey());
            }
            if (Objects.nonNull(navigationNode.getKey())) {
                navigationNodeMaintainService.deleteIfExists(navigationNode.getKey());
            }
        }
    }

    @Test
    public void testForParentCascade() throws Exception {
        try {
            navigationNodeMaintainService.insertOrUpdate(navigationNode);
            parent.setKey(navigationNodeItemMaintainService.insert(parent));
            for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
                navigationNodeItem.setNodeKey(navigationNode.getKey());
                navigationNodeItem.setParentKey(parent.getKey());
                navigationNodeItem.setKey(navigationNodeItemMaintainService.insert(navigationNodeItem));
            }

            assertEquals(
                    navigationNodeItems.size(),
                    navigationNodeItemMaintainService.lookupAsList(
                            NavigationNodeItemMaintainService.CHILD_FOR_PARENT, new Object[]{parent.getKey()}
                    ).size()
            );

            navigationNodeItemMaintainService.deleteIfExists(parent.getKey());

            assertEquals(
                    0,
                    navigationNodeItemMaintainService.lookupAsList(
                            NavigationNodeItemMaintainService.CHILD_FOR_PARENT, new Object[]{parent.getKey()}
                    ).size()
            );

            for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
                assertFalse(navigationNodeItemMaintainService.exists(navigationNodeItem.getKey()));
            }
        } finally {
            for (NavigationNodeItem navigationNodeItem : navigationNodeItems) {
                if (Objects.isNull(navigationNodeItem.getKey())) {
                    continue;
                }
                navigationNodeItemMaintainService.deleteIfExists(navigationNodeItem.getKey());
            }
            if (Objects.nonNull(parent) && Objects.nonNull(parent.getKey())) {
                navigationNodeItemMaintainService.deleteIfExists(parent.getKey());
            }
            if (Objects.nonNull(navigationNode.getKey())) {
                navigationNodeMaintainService.deleteIfExists(navigationNode.getKey());
            }
        }
    }
}
