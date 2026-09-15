# ChangeLog

## Release_4.0.2_20260912_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/Formatter.md。
  - docs/wiki/zh-CN/PresetFormatterImplements.md。

- 优化部分内置 Groovy 脚本内容。
  - com/dwarfeng/settingrepo/impl/groovy/ExampleFormatterProcessor.groovy。

- 新增依赖。
  - 增加依赖 `javax.servlet-api` 以规避漏洞，版本为 `4.0.1`。
  - 增加依赖 `jetty` 以规避漏洞，版本为 `9.4.58.v20250814`。

- 优化项目的国际化机制以及消息键规范。

- 优化部分国际化文本。
  - com/dwarfeng/settingrepo/impl/i18n/messages.properties。
  - com/dwarfeng/settingrepo/impl/i18n/messages_zh_CN.properties。
  - com/dwarfeng/settingrepo/node/all/he/i18n/messages.properties。
  - com/dwarfeng/settingrepo/node/all/he/i18n/messages_zh_CN.properties。

- 优化文件格式。
  - 优化 `pom.xml` 文件的格式。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_4.0.1_20260912_build_A

### 功能构建

- Wiki 编写。
  - docs/wiki/zh-CN/QuickStart.md。

- `settingrepo-sdk` 补充服务异常与国际化回归测试。

- `settingrepo-impl` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.settingrepo.impl.internal.i18n.ImplMessageKey。

- 优化 `pom.xml` 文件中的依赖配置。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `2.1.0.a` 以规避漏洞。

- 优化文件格式。
  - 优化 `pom.xml` 文件的格式。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Release_4.0.0_20260907_build_A

### 功能构建

- Wiki 更新。
  - docs/wiki/zh-CN/ConfDirectory.md。
  - docs/wiki/zh-CN/SystemRequirements.md。
  - docs/wiki/zh-CN/TelqosCommands.md。

- 升级 JDK 版本至 25。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## 更早的版本

[View all changelogs](./changelogs)
