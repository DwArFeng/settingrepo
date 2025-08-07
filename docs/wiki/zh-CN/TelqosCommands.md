# Telqos Commands - Telqos 命令

TODO 待实现

```text
lc
16  txtnode       文本节点操作服务
```

## 概览

本文档主要介绍 Setting Repository 2.x 版本提供的 Telqos 命令。

Setting Repository 2.x 版本提供的 Telqos 命令分为两类：

- 基础命令：Telqos 项目的内置命令，包括列出命令和查看命令等维护性命令。
- 第三方命令：Telqos 项目集成的第三方命令。
- 服务命令：Setting Repository 2.x 版本提供的服务功能相关的命令。

本文将会基于命令的一下几个方面进行介绍：

- 命令标识：命令的标识符。
- 命令说明：命令的简要说明。
- 可用版本：命令的可用版本。
- 命令语法：命令的语法说明。
- JSON schema 参考：对于部分需要使用 JSON 字符串的命令，本文档将会提供这些 JSON 字符串或 JSON 文件的参考类。
- 命令示例：命令的一个或多个示例。

需要注意的是，在命令示例中，由于部分指令的输出内容较长，对于列表中的迭代的输出部分或是不重要的部分，将会省略，
省略部分将使用 `etc...` 进行标注。

## 基础命令

### lc

命令标识：`lc`。

命令说明：列出指令。

可用版本：`1.0.0`。

命令语法：

```text
usage: lc [-p prefix|--prefix prefix]
列出指令
 -p,--prefix <prefix>   列出包含指定前缀的命令
```

命令示例 - 列出所有指令：

```text
lc
1   datamark      数据标记服务
2   dubbo         分布式服务上线/下线
etc...
15  shutdown      关闭/重启程序
16  txtnode       文本节点操作服务
----------------------------
共 16 条
OK
```

命令示例 - 列出特定命令：

```text
lc -p i
1   iahnnode   国际化节点操作服务
2   imglnode   图片列表节点操作服务
3   imgnode    图片节点操作服务
-------------------------
共 3 条
OK
```

### man

命令标识：`man`。

命令说明：显示指令的详细信息。

可用版本：`1.0.0`。

命令语法：

```text
usage: man [command]
显示指令的详细信息
```

命令示例：

```text
man man
usage: man [command]
显示指令的详细信息
```

### quit

命令标识：`quit`。

命令说明：退出 Telqos 运维平台。

可用版本：`1.0.0`。

命令语法：

```text
usage: quit
```

命令示例：

```text
quit
Bye
服务端主动与您中断连接
再见!
```

## 第三方功能命令

### datamark

命令标识：`datamark`。

命令说明：数据标记服务。

可用版本：`2.3.0`。

命令语法：

```text
usage: datamark -lh
datamark -ua [-hn handler-name]
datamark -get [-hn handler-name]
datamark -refresh [-hn handler-name]
datamark -update [-hn handler-name] [-dv datamark-value]
数据标记服务
 -dv <arg>              数据标记值
 -get                   获取数据标记值
 -hn <arg>              数据服务 ID
 -lh,--list-handlers    列出所有可用的数据标记处理器
 -refresh               刷新数据标记值
 -ua,--update-allowed   返回处理器是否允许更新
 -update                更新数据标记值
```

命令示例 - 列出所有可用的数据标记处理器：

```text
datamark -lh
可用的处理器名称:
    1: settingCategoryDatamarkHandler
    2: settingNodeDatamarkHandler
OK
```

命令示例 - 返回处理器是否允许更新：

```text
datamark -ua -hn settingCategoryDatamarkHandler
处理器名称: settingCategoryDatamarkHandler, 允许更新: true
OK
```

命令示例 - 获取数据标记值：

```text
datamark -get -hn settingCategoryDatamarkHandler
处理器名称: settingCategoryDatamarkHandler, 数据标记值: settingrepo-node
OK
```

