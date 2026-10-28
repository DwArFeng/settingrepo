# Warmup - 预热机制

## 说明

预热机制是 SettingRepo 主动加载节点查询结果缓存的机制。
业务查询处理器在读取键值对节点条目、导航节点或国际化节点时，会使用相应的查询结果缓存，
预热处理器则按照预热设置和预热驱动信息的配置，在业务请求到达之前触发相同的查询流程。

预热设置由 `WarmupSetting` 描述。每条预热设置通过 `type` 指定缓存目标，通过目标字段保存节点定位和查询条件，
通过 `enabled` 控制是否参与预热。预热驱动信息由 `WarmupDriverInfo` 描述，
通过 `warmupSettingKey` 关联一条预热设置，通过 `type` 选择驱动策略，通过 `param` 保存该策略的调度参数。

## 设计理由

部分节点查询需要组合多条数据或完成较复杂的转换。
如果缓存只在首个业务请求到达时才加载，高峰期的首个请求可能承担较高的延迟，多个实例也可能同时加载相同的查询结果。

预热机制将这些查询提前到可配置的调度周期，以预热设置隔离目标查询，以驱动信息隔离调度策略。
同一个预热设置可以关联多条驱动信息，同一个驱动策略也可以服务多条预热设置。
预热设置只描述目标查询，驱动信息只描述执行时机，二者分离后可以在不修改节点查询逻辑的情况下调整预热计划。

预热调度由 Curator 分布式锁限制在集群中的单个工作节点上。
普通业务缓存仍可被多个实例读取，而预热注册和触发只由持锁节点执行，避免不同节点重复建立同一组调度。

预热驱动器不直接访问数据库、Redis 或节点操作处理器。
驱动器只负责注册和取消调度，实际预热通过 `WarmupDriver.Context` 回到业务层执行，
因此同一种触发策略可以驱动任意受支持的缓存类型。

## 接口

预热处理器 `WarmupHandler` 是一个继承自分布式锁处理器的接口，用于维护预热调度的上线、下线、启停与持锁状态。
该接口向 `WarmupQosService` 提供集群预热门面的控制能力。

预热驱动处理器 `WarmupDriveHandler` 是一个可启停处理器，用于注册和取消当前节点上的全部预热驱动。

预热驱动器查找处理器 `WarmupDriverHandler` 是一个接口，有如下方法：

| 方法签名                         | 说明                         |
|----------------------------------|------------------------------|
| `WarmupDriver find(String type)` | 根据驱动类型查找对应的驱动器 |

预热驱动器 `WarmupDriver` 是一个接口，有如下方法：

| 方法签名                                       | 说明                     |
|------------------------------------------------|--------------------------|
| `void init(Context context)`                   | 初始化驱动器上下文       |
| `void register(WarmupDriverInfo driverInfo)`   | 注册指定驱动信息         |
| `void unregisterAll()`                         | 解除注册全部驱动信息     |

预热驱动上下文 `WarmupDriver.Context` 是驱动器与业务层的交互接口，有如下方法：

| 方法签名                              | 说明                       |
|---------------------------------------|----------------------------|
| `void warmup(LongIdKey settingKey)`   | 预热指定主键对应的预热设置 |

预热驱动器提供器 `WarmupDriverProvider` 是 SDK 扩展接口，有如下方法：

| 方法签名                           | 说明                       |
|------------------------------------|----------------------------|
| `boolean supportType(String type)` | 判断提供器是否支持指定类型 |
| `WarmupDriver provide()`           | 提供对应类型的驱动器       |

预热驱动器支持器 `WarmupDriverSupporter` 是 SDK 扩展接口，有如下方法：

| 方法签名                        | 说明                         |
|---------------------------------|------------------------------|
| `String provideType()`          | 提供驱动器类型               |
| `String provideLabel()`         | 提供显示标签                 |
| `String provideDescription()`   | 提供类型说明                 |
| `String provideExampleParam()`  | 提供示例参数                 |

本地缓存处理器 `WarmupLocalCacheHandler` 按预热设置主键保存预热上下文，继承自通用本地缓存接口。
预热 QOS 服务 `WarmupQosService` 对外提供预热处理器状态查询、上线、下线、启动和停止能力。
驱动 QOS 服务 `WarmupDriveQosService` 提供预热本地缓存查询和清理能力。

具体方法的异常和线程安全约束请参阅接口的 JavaDoc。

## 预热设置信息

预热设置由 `WarmupSetting` 实体描述，核心字段如下。

