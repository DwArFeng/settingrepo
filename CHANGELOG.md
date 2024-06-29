# ChangeLog

### Release_2.0.0_20240610_build_A

#### 功能构建

- 优化约束类中的常量值。
  - 增加 `Constraints.LENGTH_SETTING_CATEGORY_ID`，以约束设置类型 ID 的长度。
  - 增加 `Constraints.LENGTH_SETTING_NODE_ID`，以约束设置节点 ID 的长度。

- 新增依赖。
  - 增加依赖 `snowflake` 以应用其功能，版本为 `1.5.1.a`。

- 新增实体操作服务。
  - com.dwarfeng.settingrepo.stack.service.ImageNodeOperateService。
  - com.dwarfeng.settingrepo.stack.service.ImageListNodeOperateService。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.settingrepo.stack.bean.entity.ImageNode。
  - com.dwarfeng.settingrepo.stack.bean.entity.ImageListNode。
  - com.dwarfeng.settingrepo.stack.bean.entity.ImageListNodeItem。

- 新增 telqos 指令。
  - com.dwarfeng.settingrepo.impl.service.telqos.SettingNodeCommand。
  - com.dwarfeng.settingrepo.impl.service.telqos.ImageNodeCommand。
  - com.dwarfeng.settingrepo.impl.service.telqos.ImageListNodeCommand。

- 增加预设的运维指令。
  - com.dwarfeng.springtelqos.api.integration.log4j2.Log4j2Command。

- 日志功能优化。
  - 优化默认日志配置，默认配置仅向控制台输出 `INFO` 级别的日志。
  - 优化日志配置结构，提供 `conf/logging/settings.xml` 配置文件及其不同平台的参考配置文件，以供用户自定义日志配置。
  - 优化日志配置结构，提供 `confext/logging-settings.xml` 配置文件，以供外部功能自定义日志配置。
  - 优化启动脚本，使服务支持新的日志配置结构。
  - 优化 `assembly.xml`，使项目打包时输出新的日志配置结构。
  - 优化 `confext/README.md`，添加新的日志配置结构的相关说明。

- 依赖升级。
  - 升级 `slf4j` 依赖版本为 `1.7.36` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.5.3.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.9.a` 以规避漏洞。

- 重构设置节点机制。
  - 增加设置节点的类型，使其除了能够存储文本外，还可以存储其它类型的数据。
  - 优化接口方法，使其更加易用。

#### Bug修复

- 补全 .gitignore 中缺失的配置项。

- 修复 telqos 工具类中部分注解不正确的 bug。

#### 功能移除

- (无)
