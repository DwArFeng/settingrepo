# ConfDirectory - 配置目录

## 总览

本项目的配置文件位于 `conf/` 目录下，包括：

```text
conf
│
├─database
│      connection.properties
│      performance.properties
│
├─datamark
│      settings.properties
│
├─dubbo
│      connection.properties
│
├─ftp
│      connection.properties
│      path.properties
│
├─logging
│      README.md
│      settings.xml
│      settings-linux.xml
│      settings-windows.xml
│
├─redis
│      connection.properties
│      prefix.properties
│      timeout.properties
│
├─settingrepo
│      background.properties
│      exception.properties
│      iahn.properties
│      image-thumbnail.properties
│      launcher.properties
│      push.properties
│      reset.properties
│
└─telqos
        connection.properties
```

鉴于大部分配置文件的配置项中都有详细地注释，此处将展示默认的配置，并重点说明一些必须要修改的配置项。

## database 目录

| 文件名                    | 说明        |
|------------------------|-----------|
| connection.properties  | 数据库连接配置文件 |
| performance.properties | 数据库性能配置文件 |

### connection.properties

数据库连接配置文件，除了标准的数据库配置四要素之外，还包括 Hibernate 的方言配置。

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://your-host-here:3306/fdr?serverTimezone=Asia/Shanghai&autoReconnect=true
jdbc.username=root
jdbc.password=your-password-here
hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### performance.properties

数据库性能配置文件，使用默认值即可，或按照实际情况进行修改。

```properties
# 数据库的批量写入量，设置激进的值以提高数据库的写入效率。
hibernate.jdbc.batch_size=100
# 数据库的批量抓取量，设置激进的值以提高数据库的读取效率。
hibernate.jdbc.fetch_size=50
# 连接池最大活动连接数量
data_source.max_active=20
# 连接池最小空闲连接数量
data_source.min_idle=0
```

## datamark 目录

| 文件名                 | 说明        |
|---------------------|-----------|
| settings.properties | 数据标记的配置文件 |

### settings.properties

数据标记的配置文件。

数据标记是本项目的一个运维与安全机制，它使用 `dwarfeng-datamark` 实现，其主要的功能是在重要数据插入/更改时，
向数据库特定的数据标记字段写入特定值，
这个特定值被记录在 `dwarfeng-datamark` 中的 `resource` 中 - 可以是 spring 框架支持的任何资源类型，
支持运行时修改，并对前端完全不可见。

运维人员可以用这个机制降低运维的工作量 - 尤其是从测试环境向正式环境迁移数据时，也可以用这个机制进行数据非法篡改的检测与取证。

```properties
#---------------------------------配置说明----------------------------------------
# 数据标记资源的 URL，格式参考 Spring 资源路径。
# datamark.xxx.resource_url=classpath:datamark/default.storage
# 数据标记资源的字符集。
# datamark.xxx.resource_charset=UTF-8
# 数据标记服务是否允许更新。
# datamark.xxx.update_allowed=true
#
#---------------------------------SettingCategory----------------------------------------
datamark.setting_category.resource_url=classpath:datamark/default.storage
datamark.setting_category.resource_charset=UTF-8
datamark.setting_category.update_allowed=true
#
#---------------------------------SettingNode----------------------------------------
datamark.setting_node.resource_url=classpath:datamark/default.storage
datamark.setting_node.resource_charset=UTF-8
datamark.setting_node.update_allowed=true
```

## dubbo 目录

| 文件名                   | 说明           |
|-----------------------|--------------|
| connection.properties | Dubbo 连接配置文件 |

### connection.properties

Dubbo 连接配置文件。

```properties
dubbo.registry.zookeeper.address=zookeeper://your-host-here:2181
dubbo.registry.zookeeper.timeout=3000
dubbo.protocol.dubbo.port=20000
dubbo.protocol.dubbo.host=your-host-here
dubbo.provider.group=
dubbo.consumer.snowflake.group=
```

其中，`dubbo.registry.zookeeper.address` 需要配置为 ZooKeeper 的地址，
`dubbo.protocol.dubbo.host` 需要配置为本机的 IP 地址。

