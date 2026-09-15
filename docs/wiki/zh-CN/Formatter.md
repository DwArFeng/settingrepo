# Formatter - 格式化器

## 说明

格式化器是 SettingRepo 根据设置类别和调用参数生成设置节点主键的机制。
业务操作处理器在访问文本、图片、文件等设置节点时，会将操作参数交给格式化器，
由格式化器返回目标设置节点的 `StringIdKey`，随后再由对应的节点操作处理器读取或写入节点。

格式化器由 `SettingCategory` 描述。一个设置类别通过 `formatterType` 指定格式化器类型，
通过 `formatterParam` 保存该类型所需的参数，因此同一种格式化器可以被多个设置类别使用，
每个类别也可以拥有独立的格式化参数。

## 设计理由

设置节点的业务参数通常以具备层级关系的 `args[]` 数组传入。
格式化器将这些参数转换为设置节点的唯一键 `StringIdKey`，使同一组层级参数能够稳定地定位到同一个节点。

格式化器只负责参数到唯一键的转换，不负责设置节点的持久化和内容读写。
`Formatter`、`FormatterMaker`、`formatterType` 与 `formatterParam` 将转换逻辑和节点操作流程解耦，
节点操作处理器可以复用统一的调用流程，而无需了解具体的键生成算法。

转换细节由具体格式化器决定，用户可以根据业务需要自定义参数拼接、层级编码、格式校验以及其它转换规则。
格式化器注册器负责声明类型和构造方式，设置类别负责保存实际使用的类型与参数，
因此同一种格式化器能够服务于多个类别，每个类别也可以拥有独立的转换逻辑。

格式化器机制同时将构造、缓存和执行职责分离：`FormatterHandler` 负责选择制造器并构造对象，
`FormatLocalCacheHandler` 负责按设置类别主键缓存对象，具体 `Formatter` 负责执行参数到节点主键的转换。
该结构便于扩展新的格式化器类型，也便于在配置变更后清理缓存并重新构造格式化器。

## 接口

格式化器 `Formatter` 是一个接口，有如下方法：

| 方法签名                            | 说明                         |
|-------------------------------------|------------------------------|
| `StringIdKey format(String[] args)` | 根据调用参数生成设置节点主键 |

格式化器处理器 `FormatterHandler` 是一个接口，有如下方法：

| 方法签名                                          | 说明                     |
|---------------------------------------------------|--------------------------|
| `Formatter make(SettingCategory settingCategory)` | 根据设置类别构造格式化器 |

格式化器构造器 `FormatterMaker` 是 SDK 扩展接口，有如下方法：

| 方法签名                                                   | 说明                       |
|------------------------------------------------------------|----------------------------|
| `boolean supportType(String type)`                         | 判断构造器是否支持指定类型 |
| `Formatter makeFormatter(SettingCategory settingCategory)` | 根据设置类别生成格式化器   |

格式化器支持器 `FormatterSupporter` 是 SDK 扩展接口，有如下方法：

| 方法签名                         | 说明             |
|----------------------------------|------------------|
| `String provideType()`           | 提供格式化器类型 |
| `String provideLabel()`          | 提供显示标签     |
| `String provideDescription()`    | 提供类型说明     |
| `String provideExampleContent()` | 提供示例参数内容 |

本地缓存处理器 `FormatLocalCacheHandler` 按设置类别主键保存格式化器，继承自通用本地缓存接口。
格式化 QOS 服务 `FormatQosService` 对外提供格式化器查询和本地缓存清理能力。

具体方法的异常和线程安全约束请参阅接口的 JavaDoc。

## 格式化器信息

设置类别由 `SettingCategory` 实体描述，核心字段如下。

| 字段名           | 类型          | 说明                                                        |
|------------------|---------------|-------------------------------------------------------------|
| `key`            | `StringIdKey` | 设置类别主键，同时作为格式化器本地缓存主键。                |
| `formatterType`  | `String`      | 格式化器类型，用于匹配 `FormatterMaker`。                   |
| `formatterParam` | `String`      | 格式化器参数，内容由具体类型解释，Groovy 类型使用脚本文本。 |
| `remark`         | `String`      | 设置类别备注。                                              |

格式化器支持信息由 `FormatterSupport` 实体描述，核心字段如下：

