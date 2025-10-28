package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.FileListNode;
import com.dwarfeng.settingrepo.stack.service.FileListNodeMaintainService;
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
public class FileListNodeMaintainServiceImplTest {

    @Autowired
    private FileListNodeMaintainService fileListNodeMaintainService;

    private List<FileListNode> fileListNodes;

    @Before
    public void setUp() {
        fileListNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FileListNode fileListNode = new FileListNode(new StringIdKey("test.file_list_node." + i), 12450);
            fileListNodes.add(fileListNode);
        }
    }

    @After
    public void tearDown() {
        fileListNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (FileListNode fileListNode : fileListNodes) {
                fileListNodeMaintainService.insertOrUpdate(fileListNode);
                FileListNode testFileListNode = fileListNodeMaintainService.get(fileListNode.getKey());
                assertEquals(BeanUtils.describe(fileListNode), BeanUtils.describe(testFileListNode));
                fileListNodeMaintainService.update(fileListNode);
                testFileListNode = fileListNodeMaintainService.get(fileListNode.getKey());
                assertEquals(BeanUtils.describe(fileListNode), BeanUtils.describe(testFileListNode));
            }
        } finally {
            for (FileListNode fileListNode : fileListNodes) {
                if (Objects.isNull(fileListNode.getKey())) {
                    continue;
                }
                fileListNodeMaintainService.deleteIfExists(fileListNode.getKey());
            }
        }
    }
}