如果您需要在本机启动多个 FDR 实例，那么需要为每个实例配置不同的 `dubbo.protocol.dubbo.port`。

如果您在本机上部署了多个项目，每个项目中都使用了 FDR，那么需要为每个项目配置不同的 `dubbo.provider.group`，
以避免微服务错误的调用。

## ftp 目录

| 文件名                   | 说明         |
|-----------------------|------------|
| connection.properties | FTP 连接配置文件 |
| path.properties       | FTP路径配置文件  |

### connection.properties

FTP 连接配置文件。

```properties
# FTP 的主机名称。
ftp.host=your-host-here
# FTP 的端口号。
ftp.port=21
# FTP 的登录用户名。
ftp.username=your-username-here
# FTP 的登录密码。
ftp.password=your-password-here
# FTP 的服务点字符集。
ftp.server_charset=UTF-8
# FTP 的连接超时时长（毫秒）。
ftp.connect_timeout=5000
# FTP 的 noop 指令周期。
# 该值需要小于 ftp.connect_timeout。
ftp.noop_interval=4000
# FTP 的缓冲区大小。
ftp.buffer_size=4096
# FTP 的临时文件目录。
# 如果希望使用系统默认的临时文件目录，请注释下方配置项。
ftp.temporary_file_directory_path=temp
# FTP 临时文件的前缀。
# 用于区分不同实例产生的文件。
ftp.temporary_file_prefix=ftp-
# FTP 临时文件的后缀。
# 用于规范文件的扩展名。
ftp.temporary_file_suffix=.tmp
# FTP 文件拷贝功能的内存缓冲区大小，文件大小超过此值，剩余部分将会通过临时文件进行缓冲。
ftp.file_copy_memory_buffer_size=4096
# FTP 的数据连接模式。
#  0: 本地主动模式（传统主动模式 / PORT 模式）。
#  1: 远程主动模式（反向主动模式）。
#  2: 本地被动模式（传统被动模式 / PASV 模式）。
#  3: 远程被动模式（反向被动模式）。
ftp.data_connection_mode=0
# FTP 的数据超时时间。
# ftp.data_connection_mode=0 时，数据连接时调用 ServerSocket.accept() 时也会应用此超时设置。
# 建议将此值设置为大于 0，以避免服务端故障或网络丢包导致程序阻塞。
ftp.data_timeout=-1
# FTP 远程主动数据连接模式下的服务主机地址。
# ftp.data_connection_mode=1 时，此设置生效。
ftp.active_remote_data_connection_mode_server_host=your-host-here
# FTP 远程主动数据连接模式下的服务端口。
# ftp.data_connection_mode=1 时，此设置生效。
ftp.active_remote_data_connection_mode_server_port=20
```

该配置文件中的注释较为完善，使用者可以根据注释对大部分参数进行妥善配置，以下对重要参数进行进一步说明。

`ftp.data_connection_mode`: FTP 的数据连接模式。本项目支持 FTP 所有的 4 种连接模式，但是在实践中，
多使用 `0（本地主动模式）` 和 `2（本地被动模式）`。

`ftp.data_timeout`: FTP 的数据超时时间。需要强调，
**当 `ftp.data_connection_mode=0` 时此值必须设置为大于 `0`，否则在服务端故障或是网络丢包时，程序将可能阻塞**。

## logging 目录

| 文件名                  | 说明                       |
|----------------------|--------------------------|
| README.md            | 说明文件                     |
| settings.xml         | 日志记录策略的配置文件              |
| settings-linux.xml   | Linux 系统中日志记录策略的配置参考文件   |
| settings-windows.xml | Windows 系统中日志记录策略的配置参考文件 |

### settings.xml

