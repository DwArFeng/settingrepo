package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.FileNode;
import com.dwarfeng.settingrepo.stack.service.FileNodeMaintainService;
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
public class FileNodeMaintainServiceImplTest {

    @Autowired
    private FileNodeMaintainService service;

    private final List<FileNode> fileNodes = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            FileNode fileNode = new FileNode(
                    new StringIdKey("test.file_node." + i), "originName", "storeName", 12450L
            );
            fileNodes.add(fileNode);
        }
    }

    @AfterEach
    public void tearDown() {
        fileNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (FileNode fileNode : fileNodes) {
                fileNode.setKey(service.insert(fileNode));
                service.update(fileNode);
                FileNode testFileNode = service.get(fileNode.getKey());
                assertEquals(BeanUtils.describe(fileNode), BeanUtils.describe(testFileNode));
            }
        } finally {
            for (FileNode fileNode : fileNodes) {
                if (Objects.isNull(fileNode.getKey())) {
                    continue;
                }
                service.deleteIfExists(fileNode.getKey());
            }
        }
    }
}
