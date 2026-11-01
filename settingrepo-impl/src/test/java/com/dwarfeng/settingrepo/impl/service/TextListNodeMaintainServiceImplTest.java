package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.TextListNode;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class TextListNodeMaintainServiceImplTest {

    @Autowired
    private TextListNodeMaintainService textListNodeMaintainService;

    private List<TextListNode> textListNodes;

    @Before
    public void setUp() {
        textListNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TextListNode textListNode = new TextListNode(new StringIdKey("test.text_list_node." + i), 12450);
            textListNodes.add(textListNode);
        }
    }

    @After
    public void tearDown() {
        textListNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (TextListNode textListNode : textListNodes) {
                textListNodeMaintainService.insertOrUpdate(textListNode);
                TextListNode testTextListNode = textListNodeMaintainService.get(textListNode.getKey());
                assertEquals(BeanUtils.describe(textListNode), BeanUtils.describe(testTextListNode));
                textListNodeMaintainService.update(textListNode);
                testTextListNode = textListNodeMaintainService.get(textListNode.getKey());
                assertEquals(BeanUtils.describe(textListNode), BeanUtils.describe(testTextListNode));
            }
        } finally {
            for (TextListNode textListNode : textListNodes) {
                if (Objects.isNull(textListNode.getKey())) {
                    continue;
                }
                textListNodeMaintainService.deleteIfExists(textListNode.getKey());
            }
        }
    }
}
