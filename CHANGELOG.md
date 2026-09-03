# ChangeLog

## Release_3.0.2_20260903_build_A

### 功能构建

- (无)

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_3.0.1_20260609_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/InstallToCentos.md。

- Wiki 更新。
  - docs/wiki/zh-CN/CompileBySource.md。

- `settingrepo-node-all-he` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.settingrepo.node.all.he.configuration.CacheConfiguration。

- `settingrepo-impl` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.settingrepo.impl.handler.resetter.KafkaResetter。
  - com.dwarfeng.settingrepo.impl.service.FileListNodeItemMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.FileListNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.FormatterSupportMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.IahnNodeLocaleMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.IahnNodeMessageMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageListNodeItemMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageListNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.LongTextNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.LongTextNodeQosServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.NavigationNodeItemMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.NavigationNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.SettingCategoryMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.configuration.CacheConfiguration。
  - com.dwarfeng.settingrepo.impl.service.ImageListNodeItemMaintainServiceImplTest。

- `settingrepo-sdk` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.settingrepo.sdk.bean.key.FastJsonIahnNodeLocaleKey。

- `settingrepo-stack` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.settingrepo.stack.dao.FileListNodeItemDao。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.8.3.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `2.0.2.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `2.0.2.a` 以规避漏洞。
  - 升级 `dwarfeng-datamark` 依赖版本为 `2.2.0.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `2.1.0.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `2.0.2.a` 以规避漏洞。

- 优化文件格式。
  - 优化 `assembly.xml` 文件的格式。
  - 优化 `pom.xml` 文件的格式。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_3.0.0_20260609_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/OptDirectory.md。

- Wiki 更新。
  - docs/wiki/zh-CN/ConfDirectory.md。
  - docs/wiki/zh-CN/CompileBySource.md。

- `settingrepo-distribute` 模块新增。
  - 新增 `settingrepo-distribute` 模块，负责各个构型的产物分发。

- `settingrepo-node` 模块重构。
  - 将 `settingrepo-node` 调整为聚合模块。
  - 新建 `settingrepo-node-all-he` 模块，迁移项目原有内容，并形成项目的 `he` 构型。
  - 其它相关文件路径、包路径、配置路径等同步调整。

- 配置命名空间全局唯一化改造。
  - 为 `settingrepo-impl` 模块配置键统一增加全球唯一前缀，以消除跨微服务同名配置冲突。
  - 为 `settingrepo-node` 模块配置键统一增加全球唯一前缀，以消除跨微服务同名配置冲突。
  - 处理 `redis/prefix.properties` 缓存前缀配置的实体命名空间。
  - 同步调整 `*.java` 中的配置读取占位符。
  - 同步调整 `*application-context-*.xml` 中的配置读取占位符。

- 部分代理类实现中的字段类型提升为对应的接口，与具体实现解耦。
  - com.dwarfeng.settingrepo.impl.cache.FileListNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.FileListNodeItemCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.FileNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.FormatterSupportCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.IahnNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.IahnNodeLocaleCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.IahnNodeMekCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.IahnNodeMessageCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.ImageListNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.ImageListNodeItemCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.ImageNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.KvNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.KvNodeItemCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.LongTextNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.NavigationNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.NavigationNodeItemCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.SettingCategoryCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.SettingNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.cache.TextNodeCacheImpl。
  - com.dwarfeng.settingrepo.impl.dao.FileListNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.FileListNodeItemDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.FileNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.FormatterSupportDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.IahnNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.IahnNodeLocaleDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.IahnNodeMekDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.IahnNodeMessageDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.ImageListNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.ImageListNodeItemDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.ImageNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.KvNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.KvNodeItemDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.LongTextNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.NavigationNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.NavigationNodeItemDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.SettingCategoryDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.SettingNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.dao.TextNodeDaoImpl。
  - com.dwarfeng.settingrepo.impl.service.FileListNodeItemMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.FileListNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.FileNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.FormatterSupportMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.IahnNodeLocaleMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.IahnNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.IahnNodeMekMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.IahnNodeMessageMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageListNodeItemMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageListNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.ImageNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.KvNodeItemMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.KvNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.LongTextNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.NavigationNodeItemMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.NavigationNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.SettingCategoryMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.SettingNodeMaintainServiceImpl。
  - com.dwarfeng.settingrepo.impl.service.TextNodeMaintainServiceImpl。

- 优化项目的异常处理机制。
  - `settingrepo-sdk` 子模块新增 `ServiceExceptionHelper` 工具类，统一维护项目自身的异常映射关系。
  - `settingrepo-impl` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。
  - `settingrepo-node` 子模块 `ServiceExceptionMapperConfiguration` 配置类的异常映射处理逻辑优化。

- 依赖升级。
  - 升级 `spring-terminator` 依赖版本为 `2.0.0.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring-telqos` 依赖版本为 `2.0.0.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-ftp` 依赖版本为 `2.0.1.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `dwarfeng-datamark` 依赖版本为 `2.0.1.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `kafka` 依赖版本为 `3.9.2` 以规避漏洞。
  - 升级 `jedis` 依赖版本为 `3.10.0` 以规避漏洞。
  - 升级 `spring-data-redis` 依赖版本为 `2.7.18` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.23` 以规避漏洞。
  - 升级 `jetty` 依赖版本为 `9.4.58.v20250814` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.9.5` 以规避漏洞。
  - 升级 `log4j2` 依赖版本为 `2.25.4` 以规避漏洞。
  - 升级 `mapstruct` 依赖版本为 `1.5.5.Final` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `0.4.2.a-beta` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `2.0.0.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.8.2.a` 以规避漏洞。
  - 升级 `jackson` 依赖版本为 `2.21.2` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `4.0.31` 以规避漏洞。

- 优化文件格式。
  - 优化 `*.properties` 文件的格式。
  - 优化 `application-context-*.xml` 文件的格式。

- 优化开发环境支持。
  - 在 .gitignore 中添加 Vibe Coding 相关文件的忽略规则。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## 更早的版本

[View all changelogs](./changelogs)
