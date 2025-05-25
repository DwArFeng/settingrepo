package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNodeItem;
import com.dwarfeng.settingrepo.stack.service.ImageListNodeItemMaintainService;
import com.dwarfeng.settingrepo.stack.service.ImageListNodeMaintainService;
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
public class ImageListNodeItemMaintainServiceImplTest {

    @Autowired
    private ImageListNodeItemMaintainService imageListNodeItemMaintainService;
    @Autowired
    private ImageListNodeMaintainService imageListNodeMaintainService;

    private List<ImageListNodeItem> imageListNodeItems;
    private ImageListNode imageListNode;

    @Before
    public void setUp() {
        imageListNodeItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageListNodeItem imageListNodeItem = new ImageListNodeItem(
                    null, null, 12450, true, "originName", "storeName", 12450L
            );
            imageListNodeItems.add(imageListNodeItem);
        }
        imageListNode = new ImageListNode(new StringIdKey("test.image_list_node"), 12450);
    }

    @After
    public void tearDown() {
        imageListNodeItems.clear();
        imageListNode = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            imageListNodeMaintainService.insertOrUpdate(imageListNode);
            for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                imageListNodeItem.setNodeKey(imageListNode.getKey());
                imageListNodeItem.setKey(imageListNodeItemMaintainService.insert(imageListNodeItem));

                ImageListNodeItem testImageListNodeItem = imageListNodeItemMaintainService.get(imageListNodeItem.getKey());
                assertEquals(BeanUtils.describe(imageListNodeItem), BeanUtils.describe(testImageListNodeItem));
                imageListNodeItemMaintainService.update(imageListNodeItem);
                testImageListNodeItem = imageListNodeItemMaintainService.get(imageListNodeItem.getKey());
                assertEquals(BeanUtils.describe(imageListNodeItem), BeanUtils.describe(testImageListNodeItem));
            }
        } finally {
            for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                if (Objects.isNull(imageListNodeItem.getKey())) {
                    continue;
                }
                imageListNodeItemMaintainService.deleteIfExists(imageListNodeItem.getKey());
            }
            if (Objects.nonNull(imageListNode.getKey())) {
                imageListNodeMaintainService.deleteIfExists(imageListNode.getKey());
            }
        }
    }

    @Test
    public void testForImageListNodeCascade() throws Exception {
        try {
            imageListNodeMaintainService.insertOrUpdate(imageListNode);
            for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                imageListNodeItem.setNodeKey(imageListNode.getKey());
                imageListNodeItem.setKey(imageListNodeItemMaintainService.insert(imageListNodeItem));
            }

            assertEquals(
                    imageListNodeItems.size(),
                    imageListNodeItemMaintainService.lookupAsList(
                            ImageListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{imageListNode.getKey()}
                    ).size()
            );

            imageListNodeMaintainService.deleteIfExists(imageListNode.getKey());

            assertEquals(
                    0,
                    imageListNodeItemMaintainService.lookupAsList(
                            ImageListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{imageListNode.getKey()}
                    ).size()
            );

            for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                assertFalse(imageListNodeItemMaintainService.exists(imageListNodeItem.getKey()));
            }
        } finally {
            for (ImageListNodeItem imageListNodeItem : imageListNodeItems) {
                if (Objects.isNull(imageListNodeItem.getKey())) {
                    continue;
                }
                imageListNodeItemMaintainService.deleteIfExists(imageListNodeItem.getKey());
            }
            if (Objects.nonNull(imageListNode.getKey())) {
                imageListNodeMaintainService.deleteIfExists(imageListNode.getKey());
            }
        }
    }
}
