# Long Text Node - 长文本节点命令

本页面覆盖 `ltxtnode`。长文本节点通过 `category` 与 `args` 定位，正文保存在服务端文件系统中，
命令本身只返回预览、字节长度和空内容标记，正文的上传和下载都通过服务器本地文件路径完成。

## 命令语法

```text
usage: ltxtnode -inspect [-json json-string] [-jf json-file]
ltxtnode -download [-json json-string] [-jf json-file]
ltxtnode -upload [-json json-string] [-jf json-file]
```

## Sub Command

| option     | 长选项 | 用途                 | JSON 参考类                        | JSON 文件                                            |
|------------|--------|----------------------|------------------------------------|------------------------------------------------------|
| `inspect`  | -      | 查看长文本预览和长度 | `WebInputLongTextNodeInspectInfo`  | [inspect.json](../json-file/ltxtnode/inspect.json)   |
| `download` | -      | 下载长文本内容       | `LongTextNodeCommand.DownloadInfo` | [download.json](../json-file/ltxtnode/download.json) |
| `upload`   | -      | 上传长文本内容       | `LongTextNodeCommand.UploadInfo`   | [upload.json](../json-file/ltxtnode/upload.json)     |

## 字段说明

| 字段        | 适用操作         | 说明                          |
|-------------|------------------|-------------------------------|
| `category`  | 全部             | 设置类别                      |
| `args`      | 全部             | 节点格式化参数                |
| `file_path` | download、upload | Telqos 服务端本地文本文件路径 |

- `inspect` 返回 `preview`、`length` 与 `nullFlag`，其中 `preview` 最多保留正文的前 `1000` 个字符，
  `length` 为上传文件的字节长度。
- 设置节点不存在或类型不是长文本节点时，`inspect` 输出 `查看结果: null`；
  设置节点类型匹配、但长文本尚未上传时，`inspect` 同样输出 `查看结果: null`。
- 长文本尚未上传时，`download` 输出 `长文本文件不存在`，且不会创建 `file_path` 指向的文件。
- `download` 依赖 `file_path` 的父目录已经存在，命令只创建文件本身，不创建目录。
- `upload` 会顺带创建或刷新对应的设置节点并将其类型置为 `1`（长文本节点），
  因此首次写入前无需手工执行 `settingnode -init`。
- `upload` 按 `UTF-8` 编码写入正文，并同时记录长度与预览。
- 设置类别不存在时返回 `exception code=1010 - setting category not exists`。

## 命令示例

命令示例 - 查看尚不存在的长文本节点：

```text
ltxtnode -inspect -jf your-json-file-here.json
查看结果: null
OK
```

命令示例 - 上传长文本内容：

```text
ltxtnode -upload -jf your-json-file-here.json
文本上传成功
OK
```

命令示例 - 查看上传后的长文本节点：

```text
ltxtnode -inspect -jf your-json-file-here.json
查看结果:
  preview: 示例长文本内容
  length: 21
  nullFlag: false
OK
```

命令示例 - 下载长文本内容：

```text
ltxtnode -download -jf your-json-file-here.json
文本下载成功
OK
```

## 风险提示

- `upload` 会覆盖节点原有的长文本内容。
- `download` 会写入 `file_path` 指定的文件，应避免覆盖关键文件。
- 长文本上传前应确认文件编码与业务读取方式一致。
