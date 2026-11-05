# Telqos Commands - Telqos 命令

## 命令列表

Setting Repository 提供的 Telqos 命令如下所示：

| 命令                                   | 说明                    | 可用版本 |
|----------------------------------------|-------------------------|----------|
| [lc](#lc-命令)                         | 列出指令                | 1.0.0    |
| [man](#man-命令)                       | 显示指令的详细信息      | 1.0.0    |
| [quit](#quit-命令)                     | 退出                    | 1.0.0    |
| [datamark](#datamark-命令)             | 数据标记服务            | 2.3.0    |
| [dubbo](#dubbo-命令)                   | 分布式服务上线/下线     | 1.0.0    |
| [filelnode](#filelnode-命令)           | 文件列表节点操作服务    | 2.4.2    |
| [filenode](#filenode-命令)             | 文件节点操作服务        | 2.4.2    |
| [flc](#flc-命令)                       | 格式化器本地缓存操作    | 1.0.0    |
| [ftp](#ftp-命令)                       | FTP QoS 服务            | 3.0.0    |
| [iahnnode](#iahnnode-命令)             | 国际化节点操作服务      | 2.1.0    |
| [imglnode](#imglnode-命令)             | 图片列表节点操作服务    | 2.0.0    |
| [imgnode](#imgnode-命令)               | 图片节点操作服务        | 2.0.0    |
| [jmxremote](#jmxremote-命令)           | JMX 远程管理操作        | 2.4.2    |
| [kvnode](#kvnode-命令)                 | 键值对节点操作服务      | 2.5.0    |
| [log4j2](#log4j2-命令)                 | Log4j2 命令             | 2.0.0    |
| [ltxtnode](#ltxtnode-命令)             | 长文本节点操作服务      | 2.2.0    |
| [memory](#memory-命令)                 | 内存监视                | 1.0.0    |
| [navigationnode](#navigationnode-命令) | 导航节点操作服务        | 2.4.2    |
| [reset](#reset-命令)                   | 重置处理器操作/查看     | 1.1.1    |
| [settingnode](#settingnode-命令)       | 设置节点操作服务        | 2.0.0    |
| [shutdown](#shutdown-命令)             | 关闭/重启程序           | 1.0.0    |
| [support](#support-命令)               | 支持操作                | 2.4.1    |
| [textlnode](#textlnode-命令)           | 文本列表节点操作服务    | 3.2.0    |
| [txtnode](#txtnode-命令)               | 文本节点操作服务        | 2.0.0    |
| [uptime](#uptime-命令)                 | 系统运行时间            | 2.4.2    |
| [videolnode](#videolnode-命令)         | 视频列表节点操作服务    | 3.2.0    |
| [videonode](#videonode-命令)           | 视频节点操作服务        | 3.2.0    |
| [warmup](#warmup-命令)                 | 预热处理器操作/查看     | 3.1.0    |
| [warmupdrive](#warmupdrive-命令)       | 预热驱动处理器操作/查看 | 3.1.0    |

使用 `lc` 命令可以列出全部命令，其输出与上表一一对应：

```text
lc
1   lc               列出指令
2   man              显示指令的详细信息
3   quit             退出
4   datamark         数据标记服务
5   dubbo            分布式服务上线/下线
6   filelnode        文件列表节点操作服务
7   filenode         文件节点操作服务
8   flc              格式化器本地缓存操作
9   ftp              FTP QoS 服务
10  iahnnode         国际化节点操作服务
11  imglnode         图片列表节点操作服务
12  imgnode          图片节点操作服务
13  jmxremote        JMX 远程管理操作
14  kvnode           键值对节点操作服务
15  log4j2           Log4j2 命令
16  ltxtnode         长文本节点操作服务
17  memory           内存监视
18  navigationnode   导航节点操作服务
19  reset            重置处理器操作/查看
20  settingnode      设置节点操作服务
21  shutdown         关闭/重启程序
22  support          支持操作
23  textlnode        文本列表节点操作服务
24  txtnode          文本节点操作服务
25  uptime           系统运行时间
26  videolnode       视频列表节点操作服务
27  videonode        视频节点操作服务
28  warmup           预热处理器操作/查看
29  warmupdrive      预热驱动处理器操作/查看
---------------------------------
共 29 条
OK
```

鉴于所有指令都可以实际操作验证，因此本文对于较长的输出将予以省略，省略的部分将会使用 `etc...` 进行标注。

## lc 命令

列出 Telqos 支持的所有命令。

### 语法

```text
usage: lc [-p prefix]
列出指令
 -p <arg>   列出包含指定前缀的指令
```

`-p` 参数用于列出包含指定前缀的命令。

### 示例

```text
lc -p l
1   lc         列出指令
2   log4j2     Log4j2 命令
3   ltxtnode   长文本节点操作服务
------------------------
共 3 条
OK
```

## man 命令

显示指定命令的详细信息。

### 语法

```text
usage: man [command]
显示指令的详细信息
```

如果不指定 `[command]` 参数，则显示 `man` 命令自身的详细信息。

### 示例

```text
man
usage: man [command]
显示指令的详细信息


OK
```

查看其它命令的详细信息时，用法相同，例如 `man lc`：

```text
man lc
usage: lc [-p prefix]
列出指令
 -p <arg>   列出包含指定前缀的指令


OK
```

## quit 命令

退出 Telqos 运维平台。

### 语法

```text
usage: quit
退出
```

### 示例

```text
quit
Bye
服务端主动与您中断连接
再见!
```

## datamark 命令

数据标记服务操作。数据标记用于在系统内部生成一个可以被业务逻辑读取的标记值，
例如在数据全量刷新完成后，通过数据标记通知其它系统刷新本地缓存。

### 语法

```text
usage: datamark -lh
datamark -ua [-hn handler-name]
datamark -get [-hn handler-name]
datamark -refresh [-hn handler-name]
datamark -update [-hn handler-name] [-dv datamark-value]
数据标记服务
 -dv,--datamark-value <arg>   数据标记值
 -get                         获取数据标记值
 -hn,--handler-name <arg>     处理器名称
 -lh,--list-handlers          列出所有可用的数据标记处理器
 -refresh                     刷新数据标记值
 -ua,--update-allowed         返回处理器是否允许更新
 -update                      更新数据标记值
```

`-hn` 参数用于指定数据标记处理器名称；如果不指定该参数，则使用默认的数据标记处理器。
如果不确定当前项目中有哪些数据标记处理器，可以先使用 `-lh` 参数列出。

### 示例

#### 列出所有可用的数据标记处理器

```text
datamark -lh
可用的处理器名称: 
    1: settingCategoryDatamarkHandler
    2: settingNodeDatamarkHandler
    3: warmupDriverInfoDatamarkHandler
    4: warmupSettingDatamarkHandler
OK
```

如果不指定 `-hn` 参数，部分子命令会以交互的方式询问处理器名称：

```text
datamark -ua
可用的处理器名称: 
    1: settingCategoryDatamarkHandler
    2: settingNodeDatamarkHandler
    3: warmupDriverInfoDatamarkHandler
    4: warmupSettingDatamarkHandler
请输入处理器名称:
```

#### 查看处理器是否允许更新

```text
datamark -ua -hn settingNodeDatamarkHandler
处理器名称: settingNodeDatamarkHandler, 允许更新: true
OK
```

#### 获取数据标记值

```text
datamark -get -hn settingNodeDatamarkHandler
处理器名称: settingNodeDatamarkHandler, 数据标记值: settingrepo-node-all-he
OK
```

#### 刷新数据标记值

```text
datamark -refresh -hn settingNodeDatamarkHandler
刷新成功!
处理器名称: settingNodeDatamarkHandler, 刷新后的数据标记值: settingrepo-node-all-he
OK
```

#### 更新数据标记值

```text
datamark -update -hn settingNodeDatamarkHandler -dv 20260101
更新成功!
处理器名称: settingNodeDatamarkHandler, 更新的数据标记值: 20260101
OK
```

`-dv` 参数用于指定更新后的数据标记值。更新完成后可以使用 `-get` 子命令确认结果：

```text
datamark -get -hn settingNodeDatamarkHandler
处理器名称: settingNodeDatamarkHandler, 数据标记值: 20260101
OK
```

如果处理器的配置中不允许更新数据标记值，则 `-update` 子命令将会抛出 `exception code=4010 - datamark qos failed` 异常。

## dubbo 命令

分布式服务查询/上线/下线。

### 语法

```text
usage: dubbo -online [service-name]
dubbo -offline [service-name]
dubbo -ls
分布式服务上线/下线
 -ls              列出服务
 -offline <arg>   下线服务
 -online <arg>    上线服务
```

`[service-name]` 参数为正则表达式，只有服务的全名称匹配正则表达式时，才会被上线/下线。

如果想要上线/下线名称中包含 `VideoNodeMaintainService` 的服务，
则可以使用 `.*VideoNodeMaintainService.*` 作为正则表达式。

如果不指定 `[service-name]` 参数，则使所有服务上线/下线。

### 示例

#### 列出服务

```text
dubbo -ls
As Provider side:
+------------------------------------------------------------------------------+---+
|                             Provider Service Name                            |PUB|
+------------------------------------------------------------------------------+---+
|      com.dwarfeng.settingrepo.stack.service.WarmupSettingMaintainService     | Y |
+------------------------------------------------------------------------------+---+
|      com.dwarfeng.settingrepo.stack.service.ImageListNodeMaintainService     | Y |
+------------------------------------------------------------------------------+---+
etc...
As Consumer side:
+-----------------------------------------------+---+
|             Consumer Service Name             |NUM|
+-----------------------------------------------+---+
|com.dwarfeng.sfds.stack.service.GenerateService| 3 |
+-----------------------------------------------+---+
OK
```

#### 上线服务

```text
dubbo -online com.dwarfeng.settingrepo.stack.service.VideoNodeMaintainService
OK
```

随后可以列出服务观察上线效果。

#### 下线服务

```text
dubbo -offline com.dwarfeng.settingrepo.stack.service.VideoNodeMaintainService
OK
```

随后可以列出服务观察下线效果。

#### 上线/下线不存在的服务

```text
dubbo -offline .*NoSuchService.*
service not found
OK
```

当正则表达式没有匹配到任何服务时，命令不会报错，而是输出 `service not found`。

## filelnode 命令

文件列表节点操作服务。文件列表节点用于存储一组文件，节点中的每个条目通过索引进行标识。

### 语法

```text
usage: filelnode -size [-json json-string] [-jf json-file]
filelnode -inspect [-json json-string] [-jf json-file]
filelnode -download [-json json-string] [-jf json-file]
filelnode -upload [-json json-string] [-jf json-file]
filelnode -update [-json json-string] [-jf json-file]
filelnode -co [-json json-string] [-jf json-file]
filelnode -remove [-json json-string] [-jf json-file]
文件列表节点操作服务
 -co,--change-order      更改文件列表节点的顺序
 -download               下载文件列表节点文件
 -inspect                查看文件列表节点
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -remove                 移除文件列表节点
 -size                   获取文件列表节点的大小
 -update                 更新文件列表节点文件
 -upload                 上传文件列表节点文件
```

所有子命令均需要指定 JSON 字符串或 JSON 文件，二者的内容格式相同。
使用 `-json` 参数直接指定 JSON 字符串，使用 `-jf` 参数指定 JSON 文件路径。

上传、更新、下载等操作需要访问节点所在机器的本地文件系统，`file_path` 参数为本地文件的路径，
该路径相对于节点程序的工作目录。上传与更新时 `file_path` 为源文件；下载时 `file_path` 为目标文件，
其父目录必须已经存在，否则命令将会抛出 `java.nio.file.NoSuchFileException` 异常。

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 上传文件

```text
filelnode -upload -jf confext\json-example\filelnode\upload.json
文件上传成功
OK
```

#### 获取文件列表节点的大小

```text
filelnode -size -jf confext\json-example\filelnode\size.json
文件列表节点的大小: 
  size: 1
OK
```

#### 查看文件列表节点

```text
filelnode -inspect -jf confext\json-example\filelnode\inspect.json
查看结果: 
  items: total size: 1
    0/1: 
      nullFlag: false
      originName: bar.txt
      length: 1175
OK
```

#### 下载文件

```text
filelnode -download -jf confext\json-example\filelnode\download.json
文件下载成功
OK
```

#### 更新文件

```text
filelnode -update -jf confext\json-example\filelnode\update.json
文件更新成功
OK
```

#### 更改文件列表节点的顺序

先上传第二个文件，使节点中包含两个条目：

```text
filelnode -upload -jf confext\json-example\filelnode\upload.json
文件上传成功
OK
```

```text
filelnode -co -jf confext\json-example\filelnode\change-order.json
更改顺序成功
OK
```

随后查看节点可以观察顺序变化：

```text
filelnode -inspect -jf confext\json-example\filelnode\inspect.json
查看结果: 
  items: total size: 2
    0/2: 
      nullFlag: false
      originName: foo.txt
      length: 1190
    1/2: 
      nullFlag: false
      originName: bar.txt
      length: 1175
OK
```

如果索引超出节点的大小范围，命令将会抛出异常：

```text
filelnode -co -jf confext\json-example\filelnode\change-order.json
Exception
com.dwarfeng.springtelqos.stack.exception.CommandExecutorException: etc...
  at com.dwarfeng.springtelqos.sdk.command.etc...
etc...
```

上例中节点的大小为 1，因此索引 1 超出了范围。

#### 移除文件列表节点

```text
filelnode -remove -jf confext\json-example\filelnode\remove.json
移除成功
OK
```

## filenode 命令

文件节点操作服务。文件节点用于存储单个文件。

### 语法

```text
usage: filenode -inspect [-json json-string] [-jf json-file]
filenode -download [-json json-string] [-jf json-file]
filenode -upload [-json json-string] [-jf json-file]
文件节点操作服务
 -download               下载文件节点文件
 -inspect                查看文件节点文件
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -upload                 上传文件节点文件
```

与 `filelnode` 相同，`file_path` 参数为节点所在机器的本地文件路径，下载时目标文件的父目录必须已经存在。

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 上传文件

```text
filenode -upload -jf confext\json-example\filenode\upload.json
文件上传成功
OK
```

#### 查看文件节点

```text
filenode -inspect -jf confext\json-example\filenode\inspect.json
查看结果: 
  originName: bar.txt
  length: 1175
OK
```

#### 下载文件

```text
filenode -download -jf confext\json-example\filenode\download.json
文件下载成功
OK
```

## flc 命令

格式化器本地缓存操作。格式化器在运行过程中会将部分数据缓存到本地，`flc` 命令用于查看或清除这些缓存。

### 语法

```text
usage: flc -l id
flc -c
格式化器本地缓存操作
 -c         清除格式化器
 -l <arg>   查询格式化器
```

`-l` 参数用于查询指定格式化器的缓存内容，参数为格式化器的标识；`-c` 参数用于清除本地缓存。

### 示例

#### 查询格式化器

```text
flc -l telqos.demo
GroovyFormatter{settingCategory=SettingCategory{key=StringIdKey{stringId='telqos.demo'}, etc...
import com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory
etc...
```

由于格式化器的缓存内容包含了完整的格式化器参数，因此输出较长，此处做省略处理。

如果指定的标识不存在缓存，则输出 `not exists`：

```text
flc -l 1
not exists
OK
```

#### 清除本地缓存

```text
flc -c
本地缓存已清除
OK
```

## ftp 命令

FTP QoS 服务。本项目中的图片、视频、文件等节点数据实际存储在 FTP 服务器上，
`ftp` 命令提供了对这些 FTP 资源的直接操作能力，便于排查问题。

### 语法

```text
usage: ftp -lh
ftp -is [-hn handler-name]
ftp -st [-hn handler-name]
ftp -sp [-hn handler-name]
ftp -ex [-hn handler-name] [-rfp remote-file-path]
ftp -up [-hn handler-name] [-lp local-path] [-rfp remote-file-path]
ftp -dn [-hn handler-name] [-rfp remote-file-path] [-lp local-path]
ftp -df [-hn handler-name] [-rfp remote-file-path]
ftp -rd [-hn handler-name] [-rdp remote-directory-path]
ftp -lf [-hn handler-name] [-rdp remote-directory-path]
ftp -ln [-hn handler-name] [-rdp remote-directory-path]
ftp -dc [-hn handler-name] [-rfp remote-file-path]
ftp -rn [-hn handler-name] [-orfp old-remote-file-path] [-nrfp new-remote-file-path]
ftp -mv [-hn handler-name] [-orfp old-remote-file-path] [-nrfp new-remote-file-path]
ftp -cp [-hn handler-name] [-orfp old-remote-file-path] [-nrfp new-remote-file-path]
ftp -cd [-hn handler-name] [-rdp remote-directory-path]
FTP QoS 服务
 -cd,--clear-directory                清空 FTP 目录
 -cp,--copy-file                      复制 FTP 文件
 -dc,--desc-file                      描述 FTP 文件
 -df,--delete-file                    删除 FTP 文件
 -dn,--download                       下载 FTP 文件到本地
 -ex                                  检查 FTP 文件是否存在
 -hn,--handler-name <arg>             FTP 处理器名称
 -is,--is-started                     查询 FTP 处理器是否已启动
 -lf,--list-files                     列出 FTP 目录文件
 -lh,--list-handlers                  列出所有可用的 FTP 处理器
 -ln,--list-file-names                列出 FTP 目录文件名
 -lp,--local-path <arg>               本地文件路径
 -mv,--move-file                      移动 FTP 文件
 -nrfp,--new-remote-file-path <arg>   新远端文件路径
 -orfp,--old-remote-file-path <arg>   旧远端文件路径
 -rd,--remove-directory               删除 FTP 目录
 -rdp,--remote-directory-path <arg>   远端目录路径
 -rfp,--remote-file-path <arg>        远端文件路径
 -rn,--rename-file                    重命名 FTP 文件
 -sp                                  停止 FTP 处理器
 -st                                  启动 FTP 处理器
 -up,--upload                         上传本地文件到 FTP
```

`-hn` 参数用于指定 FTP 处理器名称；如果不指定，则输出中使用 `<default>` 表示当前使用的是默认处理器。

`-lf` 与 `-ln` 两个子命令在没有指定 `-rdp` 参数时，会以交互的方式询问远端目录路径，
此时直接输入路径即可，输入 `/` 表示 FTP 根目录。

### 示例

#### 列出所有可用的 FTP 处理器

```text
ftp -lh
可用的处理器名称:
    1: ftpHandler
OK
```

#### 查询 FTP 处理器是否已启动

```text
ftp -is
处理器名称: <default>, 已启动: true
OK
```

#### 停止和启动 FTP 处理器

```text
ftp -sp
停止成功!
处理器名称: <default>
OK
```

```text
ftp -is
处理器名称: <default>, 已启动: false
OK
```

```text
ftp -st
启动成功!
处理器名称: <default>
OK
```

```text
ftp -is
处理器名称: <default>, 已启动: true
OK
```

#### 列出 FTP 目录文件

```text
ftp -lf
请输入远端目录路径（根目录可输入 /）:

/
处理器名称: <default>
远端目录路径: /
目录文件:
    1: name=buddy, type=directory, size=20
    2: name=essentials, type=directory, size=52
    3: name=demo-assets, type=directory, size=63
etc...
   14: name=toolhouse, type=directory, size=18
OK
```

也可以使用 `-rdp` 参数直接指定目录，避免交互：

```text
ftp -lf -rdp /settingrepo/telqos-demo
处理器名称: <default>
远端目录路径: /settingrepo/telqos-demo
目录文件:
    1: name=ftp-demo.txt, type=file, size=17
OK
```

#### 列出 FTP 目录文件名

```text
ftp -ln
请输入远端目录路径（根目录可输入 /）:

/
处理器名称: <default>
远端目录路径: /
目录文件名:
    1: foo
    2: bar
etc...
   14: baz
OK
```

当目录中没有文件时，输出 `(Empty)`：

```text
ftp -ln
请输入远端目录路径（根目录可输入 /）:

/settingrepo/telqos-demo
处理器名称: <default>
远端目录路径: /settingrepo/telqos-demo
目录文件名:
  (Empty)
OK
```

#### 检查 FTP 文件是否存在

```text
ftp -ex -rfp /settingrepo/telqos-demo/ftp-demo.txt
处理器名称: <default>
远端文件路径: /settingrepo/telqos-demo/ftp-demo.txt
存在: true
OK
```

```text
ftp -ex -rfp /settingrepo/telqos-demo
处理器名称: <default>
远端文件路径: /settingrepo/telqos-demo
存在: false
OK
```

#### 上传本地文件到 FTP

```text
ftp -up -lp confext\telqos\ltxt\ftp-demo.txt -rfp /settingrepo/telqos-demo/ftp-demo.txt
上传成功!
处理器名称: <default>
本地文件路径: confext\telqos\ltxt\ftp-demo.txt
远端文件路径: /settingrepo/telqos-demo/ftp-demo.txt
OK
```

`-lp` 参数指定的本地文件路径相对于节点程序的工作目录，输出中的 `本地文件路径` 为解析后的路径。

#### 描述 FTP 文件

```text
ftp -dc -rfp /settingrepo/telqos-demo/ftp-demo.txt
处理器名称: <default>
远端文件路径: /settingrepo/telqos-demo/ftp-demo.txt
描述结果:
  name: ftp-demo.txt
  type: file
  size: 17
OK
```

#### 重命名 FTP 文件

```text
ftp -rn -orfp /settingrepo/telqos-demo/ftp-demo.txt -nrfp /settingrepo/telqos-demo/ftp-demo-renamed.txt
重命名成功!
处理器名称: <default>
旧远端文件路径: /settingrepo/telqos-demo/ftp-demo.txt
新远端文件路径: /settingrepo/telqos-demo/ftp-demo-renamed.txt
OK
```

#### 复制 FTP 文件

```text
ftp -cp -orfp /settingrepo/telqos-demo/ftp-demo-renamed.txt -nrfp /settingrepo/telqos-demo/ftp-demo-copy.txt
复制成功!
处理器名称: <default>
旧远端文件路径: /settingrepo/telqos-demo/ftp-demo-renamed.txt
新远端文件路径: /settingrepo/telqos-demo/ftp-demo-copy.txt
OK
```

#### 移动 FTP 文件

```text
ftp -mv -orfp /settingrepo/telqos-demo/ftp-demo-copy.txt -nrfp /settingrepo/telqos-demo/ftp-demo-moved.txt
移动成功!
处理器名称: <default>
旧远端文件路径: /settingrepo/telqos-demo/ftp-demo-copy.txt
新远端文件路径: /settingrepo/telqos-demo/ftp-demo-moved.txt
OK
```

#### 下载 FTP 文件到本地

```text
ftp -dn -rfp /settingrepo/telqos-demo/ftp-demo-renamed.txt -lp confext\telqos\ltxt\ftp-demo-downloaded.txt
下载成功!
处理器名称: <default>
远端文件路径: /settingrepo/telqos-demo/ftp-demo-renamed.txt
本地文件路径: confext\telqos\ltxt\ftp-demo-downloaded.txt
OK
```

#### 删除 FTP 文件

```text
ftp -df -rfp /settingrepo/telqos-demo/ftp-demo-renamed.txt
删除文件成功!
处理器名称: <default>
远端文件路径: /settingrepo/telqos-demo/ftp-demo-renamed.txt
OK
```

#### 清空 FTP 目录

```text
ftp -cd -rdp /settingrepo/telqos-demo
清空目录成功!
处理器名称: <default>
远端目录路径: /settingrepo/telqos-demo
OK
```

#### 删除 FTP 目录

```text
ftp -rd -rdp /settingrepo/telqos-demo
删除目录成功!
处理器名称: <default>
远端目录路径: /settingrepo/telqos-demo
OK
```

## iahnnode 命令

国际化节点操作服务。国际化节点用于存储一组国际化地区（locale）、一组国际化 Mek（消息键）以及二者交叉形成的国际化消息表。

### 语法

```text
usage: iahnnode -im [-json json-string] [-jf json-file]
iahnnode -biml [-json json-string] [-jf json-file]
iahnnode -ill [-json json-string] [-jf json-file]
iahnnode -iml [-json json-string] [-jf json-file]
iahnnode -imt [-json json-string] [-jf json-file]
iahnnode -pl [-json json-string] [-jf json-file]
iahnnode -rl [-json json-string] [-jf json-file]
iahnnode -pm [-json json-string] [-jf json-file]
iahnnode -rm [-json json-string] [-jf json-file]
iahnnode -um [-json json-string] [-jf json-file]
iahnnode -buml [-json json-string] [-jf json-file]
iahnnode -bumm [-json json-string] [-jf json-file]
iahnnode -cc [-json json-string] [-jf json-file]
国际化节点操作服务
 -biml,--batch-inspect-message-by-locale   基于国际化地区批量查询国际化消息
 -buml,--batch-upsert-message-by-locale    基于国际化地区批量插入或更新国际化消息
 -bumm,--batch-upsert-message-by-mek       基于国际化 Mek 批量插入或更新国际化消息
 -cc,--clear-cache                         清除国际化节点缓存
 -ill,--inspect-locale-list                查询国际化地区列表
 -im,--inspect-message                     查询国际化消息
 -iml,--inspect-mek-list                   查询国际化 Mek 列表
 -imt,--inspect-message-table              查询国际化消息表
 -jf,--json-file <arg>                     JSON 文件
 -json <arg>                               JSON 字符串
 -pl,--put-locale                          推入国际化地区
 -pm,--put-mek                             推入国际化 Mek
 -rl,--remove-locale                       移除国际化地区
 -rm,--remove-mek                          移除国际化 Mek
 -um,--upsert-message                      插入或更新国际化消息
```

`-ill`、`-iml`、`-imt` 三个子命令的输出采用分页交互的方式：执行后先显示数据总数与交互提示，
此时可以输入 `all` 查看所有数据，输入 `begin-end` 查看指定范围的数据（索引从 0 开始），输入 `q` 退出交互。

批量操作相关的子命令中，批量数据的键名为 `items`，而不是 `messages`；
如果使用错误的键名，命令将会抛出 `exception code=70 - handler failed` 异常。

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 推入国际化地区

```text
iahnnode -pl -jf confext\json-example\iahnnode\put-locale.json
推入成功
OK
```

#### 推入国际化 Mek

```text
iahnnode -pm -jf confext\json-example\iahnnode\put-mek.json
推入成功
OK
```

#### 插入或更新国际化消息

```text
iahnnode -um -jf confext\json-example\iahnnode\upsert-message.json
插入或更新成功
OK
```

#### 查询国际化地区列表

```text
iahnnode -ill -jf confext\json-example\iahnnode\inspect-locale-list.json
数据总数: 2

输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

all

索引: 0/2
  language: en
  country: us
  variant: 
  label: English (US)
  remark: 美式英语

索引: 1/2
  language: zh
  country: cn
  variant: 
  label: 简体中文
  remark: 中文地区

数据总数: 2

输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

q
OK
```

#### 查询国际化 Mek 列表

```text
iahnnode -iml -jf confext\json-example\iahnnode\inspect-mek-list.json
数据总数: 2

输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

all

索引: 0/2
  mekId: demo.bye
  label: 告别语
  defaultMessage: Bye
  remark: 默认告别

索引: 1/2
  mekId: demo.hello
  label: 问候语
  defaultMessage: Hello
  remark: 默认问候

数据总数: 2

输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

q
OK
```

#### 查询国际化消息表

```text
iahnnode -imt -jf confext\json-example\iahnnode\inspect-message-table.json
数据总数: 2

输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

all

列总数: 2
    0: en-us
    1: zh-cn
索引: 0/2
  mekId: demo.bye
  label: 告别语
  defaultMessage: Bye
  remark: 默认告别
  rowDatas: 
      0: 
      message: Goodbye
      1: 
      message: 再见
列总数: 2
    0: en-us
    1: zh-cn
索引: 1/2
  mekId: demo.hello
  label: 问候语
  defaultMessage: Hello
  remark: 默认问候
  rowDatas: 
      0: 
      message: Hello
      1: 
      message: 你好
数据总数: 2

输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

q
OK
```

#### 查询国际化消息

```text
iahnnode -im -jf confext\json-example\iahnnode\inspect-message.json
查询结果: 
  message: Goodbye
OK
```

#### 基于国际化地区批量查询国际化消息

```text
iahnnode -biml -jf confext\json-example\iahnnode\batch-inspect-message-by-locale.json
查询结果: 
  items: total size: 2
0 / 2: medId = demo.bye, message = 再见
1 / 2: medId = demo.hello, message = 你好
OK
```

上例输出中的 `medId` 为程序输出时的笔误，实际含义为 `mekId`。

#### 基于国际化地区批量插入或更新国际化消息

```text
iahnnode -buml -jf confext\json-example\iahnnode\batch-upsert-message-by-locale.json
批量插入或更新成功
OK
```

如果指定的国际化地区不存在，命令将会抛出异常：

```text
iahnnode -buml -jf confext\json-example\iahnnode\batch-upsert-message-by-locale.json
Exception
com.dwarfeng.springtelqos.stack.exception.CommandExecutorException: etc...
  at com.dwarfeng.springtelqos.sdk.command.etc...
etc...
```

上例中需要先使用 `-pl` 参数推入 `en-us` 地区，才能批量插入或更新该地区的消息。

#### 基于国际化 Mek 批量插入或更新国际化消息

```text
iahnnode -bumm -jf confext\json-example\iahnnode\batch-upsert-message-by-mek.json
批量插入或更新成功
OK
```

如果指定的国际化 Mek 不存在，命令将会抛出 `exception code=1080 - iahn node mek not exists` 异常，
需要先使用 `-pm` 参数推入该 Mek。

#### 移除国际化地区

```text
iahnnode -rl -jf confext\json-example\iahnnode\remove-locale.json
移除成功
OK
```

#### 移除国际化 Mek

```text
iahnnode -rm -jf confext\json-example\iahnnode\remove-mek.json
移除成功
OK
```

#### 清除国际化节点缓存

```text
iahnnode -cc -jf confext\json-example\iahnnode\clear-cache.json
清理缓存成功
OK
```

## imglnode 命令

图片列表节点操作服务。图片列表节点用于存储一组图片，节点中的每个条目通过索引进行标识。

### 语法

```text
usage: imglnode -size [-json json-string] [-jf json-file]
imglnode -inspect [-json json-string] [-jf json-file]
imglnode -download [-json json-string] [-jf json-file]
imglnode -upload [-json json-string] [-jf json-file]
imglnode -update [-json json-string] [-jf json-file]
imglnode -co [-json json-string] [-jf json-file]
imglnode -remove [-json json-string] [-jf json-file]
图片列表节点操作服务
 -co,--change-order      更改图片列表节点的顺序
 -download               下载图片列表节点文件
 -inspect                查看图片列表节点
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -remove                 移除图片列表节点
 -size                   获取图片列表节点的大小
 -update                 更新图片列表节点文件
 -upload                 上传图片列表节点文件
```

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 上传图片

```text
imglnode -upload -jf confext\json-example\imglnode\upload.json
文件上传成功
OK
```

#### 获取图片列表节点的大小

```text
imglnode -size -jf confext\json-example\imglnode\size.json
图片列表节点的大小: 
  size: 1
OK
```

#### 查看图片列表节点

```text
imglnode -inspect -jf confext\json-example\imglnode\inspect.json
查看结果: 
  items: total size: 1
    0/1: 
      nullFlag: false
      originName: test-cover-1.jpg
      length: 59840
OK
```

#### 下载图片

```text
imglnode -download -jf confext\json-example\imglnode\download.json
文件下载成功
OK
```

#### 更新图片

```text
imglnode -update -jf confext\json-example\imglnode\update.json
文件更新成功
OK
```

#### 更改图片列表节点的顺序

先上传第二个文件，使节点中包含两个条目：

```text
imglnode -upload -jf confext\json-example\imglnode\upload.json
文件上传成功
OK
```

```text
imglnode -co -jf confext\json-example\imglnode\change-order.json
更改顺序成功
OK
```

```text
imglnode -inspect -jf confext\json-example\imglnode\inspect.json
查看结果: 
  items: total size: 2
    0/2: 
      nullFlag: false
      originName: test-cover-3.jpg
      length: 49363
    1/2: 
      nullFlag: false
      originName: test-cover-1.jpg
      length: 59840
OK
```

#### 移除图片列表节点

```text
imglnode -remove -jf confext\json-example\imglnode\remove.json
移除成功
OK
```

## imgnode 命令

图片节点操作服务。图片节点用于存储单张图片。

### 语法

```text
usage: imgnode -inspect [-json json-string] [-jf json-file]
imgnode -download [-json json-string] [-jf json-file]
imgnode -upload [-json json-string] [-jf json-file]
图片节点操作服务
 -download               下载图片节点图片
 -inspect                查看图片节点图片
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -upload                 上传图片节点图片
```

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 上传图片

```text
imgnode -upload -jf confext\json-example\imgnode\upload.json
文件上传成功
OK
```

#### 查看图片节点

```text
imgnode -inspect -jf confext\json-example\imgnode\inspect.json
查看结果: 
  originName: test-cover.jpg
  length: 56230
OK
```

#### 下载图片

```text
imgnode -download -jf confext\json-example\imgnode\download.json
文件下载成功
OK
```

## jmxremote 命令

JMX 远程管理操作。通过该命令可以在程序运行过程中动态开启或关闭 JMX 远程管理端口，
便于使用 JConsole、VisualVM 等工具连接程序进行诊断。

### 语法

```text
usage: jmxremote -start [-p port]
jmxremote -stop
jmxremote -status
JMX 远程管理操作
 -p <arg>   JMX 远程管理端口号
 -start     启动 JMX 远程管理
 -status    查看 JMX 远程管理状态
 -stop      停止 JMX 远程管理
```

`-p` 参数用于指定 JMX 远程管理端口号，不指定时使用默认端口。

### 示例

#### 启动 JMX 远程管理

```text
jmxremote -start -p 23000
JMX 远程管理启动成功
  端口: 23000
  服务地址: service:jmx:rmi:///jndi/rmi://:23000/jmxrmi
OK
```

#### 查看 JMX 远程管理状态

```text
jmxremote -status
JMX 远程管理状态: 运行中
  端口: 23000
  服务地址: service:jmx:rmi:///jndi/rmi//:23000/jmxrmi
OK
```

上例中的服务地址为程序输出时的笔误，正确形式参见 `-start` 子命令的输出。

#### 停止 JMX 远程管理

```text
jmxremote -stop
JMX 远程管理已停止
OK
```

停止之后再查看状态：

```text
jmxremote -status
JMX 远程管理未启动
OK
```

## kvnode 命令

键值对节点操作服务。键值对节点用于存储一组键值对，键为字符串，值为字符串。

### 语法

```text
usage: kvnode -cnt [-json json-string] [-jf json-file]
kvnode -i [-json json-string] [-jf json-file]
kvnode -ii [-json json-string] [-jf json-file]
kvnode -pi [-json json-string] [-jf json-file]
kvnode -ri [-json json-string] [-jf json-file]
kvnode -clr [-json json-string] [-jf json-file]
kvnode -cc [-json json-string] [-jf json-file]
键值对节点操作服务
 -cc,--clear-cache       清除键值对节点缓存
 -clr,--clear            清空键值对节点中的全部条目
 -cnt,--count            键值对节点的条目数量
 -i,--inspect            查看键值对节点
 -ii,--inspect-item      查看键值对节点中的指定条目
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -pi,--put-item          向键值对节点中放入指定条目
 -ri,--remove-item       移除键值对节点中的指定条目
```

`-i` 子命令的输出采用分页交互的方式：执行后先显示数据总数与交互提示，
此时可以输入 `all` 查看所有数据，输入 `begin-end` 查看指定范围的数据（索引从 0 开始），
输入 `q` 退出交互。输入内容前后不能带空格，否则会输出 `输入格式错误` 并重新显示交互提示。

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 查询条目数量

```text
kvnode -cnt -jf confext\json-example\kvnode\count.json
查询结果: 
  count: 2
OK
```

#### 放入条目

```text
kvnode -pi -jf confext\json-example\kvnode\put-item.json
放入成功
OK
```

#### 查看键值对节点

```text
kvnode -i -jf confext\json-example\kvnode\inspect.json
数据总数: 2

输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

all

索引: 0/2
  itemStringId: item.one
  value: 示例值一

索引: 1/2
  itemStringId: item.two
  value: 示例值二

数据总数: 2

输入 all 查看所有数据
输入 begin-end 查看指定范围的数据(开始于 0)
输入 q 退出

q
OK
```

#### 查看指定条目

```text
kvnode -ii -jf confext\json-example\kvnode\inspect-item.json
查询结果: 
  value: 示例值一
OK
```

#### 移除指定条目

```text
kvnode -ri -jf confext\json-example\kvnode\remove-item.json
移除成功
OK
```

#### 清空键值对节点

```text
kvnode -clr -jf confext\json-example\kvnode\clear.json
清空成功
OK
```

#### 清除键值对节点缓存

```text
kvnode -cc -jf confext\json-example\kvnode\clear-cache.json
清理缓存成功
OK
```

## log4j2 命令

Log4j2 命令。`-reconfigure` 参数用于在程序运行过程中重新加载日志配置，修改日志配置文件后无需重启程序即可生效。

### 语法

```text
usage: log4j2 -reconfigure
Log4j2 命令
 -reconfigure   重新配置 Log4j2
```

### 示例

```text
log4j2 -reconfigure
Log4j2 已重新配置!
OK
```

## ltxtnode 命令

长文本节点操作服务。长文本节点用于存储较长的文本内容，例如脚本、配置文件内容等。

### 语法

```text
usage: ltxtnode -inspect [-json json-string] [-jf json-file]
ltxtnode -download [-json json-string] [-jf json-file]
ltxtnode -upload [-json json-string] [-jf json-file]
长文本节点操作服务
 -download               下载长文本节点长文本
 -inspect                查看长文本节点长文本
 -jf,--json-file <arg>   JSON 文本
 -json <arg>             JSON 字符串
 -upload                 上传长文本节点长文本
```

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 上传长文本

```text
ltxtnode -upload -jf confext\json-example\ltxtnode\upload.json
文本上传成功
OK
```

#### 查看长文本节点

```text
ltxtnode -inspect -jf confext\json-example\ltxtnode\inspect.json
查看结果: 
  preview: #!/bin/sh

# 设置程序的根目录。
basedir="$(cd "$(dirname "$0")/.." && pwd)"
etc...
echo $! >"$basedir/settingrepo.pid"

  length: 1175
  nullFlag: false
OK
```

由于长文本的内容较长，`-inspect` 子命令仅输出内容的前若干行作为预览，并附带长文本的长度。

#### 下载长文本

```text
ltxtnode -download -jf confext\json-example\ltxtnode\download.json
文本下载成功
OK
```

## memory 命令

内存监视。显示 JVM 的最大内存、分配内存与可用内存。

### 语法

```text
usage: memory [-u unit]
内存监视
 -u <arg>   显示单位
```

`-u` 参数用于指定显示单位，支持 `b`、`kib`、`mib`、`gib` 等，默认单位为 `gib`。

### 示例

使用默认单位：

```text
memory
JVM 最大内存: 7.09GiB
JVM 分配内存: 1.20GiB
JVM 可用内存: 1.05GiB
OK
```

使用 `mib` 为单位：

```text
memory -u mib
JVM 最大内存: 7264.00MiB
JVM 分配内存: 1141.50MiB
JVM 可用内存: 843.91MiB
OK
```

## navigationnode 命令

导航节点操作服务。导航节点用于存储树形结构的导航数据，每个条目通过条目标识（`item_key`）进行标识。

### 语法

```text
usage: navigationnode -size [-json json-string] [-jf json-file]
navigationnode -inspect [-json json-string] [-jf json-file]
navigationnode -ii [-json json-string] [-jf json-file]
navigationnode -ui [-json json-string] [-jf json-file]
navigationnode -ri [-json json-string] [-jf json-file]
navigationnode -fi [-json json-string] [-jf json-file]
navigationnode -cc [-json json-string] [-jf json-file]
导航节点操作服务
 -cc,--clear-cache       清除导航节点缓存
 -fi,--format-index      格式化导航节点索引
 -ii,--insert-item       插入导航节点条目
 -inspect                查看导航节点
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -ri,--remove-item       移除导航节点条目
 -size                   查看导航节点大小
 -ui,--update-item       更新导航节点条目
```

与其它节点操作命令不同，`navigationnode -inspect` 子命令不会以分页交互的方式输出，
而是直接输出完整的 JSON 树。

`parent_item_key` 参数为父级条目的标识，根级条目的父级标识为 `null`。

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 查看导航节点大小

```text
navigationnode -size -jf confext\json-example\navigationnode\size.json
查询结果: 
  size: 0
OK
```

#### 插入导航节点条目

```text
navigationnode -ii -jf confext\json-example\navigationnode\insert-item.json
插入成功
  category: telqos.demo
  args: [node-7]
  itemKey: LongIdKey{longId=1568027392004321280}
OK
```

插入成功后，命令会输出新条目的标识，后续的更新与移除操作需要使用该标识。

#### 查看导航节点

```text
navigationnode -inspect -jf confext\json-example\navigationnode\inspect.json
查看结果: 
{
  "count":1,
  "content":"",
  "children":[
    {
      "key":{
        "long_id":1568027392004321280
      },
      "index":0,
      "name":"示例条目",
      "content":"示例内容",
      "remark":"示例备注",
      "children":[]
    }
  ]
}
OK
```

#### 更新导航节点条目

```text
navigationnode -ui -jf confext\json-example\navigationnode\update-item.json
更新成功
OK
```

#### 格式化导航节点索引

```text
navigationnode -fi -jf confext\json-example\navigationnode\format-index.json
格式化成功
OK
```

#### 移除导航节点条目

```text
navigationnode -ri -jf confext\json-example\navigationnode\remove-item.json
移除成功
OK
```

#### 清除导航节点缓存

```text
navigationnode -cc -jf confext\json-example\navigationnode\clear-cache.json
清理缓存成功
OK
```

## reset 命令

重置处理器操作/查看。重置处理器用于在特定条件下执行重置操作，例如定时重置、Dubbo 服务下线后重置等。

### 语法

```text
usage: reset -l
reset -start
reset -stop
reset -status
reset -rf
reset -rw
重置处理器操作/查看
 -l                   查看重置处理器
 -rf,--reset-format   执行格式化重置操作
 -rw,--reset-warmup   执行预热重置操作
 -start               启动重置处理器
 -status              查看重置处理器状态
 -stop                停止重置处理器
```

### 示例

#### 查看重置处理器

```text
reset -l
01. CronResetter{cron='0 0 1 * * *'}
02. DubboResetter{ctx=org.springframework.context.support.ClassPathXmlApplicationContext@2d1ef81a, etc...
03. NeverResetter{}
OK
```

输出中列出的属性为重置处理器的内部状态，便于排查配置是否正确。

#### 执行格式化重置操作

```text
reset -rf
重置成功!
OK
```

#### 执行预热重置操作

```text
reset -rw
重置成功!
OK
```

#### 停止和启动重置处理器

```text
reset -stop
重置处理器已停止!
OK
```

```text
reset -status
started: false
OK
```

```text
reset -start
重置处理器已启动!
OK
```

```text
reset -status
started: true
OK
```

## settingnode 命令

设置节点操作服务。设置节点是 Setting Repository 的核心概念，每个设置节点属于一个设置分类，
并具有一个类型，类型决定了该节点存储的数据种类以及可用的操作命令。

### 语法

```text
usage: settingnode -exists [-json json-string] [-jf json-file]
settingnode -inspect [-json json-string] [-jf json-file]
settingnode -init [-json json-string] [-jf json-file]
settingnode -remove [-json json-string] [-jf json-file]
设置节点操作服务
 -exists                 判断指定的设置节点是否存在
 -init                   初始化指定的设置节点
 -inspect                查看指定的设置节点
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -remove                 移除指定的设置节点
```

`-init` 子命令的 JSON 中需要指定节点的类型 `type` 与备注 `remark`。设置节点的类型如下所示：

| 类型 | 说明           | 操作命令         |
|------|----------------|------------------|
| 0    | 文本           | `txtnode`        |
| 1    | 长文本         | `ltxtnode`       |
| 2    | 图片           | `imgnode`        |
| 3    | 图片列表       | `imglnode`       |
| 4    | 国际化（IAHN） | `iahnnode`       |
| 5    | 文件           | `filenode`       |
| 6    | 文件列表       | `filelnode`      |
| 7    | 导航           | `navigationnode` |
| 8    | 键值对         | `kvnode`         |
| 9    | 文本列表       | `textlnode`      |
| 10   | 视频           | `videonode`      |
| 11   | 视频列表       | `videolnode`     |

其中类型 9、10、11 在 3.2.0 版本中新增。

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 判断指定的设置节点是否存在

```text
settingnode -exists -jf confext\json-example\settingnode\exists.json
节点存在: true
OK
```

#### 初始化指定的设置节点

```text
settingnode -init -jf confext\json-example\settingnode\init.json
初始化成功
OK
```

如果节点已经存在，则命令将会抛出异常：

```text
settingnode -init -jf confext\json-example\settingnode\init.json
Exception
com.dwarfeng.springtelqos.stack.exception.CommandExecutorException: etc...
  at com.dwarfeng.springtelqos.sdk.command.etc...
etc...
```

如果指定的设置分类不存在，则命令将会抛出 `exception code=1010 - setting category not exists` 异常；
如果设置分类的格式化器不支持该类型的节点，则命令将会抛出 `exception code=1003 - unsupported formatter type` 异常。

#### 查看指定的设置节点

```text
settingnode -inspect -jf confext\json-example\settingnode\inspect.json
查询结果: 
  type: 0 (文本)
  lastModifiedDate: Thu Nov 05 22:17:37 CST 2026
  remark: demo node type 0
OK
```

#### 移除指定的设置节点

```text
settingnode -remove -jf confext\json-example\settingnode\remove.json
删除成功
OK
```

## shutdown 命令

关闭/重启程序。

### 语法

```text
usage: shutdown [-s/-r] [-e exit-code] [-c comment]
关闭/重启程序
 -c <comment>     备注
 -e <exit-code>   退出代码
 -r               重启程序
 -s               退出程序
```

在本项目中，重启程序功能不可用，只能使用关闭程序功能。

增加 `-c` 参数，可以在关闭程序时添加备注信息，备注信息将会被记录到日志中。

`-r` 参数不可用。

### 示例

```text
shutdown
服务将会关闭，您可能需要登录远程主机才能重新启动该服务，是否继续? Y/N
Y
已确认请求，服务即将关闭...
服务端主动与您中断连接
再见!
```

## support 命令

支持操作。`support` 命令用于在运行过程中重置 `FormatterSupport` 与 `WarmupDriverSupport` 的支持信息，
修改格式化器支持或预热驱动器支持的配置后，无需重启程序即可生效。

### 语法

```text
usage: support -rf
support -rwd
支持操作
 -rf,--reset-formatter        重置格式化器支持
 -rwd,--reset-warmup-driver   重置预热驱动器支持
```

### 示例

#### 重置格式化器支持

```text
support -rf
重置格式化器支持成功。
OK
```

#### 重置预热驱动器支持

```text
support -rwd
重置预热驱动器支持成功。
OK
```

## textlnode 命令

文本列表节点操作服务。文本列表节点用于存储一组文本，节点中的每个条目通过索引进行标识。
该命令在 3.2.0 版本中新增。

### 语法

```text
usage: textlnode -size [-json json-string] [-jf json-file]
textlnode -inspect [-json json-string] [-jf json-file]
textlnode -fetch [-json json-string] [-jf json-file]
textlnode -insert [-json json-string] [-jf json-file]
textlnode -update [-json json-string] [-jf json-file]
textlnode -co [-json json-string] [-jf json-file]
textlnode -remove [-json json-string] [-jf json-file]
文本列表节点操作服务
 -co,--change-order      更改文本列表节点的顺序
 -fetch                  拉取文本列表节点文本
 -insert                 插入文本列表节点文本
 -inspect                查看文本列表节点
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -remove                 移除文本列表节点
 -size                   获取文本列表节点的大小
 -update                 更新文本列表节点文本
```

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 获取文本列表节点的大小

```text
textlnode -size -jf confext\json-example\textlnode\size.json
文本列表节点的大小: 
  size: 0
OK
```

#### 插入文本

```text
textlnode -insert -jf confext\json-example\textlnode\insert.json
插入成功
OK
```

再次执行该命令可以插入第二条文本：

```text
textlnode -insert -jf confext\json-example\textlnode\insert.json
插入成功
OK
```

#### 查看文本列表节点

```text
textlnode -inspect -jf confext\json-example\textlnode\inspect.json
查看结果: 
  items: total size: 2
    0/2: 
      index: 0
      nullFlag: false
    1/2: 
      index: 1
      nullFlag: false
OK
```

`-inspect` 子命令仅输出条目的索引与状态，文本内容需要使用 `-fetch` 子命令拉取。

#### 拉取文本

```text
textlnode -fetch -jf confext\json-example\textlnode\fetch.json
拉取结果: 
  content: 示例文本一
OK
```

#### 更新文本

```text
textlnode -update -jf confext\json-example\textlnode\update.json
更新成功
OK
```

#### 更改文本列表节点的顺序

```text
textlnode -co -jf confext\json-example\textlnode\change-order.json
更改顺序成功
OK
```

#### 移除文本列表节点

```text
textlnode -remove -jf confext\json-example\textlnode\remove.json
移除成功
OK
```

## txtnode 命令

文本节点操作服务。文本节点用于存储单个文本。

### 语法

```text
usage: txtnode -inspect [-json json-string] [-jf json-file]
txtnode -put [-json json-string] [-jf json-file]
文本节点操作服务
 -inspect                查看指定的文本节点
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -put                    向指定的文本节点中放入指定的信息
```

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 放入文本

```text
txtnode -put -jf confext\json-example\txtnode\put.json
放入成功
OK
```

#### 查看文本节点

```text
txtnode -inspect -jf confext\json-example\txtnode\inspect.json
查看结果: 
  value: hello telqos
OK
```

## uptime 命令

系统运行时间。显示当前时间以及程序已经运行的时间。

### 语法

```text
usage: uptime
系统运行时间
```

### 示例

```text
uptime
22:46:18 up 0 days, 00:44
OK
```

## videolnode 命令

视频列表节点操作服务。视频列表节点用于存储一组视频，节点中的每个条目通过索引进行标识。
每个视频包含视频文件本身以及一张封面图片。

该命令在 3.2.0 版本中新增。

### 语法

```text
usage: videolnode -size [-json json-string] [-jf json-file]
videolnode -inspect [-json json-string] [-jf json-file]
videolnode -download [-json json-string] [-jf json-file]
videolnode -upload [-json json-string] [-jf json-file]
videolnode -update [-json json-string] [-jf json-file]
videolnode -co [-json json-string] [-jf json-file]
videolnode -remove [-json json-string] [-jf json-file]
视频列表节点操作服务
 -co,--change-order      更改视频列表节点的顺序
 -download               下载视频列表节点文件
 -inspect                查看视频列表节点
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -remove                 移除视频列表节点
 -size                   获取视频列表节点的大小
 -update                 更新视频列表节点文件
 -upload                 上传视频列表节点文件
```

上传与更新时，JSON 中需要同时指定视频文件路径 `file_path` 与封面图片路径 `cover_file_path`。

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 上传视频

```text
videolnode -upload -jf confext\json-example\videolnode\upload.json
文件上传成功
OK
```

#### 获取视频列表节点的大小

```text
videolnode -size -jf confext\json-example\videolnode\size.json
视频列表节点的大小: 
  size: 1
OK
```

#### 查看视频列表节点

```text
videolnode -inspect -jf confext\json-example\videolnode\inspect.json
查看结果: 
  items: total size: 1
    0/1: 
      nullFlag: false
      originName: test-video-1.mp4
      length: 1139614
OK
```

#### 下载视频

```text
videolnode -download -jf confext\json-example\videolnode\download.json
文件下载成功
OK
```

#### 更新视频

```text
videolnode -update -jf confext\json-example\videolnode\update.json
文件更新成功
OK
```

#### 更改视频列表节点的顺序

先上传第二个文件，使节点中包含两个条目：

```text
videolnode -upload -jf confext\json-example\videolnode\upload.json
文件上传成功
OK
```

```text
videolnode -co -jf confext\json-example\videolnode\change-order.json
更改顺序成功
OK
```

```text
videolnode -inspect -jf confext\json-example\videolnode\inspect.json
查看结果: 
  items: total size: 2
    0/2: 
      nullFlag: false
      originName: test-video-2.mp4
      length: 1139614
    1/2: 
      nullFlag: false
      originName: test-video-1.mp4
      length: 1139614
OK
```

如果索引超出节点的大小范围，命令将会抛出异常：

```text
videolnode -co -jf confext\json-example\videolnode\change-order.json
Exception
com.dwarfeng.springtelqos.stack.exception.CommandExecutorException: etc...
  at com.dwarfeng.springtelqos.sdk.command.etc...
etc...
```

#### 移除视频列表节点

```text
videolnode -remove -jf confext\json-example\videolnode\remove.json
移除成功
OK
```

## videonode 命令

视频节点操作服务。视频节点用于存储单个视频，视频包含视频文件本身以及一张封面图片。
该命令在 3.2.0 版本中新增。

### 语法

```text
usage: videonode -inspect [-json json-string] [-jf json-file]
videonode -download [-json json-string] [-jf json-file]
videonode -upload [-json json-string] [-jf json-file]
视频节点操作服务
 -download               下载视频节点文件
 -inspect                查看视频节点
 -jf,--json-file <arg>   JSON 文件
 -json <arg>             JSON 字符串
 -upload                 上传视频节点文件
```

### 示例

下述示例统一使用 `-jf` 参数指定 JSON 文件，文件路径相对于节点程序的工作目录，
文件中的字段与 `-json` 参数指定的 JSON 字符串一致。

#### 上传视频

```text
videonode -upload -jf confext\json-example\videonode\upload.json
文件上传成功
OK
```

#### 查看视频节点

```text
videonode -inspect -jf confext\json-example\videonode\inspect.json
查看结果: 
  originName: test-video.mp4
  length: 1139614
OK
```

#### 下载视频

```text
videonode -download -jf confext\json-example\videonode\download.json
文件下载成功
OK
```

## warmup 命令

预热处理器操作/查看。预热处理器用于在程序启动后预热设置节点的相关数据，
通过该命令可以查看预热处理器的状态，以及控制预热处理器的上线/下线与启动/停止。

该命令在 3.1.0 版本中新增。

### 语法

```text
usage: warmup -online
warmup -offline
warmup -start
warmup -stop
warmup -status
预热处理器操作/查看
 -offline   下线预热处理器
 -online    上线预热处理器
 -start     启动预热处理器
 -status    查看预热处理器状态
 -stop      停止预热处理器
```

`-status` 子命令的输出包含以下字段：

- `online`：预热处理器是否在线。
- `latch holding`：预热处理器是否处于等待状态。
- `started`：预热处理器是否已启动。
- `working`：预热处理器是否正在工作。

### 示例

#### 停止和启动预热处理器

```text
warmup -stop
预热处理器已停止!
OK
```

```text
warmup -status
online: true, latch holding: false, started: false, working: false.
OK
```

```text
warmup -start
预热处理器已启动!
OK
```

```text
warmup -status
online: true, latch holding: false, started: true, working: false.
OK
```

#### 下线和上线预热处理器

```text
warmup -offline
预热处理器已下线!
OK
```

```text
warmup -status
online: false, latch holding: false, started: true, working: false.
OK
```

```text
warmup -online
预热处理器已上线!
OK
```

```text
warmup -status
online: true, latch holding: false, started: true, working: false.
OK
```

## warmupdrive 命令

预热驱动处理器操作/查看。预热驱动处理器根据预热设置与预热驱动信息执行具体的预热动作。
该命令在 3.1.0 版本中新增。

### 语法

```text
usage: warmupdrive -status
预热驱动处理器操作/查看
 -status   查看预热驱动处理器状态
```

### 示例

```text
warmupdrive -status
started: false.
OK
```

输出中的 `started` 表示预热驱动处理器是否已启动。

关于预热功能的详细信息，请参见 [Warmup.md](./Warmup.md)。