| 字段名                   | 类型        | 说明                                             |
|--------------------------|-------------|--------------------------------------------------|
| `key`                    | `LongIdKey` | 预热设置主键，同时作为预热本地缓存主键。         |
| `type`                   | `int`       | 预热目标类型，与设置节点类型属于相互独立的空间。 |
| `targetStringAlpha`      | `String`    | 预热字符串 1#，含义由 `type` 决定。              |
| `targetStringBravo`      | `String`    | 预热字符串 2#，含义由 `type` 决定。              |
| `targetStringCharlie`    | `String`    | 预热字符串 3#，含义由 `type` 决定。              |
| `targetStringDelta`      | `String`    | 预热字符串 4#，含义由 `type` 决定。              |
| `targetStringEcho`       | `String`    | 预热字符串 5#，含义由 `type` 决定。              |
| `targetStringArrayAlpha` | `String[]`  | 设置类别所属节点的参数数组。                     |
| `enabled`                | `boolean`   | 是否启用该预热设置。                             |
| `remark`                 | `String`    | 预热设置备注。                                   |

预热目标类型及取值如下：

| 类型值 | 目标                   | 目标字符串字段                                  |
|-------:|------------------------|-------------------------------------------------|
|    `0` | 键值对节点条目查询结果 | 设置类别、条目字符串 ID；节点参数使用目标数组。 |
|   `10` | 导航节点查询结果       | 设置类别；节点参数使用目标数组。                |
|   `20` | 国际化节点地区映射     | 设置类别、语言、国家、变体；参数使用目标数组。  |
|   `21` | 国际化节点消息映射     | 设置类别、语言、国家、变体；参数使用目标数组。  |
|   `22` | 国际化节点地区列表     | 设置类别；节点参数使用目标数组。                |
|   `23` | 国际化节点 Mek 列表    | 设置类别；节点参数使用目标数组。                |
|   `24` | 国际化节点消息表       | 设置类别；节点参数使用目标数组。                |

目标字符串字段的具体用途取决于 `type`，完整定义以 `WarmupSetting` 的 JavaDoc 为准。

预热驱动信息由 `WarmupDriverInfo` 实体描述，核心字段如下：

| 字段名              | 类型        | 说明                                                   |
|---------------------|-------------|--------------------------------------------------------|
| `key`               | `LongIdKey` | 预热驱动信息主键。                                     |
| `warmupSettingKey`  | `LongIdKey` | 所属预热设置的主键。                                   |
| `enabled`           | `boolean`   | 是否启用该驱动信息。                                   |
| `type`              | `String`    | 驱动策略类型，用于匹配 `WarmupDriverProvider`。        |
| `param`             | `String`    | 驱动策略参数，格式由 `type` 决定并由驱动器负责解析。   |
| `remark`            | `String`    | 预热驱动信息备注。                                     |

预热驱动器支持信息由 `WarmupDriverSupport` 实体描述，核心字段如下：

| 字段名         | 类型          | 说明                       |
|----------------|---------------|----------------------------|
| `key`          | `StringIdKey` | 预热驱动器类型。           |
| `label`        | `String`      | 预热驱动器显示标签。       |
| `description`  | `String`      | 预热驱动器说明。           |
| `exampleParam` | `String`      | 预热驱动器示例参数内容。   |

`WarmupDriverSupporter` 提供驱动器类型的展示信息，`WarmupDriverSupport` 保存当前可用的支持快照。
`WarmupDriverSupportMaintainService` 提供该支持快照的查询和维护能力。
支持快照与 `WarmupDriverInfo` 分离：前者描述当前可用的驱动类型，后者描述具体预热设置的调度配置。

本地缓存的预热上下文由 `WarmupLocalCache` 描述，包含对应的 `WarmupSetting`，
以及由 `WarmupDriverInfo` 和 `WarmupDriver` 组成的驱动映射。

## 预热生命周期

### 构造

`WarmupDriverHandlerImpl` 初始化时遍历容器中的 `WarmupDriverProvider`，
调用各提供器的 `provide()` 获取驱动器，并通过 `WarmupDriver.init(context)` 注入统一的预热上下文。

当预热本地缓存未命中时，`WarmupLocalCacheHandlerImpl.WarmupLocalCacheFetcher` 读取启用的预热设置，
查询属于该设置的已启用驱动信息，再按每条驱动信息的 `type` 查找驱动器，
最终构造包含预热设置和驱动映射的 `WarmupLocalCache`。

### 缓存

构造成功的预热上下文写入 `WarmupLocalCacheHandler`，键为预热设置的 `LongIdKey`。
本地缓存只保存启用的预热设置；预热设置本身未启用时，缓存查询会直接判定为不存在。

`WarmupSetting` 和 `WarmupDriverInfo` 实体还可通过 Redis 缓存加速读取。
本地缓存中的预热上下文包含已解析的驱动映射，因此配置变更后应清理预热本地缓存，使下一次加载读取最新内容。

### 执行预热

驱动处理器在持锁节点启动后读取全部启用的预热设置，并从预热本地缓存取得驱动映射。
对于每条驱动信息，驱动处理器调用相应 `WarmupDriver.register(driverInfo)`，由驱动策略建立调度。

调度到达执行时间时，驱动器通过 `WarmupDriver.Context.warmup(warmupSettingKey)` 回调业务层。
`WarmupDriverHandlerImpl` 重新读取该主键对应的 `WarmupSetting`，根据 `type` 调用
`KvNodeOperateHandler`、`NavigationNodeOperateHandler` 或 `IahnNodeOperateHandler` 的预热操作方法，
使用 `targetStringAlpha`、`targetStringArrayAlpha` 等目标字段作为查询条件。

