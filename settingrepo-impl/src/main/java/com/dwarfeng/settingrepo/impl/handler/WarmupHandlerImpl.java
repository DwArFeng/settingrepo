package com.dwarfeng.settingrepo.impl.handler;

import com.dwarfeng.settingrepo.stack.handler.WarmupDriveHandler;
import com.dwarfeng.settingrepo.stack.handler.WarmupHandler;
import com.dwarfeng.subgrade.impl.handler.CuratorDistributedLockHandler;
import com.dwarfeng.subgrade.impl.handler.Worker;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WarmupHandlerImpl implements WarmupHandler {

    private final CuratorDistributedLockHandler handler;

    public WarmupHandlerImpl(
            CuratorFramework curatorFramework,
            @Value("${com.dwarfeng.settingrepo.curator.latch_path.warmup.leader_latch}") String leaderLatchPath,
            WarmupWorker warmupWorker
    ) {
        handler = new CuratorDistributedLockHandler(curatorFramework, leaderLatchPath, warmupWorker);
    }

    @BehaviorAnalyse
    @Override
    public boolean isOnline() {
        return handler.isOnline();
    }

    @BehaviorAnalyse
    @Override
    public void online() throws HandlerException {
        handler.online();
    }

    @BehaviorAnalyse
    @Override
    public void offline() throws HandlerException {
        handler.offline();
    }

    @BehaviorAnalyse
    @Override
    public boolean isStarted() {
        return handler.isStarted();
    }

    @BehaviorAnalyse
    @Override
    public void start() throws HandlerException {
        handler.start();
    }

    @BehaviorAnalyse
    @Override
    public void stop() throws HandlerException {
        handler.stop();
    }

    @BehaviorAnalyse
    @Override
    public boolean isLockHolding() {
        return handler.isLockHolding();
    }

    @BehaviorAnalyse
    @Override
    public boolean isWorking() {
        return handler.isWorking();
    }

    /**
     * 预热工作器。
     *
     * <p>
     * 工作器只在持锁节点上启停预热驱动器，不自行获取或释放锁，
     * 以保证所有 Curator 状态判断由外层处理器统一维护。
     */
    @Component
    public static class WarmupWorker implements Worker {

        private static final Logger LOGGER = LoggerFactory.getLogger(WarmupWorker.class);

        private final WarmupDriveHandler warmupDriveHandler;

        public WarmupWorker(WarmupDriveHandler warmupDriveHandler) {
            this.warmupDriveHandler = warmupDriveHandler;
        }

        @Override
        public void work() throws Exception {
            // 工作器只在 Curator 锁的持有者上被调用，锁的释放会触发 rest，从而停止全部调度。
            LOGGER.info("预热处理器开始工作...");
            warmupDriveHandler.start();
        }

        @Override
        public void rest() throws Exception {
            LOGGER.info("预热处理器停止工作...");
            warmupDriveHandler.stop();
        }
    }
}
