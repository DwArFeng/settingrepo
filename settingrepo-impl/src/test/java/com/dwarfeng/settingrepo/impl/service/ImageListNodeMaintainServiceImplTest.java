package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageListNode;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class ImageListNodeMaintainServiceImplTest {

    @Autowired
    private ImageListNodeMaintainService imageListNodeMaintainService;

    private List<ImageListNode> imageListNodes;

    @Before
    public void setUp() {
        imageListNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageListNode imageListNode = new ImageListNode(new StringIdKey("test.image_list_node." + i), 12450);
            imageListNodes.add(imageListNode);
        }
    }

    @After
    public void tearDown() {
        imageListNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (ImageListNode imageListNode : imageListNodes) {
                imageListNodeMaintainService.insertOrUpdate(imageListNode);
                ImageListNode testImageListNode = imageListNodeMaintainService.get(imageListNode.getKey());
                assertEquals(BeanUtils.describe(imageListNode), BeanUtils.describe(testImageListNode));
                imageListNodeMaintainService.update(imageListNode);
                testImageListNode = imageListNodeMaintainService.get(imageListNode.getKey());
                assertEquals(BeanUtils.describe(imageListNode), BeanUtils.describe(testImageListNode));
            }
        } finally {
            for (ImageListNode imageListNode : imageListNodes) {
                if (Objects.isNull(imageListNode.getKey())) {
                    continue;
                }
                imageListNodeMaintainService.deleteIfExists(imageListNode.getKey());
            }
        }
    }
}
