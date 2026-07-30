package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.KvNode;
import com.dwarfeng.settingrepo.stack.bean.entity.KvNodeItem;
import com.dwarfeng.settingrepo.stack.bean.key.KvNodeItemKey;
import com.dwarfeng.settingrepo.stack.service.KvNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.KvNodeMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:com/dwarfeng/settingrepo/impl/spring/application-context*.xml")
public class KvNodeItemMaintainServiceImplTest {

    private static final String KV_NODE_ID = "test.kv_node";
    private static final String ITEM_INDEX = "0";

    @Autowired
    private KvNodeItemMaintainService kvNodeItemMaintainService;
    @Autowired
    private KvNodeMaintainService kvNodeMaintainService;

    private KvNodeItem kvNodeItem;
    private KvNode kvNode;

    @BeforeEach
    public void setUp() {
        kvNodeItem = new KvNodeItem(
                new KvNodeItemKey(KV_NODE_ID, ITEM_INDEX), "value"
        );
        kvNode = new KvNode(new StringIdKey(KV_NODE_ID), 1);
    }

    @AfterEach
    public void tearDown() {
        kvNodeItem = null;
        kvNode = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            kvNodeMaintainService.insertOrUpdate(kvNode);

            kvNodeItemMaintainService.insertOrUpdate(kvNodeItem);
            KvNodeItem testKvNodeItem = kvNodeItemMaintainService.get(kvNodeItem.getKey());
            assertEquals(BeanUtils.describe(kvNodeItem), BeanUtils.describe(testKvNodeItem));
            kvNodeItemMaintainService.update(kvNodeItem);
            testKvNodeItem = kvNodeItemMaintainService.get(kvNodeItem.getKey());
            assertEquals(BeanUtils.describe(kvNodeItem), BeanUtils.describe(testKvNodeItem));
        } finally {
            if (Objects.nonNull(kvNodeItem.getKey())) {
                kvNodeItemMaintainService.deleteIfExists(kvNodeItem.getKey());
            }

            if (Objects.nonNull(kvNode.getKey())) {
                kvNodeMaintainService.deleteIfExists(kvNode.getKey());
            }
        }
    }

    @Test
    public void testForKvNodeCascade() throws Exception {
        try {
            kvNodeMaintainService.insertOrUpdate(kvNode);

            kvNodeItemMaintainService.insertOrUpdate(kvNodeItem);

            assertEquals(
                    1,
                    kvNodeItemMaintainService.lookupAsList(
                            KvNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{kvNode.getKey()}
                    ).size()
            );

            kvNodeMaintainService.deleteIfExists(kvNode.getKey());

            assertEquals(
                    0,
                    kvNodeItemMaintainService.lookupAsList(
                            KvNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{kvNode.getKey()}
                    ).size()
            );

            assertFalse(kvNodeItemMaintainService.exists(kvNodeItem.getKey()));
        } finally {
            if (Objects.nonNull(kvNodeItem.getKey())) {
                kvNodeItemMaintainService.deleteIfExists(kvNodeItem.getKey());
            }

            if (Objects.nonNull(kvNode.getKey())) {
                kvNodeMaintainService.deleteIfExists(kvNode.getKey());
            }
        }
    }
}
