# Image Nodes - 图片节点命令

本页面覆盖 `imglnode` 和 `imgnode`。图片内容通过服务器本地文件路径 `file_path` 上传或下载。

## imglnode

图片列表节点操作服务。

```text
usage: imglnode -size [-json json-string] [-jf json-file]
imglnode -inspect [-json json-string] [-jf json-file]
imglnode -download [-json json-string] [-jf json-file]
imglnode -upload [-json json-string] [-jf json-file]
imglnode -update [-json json-string] [-jf json-file]
imglnode -co,--change-order [-json json-string] [-jf json-file]
imglnode -remove [-json json-string] [-jf json-file]
```

| option     | 长选项         | 用途             | JSON 参考类                            | JSON 文件                                                    |
|------------|----------------|------------------|----------------------------------------|--------------------------------------------------------------|
| `size`     | -              | 获取图片列表大小 | `WebInputImageListNodeSizeInfo`        | [size.json](../json-file/imglnode/size.json)                 |
| `inspect`  | -              | 查看图片列表     | `WebInputImageListNodeInspectInfo`     | [inspect.json](../json-file/imglnode/inspect.json)           |
| `download` | -              | 下载指定图片     | `ImageListNodeCommand.DownloadInfo`    | [download.json](../json-file/imglnode/download.json)         |
| `upload`   | -              | 上传图片到列表   | `ImageListNodeCommand.UploadInfo`      | [upload.json](../json-file/imglnode/upload.json)             |
| `update`   | -              | 更新指定图片     | `ImageListNodeCommand.UpdateInfo`      | [update.json](../json-file/imglnode/update.json)             |
| `co`       | `change-order` | 调整图片顺序     | `WebInputImageListNodeChangeOrderInfo` | [change-order.json](../json-file/imglnode/change-order.json) |
| `remove`   | -              | 移除图片条目     | `WebInputImageListNodeRemoveInfo`      | [remove.json](../json-file/imglnode/remove.json)             |

### 字段说明

| 字段        | 适用操作                         | 说明                          |
|-------------|----------------------------------|-------------------------------|
| `category`  | 全部                             | 设置类别                      |
| `args`      | 全部                             | 节点格式化参数                |
| `index`     | download、upload、update、remove | 图片列表索引，从 `0` 开始     |
| `file_path` | download、upload、update         | Telqos 服务端本地图片文件路径 |
| `old_index` | change-order                     | 调整前的索引                  |
| `neo_index` | change-order                     | 调整后的索引                  |

## imgnode

图片节点操作服务。

```text
usage: imgnode -inspect [-json json-string] [-jf json-file]
imgnode -download [-json json-string] [-jf json-file]
imgnode -upload [-json json-string] [-jf json-file]
```

| option     | 长选项 | 用途             | JSON 参考类                     | JSON 文件                                           |
|------------|--------|------------------|---------------------------------|-----------------------------------------------------|
| `inspect`  | -      | 查看图片节点     | `WebInputImageNodeInspectInfo`  | [inspect.json](../json-file/imgnode/inspect.json)   |
| `download` | -      | 下载图片节点内容 | `ImageNodeCommand.DownloadInfo` | [download.json](../json-file/imgnode/download.json) |
| `upload`   | -      | 上传图片节点内容 | `ImageNodeCommand.UploadInfo`   | [upload.json](../json-file/imgnode/upload.json)     |

### 字段说明

| 字段        | 适用操作         | 说明                          |
|-------------|------------------|-------------------------------|
| `category`  | 全部             | 设置类别                      |
| `args`      | 全部             | 节点格式化参数                |
| `file_path` | download、upload | Telqos 服务端本地图片文件路径 |

## 风险提示

- 图片上传前应确认文件格式、大小和业务允许的图片类型。
- `update` 会替换列表中的图片内容，执行前应确认 `index`。
- 下载路径应有明确命名，避免覆盖已有图片文件。
