package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageNode;
import com.dwarfeng.settingrepo.stack.service.ImageNodeMaintainService;
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
public class ImageNodeMaintainServiceImplTest {

    @Autowired
    private ImageNodeMaintainService service;

    private final List<ImageNode> imageNodes = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            ImageNode imageNode = new ImageNode(
                    new StringIdKey("test.image_node." + i), "originName", "storeName", 12450L
            );
            imageNodes.add(imageNode);
        }
    }

    @After
    public void tearDown() {
        imageNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (ImageNode imageNode : imageNodes) {
                imageNode.setKey(service.insert(imageNode));
                service.update(imageNode);
                ImageNode testImageNode = service.get(imageNode.getKey());
                assertEquals(BeanUtils.describe(imageNode), BeanUtils.describe(testImageNode));
            }
        } finally {
            for (ImageNode imageNode : imageNodes) {
                if (Objects.isNull(imageNode.getKey())) {
                    continue;
                }
                service.deleteIfExists(imageNode.getKey());
            }
        }
    }
}
