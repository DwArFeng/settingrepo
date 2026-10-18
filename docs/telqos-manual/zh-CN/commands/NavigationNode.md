# Navigation Node - 导航节点命令

`navigationnode` 用于维护导航节点和树形条目。条目键使用 `WebInputLongIdKey`，JSON 中表示为 `long_id` 对象。

## 命令语法

```text
usage: navigationnode -size [-json json-string] [-jf json-file]
navigationnode -inspect [-json json-string] [-jf json-file]
navigationnode -ii,--insert-item [-json json-string] [-jf json-file]
navigationnode -ui,--update-item [-json json-string] [-jf json-file]
navigationnode -ri,--remove-item [-json json-string] [-jf json-file]
navigationnode -fi,--format-index [-json json-string] [-jf json-file]
navigationnode -cc,--clear-cache [-json json-string] [-jf json-file]
```

## Sub Command

| option    | 长选项         | 用途                   | JSON 参考类                             | JSON 文件                                                          |
|-----------|----------------|------------------------|-----------------------------------------|--------------------------------------------------------------------|
| `size`    | -              | 查询导航节点大小       | `WebInputNavigationNodeSizeInfo`        | [size.json](../json-file/navigationnode/size.json)                 |
| `inspect` | -              | 查看导航节点           | `WebInputNavigationNodeInspectInfo`     | [inspect.json](../json-file/navigationnode/inspect.json)           |
| `ii`      | `insert-item`  | 插入导航条目           | `WebInputNavigationNodeItemInsertInfo`  | [insert-item.json](../json-file/navigationnode/insert-item.json)   |
| `ui`      | `update-item`  | 更新导航条目           | `WebInputNavigationNodeItemUpdateInfo`  | [update-item.json](../json-file/navigationnode/update-item.json)   |
| `ri`      | `remove-item`  | 移除导航条目           | `WebInputNavigationNodeItemRemoveInfo`  | [remove-item.json](../json-file/navigationnode/remove-item.json)   |
| `fi`      | `format-index` | 格式化指定父条目的索引 | `WebInputNavigationNodeFormatIndexInfo` | [format-index.json](../json-file/navigationnode/format-index.json) |
| `cc`      | `clear-cache`  | 清除导航节点缓存       | `WebInputNavigationNodeClearCacheInfo`  | [clear-cache.json](../json-file/navigationnode/clear-cache.json)   |

## 键对象

`item_key` 和 `parent_item_key` 的结构为：

```text
{"long_id": 1}
```

`long_id` 必须为整数。

## 字段说明

| 字段              | 适用操作   | 说明                            |
|-------------------|------------|---------------------------------|
| `category`        | 全部       | 设置类别                        |
| `args`            | 全部       | 节点格式化参数                  |
| `item_key`        | ui、ri     | 被操作条目的主键                |
| `parent_item_key` | ii、ui、fi | 父条目主键；根级操作可为 `null` |
| `index`           | ii、ui     | 同级条目中的位置                |
| `name`            | ii、ui     | 条目名称                        |
| `content`         | ii、ui     | 条目内容                        |
| `remark`          | ii、ui     | 条目备注                        |

## 风险提示

- `ui` 会覆盖条目名称、内容、备注和父级关系。
- `ri` 可能同时影响子条目的可见结构，执行前应确认树形关系。
- `fi` 会调整同级条目索引，可能改变业务展示顺序。
