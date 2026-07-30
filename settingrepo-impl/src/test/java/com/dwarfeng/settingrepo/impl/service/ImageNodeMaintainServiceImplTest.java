package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.ImageNode;
import com.dwarfeng.settingrepo.stack.service.ImageNodeMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:com/dwarfeng/settingrepo/impl/spring/application-context*.xml")
public class ImageNodeMaintainServiceImplTest {

    @Autowired
    private ImageNodeMaintainService service;

    private final List<ImageNode> imageNodes = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            ImageNode imageNode = new ImageNode(
                    new StringIdKey("test.image_node." + i), "originName", "storeName", 12450L
            );
            imageNodes.add(imageNode);
        }
    }

    @AfterEach
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
