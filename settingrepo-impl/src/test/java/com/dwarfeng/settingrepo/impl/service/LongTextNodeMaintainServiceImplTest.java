package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.LongTextNode;
import com.dwarfeng.settingrepo.stack.service.LongTextNodeMaintainService;
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
public class LongTextNodeMaintainServiceImplTest {

    @Autowired
    private LongTextNodeMaintainService service;

    private final List<LongTextNode> longTextNodes = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            LongTextNode longTextNode = new LongTextNode(
                    new StringIdKey("test.long_text_node." + i), "preview", "storeName", 12450L, true
            );
            longTextNodes.add(longTextNode);
        }
    }

    @AfterEach
    public void tearDown() {
        longTextNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (LongTextNode longTextNode : longTextNodes) {
                longTextNode.setKey(service.insert(longTextNode));
                service.update(longTextNode);
                LongTextNode testLongTextNode = service.get(longTextNode.getKey());
                assertEquals(BeanUtils.describe(longTextNode), BeanUtils.describe(testLongTextNode));
            }
        } finally {
            for (LongTextNode longTextNode : longTextNodes) {
                if (Objects.isNull(longTextNode.getKey())) {
                    continue;
                }
                service.deleteIfExists(longTextNode.getKey());
            }
        }
    }
}
