package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNode;
import com.dwarfeng.settingrepo.stack.service.VideoListNodeMaintainService;
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
 * 视频列表节点维护服务测试。
 *
 * @author fucl
 * @since 3.2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class VideoListNodeMaintainServiceImplTest {

    @Autowired
    private VideoListNodeMaintainService videoListNodeMaintainService;

    private List<VideoListNode> videoListNodes;

    @Before
    public void setUp() {
        videoListNodes = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VideoListNode videoListNode = new VideoListNode(new StringIdKey("test.video_list_node." + i), 12450);
            videoListNodes.add(videoListNode);
        }
    }

    @After
    public void tearDown() {
        videoListNodes.clear();
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            for (VideoListNode videoListNode : videoListNodes) {
                videoListNodeMaintainService.insertOrUpdate(videoListNode);
                VideoListNode testVideoListNode = videoListNodeMaintainService.get(videoListNode.getKey());
                assertEquals(BeanUtils.describe(videoListNode), BeanUtils.describe(testVideoListNode));
                videoListNodeMaintainService.update(videoListNode);
                testVideoListNode = videoListNodeMaintainService.get(videoListNode.getKey());
                assertEquals(BeanUtils.describe(videoListNode), BeanUtils.describe(testVideoListNode));
            }
        } finally {
            for (VideoListNode videoListNode : videoListNodes) {
                if (Objects.isNull(videoListNode.getKey())) {
                    continue;
                }
                videoListNodeMaintainService.deleteIfExists(videoListNode.getKey());
            }
        }
    }
}
