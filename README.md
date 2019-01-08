# GemFrame

**架构简介**

- GemFrame基于SpringCloud、SpringBoot、MyBatis及周边的生态技术开发。是一款稳定，高效的企业级微服务架构，更是致力面向中小型企业提供的一套全面微服务架构的解决方案。并且其具有代码规范，学习成本极低，开箱即用等特点，方便开发者快速上手。官方网址： http://www.gemframe.cn

- 服务治理监控：Gem架构封装了SpringCloud提供的负载，降级，监控和统一配置等服务，可实现对应用、服务、链路、性能、响应率、成功率、可用率等基础监控与预警，并且集成了一些优秀的开源监控组件，对系统进行多维度监控，为企业系统保驾护航，让监控都变得如此简单。

- 基础能力建设：精细化权限管理，安全身份认证，即时通讯，文件服务，消息队列，工作流等。官方提供完善的API文档、部署文档、架构介绍文档以及视频教程帮助您快速学习入门，快速上手使用。

- GemFrame是为企业提供微服务基础架构最全面的解决方案，也是您学习Java微服务平台的最佳学习案例。

**项目特点**

- 基于最新的SpringCloud、Spring Boot2.0生态技术，高度整合封装稳定、高效、安全的微服务解决方案
- 采用主流的VUE、Layui等前端模版框架，上手简单、美观大气、交互体验更完美
- 多数据源驱动支持，可接入MySQL、Oracle、SQL Server等主流数据库
- Maven多项目依赖，模块及插件分项目，尽量松耦合，方便模块升级、增减模块。  
- 集成Swagger模块，自定义注解使用代码侵入性极低，测试Api更简单
- 持久层实体类采用@Table注解配置，集成JAP模块正向生成表结构
- 完善的安全体系，身份认证、密码策略、安全审计、日志收集 
- 代码风格优雅简洁、通俗易懂，符合《阿里巴巴JAVA开发手册》规范要求，可作为企业代码规范

**服务治理**

- 网关路由代理调用：Spring Cloud Netflix Zuul
- 服务治理注册与发现：Spring Cloud Netflix Eureka
- 服务容错保护限流降级：Spring Cloud Netflix Hystrix
- 服务负载均衡器：Spring Cloud Netflix Ribbon
- 声明式服务调用：Spring Cloud OpenFeign
- 日志收集与链路监控：Spring Cloud Sleuth
- 分布式统一配置中心：Spring Cloud Config
- 异构平台整合（ 挎斗模式）：Spring Cloud Sidecar


**整体架构图**

![mahua](https://static.oschina.net/uploads/space/2019/0106/205334_lpgP_3967264.png)

**技术选型**

>前端
- Vue2.x
- Layui 单页版
- Bootstrap v3.3.6
- jQuery v2.1.4
- bootstrap-table v1.9.0
- layer v2.1
- zTree core v3.5.28
- WebUploader 0.1.5

>后端
- SpringCloud Edgware.SR4
- SpringBoot 1.5.3.RELEASE
- MyBatis-Plus 2.0.8
- MyBatis 3.4.4
- Spring 4.3.8.RELEASE
- ActiveMQ 5.15.8.RELEASE
- Kafka 2.1.0
- Redis 3.3.1
- Kaptcha 2.3.2
- FastFs 1.2.31
- Shiro 1.4.0
- Druid 1.1

**内置功能**

- 资源管理：系统中的菜单，按钮，列表字段，查询条件等元素统称为资源。
- 角色管理：角色进行精细化资源授权，实现菜单，按钮，列表以及数据控制。
- 账户管理：登录系统的帐号称作“账户”，账户可以选择一个或多个角色。
- 人员管理：公司人员，通常指使用账户登录系统的人，人员可以与账户绑定。
- 部门管理：部门也可以称为“组织”，是将人员进行组织划分的模块。
- 字典管理：系统字典管理，常量管理。
- 日志管理：系统业务操作日志，API调用日志，用户登录日志等。
- 监控管理：系统链路监控，服务TPS，QPS监控，SQL监控等。
- 代码生成：使用GemFrame的专属代码生成器，减少80%重复工作量。

**功能特点**

- 强大的角色能力，菜单级，按钮级，表单级，数据级进行精细化权限控制
- 丰富的报表形式，通过快速配置，实现曲线图，柱状图，饼状图等数据报表
- 支持多种文件格式处理能力，实现上传，下载，播放加载，导入导出等功能
- 灵活的日志管理，含登录日志、操作日志、异常日志，主便审计及BUG定位
- 采用开源Activiti流程引擎，在线流程设计能力,实现网页在线设计流程，流程表单无缝对接


**本地部署**
>环境说明：
>- JDK1.8
>- MySQL5.5+
>- Maven3.0+

- 通过git下载源码
- 创建数据库gem_admin，数据库编码为UTF-8
- 修改application.properties文件，更新MySQL账号和密码
- 运行gem-utlis-jpa即可生成数据库表结构
- 执行db/gem.sql文件，初始化表数据
- 在gemframe目录下，执行mvn clean install
- Eclipse、IDEA打开项目
  * 1.运行gem-server-eureka中的EurekaServerApplication.java
  * 2.运行gem-server-zull中的ZullServerApplication.java
  * 3.运行gem-item-xxx中的XxxServerApplication.java，即可启动项目相关业务服务
- Eureka注册中心地址：http://localhost:9999 可看到相关服务状态


- WebStrom运行gem-admin-layui前端代码运行start下的index.html
- gem-admin访问地址：http://localhost:xxxx/gem-admin/index.html
- 账号密码：admin/123456
- swagger接口测试：http://localhost:[业务端口]/swagger-ui.html




**如何交流、反馈、参与贡献？

- 开发文档：http://doc.gemframe.cn
- 官方社区：https://www.gemframe.cn/bbs
- gitee仓库：https://gitee.com/gem-team/gemframe
- github仓库：https://gitee.com/gemteam/gemframe
- 官方网站：https://www.gemframe.cn
- 官方QQ群：72940788、446017307
> 如需关注项目最新动态，请Watch、Star项目，同时也是对项目最好的支持
> 技术讨论、二次开发等咨询、问题和建议，请移步到官方社区，我会在第一时间进行解答和回复！

