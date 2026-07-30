package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.TextNode;
import com.dwarfeng.settingrepo.stack.service.TextNodeMaintainService;
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
public class TextNodeMaintainServiceImplTest {

    @Autowired
    private TextNodeMaintainService textNodeMaintainService;

    private List<TextNode> textNodes;

    @BeforeEach
    public void setUp() {
        textNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TextNode textNode = new TextNode(new StringIdKey("test.text_node." + i), "value");
            textNodes.add(textNode);
        }
    }

    @AfterEach
    public void tearDown() {
        textNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (TextNode textNode : textNodes) {
                textNodeMaintainService.insertOrUpdate(textNode);
                TextNode testTextNode = textNodeMaintainService.get(textNode.getKey());
                assertEquals(BeanUtils.describe(textNode), BeanUtils.describe(testTextNode));
                textNodeMaintainService.update(textNode);
                testTextNode = textNodeMaintainService.get(textNode.getKey());
                assertEquals(BeanUtils.describe(textNode), BeanUtils.describe(testTextNode));
            }
        } finally {
            for (TextNode textNode : textNodes) {
                if (Objects.isNull(textNode.getKey())) {
                    continue;
                }
                textNodeMaintainService.deleteIfExists(textNode.getKey());
            }
        }
    }
}
