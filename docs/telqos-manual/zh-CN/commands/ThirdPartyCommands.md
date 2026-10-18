# Third Party Commands - 第三方命令

本页面说明项目集成或扩展的运维命令。第三方命令是否可用取决于当前部署版本的依赖和配置。

## datamark

数据标记服务，用于查看和刷新项目中的数据标记处理器。

```text
usage: datamark -lh
datamark -ua [-hn handler-name]
datamark -get [-hn handler-name]
datamark -refresh [-hn handler-name]
datamark -update [-hn handler-name] [-dv datamark-value]
```

| 选项                   | 说明                         |
|------------------------|------------------------------|
| `-lh,--list-handlers`  | 列出所有可用的数据标记处理器 |
| `-ua,--update-allowed` | 返回处理器是否允许更新       |
| `-get`                 | 获取数据标记值               |
| `-refresh`             | 刷新数据标记值               |
| `-update`              | 更新数据标记值               |
| `-hn`                  | 处理器名称                   |
| `-dv`                  | 新的数据标记值               |

更新数据标记会改变业务数据的同步依据，执行前应确认处理器名称和更新值。

## dubbo

查看 Dubbo 服务，并执行服务上线或下线。

```text
usage: dubbo -online [service-name]
dubbo -offline [service-name]
dubbo -ls
```

| 选项       | 说明                                   |
|------------|----------------------------------------|
| `-ls`      | 列出 Provider 和 Consumer 服务         |
| `-online`  | 上线服务；未指定服务名时按当前配置处理 |
| `-offline` | 下线服务；未指定服务名时按当前配置处理 |

服务下线会影响请求路由和调用方，生产环境执行前必须确认影响范围。

## log4j2

重新加载 Log4j2 配置。

```text
usage: log4j2 -reconfigure
```

修改日志配置后执行：

```text
log4j2 -reconfigure
```

重新配置过程不会主动校验所有日志路径的写权限，应在执行后检查日志输出是否正常。

## memory

查看 JVM 内存使用情况。

```text
usage: memory [-u unit]
```

| 选项 | 说明                               |
|------|------------------------------------|
| `-u` | 显示单位，例如 `KiB`、`MiB`、`GiB` |

不指定单位时使用默认显示单位。

## shutdown

关闭或重启程序。

```text
usage: shutdown [-s/-r] [-e exit-code] [-c comment]
```

| 选项 | 说明             |
|------|------------------|
| `-s` | 退出程序         |
| `-r` | 重启程序         |
| `-e` | 指定进程退出代码 |
| `-c` | 填写操作备注     |

当前项目的重启功能不可用，只能使用关闭功能。执行关闭命令后，Telqos 会要求再次确认：

```text
shutdown -s -c "routine maintenance"
```

关闭后需要通过远程主机或服务管理器重新启动程序。