日志记录策略的配置文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration>
    <properties>
        <!--############################################### Console ###############################################-->
        <!-- 控制台输出文本的编码 -->
        <property name="console.encoding">UTF-8</property>
        <!-- 控制台输出的日志级别 -->
        <property name="console.level">INFO</property>
        <!--############################################# Rolling file ############################################-->
        <!-- 滚动文件的目录 -->
        <property name="rolling_file.dir">logs</property>
        <!-- 滚动文件的编码 -->
        <property name="rolling_file.encoding">UTF-8</property>
        <!-- 滚动文件的触发间隔（小时） -->
        <property name="rolling_file.triggering.interval">1</property>
        <!-- 滚动文件的触发大小 -->
        <property name="rolling_file.triggering.size">40MB</property>
        <!-- 滚动文件的最大数量 -->
        <property name="rolling_file.rollover.max">100</property>
        <!-- 滚动文件的删除时间 -->
        <property name="rolling_file.rollover.delete_age">7D</property>
    </properties>

    <Appenders>
        <!--############################################### Console ###############################################-->
        <Console name="std.console" target="SYSTEM_OUT" follow="true">
            <ThresholdFilter level="${console.level}" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{DEFAULT}] [%p] [%t] [%c{1.}]: %m%n" charset="${console.encoding}"/>
        </Console>
        <Async name="sync.console">
            <AppenderRef ref="std.console"/>
        </Async>
        <!--############################################# Rolling file ############################################-->
        <RollingFile
                name="std.debug.rolling_file" fileName="${rolling_file.dir}/debug.log"
                filePattern="${rolling_file.dir}/%d{yyyy-MM}/debug-%d{MM-dd-yyyy}-%i.log.gz"
        >
            <ThresholdFilter level="DEBUG" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{DEFAULT}] [%p] [%t] [%c{1.}]: %m%n" charset="${rolling_file.encoding}"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="${rolling_file.triggering.interval}" modulate="true"/>
                <SizeBasedTriggeringPolicy size="${rolling_file.triggering.size}"/>
            </Policies>
            <DefaultRolloverStrategy max="${rolling_file.rollover.max}">
                <Delete basePath="${rolling_file.dir}" maxDepth="2">
                    <IfFileName glob="*/*debug*.log.gz"/>
                    <IfLastModified age="${rolling_file.rollover.delete_age}"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>
        <RollingFile
                name="std.info.rolling_file" fileName="${rolling_file.dir}/info.log"
                filePattern="${rolling_file.dir}/%d{yyyy-MM}/info-%d{MM-dd-yyyy}-%i.log.gz"
        >
            <ThresholdFilter level="INFO" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{DEFAULT}] [%p] [%t] [%c{1.}]: %m%n" charset="${rolling_file.encoding}"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="${rolling_file.triggering.interval}" modulate="true"/>
                <SizeBasedTriggeringPolicy size="${rolling_file.triggering.size}"/>
            </Policies>
            <DefaultRolloverStrategy max="${rolling_file.rollover.max}">
                <Delete basePath="${rolling_file.dir}" maxDepth="2">
                    <IfFileName glob="*/*info*.log.gz"/>
                    <IfLastModified age="${rolling_file.rollover.delete_age}"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>
        <RollingFile
                name="std.warn.rolling_file" fileName="${rolling_file.dir}/warn.log"
                filePattern="${rolling_file.dir}/%d{yyyy-MM}/warn-%d{MM-dd-yyyy}-%i.log.gz"
        >
            <ThresholdFilter level="WARN" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{DEFAULT}] [%p] [%t] [%c{1.}]: %m%n" charset="${rolling_file.encoding}"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="${rolling_file.triggering.interval}" modulate="true"/>
                <SizeBasedTriggeringPolicy size="${rolling_file.triggering.size}"/>
            </Policies>
            <DefaultRolloverStrategy max="${rolling_file.rollover.max}">
                <Delete basePath="${rolling_file.dir}" maxDepth="2">
                    <IfFileName glob="*/*warn*.log.gz"/>
                    <IfLastModified age="${rolling_file.rollover.delete_age}"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>
        <RollingFile
                name="std.error.rolling_file" fileName="${rolling_file.dir}/error.log"
                filePattern="${rolling_file.dir}/%d{yyyy-MM}/error-%d{MM-dd-yyyy}-%i.log.gz"
        >
            <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>
            <PatternLayout pattern="[%d{DEFAULT}] [%p] [%t] [%c{1.}]: %m%n" charset="${rolling_file.encoding}"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="${rolling_file.triggering.interval}" modulate="true"/>
                <SizeBasedTriggeringPolicy size="${rolling_file.triggering.size}"/>
            </Policies>
            <DefaultRolloverStrategy max="${rolling_file.rollover.max}">
                <Delete basePath="${rolling_file.dir}" maxDepth="2">
                    <IfFileName glob="*/*error*.log.gz"/>
                    <IfLastModified age="${rolling_file.rollover.delete_age}"/>
                </Delete>
            </DefaultRolloverStrategy>
        </RollingFile>
        <Async name="sync.console">
            <AppenderRef ref="std.console"/>
        </Async>
        <Async name="sync.debug.rolling_file">
            <AppenderRef ref="std.debug.rolling_file"/>
        </Async>
        <Async name="sync.info.rolling_file">
            <AppenderRef ref="std.info.rolling_file"/>
        </Async>
        <Async name="sync.warn.rolling_file">
            <AppenderRef ref="std.warn.rolling_file"/>
        </Async>
        <Async name="sync.error.rolling_file">
            <AppenderRef ref="std.error.rolling_file"/>
        </Async>
    </Appenders>

    <Loggers>
        <!--############################################# Root logger #############################################-->
        <Root level="ALL">
            <appender-ref ref="sync.console"/>
            <appender-ref ref="sync.debug.rolling_file"/>
            <appender-ref ref="sync.info.rolling_file"/>
            <appender-ref ref="sync.warn.rolling_file"/>
            <appender-ref ref="sync.error.rolling_file"/>
        </Root>
    </Loggers>
