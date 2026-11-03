# Text Nodes - 文本节点命令

本页面覆盖 `txtnode` 和 `textlnode`。两者均通过 `category` 与 `args` 定位节点：
`txtnode` 维护单个文本值，`textlnode` 维护一个有序的文本列表。

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

- `inspect` 在设置节点不存在、类型不是文本节点，或文本节点实体尚未创建时输出 `查看结果: null`。
- 文本节点实体已存在、但其值为 `null` 时（例如以 `"value": null` 执行过 `put`），`inspect` 输出
  `查看结果:` 与 `  value: null`。
- `put` 会顺带创建或刷新对应的设置节点并将其类型置为 `0`（文本节点），因此首次写入前无需手工执行 `settingnode -init`。
- `put` 写入空字符串后，`inspect` 输出 `  value:`，冒号后没有内容。
- 设置类别不存在时返回 `exception code=1010 - setting category not exists`。

### 命令示例

命令示例 - 查看尚不存在的文本节点：

```text
txtnode -inspect -jf your-json-file-here.json
查看结果: null
OK
```

命令示例 - 写入文本节点：

```text
txtnode -put -jf your-json-file-here.json
放入成功
OK
```

命令示例 - 查看写入后的文本节点：

```text
txtnode -inspect -jf your-json-file-here.json
查看结果:
  value: 示例值
OK
```

## textlnode

文本列表节点操作服务。文本列表节点是一个有序的文本集合，每个元素都有独立的索引，内容直接以字符串形式存放在节点条目中。

```text
usage: textlnode -size [-json json-string] [-jf json-file]
textlnode -inspect [-json json-string] [-jf json-file]
textlnode -fetch [-json json-string] [-jf json-file]
textlnode -insert [-json json-string] [-jf json-file]
textlnode -update [-json json-string] [-jf json-file]
textlnode -co,--change-order [-json json-string] [-jf json-file]
textlnode -remove [-json json-string] [-jf json-file]
```

| option    | 长选项         | 用途                   | JSON 参考类                           | JSON 文件                                                    |
|-----------|----------------|------------------------|---------------------------------------|--------------------------------------------------------------|
| `size`    | -              | 获取文本列表节点的大小 | `WebInputTextListNodeSizeInfo`        | [size.json](../json-file/txtlnode/size.json)                 |
| `inspect` | -              | 查看文本列表节点       | `WebInputTextListNodeInspectInfo`     | [inspect.json](../json-file/txtlnode/inspect.json)           |
| `fetch`   | -              | 拉取文本列表节点的文本 | `WebInputTextListNodeTextFetchInfo`   | [fetch.json](../json-file/txtlnode/fetch.json)               |
| `insert`  | -              | 插入文本列表节点的文本 | `WebInputTextListNodeTextInsertInfo`  | [insert.json](../json-file/txtlnode/insert.json)             |
| `update`  | -              | 更新文本列表节点的文本 | `WebInputTextListNodeTextUpdateInfo`  | [update.json](../json-file/txtlnode/update.json)             |
| `co`      | `change-order` | 更改文本列表节点的顺序 | `WebInputTextListNodeChangeOrderInfo` | [change-order.json](../json-file/txtlnode/change-order.json) |
| `remove`  | -              | 移除文本列表节点       | `WebInputTextListNodeRemoveInfo`      | [remove.json](../json-file/txtlnode/remove.json)             |

### 字段说明

| 字段        | 适用操作                      | 说明                                                    |
|-------------|-------------------------------|---------------------------------------------------------|
| `category`  | 全部                          | 设置类别                                                |
| `args`      | 全部                          | 节点格式化参数                                          |
| `index`     | fetch、insert、update、remove | 文本列表索引，从 `0` 开始；`insert` 时可省略或为 `null` |
| `content`   | insert、update                | 需要写入的文本内容                                      |
| `old_index` | change-order                  | 调整前的索引                                            |
| `neo_index` | change-order                  | 调整后的索引                                            |

- 设置节点不存在或类型不是文本列表节点时，`size` 输出 `文本列表节点的大小: null`，
  `inspect` 输出 `查看结果: null`，`fetch` 输出 `拉取结果: null`。
- 设置节点类型匹配、但列表实体尚未建立时，`size` 输出 `size: 0`，`inspect` 输出 `items: total size: 0`，
  并顺带建立大小为 `0` 的列表实体；此状态下 `fetch` 输出 `拉取结果: null`。
- `insert`、`update`、`change-order`、`remove` 会顺带创建或刷新对应的设置节点，
  并将其类型置为 `9`（文本列表节点），因此首次写入前无需手工执行 `settingnode -init`。
- `insert` 的 `index` 可以省略或置为 `null`，此时文本被追加到列表末尾；取值小于 `size` 时插入到该位置，
  原有条目索引整体加一；取值大于 `size` 时，`size` 到 `index` 之间会补入 `nullFlag` 为 `true` 的空条目，
  并把 `size` 置为 `index + 1`。
- `update` 的 `index` 小于 `size` 时替换该位置的文本；取值不小于 `size` 时同样会在 `size` 到 `index`
  之间补入 `nullFlag` 为 `true` 的空条目，并把 `size` 置为 `index + 1`。
- `fetch` 在索引越界、条目缺失或条目 `nullFlag` 为 `true` 时均输出 `拉取结果: null`，不会因索引越界失败。
- `inspect` 会逐条给出 `index` 与 `nullFlag`。
- `remove` 之后，本次操作之前位于其后的条目索引整体减一，列表索引始终保持连续。
- `change-order` 的两个索引与 `remove` 的索引均须小于 `size`，否则返回
  `exception code=1140 - text list node index out of bound`，
  日志中会记录节点主键、期望的索引区间与实际索引。
- 设置类别不存在时返回 `exception code=1010 - setting category not exists`。

### 命令示例

命令示例 - 查看尚不存在的文本列表节点：

```text
textlnode -size -jf your-json-file-here.json
文本列表节点的大小: null
OK
```

命令示例 - 获取文本列表节点的大小：

```text
textlnode -size -jf your-json-file-here.json
文本列表节点的大小:
  size: 2
OK
```

命令示例 - 查看文本列表节点：

```text
textlnode -inspect -jf your-json-file-here.json
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

命令示例 - 拉取文本列表节点文本：

```text
textlnode -fetch -jf your-json-file-here.json
拉取结果:
  content: first
OK
```

命令示例 - 插入文本列表节点文本：

```text
textlnode -insert -jf your-json-file-here.json
插入成功
OK
```

命令示例 - 更新文本列表节点文本：

```text
textlnode -update -jf your-json-file-here.json
更新成功
OK
```

命令示例 - 更改文本列表节点的顺序：

```text
textlnode -co -jf your-json-file-here.json
更改顺序成功
OK
```

命令示例 - 移除文本列表节点：

```text
textlnode -remove -jf your-json-file-here.json
移除成功
OK
```

## 风险提示

- `put` 会覆盖节点原有的文本内容。
- `update` 会覆盖列表中原有的文本内容，执行前应确认 `index`。
- `update` 的 `index` 超过当前大小时会扩展列表，并在中间补入空条目，可能产生预期之外的空位。
- `remove` 会移除列表中的条目，并导致后续条目索引前移。
- `change-order` 对两端之外的其他条目也存在相对顺序影响，仅在 `old_index` 与 `neo_index` 不同时生效。
- `put`、`insert`、`update`、`change-order`、`remove` 都会写入数据库，属于不可自动回滚的写操作。