单次预热发生异常时，驱动器记录告警日志并放弃该次执行，已有调度继续工作。
驱动信息注册失败时，驱动处理器记录告警并忽略该条驱动信息，不会阻止其他驱动继续注册。

### 清理与重置

`ResetProcessor.resetWarmup()` 记录预热处理器原有的启动状态，停止预热处理器以取消当前调度，
清空预热本地缓存，并在原有状态为启动时重新启动，尝试推送预热重置消息。该操作不重新执行上线，
因此不会主动重新获取已经下线的预热领导者锁。

`WarmupDriveQosService.clearLocalCache()` 可清空全部预热本地缓存；
`getWarmupLocalCache(LongIdKey)` 可读取指定预热设置的本地缓存。
Telqos 命令 `reset -rw` 使用预热重置能力，`warmup` 控制预热处理器的上线、下线、启停和状态。

`SupportHandler.resetWarmupDriver()` 用于重建预热驱动器支持快照。该操作先删除已有支持记录，
再遍历当前 Spring 容器中的 `WarmupDriverSupporter`，根据其提供的信息写入新的
`WarmupDriverSupport`。Telqos 命令 `support --reset-warmup-driver` 调用同一流程。
该操作不会修改或删除 `WarmupDriverInfo`，也不会启动、停止或重新上线预热处理器。

键值对节点的查询结果缓存不由预热本地缓存管理。修改该节点条目后会清理对应查询缓存，
Telqos 命令 `kvnode -cc` 可按节点主动清理该缓存，且不删除业务条目。

## 工作机制

预热的完整调用链如下：

1. `WarmupDriverHandlerImpl` 在初始化阶段向容器中的驱动器注入预热上下文。
2. `WarmupHandler` 通过 Curator 领导者锁确定集群中唯一的工作节点。
3. 持锁节点的 `WarmupDriveHandler` 启动，并读取全部启用的 `WarmupSetting`。
4. `WarmupLocalCacheHandler` 读取预热设置及其已启用驱动，按每个驱动的 `type` 查找 `WarmupDriver`。
5. `WarmupDriveHandler` 调用 `WarmupDriver.register(WarmupDriverInfo)`，由具体策略注册调度。
6. 调度触发时，驱动器通过 `WarmupDriver.Context.warmup(LongIdKey)` 请求执行预热。
7. `WarmupDriverHandlerImpl` 根据预热设置类型调用对应节点操作处理器，主动加载查询结果缓存。

预热驱动器只负责调度的注册、取消和触发回调，不直接执行节点查询。
目标节点是否存在、节点类型是否匹配以及查询条件是否有效，由具体节点操作处理器负责检查。
节点查询结果写入哪一层缓存，也由对应的节点操作处理器决定。

## 运维与配置

使用预热机制时，需要准备启用的预热设置及其关联的启用驱动信息。
预热设置的 `type` 必须属于受支持的缓存类型，目标字段必须符合该类型的查询要求；
驱动信息的 `type` 必须与当前 Spring 容器中的 `WarmupDriverProvider` 匹配，参数必须符合该策略的格式要求。

预热处理器依赖 Curator 和 Redis。ZooKeeper 连接配置位于 `conf/curator/connection.properties`，
预热领导者锁路径由 `conf/curator/latch-path.properties` 配置，
启动时的预热上线与启用延时由 `conf/settingrepo/launcher.properties` 控制。

预热驱动信息通过 `settingrepo/warmup-driver.properties` 提供额外配置；当前三种预设驱动器没有由该文件提供额外参数。
当前 `Launcher` 加载 `file:opt/opt*.xml` 与 `file:optext/opt*.xml`，发布资源中的
`opt/opt-warmup-driver.xml` 默认未启用任何驱动器。需要启用预设驱动器时，应同时取消对应
Provider 与 Supporter 的注释并重启服务，再确认驱动器已注册。

替换或新增驱动器实现后，可通过 `support --reset-warmup-driver` 将当前已加载的
`WarmupDriverSupporter` 信息重建到 `WarmupDriverSupport` 中。该命令只刷新支持快照，
不会自动清理预热本地缓存；需要应用新的驱动配置时，应结合预热重置或重启流程。

预热处理器上线并启动后，必须由持有领导者锁的节点执行预热。
状态查询不能替代对预热设置、驱动信息、注册失败及单次预热失败日志的检查。

## 参阅

- [Preset Warmup Driver Implements](./PresetWarmupDriverImplements.md) - 预设预热驱动器实现，详细说明了本项目内置的预热驱动器。
- [Conf Directory](./ConfDirectory.md) - 配置目录说明，详细说明了 Curator、Redis、预热与启动配置。
- [Opt Directory](./OptDirectory.md) - 可选配置目录说明，详细说明了预热驱动器的加载配置。