命令示例 - 刷新数据标记值：

```text
datamark -refresh -hn settingCategoryDatamarkHandler
刷新成功!
处理器名称: settingCategoryDatamarkHandler, 刷新后的数据标记值: settingrepo-node
OK
```

命令示例 - 更新数据标记值：

```text
datamark -update -hn settingCategoryDatamarkHandler -dv foobar
更新成功!
处理器名称: settingCategoryDatamarkHandler, 更新的数据标记值: foobar
OK
```

### dubbo

命令标识：`dubbo`。

命令说明：分布式服务上线/下线。

可用版本：`1.0.0`。

命令语法：

```text
usage: dubbo -online [service-name]
dubbo -offline [service-name]
dubbo -ls
分布式服务上线/下线
 -ls              列出服务
 -offline <arg>   下线服务
 -online <arg>    上线服务
```

命令示例 - 列出服务：

```text
dubbo -ls
As Provider side:
+-----------------------------------------------------------------------------------------+---+
|                                  Provider Service Name                                  |PUB|
+-----------------------------------------------------------------------------------------+---+
|              com.dwarfeng.settingrepo.stack.service.IahnNodeMaintainService             | Y |
+-----------------------------------------------------------------------------------------+---+
etc...
+-----------------------------------------------------------------------------------------+---+
|            com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService            | Y |
+-----------------------------------------------------------------------------------------+---+
As Consumer side:
+-------------------------------------------------+---+
|              Consumer Service Name              |NUM|
+-------------------------------------------------+---+
|com.dwarfeng.voucher.stack.service.VoucherService| 3 |
+-------------------------------------------------+---+
| com.dwarfeng.sfds.stack.service.GenerateService | 3 |
+-------------------------------------------------+---+
OK
```

命令示例 - 上线服务：

```text
dubbo -online
OK

dubbo -ls
As Provider side:
+-----------------------------------------------------------------------------------------+---+
|                                  Provider Service Name                                  |PUB|
+-----------------------------------------------------------------------------------------+---+
|              com.dwarfeng.settingrepo.stack.service.IahnNodeMaintainService             | Y |
+-----------------------------------------------------------------------------------------+---+
etc...
+-----------------------------------------------------------------------------------------+---+
|            com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService            | Y |
+-----------------------------------------------------------------------------------------+---+
As Consumer side:
+-------------------------------------------------+---+
|              Consumer Service Name              |NUM|
+-------------------------------------------------+---+
|com.dwarfeng.voucher.stack.service.VoucherService| 3 |
+-------------------------------------------------+---+
| com.dwarfeng.sfds.stack.service.GenerateService | 3 |
+-------------------------------------------------+---+
OK
```

命令示例 - 下线服务：

```text
dubbo -offline
OK

dubbo -ls
As Provider side:
+-----------------------------------------------------------------------------------------+---+
|                                  Provider Service Name                                  |PUB|
+-----------------------------------------------------------------------------------------+---+
|              com.dwarfeng.settingrepo.stack.service.IahnNodeMaintainService             | N |
+-----------------------------------------------------------------------------------------+---+
etc...
+-----------------------------------------------------------------------------------------+---+
|            com.dwarfeng.settingrepo.stack.service.SettingNodeMaintainService            | N |
+-----------------------------------------------------------------------------------------+---+
As Consumer side:
+-------------------------------------------------+---+
|              Consumer Service Name              |NUM|
+-------------------------------------------------+---+
|com.dwarfeng.voucher.stack.service.VoucherService| 3 |
+-------------------------------------------------+---+
| com.dwarfeng.sfds.stack.service.GenerateService | 3 |
+-------------------------------------------------+---+
```

### log4j2

命令标识：`log4j2`。

命令说明：Log4j2 命令。

可用版本：`2.0.0`。

命令语法：

```text
usage: log4j2 -reconfigure
Log4j2 命令
 -reconfigure   重新配置 Log4j2
```

