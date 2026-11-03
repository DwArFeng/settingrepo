package com.dwarfeng.settingrepo.impl.service;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNode;
import com.dwarfeng.settingrepo.stack.bean.entity.VideoListNodeItem;
import com.dwarfeng.settingrepo.stack.service.VideoListNodeItemMaintainService;
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
import static org.junit.Assert.assertFalse;

/**
 * 视频列表节点条目维护服务测试。
 *
 * @author fucl
 * @since 3.2.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/application-context*.xml")
public class VideoListNodeItemMaintainServiceImplTest {

    @Autowired
    private VideoListNodeItemMaintainService videoListNodeItemMaintainService;
    @Autowired
    private VideoListNodeMaintainService videoListNodeMaintainService;

    private List<VideoListNodeItem> videoListNodeItems;
    private VideoListNode videoListNode;

    @Before
    public void setUp() {
        videoListNodeItems = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VideoListNodeItem videoListNodeItem = new VideoListNodeItem(
                    null, null, 12450, true, "originName", "storeName", 12450L
            );
            videoListNodeItems.add(videoListNodeItem);
        }
        videoListNode = new VideoListNode(new StringIdKey("test.video_list_node"), 12450);
    }

    @After
    public void tearDown() {
        videoListNodeItems.clear();
        videoListNode = null;
    }

    @Test
    public void testForCrud() throws Exception {
        try {
            videoListNodeMaintainService.insertOrUpdate(videoListNode);
            for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                videoListNodeItem.setNodeKey(videoListNode.getKey());
                videoListNodeItem.setKey(videoListNodeItemMaintainService.insert(videoListNodeItem));

                VideoListNodeItem testVideoListNodeItem = videoListNodeItemMaintainService.get(
                        videoListNodeItem.getKey()
                );
                assertEquals(BeanUtils.describe(videoListNodeItem), BeanUtils.describe(testVideoListNodeItem));
                videoListNodeItemMaintainService.update(videoListNodeItem);
                testVideoListNodeItem = videoListNodeItemMaintainService.get(videoListNodeItem.getKey());
                assertEquals(BeanUtils.describe(videoListNodeItem), BeanUtils.describe(testVideoListNodeItem));
            }
        } finally {
            for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                if (Objects.isNull(videoListNodeItem.getKey())) {
                    continue;
                }
                videoListNodeItemMaintainService.deleteIfExists(videoListNodeItem.getKey());
            }
            if (Objects.nonNull(videoListNode.getKey())) {
                videoListNodeMaintainService.deleteIfExists(videoListNode.getKey());
            }
        }
    }

    @Test
    public void testForVideoListNodeCascade() throws Exception {
        try {
            videoListNodeMaintainService.insertOrUpdate(videoListNode);
            for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                videoListNodeItem.setNodeKey(videoListNode.getKey());
                videoListNodeItem.setKey(videoListNodeItemMaintainService.insert(videoListNodeItem));
            }

            assertEquals(
                    videoListNodeItems.size(),
                    videoListNodeItemMaintainService.lookupAsList(
                            VideoListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{videoListNode.getKey()}
                    ).size()
            );

            videoListNodeMaintainService.deleteIfExists(videoListNode.getKey());

            assertEquals(
                    0,
                    videoListNodeItemMaintainService.lookupAsList(
                            VideoListNodeItemMaintainService.CHILD_FOR_NODE, new Object[]{videoListNode.getKey()}
                    ).size()
            );

            for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                assertFalse(videoListNodeItemMaintainService.exists(videoListNodeItem.getKey()));
            }
        } finally {
            for (VideoListNodeItem videoListNodeItem : videoListNodeItems) {
                if (Objects.isNull(videoListNodeItem.getKey())) {
                    continue;
                }
                videoListNodeItemMaintainService.deleteIfExists(videoListNodeItem.getKey());
            }
            if (Objects.nonNull(videoListNode.getKey())) {
                videoListNodeMaintainService.deleteIfExists(videoListNode.getKey());
            }
        }
    }
}
