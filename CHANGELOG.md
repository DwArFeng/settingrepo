# ChangeLog

### Release_1.1.5_20240128_build_A

#### 功能构建

- 将工程中的 `Spring Bean` 注册方式尽可能地由 `@Autowired` 变更为构造器注入。

- 增加 `PusherAdapter`。
  - 建议任何插件的推送器实现都继承自该适配器。
  - 适配器对所有的事件推送方法都进行了空实现。
  - 解决了增加了新的事件时，旧的推送器实现必须实现新的方法的问题。
  - 从此以后，推送器增加新的事件，将被视作兼容性更新。

- 优化文件格式。
  - 优化 `*.properties` 文件的格式。

- 升级 spring-telqos 并应用其新功能。
  - 使用包扫描的方式注册指令。
  - 优化 `telqos/connection.properties` 中配置的键名。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.4.8.b` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring-terminator` 依赖版本为 `1.0.12.a` 并解决兼容性问题，以应用其新功能。
  - 升级 `spring` 依赖版本为 `5.3.31` 以规避漏洞。
  - 升级 `kafka` 依赖版本为 `3.6.1` 以规避漏洞。
  - 升级 `spring-kafka` 依赖版本为 `2.9.1` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.2.0` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.104.final` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.7.2` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.8.a` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.4_20230618_build_A

#### 功能构建

- 优化了 AbstractResetter 的代码结构，同时对其实现进行了修改。
  - com.dwarfeng.settingrepo.impl.handler.resetter.CronResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.DubboResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.FixedDelayResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.FixedRateResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.KafkaResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.NeverResetter。

- 依赖升级。
  - 升级 `dubbo` 依赖版本为 `2.7.22` 以规避漏洞。
  - 升级 `guava` 依赖版本为 `32.0.1-jre` 以规避漏洞。

#### Bug修复

- 修复 `DubboResetter` 注册微服务时没有指定 `group` 的问题。

#### 功能移除

- (无)

---

### Release_1.1.3_20230421_build_A

#### 功能构建

- 优化 Mapper 接口的文件路径。

- 启停脚本优化。
  - 优化 Windows 系统的启动脚本。
  - 优化 Linux 系统的启停脚本。

- 优化项目结构，增加项目目录。
  - `./confext/`。

- 依赖升级。
  - 升级 `spring` 依赖版本为 `5.3.27` 以规避漏洞。
  - 升级 `snakeyaml` 依赖版本为 `2.0.0` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.21` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.86.Final` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.3.3.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.11.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.6.a` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.2_20230103_build_A

#### 功能构建

- 实现推送机制，并开发预设推送器。
  - com.dwarfeng.settingrepo.impl.handler.pusher.DrainPusher。
  - com.dwarfeng.settingrepo.impl.handler.pusher.LogPusher。
  - com.dwarfeng.settingrepo.impl.handler.pusher.MultiPusher。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.1_20230103_build_A

#### 功能构建

- 使用 `subgrade` 预设的 `GeneralLocalCacheHandler` 重写本地缓存。
  - com.dwarfeng.settingrepo.impl.handler.FormatLocalCacheHandlerImpl。

- 增加重置机制，实现格式化本地缓存的动态重置。
  - com.dwarfeng.settingrepo.impl.handler.resetter.CronResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.DubboResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.FixedDelayResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.FixedRateResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.KafkaResetter。
  - com.dwarfeng.settingrepo.impl.handler.resetter.NeverResetter。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.1.0_20221201_build_A

#### 功能构建

- Dubbo 微服务增加分组配置。

- 优化 `assembly.xml`，减少无用的依赖项目输出。

- 使用 `MapStruct` 重构 `BeanTransformer`。

- 增加依赖。
  - 增加依赖 `protobuf` 以规避漏洞，版本为 `3.19.6`。
  - 增加依赖 `guava` 以规避漏洞，版本为 `31.1-jre`。
  - 增加依赖 `gson` 以规避漏洞，版本为 `2.8.9`。
  - 增加依赖 `snakeyaml` 以规避漏洞，版本为 `1.33`。
  - 增加依赖 `jackson` 以规避漏洞，版本为 `2.14.0`。

- 依赖升级。
  - 升级 `mysql` 依赖版本为 `8.0.31` 以规避漏洞。
  - 升级 `jedis` 依赖版本为 `3.8.0` 以规避漏洞。
  - 升级 `spring-data-redis` 依赖版本为 `2.7.5` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.18` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.5.7` 以规避漏洞。
  - 升级 `curator` 依赖版本为 `4.3.0` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.2.5.Final` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.2.a` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.4.10.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.14.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.5.a` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `4.0.6` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- 删除不需要的依赖。
  - 删除 `el` 依赖。
  - 删除 `zkclient` 依赖。
  - 删除 `commons-lang3` 依赖。
  - 删除 `hessian` 依赖。
  - 删除 `jetty` 依赖。
  - 删除 `dozer` 依赖。

---

### Release_1.0.3_20221009_build_A

#### 功能构建

- 依赖升级。
  - 升级 `junit` 依赖版本为 `4.13.2` 以规避漏洞。
  - 升级 `spring` 依赖版本为 `5.3.20` 以规避漏洞。
  - 升级 `mysql` 依赖版本为 `8.0.28` 以规避漏洞。
  - 升级 `fastjson` 依赖版本为 `1.2.83` 以规避漏洞。
  - 升级 `dubbo` 依赖版本为 `2.7.15` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.1.77.Final` 以规避漏洞。
  - 升级 `hibernate` 依赖版本为 `5.3.20.Final` 以规避漏洞。
  - 升级 `hibernate-validator` 依赖版本为 `6.0.21.Final` 以规避漏洞。
  - 升级 `log4j2` 依赖版本为 `2.17.2` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `3.0.7` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- 删除不需要的依赖。
  - 删除 `joda-time` 依赖。
  - 删除 `commons-lang3` 依赖。
  - 删除 `commons-io` 依赖。
  - 删除 `httpcomponents` 依赖。

---

### Release_1.0.2_20220912_build_A

#### 功能构建

- 插件升级。
  - 升级 `maven-deploy-plugin` 插件版本为 `2.8.2`。

- 依赖升级。
  - 升级 `spring-terminator` 依赖版本为 `1.0.9.a` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `beta-0.3.1.a` 以规避漏洞。
  - 升级 `subgrade` 依赖版本为 `1.2.10.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.4.a` 以规避漏洞。

#### Bug修复

- (无)

#### 功能移除

- (无)

---

### Release_1.0.1_20220412_build_A

#### 功能构建

- (无)

#### Bug修复

- 修正项目中不合理的配置项名称。

#### 功能移除

- (无)

---

### Release_1.0.0_20220323_build_A

#### 功能构建

- 程序结构建立，清理测试通过。

- 实体建立，单元测试通过。
  - com.dwarfeng.settingrepo.stack.bean.entity.FormatterSupport。
  - com.dwarfeng.settingrepo.stack.bean.entity.SettingCategory。
  - com.dwarfeng.settingrepo.stack.bean.entity.SettingNode。

- 实现设置节点主键的格式化机制。

- 实现操作服务。
  - com.dwarfeng.settingrepo.stack.service.SettingNodeOperateService。

- 程序开发完成，运行测试通过，打包测试通过。

#### Bug修复

- (无)

#### 功能移除

- (无)
