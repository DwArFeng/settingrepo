package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.FileListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.FileListNodeItem;
import com.dwarfeng.settingrepo.stack.service.FileListNodeItemMaintainService;
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
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class FileListNodeItemMaintainServiceImplTest {

    @Autowired
    private FileListNodeItemMaintainService fileListNodeItemMaintainService;
    @Autowired
    private FileListNodeMaintainService fileListNodeMaintainService;

    private List<FileListNodeItem> fileListNodeItems;
    private FileListNode fileListNode;

    @Before
    public void setUp() {
        fileListNodeItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FileListNodeItem fileListNodeItem = new FileListNodeItem(
                    null, null, 12450, true, "originName", "storeName", 12450L
            );
            fileListNodeItems.add(fileListNodeItem);
        }
        fileListNode = new FileListNode(new StringIdKey("test.file_list_node"), 12450);
    }

    @After
    public void tearDown() {
        fileListNodeItems.clear();
        fileListNode = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            fileListNodeMaintainService.insertOrUpdate(fileListNode);
            for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                fileListNodeItem.setNodeKey(fileListNode.getKey());
                fileListNodeItem.setKey(fileListNodeItemMaintainService.insert(fileListNodeItem));

                FileListNodeItem testFileListNodeItem = fileListNodeItemMaintainService.get(fileListNodeItem.getKey());
                assertEquals(BeanUtils.describe(fileListNodeItem), BeanUtils.describe(testFileListNodeItem));
                fileListNodeItemMaintainService.update(fileListNodeItem);
                testFileListNodeItem = fileListNodeItemMaintainService.get(fileListNodeItem.getKey());
                assertEquals(BeanUtils.describe(fileListNodeItem), BeanUtils.describe(testFileListNodeItem));
            }
        } finally {
            for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                if (Objects.isNull(fileListNodeItem.getKey())) {
                    continue;
                }
                fileListNodeItemMaintainService.deleteIfExists(fileListNodeItem.getKey());
            }
            if (Objects.nonNull(fileListNode.getKey())) {
                fileListNodeMaintainService.deleteIfExists(fileListNode.getKey());
            }
        }
    }

    @Test
    public void testForFileListNodeCascade() throws Exception {
        try {
            fileListNodeMaintainService.insertOrUpdate(fileListNode);
            for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                fileListNodeItem.setNodeKey(fileListNode.getKey());
                fileListNodeItem.setKey(fileListNodeItemMaintainService.insert(fileListNodeItem));
            }

            assertEquals(
                    fileListNodeItems.size(),
                    fileListNodeItemMaintainService.lookupAsList(
                            FileListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{fileListNode.getKey()}
                    ).size()
            );

            fileListNodeMaintainService.deleteIfExists(fileListNode.getKey());

            assertEquals(
                    0,
                    fileListNodeItemMaintainService.lookupAsList(
                            FileListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{fileListNode.getKey()}
                    ).size()
            );

            for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                assertFalse(fileListNodeItemMaintainService.exists(fileListNodeItem.getKey()));
            }
        } finally {
            for (FileListNodeItem fileListNodeItem : fileListNodeItems) {
                if (Objects.isNull(fileListNodeItem.getKey())) {
                    continue;
                }
                fileListNodeItemMaintainService.deleteIfExists(fileListNodeItem.getKey());
            }
            if (Objects.nonNull(fileListNode.getKey())) {
                fileListNodeMaintainService.deleteIfExists(fileListNode.getKey());
            }
        }
    }
}
