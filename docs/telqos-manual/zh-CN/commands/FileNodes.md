# File Nodes - 文件节点命令

本页面覆盖 `filelnode` 和 `filenode`。两者均通过 `category` 与 `args` 定位节点，并使用服务器本地
`file_path` 完成文件上传、下载和更新。

## filelnode

文件列表节点操作服务。

```text
usage: filelnode -size [-json json-string] [-jf json-file]
filelnode -inspect [-json json-string] [-jf json-file]
filelnode -download [-json json-string] [-jf json-file]
filelnode -upload [-json json-string] [-jf json-file]
filelnode -update [-json json-string] [-jf json-file]
filelnode -co,--change-order [-json json-string] [-jf json-file]
filelnode -remove [-json json-string] [-jf json-file]
```

| option     | 长选项         | 用途               | JSON 参考类                           | JSON 文件                                                     |
|------------|----------------|--------------------|---------------------------------------|---------------------------------------------------------------|
| `size`     | -              | 获取文件列表大小   | `WebInputFileListNodeSizeInfo`        | [size.json](../json-file/filelnode/size.json)                 |
| `inspect`  | -              | 查看文件列表       | `WebInputFileListNodeInspectInfo`     | [inspect.json](../json-file/filelnode/inspect.json)           |
| `download` | -              | 下载列表中指定文件 | `FileListNodeCommand.DownloadInfo`    | [download.json](../json-file/filelnode/download.json)         |
| `upload`   | -              | 上传文件到列表     | `FileListNodeCommand.UploadInfo`      | [upload.json](../json-file/filelnode/upload.json)             |
| `update`   | -              | 更新列表中指定文件 | `FileListNodeCommand.UpdateInfo`      | [update.json](../json-file/filelnode/update.json)             |
| `co`       | `change-order` | 调整列表条目顺序   | `WebInputFileListNodeChangeOrderInfo` | [change-order.json](../json-file/filelnode/change-order.json) |
| `remove`   | -              | 移除列表条目       | `WebInputFileListNodeRemoveInfo`      | [remove.json](../json-file/filelnode/remove.json)             |

### 字段说明

| 字段        | 适用操作                         | 说明                      |
|-------------|----------------------------------|---------------------------|
| `category`  | 全部                             | 设置类别                  |
| `args`      | 全部                             | 节点格式化参数            |
| `index`     | download、upload、update、remove | 列表条目索引，从 `0` 开始 |
| `file_path` | download、upload、update         | Telqos 服务端本地文件路径 |
| `old_index` | change-order                     | 调整前的索引              |
| `neo_index` | change-order                     | 调整后的索引              |

下载命令将文件写入 `file_path`；上传命令从 `file_path` 读取文件；更新命令从该路径读取替换内容。

## filenode

文件节点操作服务。

```text
usage: filenode -inspect [-json json-string] [-jf json-file]
filenode -download [-json json-string] [-jf json-file]
filenode -upload [-json json-string] [-jf json-file]
```

| option     | 长选项 | 用途             | JSON 参考类                    | JSON 文件                                            |
|------------|--------|------------------|--------------------------------|------------------------------------------------------|
| `inspect`  | -      | 查看文件节点     | `WebInputFileNodeInspectInfo`  | [inspect.json](../json-file/filenode/inspect.json)   |
| `download` | -      | 下载文件节点内容 | `FileNodeCommand.DownloadInfo` | [download.json](../json-file/filenode/download.json) |
| `upload`   | -      | 上传文件节点内容 | `FileNodeCommand.UploadInfo`   | [upload.json](../json-file/filenode/upload.json)     |

### 字段说明

| 字段        | 适用操作         | 说明                      |
|-------------|------------------|---------------------------|
| `category`  | 全部             | 设置类别                  |
| `args`      | 全部             | 节点格式化参数            |
| `file_path` | download、upload | Telqos 服务端本地文件路径 |

## 风险提示

- `upload` 和 `update` 会修改节点内容，执行前应确认 `category`、`args` 和 `index`。
- `download` 和 `update` 可能覆盖 `file_path` 指向的本地文件。
- `remove` 会移除列表条目，执行前应确认节点内容和索引。
