package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoNode;
import com.dwarfeng.settingrepo.stack.service.VideoNodeMaintainService;
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

/**
 * 视频节点维护服务测试。
 *
 * @author fucl
 * @since 3.2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class VideoNodeMaintainServiceImplTest {

    @Autowired
    private VideoNodeMaintainService service;

    private final List<VideoNode> videoNodes = new ArrayList<>();

    @Before
    public void setUp() {
        for (int i = 0; i < 5; i++) {
            VideoNode videoNode = new VideoNode(
                    new StringIdKey("test.video_node." + i), "originName", "storeName", 12450L
            );
            videoNodes.add(videoNode);
        }
    }

    @After
    public void tearDown() {
        videoNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (VideoNode videoNode : videoNodes) {
                videoNode.setKey(service.insert(videoNode));
                service.update(videoNode);
                VideoNode testVideoNode = service.get(videoNode.getKey());
                assertEquals(BeanUtils.describe(videoNode), BeanUtils.describe(testVideoNode));
            }
        } finally {
            for (VideoNode videoNode : videoNodes) {
                if (Objects.isNull(videoNode.getKey())) {
                    continue;
                }
                service.deleteIfExists(videoNode.getKey());
            }
        }
    }
}
