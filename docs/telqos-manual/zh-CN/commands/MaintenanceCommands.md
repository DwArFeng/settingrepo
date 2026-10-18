# Maintenance Commands - 服务维护命令

本页面说明格式化器缓存、重置处理器和支持操作。

## flc

格式化器本地缓存操作。

```text
usage: flc -l id
flc -c
```

| 选项      | 说明                       |
|-----------|----------------------------|
| `-l <id>` | 查询指定设置类别的格式化器 |
| `-c`      | 清除格式化器本地缓存       |

查询格式化器：

```text
flc -l foobar
```

清除格式化器本地缓存：

```text
flc -c
```

清除缓存后，后续节点操作可能重新加载格式化器或重新读取格式化器配置。

## reset

重置处理器操作与状态查看。

```text
usage: reset -l
reset -start
reset -stop
reset -status
reset --reset-format
```

| 选项             | 说明               |
|------------------|--------------------|
| `-l`             | 查看全部重置处理器 |
| `-start`         | 启动重置处理器     |
| `-stop`          | 停止重置处理器     |
| `-status`        | 查看重置处理器状态 |
| `--reset-format` | 执行格式化重置操作 |

查看处理器：

```text
reset -l
```

查看状态：

```text
reset -status
```

重置格式化操作可能与业务格式化状态相关，执行前应确认当前维护窗口和影响范围。

## support

支持操作命令。

```text
usage: support --reset-formatter
```

| 选项                | 说明             |
|---------------------|------------------|
| `--reset-formatter` | 重置格式化器支持 |

该选项只提供长选项形式，执行示例：

```text
support --reset-formatter
```

重置格式化器支持会重新初始化格式化器相关支持内容。执行后应通过节点查询命令确认业务结果。
