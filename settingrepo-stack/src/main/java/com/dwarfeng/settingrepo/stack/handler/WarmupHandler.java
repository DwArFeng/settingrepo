package com.dwarfeng.settingrepo.stack.handler;

import com.dwarfeng.subgrade.stack.handler.DistributedLockHandler;

/**
 * 预热处理器。
 *
 * <p>
 * 该处理器将预热调度放在 Curator 分布式锁内，集群中最多只有一个节点实际运行预热驱动器。
 * 节点失去锁或发生故障后，其他在线节点可以接管。
 *
 * @author DwArFeng
 * @since 3.1.0
 */
public interface WarmupHandler extends DistributedLockHandler {
}
