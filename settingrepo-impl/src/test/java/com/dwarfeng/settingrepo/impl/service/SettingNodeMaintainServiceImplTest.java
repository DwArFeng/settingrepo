package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
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

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class SettingNodeMaintainServiceImplTest {

    @Autowired
    private SettingNodeMaintainService settingNodeMaintainService;

    private List<SettingNode> settingNodes;

    @Before
    public void setUp() {
        settingNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SettingNode settingNode = new SettingNode(new StringIdKey("test.setting_node." + i), "value", "remark");
            settingNodes.add(settingNode);
        }
    }

    @After
    public void tearDown() {
        settingNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (SettingNode settingNode : settingNodes) {
                settingNodeMaintainService.insertOrUpdate(settingNode);
                SettingNode testSettingNode = settingNodeMaintainService.get(settingNode.getKey());
                assertEquals(BeanUtils.describe(settingNode), BeanUtils.describe(testSettingNode));
                settingNodeMaintainService.update(settingNode);
                testSettingNode = settingNodeMaintainService.get(settingNode.getKey());
                assertEquals(BeanUtils.describe(settingNode), BeanUtils.describe(testSettingNode));
            }
        } finally {
            for (SettingNode settingNode : settingNodes) {
                settingNodeMaintainService.deleteIfExists(settingNode.getKey());
            }
        }
    }
}
