package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.NavigationNode;
import com.dwarfeng.settingrepo.stack.service.NavigationNodeMaintainService;
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
public class NavigationNodeMaintainServiceImplTest {

    @Autowired
    private NavigationNodeMaintainService navigationNodeMaintainService;

    private List<NavigationNode> navigationNodes;

    @BeforeEach
    public void setUp() {
        navigationNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            NavigationNode navigationNode = new NavigationNode(
                    new StringIdKey("test.navigation_node." + i), 12450, "content"
            );
            navigationNodes.add(navigationNode);
        }
    }

    @AfterEach
    public void tearDown() {
        navigationNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (NavigationNode navigationNode : navigationNodes) {
                navigationNodeMaintainService.insertOrUpdate(navigationNode);
                NavigationNode testNavigationNode = navigationNodeMaintainService.get(navigationNode.getKey());
                assertEquals(BeanUtils.describe(navigationNode), BeanUtils.describe(testNavigationNode));
                navigationNodeMaintainService.update(navigationNode);
                testNavigationNode = navigationNodeMaintainService.get(navigationNode.getKey());
                assertEquals(BeanUtils.describe(navigationNode), BeanUtils.describe(testNavigationNode));
            }
        } finally {
            for (NavigationNode navigationNode : navigationNodes) {
                if (Objects.isNull(navigationNode.getKey())) {
                    continue;
                }
                navigationNodeMaintainService.deleteIfExists(navigationNode.getKey());
            }
        }
    }
}
