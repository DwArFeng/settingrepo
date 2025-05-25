package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.IahnNode;
import com.dwarfeng.settingrepo.stack.service.IahnNodeMaintainService;
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
public class IahnNodeMaintainServiceImplTest {

    @Autowired
    private IahnNodeMaintainService service;

    private final List<IahnNode> iahnNodes = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            IahnNode iahnNode = new IahnNode(new StringIdKey("test.iahn_node." + i));
            iahnNodes.add(iahnNode);
        }
    }

    @After
    public void tearDown() {
        iahnNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (IahnNode iahnNode : iahnNodes) {
                iahnNode.setKey(service.insert(iahnNode));
                service.update(iahnNode);
                IahnNode testIahnNode = service.get(iahnNode.getKey());
                assertEquals(BeanUtils.describe(iahnNode), BeanUtils.describe(testIahnNode));
            }
        } finally {
            for (IahnNode iahnNode : iahnNodes) {
                if (Objects.isNull(iahnNode.getKey())) {
                    continue;
                }
                service.deleteIfExists(iahnNode.getKey());
            }
        }
    }
}
