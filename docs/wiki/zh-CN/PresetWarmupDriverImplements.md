# Preset Warmup Driver Implements - 预设预热驱动器实现

## 说明

本文档详细说明了本项目内置的所有预热驱动器。

内置预热驱动器提供器和支持器的代码位于 `com.dwarfeng.settingrepo.impl.handler.wudriver` 包中，
您可以通过查看该包下的实现类了解本项目内置的预热驱动器类型和调度方式。

项目中所有的内置预热驱动器如下：

| 类型                  | 提供器                             | 支持器                            | 说明                                           |
|-----------------------|------------------------------------|-----------------------------------|------------------------------------------------|
| `cron_driver`         | `CronWarmupDriverProvider`         | `CronWarmupDriverSupporter`       | 通过 Cron 表达式按日历时间触发预热。           |
| `fixed_delay_driver`  | `FixedDelayWarmupDriverProvider`   | `FixedDelayWarmupDriverSupporter` | 上一次预热完成后等待固定间隔再执行下一次预热。 |
| `fixed_rate_driver`   | `FixedRateWarmupDriverProvider`    | `FixedRateWarmupDriverSupporter`  | 按固定频率触发预热，执行延迟时保留原计划时间。 |

以下实现的示例参数会作为对应 `WarmupDriverSupporter.provideExampleParam()` 的返回值。
实际使用时，应根据预热频率、查询耗时和系统负载调整 `WarmupDriverInfo.param`。

## CronWarmupDriverProvider

### 介绍

`CronWarmupDriverProvider` 是基于 Cron 表达式的预热驱动器提供器，类型为 `cron_driver`。
它从 `WarmupDriverInfo.param` 读取 Cron 表达式，使用 Spring `CronTrigger` 安排调度，
并在触发时通过 `WarmupDriver.Context` 预热对应的预热设置。

该提供器返回 `CronWarmupDriver`。驱动注册时创建一个 Cron 处理器，
之后由 `ThreadPoolTaskScheduler` 按 Cron 表达式调用该处理器。
驱动取消注册时，会取消已注册的调度并停止对应的处理器。

### 参数

| 字段名             | 类型        | 说明                                 |
|--------------------|-------------|--------------------------------------|
| `warmupSettingKey` | `LongIdKey` | 必须关联有效的预热设置。             |
| `type`             | `String`    | 必须填写 `cron_driver`。             |
| `param`            | `String`    | Spring 支持的 Cron 表达式。          |
| `enabled`          | `boolean`   | 必须为 `true` 才能参与预热驱动注册。 |

驱动器使用以下方法注册和取消调度：

| 方法签名                                     | 说明                         |
|----------------------------------------------|------------------------------|
| `void register(WarmupDriverInfo driverInfo)` | 为指定预热设置注册 Cron 调度 |
| `void unregisterAll()`                       | 取消全部 Cron 调度           |

需要确认 Cron 表达式符合 Spring 使用的六字段格式。表达式无效时，驱动注册失败并在驱动处理器中记录告警。

### 示例参数

以下示例每五分钟触发一次预热：

```text
0 0/5 * * * *
```

该值对应 `CronWarmupDriverSupporter.provideExampleParam()` 的返回值，应写入 `WarmupDriverInfo.param`。

## FixedDelayWarmupDriverProvider

### 介绍

`FixedDelayWarmupDriverProvider` 是基于固定延迟的预热驱动器提供器，类型为 `fixed_delay_driver`。
它从 `WarmupDriverInfo.param` 读取间隔毫秒数，使用 `ThreadPoolTaskScheduler.scheduleWithFixedDelay`
安排本次预热完成后再等待指定时间执行下一次预热。

该提供器返回 `FixedDelayWarmupDriver`。
前一次预热执行时间较长时，下次调用会在其完成后再计算延迟，因此相邻两次执行开始时间的间隔可能随查询耗时增长。

### 参数

| 字段名             | 类型        | 说明                                      |
|--------------------|-------------|-------------------------------------------|
| `warmupSettingKey` | `LongIdKey` | 必须关联有效的预热设置。                  |
| `type`             | `String`    | 必须填写 `fixed_delay_driver`。           |
| `param`            | `String`    | 固定延迟的毫秒数，必须能够解析为 `long`。 |
| `enabled`          | `boolean`   | 必须为 `true` 才能参与预热驱动注册。      |

驱动器使用以下方法注册和取消调度：

| 方法签名                                     | 说明                             |
|----------------------------------------------|----------------------------------|
| `void register(WarmupDriverInfo driverInfo)` | 为指定预热设置注册固定延迟调度   |
| `void unregisterAll()`                       | 取消全部固定延迟调度             |

需要确认参数可以解析为合适的延迟值，避免配置负值或无法解析的内容。注册异常由驱动处理器记录为告警。

### 示例参数

以下示例在每次预热完成六十秒后执行下一次预热：

```text
60000
```

该值对应 `FixedDelayWarmupDriverSupporter.provideExampleParam()` 的返回值，单位为毫秒。

## FixedRateWarmupDriverProvider

### 介绍

`FixedRateWarmupDriverProvider` 是基于固定频率的预热驱动器提供器，类型为 `fixed_rate_driver`。
它从 `WarmupDriverInfo.param` 读取间隔毫秒数，使用 `ThreadPoolTaskScheduler.scheduleAtFixedRate`
安排固定频率调用。

该提供器返回 `FixedRateWarmupDriver`。
调度按固定频率安排后续执行，即使某次预热执行延迟，后续计划仍以原计划时间为准，不受本次执行耗时的影响。

### 参数

| 字段名             | 类型        | 说明                                      |
|--------------------|-------------|-------------------------------------------|
| `warmupSettingKey` | `LongIdKey` | 必须关联有效的预热设置。                  |
| `type`             | `String`    | 必须填写 `fixed_rate_driver`。            |
| `param`            | `String`    | 固定频率的毫秒数，必须能够解析为 `long`。 |
| `enabled`          | `boolean`   | 必须为 `true` 才能参与预热驱动注册。      |

驱动器使用以下方法注册和取消调度：

| 方法签名                                     | 说明                             |
|----------------------------------------------|----------------------------------|
| `void register(WarmupDriverInfo driverInfo)` | 为指定预热设置注册固定频率调度   |
| `void unregisterAll()`                       | 取消全部固定频率调度             |

预热执行耗时超过配置频率时，应重新评估实际负载，避免调度堆积或频繁触发。
单次预热异常由驱动器记录告警，并允许后续调度继续执行。

### 示例参数

以下示例每六十秒触发一次预热：

```text
60000
```

该值对应 `FixedRateWarmupDriverSupporter.provideExampleParam()` 的返回值，单位为毫秒。

## 参阅

- [Warmup](./Warmup.md) - 预热机制，详细说明了本项目的预热机制。
- [Opt Directory](./OptDirectory.md) - 可选配置目录说明，详细介绍了本项目的可选配置，即 `opt/` 目录下的内容。
