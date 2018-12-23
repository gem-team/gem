# GemFrame
架构简介

GemFrame基于SpringCloud及周边的生态技术开发。是一款稳定，高效的企业级微服务架构，致力面向中小型企业及个人开发者提供全面微服务架构解决方案。其具有代码规范，学习门槛极低，开箱即用等优势，方便开发者快速开发使用。官方网址： http://www.gemframe.cn

基础能力包括：精细化权限管理，安全身份认证，即时通讯，文件服务，消息队列，工作流等，能够满足所有中小型企业或团队的基础架构需求。

所用技术

前端

Bootstrap v3.3.6
jQuery v2.1.4
bootstrap-table v1.9.0
layer v2.1
zTree core v3.5.28
WebUploader 0.1.5
后端

SpringCloud Edgware.SR4
SpringBoot 1.5.3.RELEASE
MyBatis-Plus 2.0.8
MyBatis 3.4.4
Spring 4.3.8.RELEASE
ActiveMQ 5.15.8.RELEASE
Kafka 2.1.0
Redis 3.3.1
Kaptcha 2.3.2
FastFs 1.2.31
Shiro 1.4.0
Druid 1.0.31


功能简介

资源管理：系统中的菜单，按钮，列表字段，查询条件等元素统称为资源。

角色管理：角色进行精细化资源授权，实现菜单，按钮，列表以及数据控制。

账户管理：登录系统的帐号称作“账户”，账户可以选择一个或多个角色。

人员管理：公司人员，通常指使用账户登录系统的人，人员可以与账户绑定。

部门管理：部门也可以称为“组织”，是将人员进行组织划分的模块。

字典管理：系统字典管理，常量管理。

日志管理：系统业务操作日志，API调用日志，用户登录日志等。

监控管理：系统链路监控，服务TPS，QPS监控，SQL监控等。

代码生成：使用GemFrame的专属代码生成器，减少80%重复工作量。