命令示例 - 重新配置 Log4j2：

```text
log4j2 -reconfigure
Log4j2 已重新配置!
OK
```

### memory

命令标识：`memory`。

命令说明：内存监视。

可用版本：`1.0.0`。

命令语法：

```text
usage: memory [-u unit]
内存监视
 -u <arg>   显示单位
```

命令示例 - 使用默认单位：

```text
memory
JVM 最大内存: 7.09GiB
JVM 分配内存: 999.50MiB
JVM 可用内存: 518.13MiB
OK
```

命令示例 - 使用指定单位：

```text
memory -u KiB
JVM 最大内存: 7438336.00KiB
JVM 分配内存: 1023488.00KiB
JVM 可用内存: 529027.88KiB
OK
```

### shutdown

命令标识：`shutdown`。

命令说明：关闭/重启程序。

可用版本：`1.0.0`。

命令语法：

```text
man shutdown
usage: shutdown [-s/-r] [-e exit-code] [-c comment]
关闭/重启程序
 -c <comment>     备注
 -e <exit-code>   退出代码
 -r               重启程序
 -s               退出程序
```

需要注意的是，在本项目中，重启程序功能不可用，只能使用关闭程序功能。

命令示例 - 关闭程序：

```text
shutdown -s -c "routine maintenance"
服务将会关闭，您可能需要登录远程主机才能重新启动该服务，是否继续? Y/N
Y
已确认请求，服务即将关闭...
服务端主动与您中断连接
再见!
```

## 服务功能命令

### flc

命令标识：`flc`。

命令说明：格式化器本地缓存操作。

可用版本：`1.0.0`。

命令语法：

```text
usage: flc -l id
flc -c
格式化器本地缓存操作
 -c         清除格式化器
 -l <arg>   查询格式化器
```

命令示例 - 查询格式化器：

```text
flc -l foobar
GroovyFormatter{settingCategory= etc...
OK
```

命令示例 - 清除格式化器：

```text
flc -c
本地缓存已清除
OK
```

### iahnnode

命令标识：`iahnnode`。

命令说明：国际化节点操作服务。

可用版本：`2.1.0`。

命令语法：

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
国际化节点操作服务
 -biml,--batch-inspect-message-by-locale   基于国际化地区批量查询国际化消息
 -buml,--batch-upsert-message-by-locale    基于国际化地区批量插入或更新国际化消息
 -bumm,--batch-upsert-message-by-mek       基于国际化 Mek 批量插入或更新国际化消息
 -ill,--inspect-locale-list                查询国际化地区列表
 -im,--inspect-message                     查询国际化消息
 -iml,--inspect-mek-list                   查询国际化 Mek 列表
 -imt,--inspect-message-table              查询国际化消息表
 -jf,--json-file <arg>                     JSON文件
 -json <arg>                               JSON字符串
 -pl,--put-locale                          推入国际化地区
 -pm,--put-mek                             推入国际化 Mek
 -rl,--remove-locale                       移除国际化地区
 -rm,--remove-mek                          移除国际化 Mek
 -um,--upsert-message                      插入或更新国际化消息
```

JSON schema 参考：

| option | reference class                                                                  |
|:-------|:---------------------------------------------------------------------------------|
| im     | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeMessageInspectInfo         |
| biml   | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeMessageInspectByLocaleInfo |
| ill    | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeLocaleListInspectInfo      |
| iml    | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeMekListInspectInfo         |
| imt    | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeMessageTableInspectInfo    |
| pl     | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeLocalePutInfo              |
| rl     | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeLocaleRemoveInfo           |
| pm     | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeMekPutInfo                 |
| rm     | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeMekRemoveInfo              |
| um     | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeMessageUpsertInfo          |
| buml   | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeMessageUpsertByLocaleInfo  |
| bumm   | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputIahnNodeMessageUpsertByMekInfo     |

命令示例 - 查询国际化消息：

```text
iahnnode -im -jf your-json-file-here.json
查询结果:
  message: 你好
