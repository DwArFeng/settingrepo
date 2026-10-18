# Connection - 连接 Telqos

## 配置项

Telqos 的端口号、字符集和连接过滤规则配置在：

```text
conf/telqos/connection.properties
```

主要配置项如下：

| 配置项                                            | 说明                 | 默认值  |
|---------------------------------------------------|----------------------|---------|
| `com.dwarfeng.settingrepo.telqos.port`            | Telnet 监听端口      | `23`    |
| `com.dwarfeng.settingrepo.telqos.charset`         | Telqos 字符集        | `UTF-8` |
| `com.dwarfeng.settingrepo.telqos.whitelist_regex` | 客户端 IP 白名单正则 | 空      |
| `com.dwarfeng.settingrepo.telqos.blacklist_regex` | 客户端 IP 黑名单正则 | 空      |

同一主机部署多个 Telqos 项目时，各项目的监听端口不能冲突。

白名单和黑名单同时配置时，客户端 IP 必须匹配白名单且不能匹配黑名单，Telqos 才会接受连接。

## 字符集

Linux 环境通常使用 `UTF-8`。Windows 环境中的 Telnet 客户端受控制台代码页影响，
常见情况下需要将 Telqos 字符集配置为 `GBK`。
如果中文显示乱码，应同时检查：

- `connection.properties` 中的字符集。
- Telnet 客户端当前代码页。
- JSON 文件本身的编码，JSON 文件应保持 UTF-8。

## 建立连接

使用任意支持 Telnet 的客户端连接：

```text
telnet <host> <port>
```

其中 `<host>` 为 Telqos 所在主机地址，`<port>` 为配置的监听端口。

连接成功后，Telqos 会输出版本和欢迎信息。看到欢迎信息后，可以输入命令。

## 查看命令

连接后使用 `lc` 查看当前环境加载的全部命令：

```text
lc
```

使用 `man` 查看指定命令的帮助：

```text
man txtnode
```

## 工作目录与文件路径

`-jf` 以及 JSON 中 `file_path` 的相对路径均由 Telqos 服务进程的工作目录解析，而不是由 Telnet 客户端解析。

例如，Telqos 进程工作目录为 `/opt/settingrepo` 时：

```text
txtnode -inspect -jf docs/telqos-manual/zh-CN/json-file/txtnode/inspect.json
```

实际读取的文件为：

```text
/opt/settingrepo/docs/telqos-manual/zh-CN/json-file/txtnode/inspect.json
```

生产环境建议使用绝对路径，避免因启动脚本、服务管理器或容器工作目录变化而导致文件无法找到。

## 退出

完成操作后输入：

```text
quit
```

Telqos 将主动断开当前连接。

## 参阅

- [JSON 输入规范](./JsonInput.md)
- [命令索引](./CommandIndex.md)
