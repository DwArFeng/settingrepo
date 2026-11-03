package com.dwarfeng.settingrepo.stack.dao;

import com.dwarfeng.settingrepo.stack.bean.entity.VideoNode;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 视频节点数据访问层。
 *
 * @author fucl
 * @since 3.2.0
 */
public interface VideoNodeDao extends BatchBaseDao<StringIdKey, VideoNode>, EntireLookupDao<VideoNode>,
        PresetLookupDao<VideoNode> {
}