</Configuration>
```

需要注意的是，日志记录策略 **必须** 定义在 `settings.xml` 中才能生效，所有的 `settings-xx.xml` 都是参考文件，
在这些文件中进行任何配置 **均不会生效**。

常用的做法是，针对不同的操作系统，将参考文件中的内容直接复制到 `settings.xml` 中，随后对属性进行调整。

- 如果 debug 级别的日志量太大，当前的配置无法记录一天内的全部日志，可上调 `rolling_file.rollover.max` 参数。
- 如果有等保需求，日志至少需要保留 6 个月，需要调整 `rolling_file.rollover.delete_age` 参数至 `200D`。

## redis 目录

| 文件名                   | 说明   |
|-----------------------|------|
| connection.properties | 连接配置 |
| prefix.properties     | 前缀配置 |
| timeout.properties    | 超时配置 |

### connection.properties

Redis 连接配置文件。

```properties
# ip地址
redis.hostName=your-host-here
# 端口号
redis.port=6379
# 如果有密码
redis.password=your-password-here
# 客户端超时时间单位是毫秒 默认是2000
redis.timeout=10000
# 最大空闲数
redis.maxIdle=300
# 控制一个 pool可分配多少个 jedis 实例, 设为0表示无限制。
redis.maxTotal=1000
# 最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
redis.maxWaitMillis=1000
# 连接的最小空闲时间 默认1800000毫秒(30分钟)
redis.minEvictableIdleTimeMillis=300000
# 每次释放连接的最大数目,默认3
redis.numTestsPerEvictionRun=1024
# 逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
redis.timeBetweenEvictionRunsMillis=30000
# 是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个
redis.testOnBorrow=true
# 在空闲时检查有效性, 默认false
redis.testWhileIdle=true
# redis.sentinel.host1=172.20.1.230
# redis.sentinel.port1=26379
# redis.sentinel.host2=172.20.1.231
# redis.sentinel.port2=26379
# redis.sentinel.host3=172.20.1.232
# redis.sentinel.port3=26379
```

### prefix.properties

Redis 前缀配置文件。

```properties
#------------------------------------------------------------------------------------
#  缓存时实体的键的格式
#------------------------------------------------------------------------------------
# 格式化器支持对象的主键格式。
cache.prefix.entity.formatter_support=entity.formatter_support.
# 设置类别对象的主键格式。
cache.prefix.entity.setting_category=entity.setting_category.
# 设置节点对象的主键格式。
cache.prefix.entity.setting_node=entity.setting_node.
# 文本节点对象的主键格式。
cache.prefix.entity.text_node=entity.text_node.
# 图片节点对象的主键格式。
cache.prefix.entity.image_node=entity.image_node.
# 图片列表节点对象的主键格式。
cache.prefix.entity.image_list_node=entity.image_list_node.
# 图片列表节点条目对象的主键格式。
cache.prefix.entity.image_list_node_item=entity.image_list_node_item.
# 国际化节点对象的主键格式。
cache.prefix.entity.iahn_node=entity.iahn_node.
# 国际化节点条目地区对象的主键格式。
cache.prefix.entity.iahn_node_locale=entity.iahn_node_locale.
# 国际化节点条目 Mek 对象的主键格式。
cache.prefix.entity.iahn_node_mek=entity.iahn_node_mek.
# 国际化节点条目 Message 对象的主键格式。
cache.prefix.entity.iahn_node_message=entity.iahn_node_message.
# 长文本节点对象的主键格式。
cache.prefix.entity.long_text_node=entity.long_text_node.
```

Redis 利用该配置文件，为缓存的主键添加前缀，以示区分。

如果您的项目包含其它使用 Redis 的模块，您可以修改该配置文件，以避免不同项目的同名实体前缀冲突，相互覆盖。

一个典型的前缀更改方式是在前缀的头部添加项目的名称，如：

```properties
# 数据点对象的主键格式。
cache.prefix.entity.formatter_support=entity.settingrepo.formatter_support.
# etc...
```

### timeout.properties

Redis 缓存的超时配置文件。

```properties
#------------------------------------------------------------------------------------
#  实体缓存时的超时时间
#------------------------------------------------------------------------------------
# 格式化器支持对象缓存的超时时间。
cache.timeout.entity.formatter_support=3600000
# 设置类别对象缓存的超时时间。
cache.timeout.entity.setting_category=3600000
# 设置节点对象缓存的超时时间。
cache.timeout.entity.setting_node=3600000
# 文本节点对象缓存的超时时间。
cache.timeout.entity.text_node=3600000
# 图片节点对象缓存的超时时间。
cache.timeout.entity.image_node=3600000
# 图片列表节点对象缓存的超时时间。
cache.timeout.entity.image_list_node=3600000
# 图片列表节点条目对象缓存的超时时间。
cache.timeout.entity.image_list_node_item=3600000
# 国际化节点对象缓存的超时时间。
cache.timeout.entity.iahn_node=3600000
# 国际化节点条目地区对象缓存的超时时间。
cache.timeout.entity.iahn_node_locale=3600000
# 国际化节点条目 Mek 对象缓存的超时时间。
cache.timeout.entity.iahn_node_mek=3600000
# 国际化节点条目 Message 对象缓存的超时时间。
cache.timeout.entity.iahn_node_message=3600000
# 长文本节点对象缓存的超时时间。
cache.timeout.entity.long_text_node=3600000
```

如果您希望缓存更快或更慢地过期，您可以修改该配置文件。

## settingrepo 目录

| 文件名                        | 说明                           |
|----------------------------|------------------------------|
| background.properties      | 后台服务配置文件，包括线程池的线程数及其它        |
| exception.properties       | ServiceException 的异常代码的偏移量配置 |
| iahn.properties            | 国际化节点配置文件                    |
| image-thumbnail.properties | 图片缩略图配置文件                    |
| launcher.properties        | 启动器配置文件                      |
| push.properties            | 推送服务配置文件                     |
| reset.properties           | 重置服务配置文件                     |

### background.properties

后台服务配置文件，包括线程池的线程数及其它。

```properties
# 任务执行器的线程池数量范围。
executor.pool_size=50-75
# 任务执行器的队列容量。
executor.queue_capacity=100
# 任务执行器的保活时间（秒）。
executor.keep_alive=120
# 计划执行器的线程池数量范围。
scheduler.pool_size=10
```

### exception.properties

ServiceException 的异常代码的偏移量配置。

```properties
# settingrepo 工程自身的异常代号偏移量。
settingrepo.exception_code_offset=10000
# settingrepo 工程中 subgrade 的异常代号偏移量。
settingrepo.exception_code_offset.subgrade=0
# settingrepo 工程中 snowflake 的异常代号偏移量。
settingrepo.exception_code_offset.snowflake=1500
# settingrepo 工程中 dwarfeng-ftp 的异常代号偏移量。
settingrepo.exception_code_offset.dwarfeng_ftp=2000
# settingrepo 工程中 dwarfeng-datamark 的异常代号偏移量。
settingrepo.exception_code_offset.dwarfeng_datamark=2500
```

Subgrade 框架中，会将微服务抛出的异常映射为 `ServiceException`，每个 `ServiceException` 都有一个异常代码，
用于标识异常的类型。

如果您的项目中使用了多个基于 Subgrade 框架的微服务，那么，您需要为每个微服务配置一个异常代码偏移量，
以免不同的微服务生成异常代码相同的 `ServiceException`。

### iahn.properties

国际化节点配置文件。

```properties
# 国际化节点地区映射缓存的前缀。
iahn.cache.prefix.locale_map=iahn.cache.locale_map.
# 国际化节点地区映射缓存的超时时间。
iahn.cache.timeout.locale_map=3600000
#
# 国际化节点消息映射缓存的前缀。
iahn.cache.prefix.message_map=iahn.cache.message_map.
# 国际化节点消息映射缓存的超时时间。
iahn.cache.timeout.message_map=3600000
```

用于定义国际化节点在其业务逻辑中需要使用的配置项。

国际化节点使用了特殊的缓存机制，以缩短国际化查询的响应时间，
这些特殊的缓存机制使用了额外的缓存键与超时时间，因此需要单独设置。

### image-thumbnail.properties

图片缩略图配置文件。

```properties
# 图片缩略图的宽度。
image_thumbnail.width=980
# 图片缩略图的高度。
image_thumbnail.height=540
# 图片缩略图的质量。
image_thumbnail.quality=0.5
# 图片缩略图的输出格式。
image_thumbnail.output_format=jpg
```

该配置文件作用于 `ImageNode（图片节点）` 和 `ImageListNode（图片列表节点）`，用于对这些节点的缩略图生成业务逻辑。

上述与图片相关的节点，为了优化用户的体验，使用了缩略图机制。
缩略图体积很小，并在小面板中足够清晰，可以作为用户的参考依据 - 如果这张图片是用户需要的，再访问原始图片。

如果调用者能够提前确定缩略图展示区域的尺寸，则可对该参数进行调整，以输出足够清晰的缩略图。

### launcher.properties

启动器配置文件，决定了启动时的一些行为。

```properties
# 程序启动完成后，是否重置格式化器支持。
launcher.reset_formatter_support=true
# 程序启动完成后，启动重置的延时时间。
# 有些数据仓库以及重置器在启动后可能会需要一些时间进行自身的初始化，调整该参数以妥善的处理这些数据源和推送器。
# 该参数等于0，意味着启动后立即启动重置服务。
# 该参数小于0，意味着程序不主动启动重置服务，需要手动启动。
launcher.start_reset_delay=30000
```

该配置文件决定了服务被运行后，哪些功能将会自动被执行。

对于负载巨大场景，需要服务集群做读写分离，一部分服务在启动后自动执行数据业务并下线微服务（下线微服务当前还未自动化），
专注与业务的处理； 另一部分则不执行数据业务并上线微服务，专注于响应调用方。

自动执行是可选的配置功能，任何启动时没有自动执行的功能模块，均可以通过服务的 telqos 系统随时进行启用。

### push.properties

推送服务配置文件。

```properties
###################################################
#                     global                      #
###################################################
# 当前的推送器类型。
# 目前该项目支持的推送器类型有:
#   drain: 简单的丢弃掉所有消息的推送器。
#   multi: 同时将消息推送给所有代理的多重推送器。
#   log: 将时间格式化后打印至日志中的推送器。
#
# 对于一个具体的项目，很可能只用一个推送器。此时希望加载
# 推送器时只加载需要的那个，其余的推送器不加载。这个需求
# 可以通过编辑 application-context-scan.xml 实现。
pusher.type=drain
#
###################################################
#                      drain                      #
###################################################
# drain推送器没有任何配置。
#
###################################################
#                      multi                      #
###################################################
# 代理的推送器，推送器之间以逗号分隔。
pusher.multi.delegate_types=drain
#
###################################################
#                       log                       #
###################################################
# 记录日志的等级，由低到高依次是 TRACE, DEBUG, INFO, WARN, ERROR。
pusher.log.log_level=INFO
```

您不必对所有的配置项进行配置。

在项目第一次启动之前，您需要修改 `opt/opt-pusher.xml`，决定项目中需要使用哪些推送器。您只需要修改使用的推送器的配置。

### reset.properties

重置服务配置文件。

```properties
###################################################
#                      never                      #
###################################################
# Never 推送器没有任何配置。
#
###################################################
#                   fixed_delay                   #
###################################################
# 重置的间隔。
resetter.fixed_delay.delay=43200000
#
###################################################
#                   fixed_rate                    #
###################################################
# 重置的间隔。
resetter.fixed_rate.rate=43200000
#
###################################################
#                      cron                       #
###################################################
# 执行重置的 CRON 表达式。
resetter.cron.cron=0 0 1 * * *
#
###################################################
#                      kafka                      #
###################################################
# 引导服务器集群。
resetter.kafka.bootstrap_servers=your-host-here:9092
# 会话的超时限制: 如果consumer在这段时间内没有发送心跳信息，一次 rebalance 将会产生。
# 该值必须在[group.min.session.timeout.ms, group.max.session.timeout.ms]范围内，默认: 10000。
resetter.kafka.session_timeout_ms=10000
# 监听器的 id，每一个节点的监听器 id 都应与该节点的其它 kafka 监听器的 id 不同。
# 该设置会覆盖 kafka 的 group.id 设置，因此无需设置 group.id。
# 不同实例的监听器的 id 必须不同。
resetter.kafka.listener_id=settingrepo.01
# 新的 group 加入 topic 时，从什么位置开始消费。
resetter.kafka.auto_offset_reset=latest
# 监听器启用的消费者的线程数。
# 每一个线程都会启动一个 KafkaConsumer，每个 KafkaConsumer 都会占用一个 partition。
# 程序分布式部署时，所有节点的线程数之和应该小于等于 topic 的 partition 数。
resetter.kafka.concurrency=1
# 监听器调用 KafkaConsumer.poll(Duration) 方法的超时时间，如果超过这个时间还没有拉取到数据，则返回空列表。
resetter.kafka.poll_timeout=3000
# 监听器的最大拉取数据量。当拉取到的数据量达到这个值时，会立即返回，不会等待 poll_timeout。
resetter.kafka.max_poll_records=100
# 监听器的最大拉取间隔。如果当前时间距离监听器上一次拉取数据的时间超过了这个值，一次 rebalance 将会产生。
resetter.kafka.max_poll_interval_ms=1000
# 监听器的目标 topic。
resetter.kafka.topic=settingrepo.reset
#
###################################################
#                      dubbo                      #
###################################################
# Dubbo 推送器没有任何配置。
```

您不必对所有的配置项进行配置。

在项目第一次启动之前，您需要修改 `opt/opt-resetter.xml`，决定项目中需要使用哪些重置器。您只需要修改使用的重置器的配置。

## telqos 目录

| 文件名                   | 说明   |
|-----------------------|------|
| connection.properties | 连接配置 |

### connection.properties

Telqos 连接配置文件。

```properties
# Telnet 端口。
telqos.port=23
# 字符集。
telqos.charset=UTF-8
# 白名单表达式。
telqos.whitelist_regex=
# 黑名单表达式。
telqos.blacklist_regex=
```

如果您的项目中有多个包含 Telqos 模块的服务，您应该修改 `telqos.port` 的值，以避免端口冲突。

请根据操作系统的默认字符集，修改 `telqos.charset` 的值，以避免乱码。一般情况下，Windows 系统的默认字符集为 `GBK`，
Linux 系统的默认字符集为 `UTF-8`。

如果您希望限制 Telqos 的使用范围，您可以修改 `telqos.whitelist_regex` 和 `telqos.blacklist_regex` 的值。
