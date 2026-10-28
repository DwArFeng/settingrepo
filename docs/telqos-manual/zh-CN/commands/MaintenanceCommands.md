# Maintenance Commands - 服务维护命令

本页面说明格式化器缓存、重置处理器和支持操作。预热处理器的启停、上线和状态查看使用
[WarmupCommands](./WarmupCommands.md)。

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
reset -rf,--reset-format
reset -rw,--reset-warmup
```

| 选项                 | 说明               |
|----------------------|--------------------|
| `-l`                 | 查看全部重置处理器 |
| `-start`             | 启动重置处理器     |
| `-stop`              | 停止重置处理器     |
| `-status`            | 查看重置处理器状态 |
| `-rf,--reset-format` | 执行格式化重置操作 |
| `-rw,--reset-warmup` | 执行预热重置操作   |

查看处理器：

```text
reset -l
```

查看状态：

```text
reset -status
```

执行格式化重置：

```text
reset -rf
重置成功!
OK
```

执行预热重置：

```text
reset -rw
重置成功!
OK
```

格式化重置会清理格式化器本地缓存，并尝试推送格式化重置消息。预热重置会停止当前预热处理、清理预热本地缓存，
再按重置前的启动状态恢复，并尝试推送预热重置消息。预热重置不会重新上线预热处理器或重新获取领导者锁。

两种重置操作都会影响后续请求的缓存加载，执行前应确认当前维护窗口和影响范围。`reset -rw` 与
`kvnode -cc` 的作用对象不同：前者重置预热机制，后者只清理指定键值对节点的查询缓存。

## support

支持操作命令。

```text
usage: support --reset-formatter
support --reset-warmup-driver
```

| 选项                      | 说明                 |
|---------------------------|----------------------|
| `--reset-formatter`       | 重置格式化器支持     |
| `--reset-warmup-driver`   | 重置预热驱动器支持   |

两个选项都只提供长选项形式，每次执行只能选择一个。重置格式化器支持：

```text
support --reset-formatter
重置格式化器支持成功。
OK
```

重置预热驱动器支持：

```text
support --reset-warmup-driver
重置预热驱动器支持成功。
OK
```

重置格式化器支持会删除已有格式化器支持信息，并根据当前 `FormatterSupporter` 重新写入。
重置预热驱动器支持会删除已有预热驱动器支持信息，并根据当前 `WarmupDriverSupporter`
重新写入 `WarmupDriverSupport`。后者不修改 `WarmupDriverInfo`、预热设置或预热本地缓存，
也不会启动或上线预热处理器。执行前应确认当前启用的可选驱动器配置，执行后应核对支持快照和预热状态。
