/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50709
Source Host           : 127.0.0.1:3306
Source Database       : gemframe

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2019-02-21 19:14:01
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
INSERT INTO `gem_tab_dictionary` VALUES ('1788144371040256', '2019-01-07 10:56:14', '2019-02-21 12:24:11', '458977872379904', '性别', 'sex', '2', '11', null, '2', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('1802415305850880', '2019-01-07 11:52:56', '2019-02-19 12:47:20', '459532355174400', '民族', 'mz111', '2', '11', null, '2', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('1859699641876480', '2019-01-07 15:40:34', '2019-01-07 15:40:34', '1802415305850880', '满族', '2', null, '1', '3', '3', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('1870904171364352', '2019-01-07 16:25:05', '2019-02-01 00:35:33', '1788144371040256', '男', '1', null, '4', '0', '3', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('1911307947933696', '2019-01-07 19:05:38', '2019-01-07 19:05:38', '1788144371040256', '女', '1', null, '2', '1', '3', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('458977872379904', '2019-01-03 18:54:36', '2019-02-01 00:19:54', '-1', '组1', 'zu1', '1', '1', null, '1', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('459532355174400', '2019-01-03 18:56:48', '2019-02-21 11:24:03', '-1', '组2', 'zu2', '1', '2', null, '1', '测试数据');
INSERT INTO `gem_tab_dictionary` VALUES ('523493578571776', '2019-01-03 23:10:58', '2019-01-03 23:10:58', '1802415305850880', '维吾尔族', 'sex', null, '3', '4', '3', '测试数据');

-- ----------------------------
-- Table structure for `gem_tab_organ`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_organ`;
CREATE TABLE `gem_tab_organ` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `organ_parentId` bigint(20) DEFAULT NULL COMMENT '父级',
  `organ_name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `organ_short_name` varchar(255) DEFAULT NULL COMMENT '部门简称',
  `organ_sort` int(11) DEFAULT NULL COMMENT '排序号',
  `organ_cause` varchar(255) DEFAULT NULL COMMENT '备注',
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
  `per_sort` int(11) DEFAULT NULL,
  `per_menus_type` int(11) DEFAULT NULL COMMENT '是否是菜单0为菜单1权限',
  `per_cause` varchar(255) DEFAULT NULL COMMENT '权限描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_permissions
-- ----------------------------
INSERT INTO `gem_tab_permissions` VALUES ('1', '2019-01-02 23:08:55', '2019-02-21 16:40:23', '-1', '工作台', '', '', 'CD_1', '1', '1', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('1302099195985920', '2019-02-02 17:01:17', '2019-02-21 16:41:04', '7', 'sql监控', '', '', '1', '2', '1', '0', '');
INSERT INTO `gem_tab_permissions` VALUES ('1327566359756800', '2019-02-02 18:42:29', '2019-02-21 13:28:02', '1302099195985920', '8083', 'iframe/link/sql8083', '111', '111', '3', '22222', '0', '1111');
INSERT INTO `gem_tab_permissions` VALUES ('1328386224553984', '2019-02-02 18:45:45', '2019-02-21 16:41:09', '7', '服务监控', '', '', '2', '2', '2', '0', '');
INSERT INTO `gem_tab_permissions` VALUES ('1328568324456448', '2019-02-02 18:46:28', '2019-02-21 13:26:56', '1328386224553984', '9999', 'iframe/link/eureka', '', '', '3', '1111', '0', '');
INSERT INTO `gem_tab_permissions` VALUES ('1329195721031680', '2019-02-02 18:48:58', '2019-02-21 16:41:21', '1302099195985920', '8084', 'iframe/link/sql8084', '', '1', '3', '2', '0', '');
INSERT INTO `gem_tab_permissions` VALUES ('22222', '2019-02-01 13:58:33', '2019-02-01 13:58:33', '622', '资源按钮12', '11', null, 'an_01', '4', null, '1', '描述111');
INSERT INTO `gem_tab_permissions` VALUES ('6', '2019-01-02 23:08:55', '2019-02-21 16:40:20', '-1', '系统管理', '', '', 'CD_6', '1', '2', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('61', '2019-01-02 23:08:55', '2019-02-21 16:40:32', '6', '用户与授权', '', '', 'CD_61', '2', '1', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('611', '2019-01-02 23:08:55', '2019-02-21 18:41:51', '61', '角色管理', 'authorize/role/index', '', 'CD_611', '3', '2', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('612', '2019-01-02 23:08:55', '2019-02-21 18:41:54', '61', '用户管理', 'authorize/user/index', '', 'CD_612', '3', '3', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('62', '2019-01-02 23:08:55', '2019-02-21 16:40:35', '6', '基础设置', '', '', 'CD_62', '2', '2', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('621', '2019-01-02 23:08:55', '2019-02-21 16:40:39', '62', '字典维护', 'common/dic/index', '', 'CD_621', '3', '1', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('622', '2019-01-02 23:08:55', '2019-02-21 18:41:46', '61', '资源管理', 'authorize/permissions/index', '', 'CD_622', '3', '1', '0', '描述');
INSERT INTO `gem_tab_permissions` VALUES ('7', '2019-01-02 23:08:55', '2019-02-21 16:40:27', '-1', '系统监控', '', '', 'CD_7', '1', '3', '0', '描述');

-- ----------------------------
-- Table structure for `gem_tab_role`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_role`;
CREATE TABLE `gem_tab_role` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `role_groupId` bigint(20) DEFAULT NULL COMMENT '所属角色组id',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名字',
  `role_code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `role_sort` int(11) DEFAULT NULL COMMENT '排序号',
  `role_cause` varchar(255) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_role
-- ----------------------------
INSERT INTO `gem_tab_role` VALUES ('1', '2019-02-21 18:22:58', '2019-02-21 18:22:58', '4916009262448640', '管理员', '1', '1', '1');
INSERT INTO `gem_tab_role` VALUES ('736084519157760', '2019-02-01 03:32:09', '2019-02-01 19:32:23', '4916009262448640', '部门经理', '01', '2', '部门经理');
INSERT INTO `gem_tab_role` VALUES ('736179897630720', '2019-02-01 03:32:32', '2019-02-21 18:26:17', '4916009262448640', '开发组长', '02', '3', '开发组长');

-- ----------------------------
-- Table structure for `gem_tab_role_group`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_role_group`;
CREATE TABLE `gem_tab_role_group` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `group_parentId` bigint(20) DEFAULT NULL COMMENT '角色组父级',
  `group_name` varchar(255) DEFAULT NULL COMMENT '角色组名字',
  `group_sort` int(11) DEFAULT NULL COMMENT '排序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_role_group
-- ----------------------------
INSERT INTO `gem_tab_role_group` VALUES ('4916009262448640', '2019-02-07 19:10:25', '2019-01-10 20:26:00', '-1', '运营组', '3');

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
INSERT INTO `gem_tab_role_permissions` VALUES ('1263796941881345', null, null, '736084519157760', '6');
INSERT INTO `gem_tab_role_permissions` VALUES ('1263797021573121', null, null, '736084519157760', '61');
INSERT INTO `gem_tab_role_permissions` VALUES ('1263797034156033', null, null, '736084519157760', '611');
INSERT INTO `gem_tab_role_permissions` VALUES ('1263797042544641', null, null, '736084519157760', '612');
INSERT INTO `gem_tab_role_permissions` VALUES ('1263797055127553', null, null, '736084519157760', '62');
INSERT INTO `gem_tab_role_permissions` VALUES ('1263797063516161', null, null, '736084519157760', '621');
INSERT INTO `gem_tab_role_permissions` VALUES ('1263797076099073', null, null, '736084519157760', '622');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402353418241', null, null, '1', '1');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402370195457', null, null, '1', '6');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402382778369', null, null, '1', '61');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402391166977', null, null, '1', '611');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402407944193', null, null, '1', '612');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402437304321', null, null, '1', '62');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402449887233', null, null, '1', '621');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402458275841', null, null, '1', '622');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402475053057', null, null, '1', '7');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402491830273', null, null, '1', '1302099195985920');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402508607489', null, null, '1', '1327566359756800');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402516996097', null, null, '1', '1329195721031680');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402525384705', null, null, '1', '1328386224553984');
INSERT INTO `gem_tab_role_permissions` VALUES ('1329402542161921', null, null, '1', '1328568324456448');
INSERT INTO `gem_tab_role_permissions` VALUES ('8208784464412673', null, null, '736179897630720', '6');
INSERT INTO `gem_tab_role_permissions` VALUES ('8208784472801281', null, null, '736179897630720', '62');
INSERT INTO `gem_tab_role_permissions` VALUES ('8208784481189889', null, null, '736179897630720', '622');
INSERT INTO `gem_tab_role_permissions` VALUES ('8208784493772801', null, null, '736179897630720', '7');
INSERT INTO `gem_tab_role_permissions` VALUES ('8208784502161409', null, null, '736179897630720', '1302099195985920');
INSERT INTO `gem_tab_role_permissions` VALUES ('8208784514744321', null, null, '736179897630720', '1329195721031680');
INSERT INTO `gem_tab_role_permissions` VALUES ('8208784527327233', null, null, '736179897630720', '1327566359756800');
INSERT INTO `gem_tab_role_permissions` VALUES ('8208784539910145', null, null, '736179897630720', '1328386224553984');
INSERT INTO `gem_tab_role_permissions` VALUES ('8208784552493057', null, null, '736179897630720', '1328568324456448');

-- ----------------------------
-- Table structure for `gem_tab_user`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_user`;
CREATE TABLE `gem_tab_user` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(255) DEFAULT NULL COMMENT '密码',
  `admin_type` varchar(11) DEFAULT NULL COMMENT '1代表是超级管理员',
  `member_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `card` varchar(255) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `states` varchar(255) DEFAULT NULL COMMENT '状态 0 禁用，1启用',
  `cause` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_user
-- ----------------------------
INSERT INTO `gem_tab_user` VALUES ('1', '2019-01-14 16:15:11', '2019-02-02 14:28:01', 'admin', '$2a$10$i6H2IaRcHYuTRuEiys3P7Otegu9Pd0TK/t3bnFvSain7PGdU18Tum', null, '管理员', '13200929999', 'test@126.com', '', null, '1', '测试用');
INSERT INTO `gem_tab_user` VALUES ('1245385662791680', '2019-02-02 13:15:56', '2019-02-03 10:24:56', 'test', '$2a$10$SAM39VXRn8IURNg8bvvUROFyLQfhfXW6DCS4oxoztnLGYomM3Fp2q', null, '测试2', '1999999999', '333@128.com', '1', null, null, '测试用');
INSERT INTO `gem_tab_user` VALUES ('1319701314142208', '2019-02-02 18:11:14', '2019-02-03 10:24:13', '1111', null, null, '11', '111', '111', '111', null, null, null);

-- ----------------------------
-- Table structure for `gem_tab_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `gem_tab_user_role`;
CREATE TABLE `gem_tab_user_role` (
  `id` varchar(20) NOT NULL COMMENT '主键',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户主键',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gem_tab_user_role
-- ----------------------------
INSERT INTO `gem_tab_user_role` VALUES ('1263513960579072', '2019-02-02 14:27:58', '2019-02-02 14:27:58', '741195098292224', '736179897630720');
INSERT INTO `gem_tab_user_role` VALUES ('1263528485453824', '2019-02-02 14:28:01', '2019-02-02 14:28:01', '1', '1');
INSERT INTO `gem_tab_user_role` VALUES ('1564560654467072', '2019-02-03 10:24:13', '2019-02-03 10:24:13', '1319701314142208', '736084519157760');
INSERT INTO `gem_tab_user_role` VALUES ('1564560688021504', '2019-02-03 10:24:13', '2019-02-03 10:24:13', '1319701314142208', '736179897630720');
INSERT INTO `gem_tab_user_role` VALUES ('1564741877760000', '2019-02-03 10:24:56', '2019-02-03 10:24:56', '1245385662791680', '736084519157760');
INSERT INTO `gem_tab_user_role` VALUES ('1564741923897344', '2019-02-03 10:24:56', '2019-02-03 10:24:56', '1245385662791680', '736179897630720');