| 字段名         | 类型          | 说明                   |
|----------------|---------------|------------------------|
| `key`          | `StringIdKey` | 格式化器类型。         |
| `label`        | `String`      | 格式化器显示标签。     |
| `description`  | `String`      | 格式化器说明。         |
| `exampleParam` | `String`      | 格式化器示例参数内容。 |

程序通过 `FormatterHandler` 遍历容器中的 `FormatterMaker`，选择支持 `formatterType` 的制造器。
内置注册器通常同时实现 `FormatterMaker` 和 `FormatterSupporter`，既提供构造能力，也提供支持信息。

## 格式化器生命周期

### 构造

当系统需要某个设置类别对应的格式化器时，`FormatLocalCacheHandler` 首先查询本地缓存。
缓存不存在时，`FormatterFetcher` 读取设置类别实体，并调用 `FormatterHandler.make(settingCategory)`。

`FormatterHandlerImpl` 会按顺序查找支持指定类型的 `FormatterMaker`，并调用其
`makeFormatter(SettingCategory)` 方法。没有匹配的制造器时抛出 `UnsupportedFormatterTypeException`；
制造器构造失败时包装为 `FormatterMakeException`。

### 缓存

构造成功的格式化器会写入 `FormatLocalCacheHandler`，键为设置类别的 `StringIdKey`。
同一设置类别后续再次获取时直接复用缓存对象，不会重复解析 `formatterParam`。

格式化器本身通常包含设置类别上下文和解析后的执行资源，因此修改设置类别后，
需要清理对应缓存或清空全部格式化器缓存，才能让新配置生效。

### 执行格式化

节点操作处理器从格式化器缓存获取格式化器，并调用 `format(String[] args)`。
格式化器根据参数生成目标设置节点主键；节点操作处理器再使用该主键查询、初始化、更新或删除设置节点。

具体格式化器在执行过程中发生异常时，应抛出 `FormatterExecutionException` 或其它 `FormatterException` 子类，
由上层服务转换为对应的服务异常。

### 清理与重置

`FormatQosService.clearLocalCache()` 可清空格式化器本地缓存；Telqos 命令 `flc -c` 使用该能力。
查询缓存中的格式化器可使用 `flc -l <设置类别主键>`。

执行格式化重置时，`ResetProcessor` 会清空本地缓存，并通过 `PushHandler.formatReset()` 通知推送端。
推送失败只记录日志，不阻止本地缓存清理完成。

## 工作机制

格式化器的完整调用链如下：

1. 节点操作接口接收设置类别主键和业务参数数组 `args`。
2. `FormatLocalCacheHandler` 根据设置类别主键查找格式化器。
3. 缓存未命中时，`FormatterFetcher` 读取 `SettingCategory`，并交给 `FormatterHandler` 构造。
4. `FormatterHandler` 根据 `formatterType` 选择匹配的 `FormatterMaker`。
5. 制造器解析 `formatterParam` 并生成 `Formatter` 实例，实例随后进入本地缓存。
6. 节点操作处理器调用 `Formatter.format(args)` 获取 `StringIdKey`。
7. 节点操作处理器使用该主键继续执行设置节点的具体业务操作。

格式化器只负责参数到节点主键的映射，不负责节点实体的持久化和节点内容读写。
节点主键是否存在、节点类型是否匹配以及节点的可访问性，由后续节点操作流程负责检查。

## 运维与配置

使用格式化器时，需要维护一条 `SettingCategory`：设置唯一主键，填写受支持的 `formatterType`，
并将符合该类型要求的内容写入 `formatterParam`。
类型必须与当前 Spring 容器中已加载的 `FormatterMaker` 匹配，否则构造时会抛出不支持类型异常。

格式化器支持信息由 `SupportHandler.resetFormatter()` 根据容器中的 `FormatterSupporter` 重建，
可通过格式化支持重置流程将内置类型、标签、说明和示例内容写入数据库。

修改 `formatterParam` 后，已经构造并缓存的格式化器不会自动替换。
应先执行 `flc -c` 清除本地缓存，或执行格式化重置操作，使后续请求按照新配置重新构造格式化器。
在多节点部署中，还应通过项目配置的推送机制通知其它节点清理各自的格式化器缓存。

Groovy 格式化器会动态编译 `formatterParam` 中的脚本并实例化处理器，
脚本应只实现约定的 `Processor` 接口，并确保返回的主键格式与设置节点配置一致。

## 参阅

- [Preset Formatter Implements](./PresetFormatterImplements.md) - 预设格式化器实现，详细说明了本项目内置的格式化器。
