package com.dwarfeng.settingrepo.node.all.he.launcher;

import com.dwarfeng.settingrepo.node.all.he.handler.LauncherSettingHandler;
import com.dwarfeng.settingrepo.stack.service.ResetQosService;
import com.dwarfeng.settingrepo.stack.service.SupportQosService;
import com.dwarfeng.settingrepo.stack.service.WarmupQosService;
import com.dwarfeng.springterminator.sdk.util.ApplicationUtil;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;

/**
 * 程序启动器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class Launcher {

    private final static Logger LOGGER = LoggerFactory.getLogger(Launcher.class);

    public static void main(String[] args) {
        ApplicationUtil.launch(new String[]{
                "classpath:spring/application-context*.xml",
                "file:opt/opt*.xml",
                "file:optext/opt*.xml"
        }, ctx -> {
            // 根据启动器设置处理器的设置，选择性重置格式化器。
            mayResetFormatter(ctx);

            // 根据启动器设置处理器的设置，选择性重置预热驱动器支持。
            mayResetWarmupDriver(ctx);

            // 根据启动器设置处理器的设置，选择性启动重置服务。
            mayStartReset(ctx);

            // 根据启动器设置处理器的设置，选择性上线预热服务。
            mayOnlineWarmup(ctx);
            // 根据启动器设置处理器的设置，选择性启动预热服务。
            mayEnableWarmup(ctx);
        });
    }

    private static void mayResetFormatter(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 如果不重置格式化器，则返回。
        if (!launcherSettingHandler.isResetFormatterSupport()) {
            return;
        }

        // 重置格式化器支持。
        LOGGER.info("重置格式化器支持...");
        SupportQosService supportQosService = ctx.getBean(SupportQosService.class);
        try {
            supportQosService.resetFormatter();
        } catch (ServiceException e) {
            LOGGER.warn("格式化器支持重置失败，异常信息如下", e);
        }
    }

    private static void mayResetWarmupDriver(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 如果不重置预热驱动器支持，则返回。
        if (!launcherSettingHandler.isResetWarmupDriverSupport()) {
            return;
        }

        // 重置预热驱动器支持。
        LOGGER.info("重置预热驱动器支持...");
        SupportQosService supportQosService = ctx.getBean(SupportQosService.class);
        try {
            supportQosService.resetWarmupDriver();
        } catch (ServiceException e) {
            LOGGER.warn("预热驱动器支持重置失败，异常信息如下", e);
        }
    }

    private static void mayStartReset(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 处理重置处理器的启动选项。
        ResetQosService resetQosService = ctx.getBean(ResetQosService.class);

        // 重置处理器是否启动重置服务。
        long startResetDelay = launcherSettingHandler.getStartResetDelay();
        if (startResetDelay == 0) {
            LOGGER.info("立即启动重置服务...");
            try {
                resetQosService.start();
            } catch (ServiceException e) {
                LOGGER.error("无法启动重置服务，异常原因如下", e);
            }
        } else if (startResetDelay > 0) {
            LOGGER.info("{} 毫秒后启动重置服务...", startResetDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("启动重置服务...");
                        try {
                            resetQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error("无法启动重置服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + startResetDelay)
            );
        }
    }

    private static void mayOnlineWarmup(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 获取预热 QOS 服务。
        WarmupQosService warmupQosService = ctx.getBean(WarmupQosService.class);

        // 判断预热处理器是否上线预热服务，并按条件执行不同的操作。
        long onlineWarmupDelay = launcherSettingHandler.getOnlineWarmupDelay();
        if (onlineWarmupDelay == 0) {
            LOGGER.info("立即上线预热服务...");
            try {
                warmupQosService.online();
            } catch (ServiceException e) {
                LOGGER.error("无法上线预热服务，异常原因如下", e);
            }
        } else if (onlineWarmupDelay > 0) {
            LOGGER.info("{} 毫秒后上线预热服务...", onlineWarmupDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("上线预热服务...");
                        try {
                            warmupQosService.online();
                        } catch (ServiceException e) {
                            LOGGER.error("无法上线预热服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + onlineWarmupDelay)
            );
        }
    }

    private static void mayEnableWarmup(ApplicationContext ctx) {
        // 获取启动器设置处理器，用于获取启动器设置，并按照设置选择性执行功能。
        LauncherSettingHandler launcherSettingHandler = ctx.getBean(LauncherSettingHandler.class);

        // 获取程序中的 ThreadPoolTaskScheduler，用于处理计划任务。
        ThreadPoolTaskScheduler scheduler = ctx.getBean(ThreadPoolTaskScheduler.class);

        // 获取预热 QOS 服务。
        WarmupQosService warmupQosService = ctx.getBean(WarmupQosService.class);

        // 判断预热处理器是否启动预热服务，并按条件执行不同的操作。
        long enableWarmupDelay = launcherSettingHandler.getEnableWarmupDelay();
        if (enableWarmupDelay == 0) {
            LOGGER.info("立即启动预热服务...");
            try {
                warmupQosService.start();
            } catch (ServiceException e) {
                LOGGER.error("无法启动预热服务，异常原因如下", e);
            }
        } else if (enableWarmupDelay > 0) {
            LOGGER.info("{} 毫秒后启动预热服务...", enableWarmupDelay);
            scheduler.schedule(
                    () -> {
                        LOGGER.info("启动预热服务...");
                        try {
                            warmupQosService.start();
                        } catch (ServiceException e) {
                            LOGGER.error("无法启动预热服务，异常原因如下", e);
                        }
                    },
                    new Date(System.currentTimeMillis() + enableWarmupDelay)
            );
        }
    }
}
