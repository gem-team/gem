/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : gemframe

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2019-02-02 13:30:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `gem_tab_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_dictionary`;
CREATE TABLE `gem_tab_dictionary` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `dic_parentId` bigint(20) DEFAULT NULL COMMENT '父级',
  `dic_name` varchar(255) DEFAULT NULL COMMENT '名字',
  `dic_code` varchar(255) DEFAULT NULL COMMENT '编码',
  `dic_level` int(11) DEFAULT NULL COMMENT '级别',
  `dic_sort` int(11) DEFAULT NULL COMMENT '排序号',
  `dic_value` varchar(255) DEFAULT NULL COMMENT '值',
  `dic_type` varchar(255) DEFAULT NULL COMMENT '类型:1组，2字典，3字典项',
  `dic_cause` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_dictionary
-- ----------------------------
INSERT INTO `gem_tab_dictionary` VALUES ('1788144371040256', '2019-01-07 10:56:14', '2019-02-01 00:26:05', '458977872379904', '性别', 'sex', '3', '11', null, '2', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('1802415305850880', '2019-01-07 11:52:56', '2019-01-07 19:04:32', '459532355174400', '民族', 'mz', '3', '11', null, '2', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('1859699641876480', '2019-01-07 15:40:34', '2019-01-07 15:40:34', '1802415305850880', '满族', '2', null, '1', '3', '3', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('1870904171364352', '2019-01-07 16:25:05', '2019-02-01 00:35:33', '1788144371040256', '男', '1', null, '4', '0', '3', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('1911307947933696', '2019-01-07 19:05:38', '2019-01-07 19:05:38', '1788144371040256', '女', '1', null, '2', '1', '3', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('458977872379904', '2019-01-03 18:54:36', '2019-02-01 00:19:54', '-1', '组1', 'zu1', '1', '1', null, '1', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('459532355174400', '2019-01-03 18:56:48', '2019-01-04 11:59:53', '-1', '组2', 'zu2', '1', '2', null, '1', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('523493578571776', '2019-01-03 23:10:58', '2019-01-03 23:10:58', '1802415305850880', '维吾尔族', 'sex', null, '3', '4', '3', '测试数据');

-- ----------------------------
-- Table structure for `gem_tab_organ`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_organ`;
CREATE TABLE `gem_tab_organ` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `approval_time` datetime DEFAULT NULL COMMENT '批准成立日期',
  `approval_unit` varchar(255) DEFAULT NULL COMMENT '批准成立单位',
  `organ_cause` varchar(255) DEFAULT NULL COMMENT '备注',
  `compile_number` varchar(255) DEFAULT NULL COMMENT '编织数',
  `organ_intrduc` varchar(255) DEFAULT NULL COMMENT '简介',
  `organ_levels` int(11) DEFAULT NULL COMMENT '部门级别',
  `organ_code` varchar(255) DEFAULT NULL COMMENT '机构代码',
  `organ_name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `organ_type` varchar(255) DEFAULT NULL COMMENT '部门类型',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级',
  `set_number` varchar(255) DEFAULT NULL COMMENT '成立文号',
  `organ_unit` varchar(255) DEFAULT NULL COMMENT '单位简称',
  `valid_time` datetime DEFAULT NULL COMMENT '生效日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_organ
-- ----------------------------

-- ----------------------------
-- Table structure for `gem_tab_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_permissions`;
CREATE TABLE `gem_tab_permissions` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `per_parentId` bigint(20) DEFAULT NULL COMMENT '权限的父级',
  `per_name` varchar(255) DEFAULT NULL COMMENT '权限名字',
  `per_path` varchar(255) DEFAULT NULL COMMENT '资源路径',
  `per_icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `per_code` varchar(255) DEFAULT NULL COMMENT '权限编码',
  `per_level` int(11) DEFAULT NULL COMMENT '权限的级别',
  `per_menus_type` int(11) DEFAULT NULL COMMENT '是否是菜单0为菜单1权限',
  `per_cause` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_permissions
-- ----------------------------
INSERT INTO `gem_tab_permissions` VALUES ('1', '2019-01-02 23:08:55', '2019-01-07 16:38:52', '-1', '工作台', '', '', 'CD_1', '1', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('22222', null, '2019-02-01 13:58:33', '622', '资源按钮12', '11', null, 'an_01', '4', '1', '描述111');
INSERT INTO `gem_tab_permissions` VALUES ('6', '2019-01-02 23:08:55', '2019-01-02 23:09:01', '-1', '系统管理', '', '', 'CD_6', '1', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('61', '2019-01-02 23:08:55', '2019-01-02 23:09:01', '6', '用户与授权', '', '', 'CD_61', '2', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('611', '2019-01-02 23:08:55', '2019-01-02 23:09:01', '61', '角色管理', 'authorize/role/index', '', 'CD_611', '3', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('612', '2019-01-02 23:08:55', '2019-01-02 23:09:01', '61', '用户管理', 'authorize/user/index', '', 'CD_612', '3', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('62', '2019-01-02 23:08:55', '2019-01-06 06:41:47', '6', '基础设置', '', '', 'CD_62', '2', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('621', '2019-01-02 23:08:55', '2019-01-02 23:09:01', '62', '字典维护', 'common/dic/index', '', 'CD_621', '3', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('622', '2019-01-02 23:08:55', '2019-01-02 23:09:01', '62', '资源管理', 'authorize/permissions/index', '', 'CD_622', '3', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('7', '2019-01-02 23:08:55', '2019-01-06 06:41:39', '-1', '系统监控', '', '', 'CD_7', '1', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('893755603943424', '2019-02-01 13:58:41', '2019-02-01 13:58:41', '622', '22', '222', null, '22', null, '1', '22');

-- ----------------------------
-- Table structure for `gem_tab_role`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_role`;
CREATE TABLE `gem_tab_role` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `role_cause` varchar(255) DEFAULT NULL COMMENT '角色描述',
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `role_groupId` bigint(20) DEFAULT NULL COMMENT '所属角色组id',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名字',
  `role_sort` int(11) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_role
-- ----------------------------
INSERT INTO `gem_tab_role` VALUES ('1', null, null, '1', '1', '4916009262448640', '管理员', null);
INSERT INTO `gem_tab_role` VALUES ('736084519157760', '2019-02-01 03:32:09', '2019-02-01 19:32:23', '部门经理', '01', '4916009262448640', '部门经理', '2');
INSERT INTO `gem_tab_role` VALUES ('736179897630720', '2019-02-01 03:32:32', '2019-02-01 19:32:45', '开发组长', '02', '4916009262448640', '开发组长', '3');

-- ----------------------------
-- Table structure for `gem_tab_role_group`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_role_group`;
CREATE TABLE `gem_tab_role_group` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `group_name` varchar(255) DEFAULT NULL COMMENT '角色组名字',
  `group_parentId` bigint(20) DEFAULT NULL COMMENT '角色组父级',
  `group_sort` int(11) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_role_group
-- ----------------------------
INSERT INTO `gem_tab_role_group` VALUES ('4916009262448640', null, '2019-01-10 20:26:00', '运营组', '-1', '3');

-- ----------------------------
-- Table structure for `gem_tab_role_permissions`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_role_permissions`;
CREATE TABLE `gem_tab_role_permissions` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色主键',
  `per_id` bigint(20) DEFAULT NULL COMMENT '权限主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_role_permissions
-- ----------------------------
INSERT INTO `gem_tab_role_permissions` VALUES ('972571575713793', null, null, '1', '1');
INSERT INTO `gem_tab_role_permissions` VALUES ('972571592491009', null, null, '1', '6');
INSERT INTO `gem_tab_role_permissions` VALUES ('972571605073921', null, null, '1', '61');
INSERT INTO `gem_tab_role_permissions` VALUES ('972571609268225', null, null, '1', '611');
INSERT INTO `gem_tab_role_permissions` VALUES ('972571634434049', null, null, '1', '612');
INSERT INTO `gem_tab_role_permissions` VALUES ('972571642822657', null, null, '1', '62');
INSERT INTO `gem_tab_role_permissions` VALUES ('972571647016961', null, null, '1', '621');
INSERT INTO `gem_tab_role_permissions` VALUES ('972571659599873', null, null, '1', '622');
INSERT INTO `gem_tab_role_permissions` VALUES ('972571667988481', null, null, '1', '7');
INSERT INTO `gem_tab_role_permissions` VALUES ('972571672182785', null, null, '1', '893755603943424');

-- ----------------------------
-- Table structure for `gem_tab_user`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_user`;
CREATE TABLE `gem_tab_user` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `admin_type` varchar(11) DEFAULT NULL COMMENT '1代表是超级管理员',
  `cause` varchar(255) DEFAULT NULL COMMENT '备注',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `card` varchar(255) DEFAULT NULL,
  `failure_time` datetime DEFAULT NULL COMMENT '失效时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `member_id` varchar(20) DEFAULT NULL COMMENT '员工id',
  `member_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `pass_word` varchar(255) DEFAULT NULL COMMENT '密码',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `states` varchar(255) DEFAULT NULL COMMENT '状态 0 禁用，1启用',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `valid_time` datetime DEFAULT NULL COMMENT '生效时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_user
-- ----------------------------
INSERT INTO `gem_tab_user` VALUES ('1', '2019-01-14 16:15:11', '2019-02-02 13:14:10', null, '测试用', 'test@126.com', '', null, null, null, '管理员', '$2a$10$i6H2IaRcHYuTRuEiys3P7Otegu9Pd0TK/t3bnFvSain7PGdU18Tum', '13200929999', '1', 'admin', null);
INSERT INTO `gem_tab_user` VALUES ('1245385662791680', '2019-02-02 13:15:56', '2019-02-02 13:15:56', null, '测试用', '333@128.com', '1', null, null, null, '测试2', '$2a$10$SAM39VXRn8IURNg8bvvUROFyLQfhfXW6DCS4oxoztnLGYomM3Fp2q', '1999999999', null, 'test2', null);
INSERT INTO `gem_tab_user` VALUES ('741195098292224', '2019-01-04 13:36:02', '2019-02-02 13:13:58', null, '测试用', 'wangsong@126.com', '11111111', null, null, null, '测试1', '$2a$10$nIFH02MqP/0w3dKQaF5pvOY/6AtOuyTXMkcsdhkM9GGeMlGHpZTTS', '18511165624', '0', 'test1', null);

-- ----------------------------
-- Table structure for `gem_tab_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_user_role`;
CREATE TABLE `gem_tab_user_role` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `failure_time` datetime DEFAULT NULL COMMENT '失效时间',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户主键',
  `valid_time` datetime DEFAULT NULL COMMENT '生效时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_user_role
-- ----------------------------
INSERT INTO `gem_tab_user_role` VALUES ('1244889799589888', '2019-02-02 13:13:58', '2019-02-02 13:13:58', null, '1', '741195098292224', null);
INSERT INTO `gem_tab_user_role` VALUES ('1244889820561408', '2019-02-02 13:13:58', '2019-02-02 13:13:58', null, '736084519157760', '741195098292224', null);
INSERT INTO `gem_tab_user_role` VALUES ('1244889824755712', '2019-02-02 13:13:58', '2019-02-02 13:13:58', null, '736179897630720', '741195098292224', null);
INSERT INTO `gem_tab_user_role` VALUES ('1244942064812032', '2019-02-02 13:14:10', '2019-02-02 13:14:10', null, '1', '1', null);
INSERT INTO `gem_tab_user_role` VALUES ('1245385662791681', null, null, null, '1', '1245385662791680', null);
INSERT INTO `gem_tab_user_role` VALUES ('1245385675374592', null, null, null, '736084519157760', '1245385662791680', null);
INSERT INTO `gem_tab_user_role` VALUES ('1245385679568896', null, null, null, '736179897630720', '1245385662791680', null);
