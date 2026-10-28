# Warmup Commands - 预热处理器命令

本页面说明 `warmup` 与 `warmupdrive`。这两个命令用于操作预热处理器和查看预热驱动处理器状态，
不接受 JSON 输入，也不会直接读取或修改 `WarmupSetting`、`WarmupDriverInfo` 实体数据。

## warmup

预热处理器操作与状态查看。该命令控制集群唯一的预热门面；预热实际执行还依赖 Curator 领导者锁、
已注册的预热驱动信息以及已启用的预热设置。

```text
usage: warmup -online
warmup -offline
warmup -start
warmup -stop
warmup -status
```

| 选项       | 说明               |
|------------|--------------------|
| `-online`  | 上线预热处理器     |
| `-offline` | 下线预热处理器     |
| `-start`   | 启动预热处理器     |
| `-stop`    | 停止预热处理器     |
| `-status`  | 查看预热处理器状态 |

上线预热处理器：

```text
warmup -online
预热处理器已上线!
OK
```

下线预热处理器：

```text
warmup -offline
预热处理器已下线!
OK
```

启动预热处理器：

```text
warmup -start
预热处理器已启动!
OK
```

停止预热处理器：

```text
warmup -stop
预热处理器已停止!
OK
```

查看预热处理器状态：

```text
warmup -status
online: true, latch holding: true, started: true, working: true.
OK
```

状态字段含义如下：

| 字段            | 说明                                                   |
|-----------------|--------------------------------------------------------|
| `online`        | 预热处理器是否处于上线状态                             |
| `latch holding` | 当前实例是否持有 Curator 预热领导者锁                  |
| `started`       | 预热处理器是否已启动                                   |
| `working`       | 当前实例是否正在执行预热工作                           |

`online` 与 `started` 是不同状态。只有持有领导者锁的实例才会执行预热工作，因此多实例部署时应结合
`latch holding` 和 `working` 判断预热是否在该实例实际运行。状态查询成功不表示预热设置或驱动配置正确，
预热执行失败时应继续检查预热设置、驱动信息、驱动加载配置及应用日志。

## warmupdrive

预热驱动处理器状态查看。当前实现只提供 `-status` 选项。

```text
usage: warmupdrive -status
```

| 选项      | 说明                   |
|-----------|------------------------|
| `-status` | 查看预热驱动处理器状态 |

查看状态：

```text
warmupdrive -status
started: true.
OK
```

`started: true.` 表示驱动处理器已启动，不等同于所有预热任务都已成功执行。
当前源码中的状态输出使用英文状态名，本文以实际输出为准。

## 执行前提

预热功能需要以下条件共同成立：

1. 预热处理器已上线并启动。
2. 当前实例成功持有 `curator/latch-path.properties` 配置的预热领导者锁。
3. 所使用的驱动提供器已由 Spring 容器加载。
4. 预热设置已启用，且其关联的驱动信息已启用。
5. 驱动参数与类型匹配，例如 Cron 表达式有效，固定间隔为正数。

当前 `opt/opt-warmup-driver.xml` 未在 `Launcher` 加载列表中，且默认未启用任何驱动器。仅启动 `warmup` 不会自动加载驱动，
也不会保证缓存已被预热。机制与驱动器说明参见 Wiki 的 [Warmup](../../../wiki/zh-CN/Warmup.md) 和
[Preset Warmup Driver Implements](../../../wiki/zh-CN/PresetWarmupDriverImplements.md)。

## 安全提示

- 修改预热处理器状态前，应确认集群中其他实例的 `latch holding` 与 `working` 状态。
- `reset -rw` 会重置预热处理并清理预热本地缓存，不属于普通查询操作。
- `warmup -stop` 与 `warmup -offline` 会改变预热调度范围，不应与常态业务停机或缓存清理混为一谈。