OK
```

命令示例 - 基于国际化地区批量查询国际化消息：

```text
iahnnode -biml -jf your-json-file-here.json
查询结果:
  items: total size: 2
0 / 2: medId = mek.2, message = 世界
1 / 2: medId = mek.1, message = こんにちは
OK
```

### imglnode

命令标识：`imglnode`。

命令说明：图片列表节点操作服务。

可用版本：`2.0.0`。

命令语法：

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
 -jf,--json-file <arg>   JSON文件
 -json <arg>             JSON字符串
 -remove                 移除图片列表节点
 -size                   获取图片列表节点的大小
 -update                 更新图片列表节点文件
 -upload                 上传图片列表节点文件
```

JSON schema 参考：

| option   | reference class                                                                |
|:---------|:-------------------------------------------------------------------------------|
| size     | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputImageListNodeSizeInfo            |
| inspect  | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputImageListNodeInspectInfo         |
| download | com.dwarfeng.settingrepo.impl.service.telqos.ImageListNodeCommand.DownloadInfo |
| upload   | com.dwarfeng.settingrepo.impl.service.telqos.ImageListNodeCommand.UploadInfo   |
| update   | com.dwarfeng.settingrepo.impl.service.telqos.ImageListNodeCommand.UpdateInfo   |
| co       | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputImageListNodeChangeOrderInfo     |
| remove   | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputImageListNodeRemoveInfo          |

命令示例 - 获取图片列表节点的大小：

```text
imglnode -size -jf your-json-file-here.json
图片列表节点的大小:
  size: 9
OK
```

命令示例 - 查看图片列表节点：

```text
imglnode -inspect -jf your-json-file-here.json
查看结果:
  items: total size: 9
    0/9:
      nullFlag: false
      originName: photo1.jpg
      length: 404619
    etc...
    8/9:
      nullFlag: false
      originName: photo2.jpg
      length: 511570
OK
```

命令示例 - 下载图片列表节点文件：

```text
imglnode -download -jf your-json-file-here.json
文件下载成功
OK
```

### imgnode

命令标识：`imgnode`。

命令说明：图片节点操作服务。

可用版本：`2.0.0`。

命令语法：

```text
usage: imgnode -inspect [-json json-string] [-jf json-file]
imgnode -download [-json json-string] [-jf json-file]
imgnode -upload [-json json-string] [-jf json-file]
图片节点操作服务
 -download               下载图片节点图片
 -inspect                查看图片节点图片
 -jf,--json-file <arg>   JSON文件
 -json <arg>             JSON字符串
 -upload                 上传图片节点图片
```

JSON schema 参考：

| option   | reference class                                                            |
|:---------|:---------------------------------------------------------------------------|
| inspect  | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputImageNodeInspectInfo         |
| download | com.dwarfeng.settingrepo.impl.service.telqos.ImageNodeCommand.DownloadInfo |
| upload   | com.dwarfeng.settingrepo.impl.service.telqos.ImageNodeCommand.UploadInfo   |

命令示例 - 查看图片节点图片：

```text
imgnode -inspect -jf your-json-file-here.json
查看结果:
  originName: photo.jpg
  length: 237670
OK
```

命令示例 - 下载图片节点图片：

```text
imgnode -download -jf your-json-file-here.json
文件下载成功
OK
```

### ltxtnode

命令标识：`ltxtnode`。

命令说明：长文本节点操作服务。

可用版本：`2.2.0`。

命令语法：

```text
usage: ltxtnode -inspect [-json json-string] [-jf json-file]
ltxtnode -download [-json json-string] [-jf json-file]
ltxtnode -upload [-json json-string] [-jf json-file]
长文本节点操作服务
 -download               下载长文本节点长文本
 -inspect                查看长文本节点长文本
 -jf,--json-file <arg>   JSON文本
 -json <arg>             JSON字符串
 -upload                 上传长文本节点长文本
```

