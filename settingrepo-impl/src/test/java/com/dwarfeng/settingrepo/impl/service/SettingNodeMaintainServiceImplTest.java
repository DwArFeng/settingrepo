package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.service.SettingCategoryMaintainService;
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
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class SettingNodeMaintainServiceImplTest {

    private static final String SETTING_CATEGORY_ID = "test.setting_category";

    @Autowired
    private SettingCategoryMaintainService settingCategoryMaintainService;
    @Autowired
    private SettingNodeMaintainService settingNodeMaintainService;

    private SettingCategory settingCategory;
    private List<SettingNode> settingNodes;

    @Before
    public void setUp() {
        settingCategory = new SettingCategory(
                new StringIdKey(SETTING_CATEGORY_ID), "formatterType", "formatterParam", "remark"
        );
        settingNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SettingNode settingNode = new SettingNode(
                    new StringIdKey("test.setting_node." + i), 12450, new Date(), "remark",
                    true, "test.setting_category", new String[]{"foo", "bar"}
            );
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
            settingCategoryMaintainService.insertOrUpdate(settingCategory);
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
                if (Objects.isNull(settingNode.getKey())) {
                    continue;
                }
                settingNodeMaintainService.deleteIfExists(settingNode.getKey());
            }
            if (Objects.nonNull(settingCategory.getKey())) {
                settingCategoryMaintainService.deleteIfExists(settingCategory.getKey());
            }
        }
    }

    public void testForSettingCategoryCascade() throws Exception {
        try {
            settingCategoryMaintainService.insertOrUpdate(settingCategory);
            for (SettingNode settingNode : settingNodes) {
                settingNodeMaintainService.insertOrUpdate(settingNode);
            }

            assertTrue(settingNodeMaintainService.allExists(
                    settingNodes.stream().map(SettingNode::getKey).collect(Collectors.toList()))
            );

            settingCategoryMaintainService.deleteIfExists(settingCategory.getKey());

            assertTrue(settingNodeMaintainService.allExists(
                    settingNodes.stream().map(SettingNode::getKey).collect(Collectors.toList()))
            );

            for (SettingNode settingNode : settingNodes) {
                SettingNode testSettingNode = settingNodeMaintainService.get(settingNode.getKey());
                assertFalse(testSettingNode.isReachable());
            }
        } finally {
            for (SettingNode settingNode : settingNodes) {
                if (Objects.isNull(settingNode.getKey())) {
                    continue;
                }
                settingNodeMaintainService.deleteIfExists(settingNode.getKey());
            }
            if (Objects.nonNull(settingCategory.getKey())) {
                settingCategoryMaintainService.deleteIfExists(settingCategory.getKey());
            }
        }
    }
}
