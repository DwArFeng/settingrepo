package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.TextListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.TextListNodeItem;
import com.dwarfeng.settingrepo.stack.service.TextListNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.TextListNodeMaintainService;
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
public class TextListNodeItemMaintainServiceImplTest {

    @Autowired
    private TextListNodeItemMaintainService textListNodeItemMaintainService;
    @Autowired
    private TextListNodeMaintainService textListNodeMaintainService;

    private List<TextListNodeItem> textListNodeItems;
    private TextListNode textListNode;

    @Before
    public void setUp() {
        textListNodeItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TextListNodeItem textListNodeItem = new TextListNodeItem(
                    null, null, i, false, "content"
            );
            textListNodeItems.add(textListNodeItem);
        }
        textListNode = new TextListNode(new StringIdKey("test.text_list_node"), 12450);
    }

    @After
    public void tearDown() {
        textListNodeItems.clear();
        textListNode = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            textListNodeMaintainService.insertOrUpdate(textListNode);
            for (TextListNodeItem textListNodeItem : textListNodeItems) {
                textListNodeItem.setNodeKey(textListNode.getKey());
                textListNodeItem.setKey(textListNodeItemMaintainService.insert(textListNodeItem));

                TextListNodeItem testTextListNodeItem = textListNodeItemMaintainService.get(
                        textListNodeItem.getKey()
                );
                assertEquals(BeanUtils.describe(textListNodeItem), BeanUtils.describe(testTextListNodeItem));
                textListNodeItemMaintainService.update(textListNodeItem);
                testTextListNodeItem = textListNodeItemMaintainService.get(textListNodeItem.getKey());
                assertEquals(BeanUtils.describe(textListNodeItem), BeanUtils.describe(testTextListNodeItem));
            }
        } finally {
            for (TextListNodeItem textListNodeItem : textListNodeItems) {
                if (Objects.isNull(textListNodeItem.getKey())) {
                    continue;
                }
                textListNodeItemMaintainService.deleteIfExists(textListNodeItem.getKey());
            }
            if (Objects.nonNull(textListNode.getKey())) {
                textListNodeMaintainService.deleteIfExists(textListNode.getKey());
            }
        }
    }

    @Test
    public void testForTextListNodeCascade() throws Exception {
        try {
            textListNodeMaintainService.insertOrUpdate(textListNode);
            for (TextListNodeItem textListNodeItem : textListNodeItems) {
                textListNodeItem.setNodeKey(textListNode.getKey());
                textListNodeItem.setKey(textListNodeItemMaintainService.insert(textListNodeItem));
            }

            assertEquals(
                    textListNodeItems.size(),
                    textListNodeItemMaintainService.lookupAsList(
                            TextListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{textListNode.getKey()}
                    ).size()
            );

            textListNodeMaintainService.deleteIfExists(textListNode.getKey());

            assertEquals(
                    0,
                    textListNodeItemMaintainService.lookupAsList(
                            TextListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{textListNode.getKey()}
                    ).size()
            );

            for (TextListNodeItem textListNodeItem : textListNodeItems) {
                assertFalse(textListNodeItemMaintainService.exists(textListNodeItem.getKey()));
            }
        } finally {
            for (TextListNodeItem textListNodeItem : textListNodeItems) {
                if (Objects.isNull(textListNodeItem.getKey())) {
                    continue;
                }
                textListNodeItemMaintainService.deleteIfExists(textListNodeItem.getKey());
            }
            if (Objects.nonNull(textListNode.getKey())) {
                textListNodeMaintainService.deleteIfExists(textListNode.getKey());
            }
        }
    }
}
