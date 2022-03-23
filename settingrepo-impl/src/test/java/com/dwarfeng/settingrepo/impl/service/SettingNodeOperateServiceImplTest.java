package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.dutil.basic.io.StringOutputStream;
import com.dwarfeng.settingrepo.impl.handler.formatter.GroovyFormatterRegistry;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeInspectInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodePutInfo;
import com.dwarfeng.settingrepo.stack.bean.dto.SettingNodeRemoveInfo;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory;
import com.dwarfeng.settingrepo.stack.bean.entity.SettingNode;
import com.dwarfeng.settingrepo.stack.service.SettingCategoryMaintainService;
import com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService;
import com.dwarfeng.settingrepo.stack.service.SettingNodeOperateService;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class SettingNodeOperateServiceImplTest {

    public static final String CATEGORY = "test.setting_category";

    @Autowired
    private SettingNodeOperateService settingNodeOperateService;
    @Autowired
    private SettingCategoryMaintainService settingCategoryMaintainService;
    @Autowired
    private SettingNodeMaintainService settingNodeMaintainService;

    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private GroovyFormatterRegistry groovyFormatterRegistry;

    private SettingCategory settingCategory;

    @Before
    public void setUp() throws Exception {
        Resource resource = ctx.getResource("classpath:groovy/TestFormatterProcessor.groovy");
        String formatterParam;
        try (
                InputStream sin = resource.getInputStream();
                StringOutputStream sout = new StringOutputStream(StandardCharsets.UTF_8, true)
        ) {
            IOUtil.trans(sin, sout, 4096);
            sout.flush();
            formatterParam = sout.toString();
        }
        settingCategory = new SettingCategory(
                new StringIdKey(CATEGORY), groovyFormatterRegistry.getFormatterType(), formatterParam, "测试用设置类别"
        );
    }

    @After
    public void tearDown() {
        settingCategory = null;
    }

    @Test
    public void test() throws Exception {
        try {
            settingCategoryMaintainService.insertOrUpdate(settingCategory);

            // 预先删除可能存在的相关节点。
            settingNodeMaintainService.deleteIfExists(new StringIdKey("foo.bar"));

            // 测试 inspect 获取不存在的数据。
            SettingNode settingNode = settingNodeOperateService.inspect(
                    new SettingNodeInspectInfo(CATEGORY, new String[]{"foo", "bar"})
            );
            assertNull(settingNode);

            // 测试在设置节点不存在时放置数据。
            settingNodeOperateService.put(
                    new SettingNodePutInfo(CATEGORY, new String[]{"foo", "bar"}, "content", "remark")
            );
            settingNode = settingNodeOperateService.inspect(
                    new SettingNodeInspectInfo(CATEGORY, new String[]{"foo", "bar"})
            );
            assertNotNull(settingNode);
            assertEquals("foo.bar", settingNode.getKey().getStringId());
            assertEquals("content", settingNode.getValue());
            assertEquals("remark", settingNode.getRemark());

            // 测试在设置节点存在时放置数据。
            settingNodeOperateService.put(
                    new SettingNodePutInfo(CATEGORY, new String[]{"foo", "bar"}, "CONTENT", "REMARK")
            );
            settingNode = settingNodeOperateService.inspect(
                    new SettingNodeInspectInfo(CATEGORY, new String[]{"foo", "bar"})
            );
            assertNotNull(settingNode);
            assertEquals("foo.bar", settingNode.getKey().getStringId());
            assertEquals("CONTENT", settingNode.getValue());
            assertEquals("REMARK", settingNode.getRemark());

            // 测试删除节点。
            settingNodeOperateService.remove(new SettingNodeRemoveInfo(CATEGORY, new String[]{"foo", "bar"}));
            settingNode = settingNodeOperateService.inspect(
                    new SettingNodeInspectInfo(CATEGORY, new String[]{"foo", "bar"})
            );
            assertNull(settingNode);
        } finally {
            settingCategoryMaintainService.deleteIfExists(settingCategory.getKey());
            settingNodeMaintainService.deleteIfExists(new StringIdKey("foo.bar"));
        }
    }
}
