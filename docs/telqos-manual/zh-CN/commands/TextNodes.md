# Text Nodes - 文本节点命令

本页面覆盖 `txtnode` 和 `ltxtnode`。普通文本节点直接使用 `value` 字段，长文本节点使用本地文件路径完成上传和下载。

## txtnode

文本节点操作服务。

```text
usage: txtnode -inspect [-json json-string] [-jf json-file]
txtnode -put [-json json-string] [-jf json-file]
```

| option    | 长选项 | 用途         | JSON 参考类                   | JSON 文件                                         |
|-----------|--------|--------------|-------------------------------|---------------------------------------------------|
| `inspect` | -      | 查看文本节点 | `WebInputTextNodeInspectInfo` | [inspect.json](../json-file/txtnode/inspect.json) |
| `put`     | -      | 写入文本节点 | `WebInputTextNodePutInfo`     | [put.json](../json-file/txtnode/put.json)         |

### 字段说明

| 字段       | 适用操作 | 说明                             |
|------------|----------|----------------------------------|
| `category` | 全部     | 设置类别                         |
| `args`     | 全部     | 节点格式化参数                   |
| `value`    | put      | 需要写入的文本值；允许为空字符串 |

## ltxtnode

长文本节点操作服务。

```text
usage: ltxtnode -inspect [-json json-string] [-jf json-file]
ltxtnode -download [-json json-string] [-jf json-file]
ltxtnode -upload [-json json-string] [-jf json-file]
```

| option     | 长选项 | 用途                 | JSON 参考类                        | JSON 文件                                            |
|------------|--------|----------------------|------------------------------------|------------------------------------------------------|
| `inspect`  | -      | 查看长文本预览和长度 | `WebInputLongTextNodeInspectInfo`  | [inspect.json](../json-file/ltxtnode/inspect.json)   |
| `download` | -      | 下载长文本内容       | `LongTextNodeCommand.DownloadInfo` | [download.json](../json-file/ltxtnode/download.json) |
| `upload`   | -      | 上传长文本内容       | `LongTextNodeCommand.UploadInfo`   | [upload.json](../json-file/ltxtnode/upload.json)     |

### 字段说明

| 字段        | 适用操作         | 说明                          |
|-------------|------------------|-------------------------------|
| `category`  | 全部             | 设置类别                      |
| `args`      | 全部             | 节点格式化参数                |
| `file_path` | download、upload | Telqos 服务端本地文本文件路径 |

## 风险提示

- `put` 和 `upload` 会覆盖节点原有内容。
- `ltxtnode download` 会写入 `file_path` 指定的文件，应避免覆盖关键文件。
- 长文本上传前应确认文件编码与业务读取方式一致。