JSON schema 参考：

| option   | reference class                                                               |
|:---------|:------------------------------------------------------------------------------|
| inspect  | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputLongTextNodeInspectInfo         |
| download | com.dwarfeng.settingrepo.impl.service.telqos.LongTextNodeCommand.DownloadInfo |
| upload   | com.dwarfeng.settingrepo.impl.service.telqos.LongTextNodeCommand.UploadInfo   |

命令示例 - 查看长文本节点长文本：

```text
ltxtnode -inspect -jf your-json-file-here.json
查看结果:
  preview: #!/bin/sh
etc...
  length: 1175
  nullFlag: false
OK
```

命令示例 - 下载长文本节点长文本：

```text
ltxtnode -download -jf your-json-file-here.json
文本下载成功
OK
```

### reset

命令标识：`reset`。

命令说明：重置处理器操作/查看。

可用版本：`1.1.1`。

命令语法：

```text
usage: reset -l
reset -start
reset -stop
reset -status
reset --reset-format
重置处理器操作/查看
    --l              查看重置处理器
    --reset-format   执行格式化重置操作
    --start          启动重置处理器
    --status         查看重置处理器状态
    --stop           停止重置处理器
```

命令示例 - 查看重置处理器：

```text
reset -l
01. CronResetter{cron='0 0 1 * * *'}
02. DubboResetter{ctx= etc...
03. NeverResetter{}
OK
```

命令示例 - 查看重置处理器状态：

```text
reset -status
started: true
OK
```

命令示例 - 执行格式化重置操作：

```text
reset --reset-format
重置成功!
OK
```

### settingnode

命令标识：`settingnode`。

命令说明：设置节点操作服务。

可用版本：`2.0.0`。

命令语法：

```text
usage: settingnode -exists [-json json-string] [-jf json-file]
settingnode -inspect [-json json-string] [-jf json-file]
settingnode -init [-json json-string] [-jf json-file]
settingnode -remove [-json json-string] [-jf json-file]
设置节点操作服务
 -exists                 判断指定的设置节点是否存在
 -init                   初始化指定的设置节点
 -inspect                查看指定的设置节点
 -jf,--json-file <arg>   JSON文件
 -json <arg>             JSON字符串
 -remove                 移除指定的设置节点
```

JSON schema 参考：

| option  | reference class                                                      |
|:--------|:---------------------------------------------------------------------|
| exists  | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeExistsInfo  |
| inspect | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeInspectInfo |
| init    | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeInitInfo    |
| remove  | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputSettingNodeRemoveInfo  |

命令示例 - 判断指定的设置节点是否存在：

```text
settingnode -exists -jf your-json-file-here.json
节点存在: true
OK
```

命令示例 - 查看指定的设置节点：

```text
settingnode -inspect -jf your-json-file-here.json
查询结果:
  type: 0 (文本)
  lastModifiedDate: 2025-06-29 14:27:28.02
  remark: 由 settingrepo 自动生成的文本节点
OK
```

### txtnode

命令标识：`txtnode`。

命令说明：文本节点操作服务。

可用版本：`2.0.0`。

命令语法：

```text
usage: txtnode -inspect [-json json-string] [-jf json-file]
txtnode -put [-json json-string] [-jf json-file]
文本节点操作服务
 -inspect                查看指定的文本节点
 -jf,--json-file <arg>   JSON文件
 -json <arg>             JSON字符串
 -put                    向指定的文本节点中放入指定的信息
```

JSON schema 参考：

| option  | reference class                                                   |
|:--------|:------------------------------------------------------------------|
| inspect | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputTextNodeInspectInfo |
| put     | com.dwarfeng.settingrepo.sdk.bean.dto.WebInputTextNodePutInfo     |

命令示例 - 查看指定的文本节点：

```text
查看结果:
  value: {"pinnedNodeKeys": etc...
OK
```
