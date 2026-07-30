package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.KvNode;
import com.dwarfeng.settingrepo.stack.service.KvNodeMaintainService;
import com.dwarfeng.subgrade.basic.stack.bean.key.StringIdKey;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:com/dwarfeng/settingrepo/impl/spring/application-context*.xml")
public class KvNodeMaintainServiceImplTest {

    @Autowired
    private KvNodeMaintainService kvNodeMaintainService;

    private List<KvNode> kvNodes;

    @BeforeEach
    public void setUp() {
        kvNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            KvNode kvNode = new KvNode(new StringIdKey("test." + i), i);
            kvNodes.add(kvNode);
        }
    }

    @AfterEach
    public void tearDown() {
        kvNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (KvNode kvNode : kvNodes) {
                // 先尝试删除可能已存在的实体，确保从干净状态开始
                kvNodeMaintainService.deleteIfExists(kvNode.getKey());

                // 使用 insert 方法插入实体
                StringIdKey key = kvNodeMaintainService.insert(kvNode);
                kvNode.setKey(key);

                // 使用 get 方法获取并验证插入的实体
                KvNode testKvNode = kvNodeMaintainService.get(kvNode.getKey());
                assertNotNull(testKvNode);
                assertEquals(kvNode.getKey(), testKvNode.getKey());
                assertEquals(kvNode.getCount(), testKvNode.getCount());

                // 更新实体
                kvNodeMaintainService.update(kvNode);
                testKvNode = kvNodeMaintainService.get(kvNode.getKey());
                assertNotNull(testKvNode);
                assertEquals(kvNode.getKey(), testKvNode.getKey());
                assertEquals(kvNode.getCount(), testKvNode.getCount());
            }
        } finally {
            for (KvNode kvNode : kvNodes) {
                if (Objects.isNull(kvNode.getKey())) {
                    continue;
                }
                kvNodeMaintainService.deleteIfExists(kvNode.getKey());
            }
        }
    }
}
