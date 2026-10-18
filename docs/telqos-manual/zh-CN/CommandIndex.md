# Command Index - 命令索引

## 命令分类

Telqos 命令可分为以下类别：

| 类别         | 说明                                   | 文档                                                     |
|--------------|----------------------------------------|----------------------------------------------------------|
| 基础命令     | Telqos 内置的命令查询、帮助和退出功能  | [BasicCommands](./commands/BasicCommands.md)             |
| 第三方命令   | Spring Telqos 集成或项目扩展的运维命令 | [ThirdPartyCommands](./commands/ThirdPartyCommands.md)   |
| 服务维护命令 | 格式缓存、重置处理器和支持操作         | [MaintenanceCommands](./commands/MaintenanceCommands.md) |
| 节点操作命令 | Setting Repository 提供的业务节点操作  | 见下方业务命令表                                         |

## 业务命令

| IDENTITY         | 说明                 | JSON 目录                                              | 文档                                           |
|------------------|----------------------|--------------------------------------------------------|------------------------------------------------|
| `filelnode`      | 文件列表节点操作服务 | [json-file/filelnode](./json-file/filelnode)           | [FileNodes](./commands/FileNodes.md)           |
| `filenode`       | 文件节点操作服务     | [json-file/filenode](./json-file/filenode)             | [FileNodes](./commands/FileNodes.md)           |
| `imglnode`       | 图片列表节点操作服务 | [json-file/imglnode](./json-file/imglnode)             | [ImageNodes](./commands/ImageNodes.md)         |
| `imgnode`        | 图片节点操作服务     | [json-file/imgnode](./json-file/imgnode)               | [ImageNodes](./commands/ImageNodes.md)         |
| `txtnode`        | 文本节点操作服务     | [json-file/txtnode](./json-file/txtnode)               | [TextNodes](./commands/TextNodes.md)           |
| `ltxtnode`       | 长文本节点操作服务   | [json-file/ltxtnode](./json-file/ltxtnode)             | [TextNodes](./commands/TextNodes.md)           |
| `iahnnode`       | 国际化节点操作服务   | [json-file/iahnnode](./json-file/iahnnode)             | [IahnNode](./commands/IahnNode.md)             |
| `kvnode`         | 键值对节点操作服务   | [json-file/kvnode](./json-file/kvnode)                 | [KvNode](./commands/KvNode.md)                 |
| `navigationnode` | 导航节点操作服务     | [json-file/navigationnode](./json-file/navigationnode) | [NavigationNode](./commands/NavigationNode.md) |
| `settingnode`    | 设置节点操作服务     | [json-file/settingnode](./json-file/settingnode)       | [SettingNode](./commands/SettingNode.md)       |

## 服务维护命令

| IDENTITY  | 说明                 | 使用 JSON | 文档                                                     |
|-----------|----------------------|----------:|----------------------------------------------------------|
| `flc`     | 格式化器本地缓存操作 |        否 | [MaintenanceCommands](./commands/MaintenanceCommands.md) |
| `reset`   | 重置处理器操作和查看 |        否 | [MaintenanceCommands](./commands/MaintenanceCommands.md) |
| `support` | 支持操作             |        否 | [MaintenanceCommands](./commands/MaintenanceCommands.md) |

## 命令发现

连接 Telqos 后，可以使用以下命令确认当前环境实际加载的命令：

```text
lc
```

查看单个命令的运行时帮助：

```text
man <identity>
```

例如：

```text
man navigationnode
```

运行时帮助与当前部署版本直接对应。当本手册与运行时帮助不一致时，应以当前部署版本的实际行为和输入类为准。
