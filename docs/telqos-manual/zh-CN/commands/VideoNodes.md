# Video Nodes - 视频节点命令

本页面覆盖 `videolnode` 和 `videonode`。视频内容通过服务器本地文件路径 `file_path` 上传或下载，
上传时还需通过 `cover_file_path` 指定服务器本地封面文件。

## videolnode

视频列表节点操作服务。

```text
usage: videolnode -size [-json json-string] [-jf json-file]
videolnode -inspect [-json json-string] [-jf json-file]
videolnode -download [-json json-string] [-jf json-file]
videolnode -upload [-json json-string] [-jf json-file]
videolnode -update [-json json-string] [-jf json-file]
videolnode -co,--change-order [-json json-string] [-jf json-file]
videolnode -remove [-json json-string] [-jf json-file]
```

| option     | 长选项         | 用途             | JSON 参考类                            | JSON 文件                                                      |
|------------|----------------|------------------|----------------------------------------|----------------------------------------------------------------|
| `size`     | -              | 获取视频列表大小 | `WebInputVideoListNodeSizeInfo`        | [size.json](../json-file/videolnode/size.json)                 |
| `inspect`  | -              | 查看视频列表     | `WebInputVideoListNodeInspectInfo`     | [inspect.json](../json-file/videolnode/inspect.json)           |
| `download` | -              | 下载指定视频     | `VideoListNodeCommand.DownloadInfo`    | [download.json](../json-file/videolnode/download.json)         |
| `upload`   | -              | 上传视频到列表   | `VideoListNodeCommand.UploadInfo`      | [upload.json](../json-file/videolnode/upload.json)             |
| `update`   | -              | 更新指定视频     | `VideoListNodeCommand.UpdateInfo`      | [update.json](../json-file/videolnode/update.json)             |
| `co`       | `change-order` | 调整视频顺序     | `WebInputVideoListNodeChangeOrderInfo` | [change-order.json](../json-file/videolnode/change-order.json) |
| `remove`   | -              | 移除视频条目     | `WebInputVideoListNodeRemoveInfo`      | [remove.json](../json-file/videolnode/remove.json)             |

### 字段说明

| 字段              | 适用操作                         | 说明                          |
|-------------------|----------------------------------|-------------------------------|
| `category`        | 全部                             | 设置类别                      |
| `args`            | 全部                             | 节点格式化参数                |
| `index`           | download、upload、update、remove | 视频列表索引，从 `0` 开始     |
| `file_path`       | download、upload、update         | Telqos 服务端本地视频文件路径 |
| `cover_file_path` | upload、update                   | Telqos 服务端本地封面文件路径 |
| `old_index`       | change-order                     | 调整前的索引                  |
| `neo_index`       | change-order                     | 调整后的索引                  |

- `size`、`inspect` 为只读操作。设置节点不存在或类型不是视频列表节点时，`size` 输出 `视频列表节点的大小: null`，
  `inspect` 输出 `查看结果: null`；设置节点类型匹配、但列表实体尚未建立时，`size` 输出 `size: 0`，
  `inspect` 输出 `items: total size: 0`。
- `upload` 的 `index` 可以省略或置为 `null`，此时视频被追加到列表末尾；取值小于 `size` 时插入到该位置，
  原有条目索引整体加一；取值大于 `size` 时，`size` 到 `index` 之间会补入 `nullFlag` 为 `true` 的空条目。
- `upload`、`update` 均需要提供 `file_path` 和 `cover_file_path`，两者均由 Telqos 服务进程读取；
  任一文件不存在时命令以 `java.nio.file.NoSuchFileException` 失败，不会写入数据库。
- 封面在写入文件之前先用于生成缩略图，因此封面内容不合法时上传直接失败，不会产生残留文件，
  也不会破坏节点中已有的数据。
- 条目 `nullFlag` 为 `true` 时，`inspect` 中该条目的 `originName` 与 `length` 均为 `null`。
- `remove` 之后，本次操作之前位于其后的条目索引整体减一，列表索引始终保持连续。

### 命令示例

