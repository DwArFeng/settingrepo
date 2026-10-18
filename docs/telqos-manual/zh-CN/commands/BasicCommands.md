# Basic Commands - 基础命令

基础命令由 Telqos 平台提供，用于查看命令、阅读帮助和安全退出。

## lc

列出 Telqos 当前加载的命令。

```text
usage: lc [-p prefix|--prefix prefix]
```

使用前缀筛选命令：

```text
lc -p i
```

输出包含命令序号、命令标识和命令说明。部署方式或插件配置不同，实际命令集合可能不同。

## man

查看指定命令的帮助信息。

```text
usage: man [command]
```

例如：

```text
man navigationnode
```

输出内容包括命令语法、可用选项和选项说明。运行时帮助与当前部署版本一致，是排查命令差异的首要依据。

## quit

退出 Telqos 运维平台。

```text
usage: quit
```

Telqos 返回退出提示后会主动断开当前 Telnet 连接。
