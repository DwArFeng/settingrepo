# Telqos Manual - Telqos 使用手册

本手册用于指导运维人员连接、查询和操作 Setting Repository 的 Telqos 运维平台。手册与服务功能命令直接对应，
并提供可修改、可直接用于 `-jf` 选项的 JSON 输入文件。

## 适用范围

本手册覆盖以下内容：

- Telqos 的连接方式、端口、字符集和基础使用规则。
- Setting Repository 提供的基础命令、第三方命令和服务功能命令。
- 文件节点、图片节点、视频节点、文本节点、文本列表节点、国际化节点、键值对节点、导航节点和设置节点的操作方法。
- `-json` 与 `-jf` 选项的差异，以及 JSON 输入文件的格式规范。

各命令的具体可用版本以命令页面中的说明为准。

## 手册结构

| 文档                                                     | 说明                                                |
|----------------------------------------------------------|-----------------------------------------------------|
| [Connection](./Connection.md)                            | Telqos 连接、配置、字符集和工作目录                 |
| [JsonInput](./JsonInput.md)                              | JSON 字符串与 JSON 文件的使用规范                   |
| [CommandIndex](./CommandIndex.md)                        | 全部命令和业务命令的导航                            |
| [BasicCommands](./commands/BasicCommands.md)             | `lc`、`man`、`quit`                                 |
| [ThirdPartyCommands](./commands/ThirdPartyCommands.md)   | `datamark`、`dubbo`、`log4j2`、`memory`、`shutdown` |
| [MaintenanceCommands](./commands/MaintenanceCommands.md) | `flc`、`reset`、`support`                           |
| [WarmupCommands](./commands/WarmupCommands.md)           | `warmup`、`warmupdrive`                             |
| [FileNodes](./commands/FileNodes.md)                     | `filelnode`、`filenode`                             |
| [ImageNodes](./commands/ImageNodes.md)                   | `imglnode`、`imgnode`                               |
| [TextNodes](./commands/TextNodes.md)                     | `txtnode`、`textlnode`                              |
| [LongTextNode](./commands/LongTextNode.md)               | `ltxtnode`                                          |
| [IahnNode](./commands/IahnNode.md)                       | `iahnnode`                                          |
| [KvNode](./commands/KvNode.md)                           | `kvnode`                                            |
| [NavigationNode](./commands/NavigationNode.md)           | `navigationnode`                                    |
| [SettingNode](./commands/SettingNode.md)                 | `settingnode`                                       |
| [VideoNodes](./commands/VideoNodes.md)                   | `videolnode`、`videonode`                           |

## JSON 文件

手册的 [`json-file`](./json-file) 目录保存了全部可直接使用的 JSON 输入文件。目录结构与命令 `IDENTITY` 对应，
每个 sub command 对应一个独立文件：

```text
json-file/
├── filelnode/
├── filenode/
├── iahnnode/
├── imglnode/
├── imgnode/
├── kvnode/
├── ltxtnode/
├── navigationnode/
├── settingnode/
├── txtlnode/
├── txtnode/
├── videolnode/
└── videonode/
```

修改 JSON 文件后，可直接通过 `-jf` 使用。例如：

```text
txtnode -inspect -jf /opt/settingrepo/docs/telqos-manual/zh-CN/json-file/txtnode/inspect.json
```

`-jf` 的路径由 Telqos 服务进程解析，不是 Telnet 客户端所在机器的路径。生产环境建议使用服务端绝对路径。

各 JSON 文件的字段说明和相对链接请参阅对应的命令页面。Markdown 文档不内嵌完整输入 JSON，
避免文档内容与实际输入文件出现两套版本。

## 使用流程

1. 参考 [Connection](./Connection.md) 连接 Telqos。
2. 使用 `lc` 查看当前环境实际加载的命令。
3. 从 [CommandIndex](./CommandIndex.md) 定位需要执行的命令。
4. 阅读对应命令页面，确认节点标识、字段含义和操作风险。
5. 修改 [`json-file`](./json-file) 目录中的对应文件。
6. 通过 `-jf` 执行命令，并根据返回结果确认操作状态。

## 安全注意事项

- `remove`、`clear`、`update` 等操作可能覆盖或删除数据，执行前应确认 `category` 与 `args` 定位的节点。
- `clear-cache` 会清理查询缓存，但不删除业务数据；仍应确认缓存范围与节点定位，避免后续查询重新加载不符合预期的数据。
- `kvnode -cc` 只清理键值对节点查询缓存；`reset -rw` 会重置预热机制并清理预热本地缓存，两者影响范围不同。
- `warmup`、`warmupdrive` 的操作和状态查询依赖 Curator、预热驱动配置及预热设置，执行前应先确认集群运行状态。
- `support --reset-warmup-driver` 会重建预热驱动器支持快照，但不修改驱动信息或预热本地缓存；执行后应核对当前驱动器配置和预热状态。
- JSON 输入中的 `file_path` 是服务器本地路径。上传文件前应确认 Telqos 进程对源文件具有读取权限。
- 下载和更新操作可能覆盖目标文件，应避免使用生产系统中的关键文件路径。
- 不要在 JSON 文件中保存密码、令牌、私钥或其他敏感信息。
- 在生产环境执行批量操作前，应先在测试环境验证 JSON 内容和命令效果。