命令示例 - 查看尚不存在的视频列表节点：

```text
videolnode -size -jf your-json-file-here.json
视频列表节点的大小: null
OK
```

命令示例 - 上传视频到列表：

```text
videolnode -upload -jf your-json-file-here.json
文件上传成功
OK
```

命令示例 - 查看视频列表节点：

```text
videolnode -inspect -jf your-json-file-here.json
查看结果:
  items: total size: 2
    0/2:
      nullFlag: false
      originName: vn_demo.mp4
      length: 1139614
    1/2:
      nullFlag: false
      originName: vn_demo.mp4
      length: 1139614
OK
```

命令示例 - 获取视频列表节点的大小：

```text
videolnode -size -jf your-json-file-here.json
视频列表节点的大小:
  size: 2
OK
```

命令示例 - 下载指定视频：

```text
videolnode -download -jf your-json-file-here.json
文件下载成功
OK
```

命令示例 - 更新指定视频：

```text
videolnode -update -jf your-json-file-here.json
文件更新成功
OK
```

命令示例 - 调整视频顺序：

```text
videolnode -co -jf your-json-file-here.json
更改顺序成功
OK
```

命令示例 - 移除视频条目：

```text
videolnode -remove -jf your-json-file-here.json
移除成功
OK
```

## videonode

视频节点操作服务。

视频节点保存单个视频及其封面，`inspect` 返回视频文件的原始名称和大小。`download` 将视频写入指定的服务器本地路径，
但不会同时下载封面。

```text
usage: videonode -inspect [-json json-string] [-jf json-file]
videonode -download [-json json-string] [-jf json-file]
videonode -upload [-json json-string] [-jf json-file]
```

| option     | 长选项 | 用途             | JSON 参考类                     | JSON 文件                                             |
|------------|--------|------------------|---------------------------------|-------------------------------------------------------|
| `inspect`  | -      | 查看视频节点     | `WebInputVideoNodeInspectInfo`  | [inspect.json](../json-file/videonode/inspect.json)   |
| `download` | -      | 下载视频节点内容 | `VideoNodeCommand.DownloadInfo` | [download.json](../json-file/videonode/download.json) |
| `upload`   | -      | 上传视频节点内容 | `VideoNodeCommand.UploadInfo`   | [upload.json](../json-file/videonode/upload.json)     |

### 字段说明

| 字段              | 适用操作         | 说明                          |
|-------------------|------------------|-------------------------------|
| `category`        | 全部             | 设置类别                      |
| `args`            | 全部             | 节点格式化参数                |
| `file_path`       | download、upload | Telqos 服务端本地视频文件路径 |
| `cover_file_path` | upload           | Telqos 服务端本地封面文件路径 |

- `upload` 需要同时提供视频文件和封面文件；`inspect` 返回文件的原始名称与大小。
- 设置节点不存在、类型不匹配或视频尚未上传时，`inspect` 输出 `查看结果: null`，`download` 输出 `视频文件不存在`。

### 命令示例

命令示例 - 查看尚不存在的视频节点：

```text
videonode -inspect -jf your-json-file-here.json
查看结果: null
OK
```

命令示例 - 上传视频节点内容：

```text
videonode -upload -jf your-json-file-here.json
文件上传成功
OK
```

命令示例 - 查看视频节点：

```text
videonode -inspect -jf your-json-file-here.json
查看结果:
  originName: vn_demo.mp4
  length: 1139614
OK
```

命令示例 - 下载视频节点内容：

```text
videonode -download -jf your-json-file-here.json
文件下载成功
OK
```

## 风险提示

- 视频上传前应确认文件格式、大小和业务允许的视频类型。
- `upload`、`update` 均需要视频与封面两个文件，缺少任意一个都会失败。
- `update` 会替换列表中原有的视频内容，执行前应确认 `index`。
- 下载与更新会覆盖 `file_path` 指向的同名文件，执行前应妥善选择目标路径。
- `upload`、`update`、`change-order`、`remove` 都会写入数据库，属于不可自动回滚的写操作。
