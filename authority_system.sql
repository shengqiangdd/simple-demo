/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : authority_system

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 25/11/2023 18:11:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `department_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '部门名称',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门地址',
  `pid` bigint NOT NULL COMMENT '所属部门编号',
  `parent_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '所属部门名称',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(0-未删除 1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES (1, '广州码农信息技术有限公司', '020-88881056', '广州市天河区', 0, '顶级部门', 1, 0);
INSERT INTO `sys_department` VALUES (2, '软件技术部', '020-88881056', '广州市天河区', 5, '软件研发部', 1, 0);
INSERT INTO `sys_department` VALUES (3, '人事管理部', '020-88881002', '广州市天河区', 1, '广州码农信息技术有限公司', 1, 0);
INSERT INTO `sys_department` VALUES (4, '市场管理部', '020-88881003', '广州市天河区', 1, '广州码农信息技术有限公司', 1, 0);
INSERT INTO `sys_department` VALUES (5, '软件研发部', '020-88881056', '广州市天河区', 1, '广州码农信息技术有限公司', 1, 0);
INSERT INTO `sys_department` VALUES (6, 'Java技术部', '020-12345678', '广州市天河区', 2, '软件技术部', NULL, 1);
INSERT INTO `sys_department` VALUES (7, 'Java技术部', '', '', 2, '软件技术部', NULL, 1);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '权限编号',
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父权限ID',
  `parent_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父权限名称',
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权标识符',
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权路径',
  `type` tinyint NULL DEFAULT NULL COMMENT '权限类型(0-目录 1-菜单 2-按钮)',
  `icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `order_num` int NULL DEFAULT NULL COMMENT '排序',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(0-未删除，1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '系统管理', 0, '顶级菜单', 'sys:manager', '/system', 'system', '/system/system', 0, 'el-icon-menu', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, 0, 0);
INSERT INTO `sys_permission` VALUES (2, '部门管理', 1, '系统管理', 'sys:department', '/department', 'department', '/system/department/department', 1, 'el-icon-s-tools', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (3, '新增', 2, '部门管理', 'sys:department:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (4, '修改', 2, '部门管理', 'sys:department:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (5, '删除', 2, '部门管理', 'sys:department:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (6, '用户管理', 1, '系统管理', 'sys:user', '/userList', 'userList', '/system/user/userList', 1, 'el-icon-s-custom', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (7, '新增', 6, '用户管理', 'sys:user:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (8, '修改', 6, '用户管理', 'sys:user:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (9, '删除', 6, '用户管理', 'sys:user:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (10, '角色管理', 1, '系统管理', 'sys:role', '/roleList', 'roleList', '/system/role/roleList', 1, 'el-icon-s-tools', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (11, '新增', 10, '角色管理', 'sys:role:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (12, '修改', 10, '角色管理', 'sys:role:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (13, '删除', 10, '角色管理', 'sys:role:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (14, '菜单管理', 1, '系统管理', 'sys:menu', '/menuList', 'menuList', '/system/menu/menuList', 1, 'el-icon-s-tools', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (15, '新增', 14, '权限管理', 'sys:menu:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (16, '修改', 14, '权限管理', 'sys:menu:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (17, '删除', 14, '权限管理', 'sys:menu:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (18, '资料管理', 0, '顶级菜单', 'sys:resource', '/resource', 'resource', '/resource/index', 0, 'el-icon-menu', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (19, '供应商管理', 18, '资料管理', 'sys:provider', '/providerList', 'providerList', '/system/provider/providerList', 1, 'el-icon-s-tools', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (20, '新增', 19, '供应商管理', 'sys:provider:add', NULL, NULL, NULL, 2, 'el-icon-plus', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (21, '修改', 19, '供应商管理', 'sys:provider:edit', NULL, NULL, NULL, 2, 'el-icon-edit', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (22, '删除', 19, '供应商管理', 'sys:provider:delete', NULL, NULL, NULL, 2, 'el-icon-delete', '2022-04-25 14:40:32', '2022-04-25 14:40:32', NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (23, '分配角色', 6, '用户管理', 'sys:user:assign', '', '', '', 2, 'el-icon-setting', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (24, '分配权限', 10, '角色管理', 'sys:role:assign', '', '', '', 2, 'el-icon-setting', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (25, '查询', 2, '部门管理', 'sys:department:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (26, '查询', 6, '用户管理', 'sys:user:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (27, '查询', 10, '角色管理', 'sys:role:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (28, '查询', 14, '菜单管理', 'sys:menu:select', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 0);
INSERT INTO `sys_permission` VALUES (29, '订单管理', 18, '资料管理', 'resource:order', '/resource/order', 'OrderList', '/resource/order/orderList', 1, 'el-icon-setting', NULL, NULL, NULL, 2, 0);
INSERT INTO `sys_permission` VALUES (30, '查询', 19, '供应商管理', '5464', '', '', '', 2, 'el-icon-search', NULL, NULL, NULL, NULL, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色编号',
  `role_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `create_user` bigint NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(0-未删除，1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 'ROLE_SYSTEM', '超级管理员', 1, '2022-04-25 14:44:23', '2022-04-25 14:44:23', NULL, 0);
INSERT INTO `sys_role` VALUES (2, 'ROLE_SYSTEM', '系统管理员', 1, '2022-04-25 14:44:23', '2022-04-25 14:44:23', '拥有系统管理功能模块的权限', 0);
INSERT INTO `sys_role` VALUES (3, 'ROLE_RESOURCE', '资料管理员', 4, NULL, NULL, '拥有资料管理模块的功能权限', 0);
INSERT INTO `sys_role` VALUES (4, 'daw', 'fwf', 2, NULL, NULL, 'fw', 0);
INSERT INTO `sys_role` VALUES (5, 'rset', 'yftuy', 2, NULL, NULL, '', 0);
INSERT INTO `sys_role` VALUES (6, 'uftu', 'ogyio', 4, NULL, NULL, '', 0);
INSERT INTO `sys_role` VALUES (7, '6dr6', '8ft', 6, NULL, NULL, '', 0);
INSERT INTO `sys_role` VALUES (8, '7dfrt7', '9gy9', 5, NULL, NULL, '', 0);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `role_Id` bigint NOT NULL COMMENT '角色ID',
  `permission_Id` bigint NOT NULL COMMENT '权限ID'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 25);
INSERT INTO `sys_role_permission` VALUES (1, 6);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 26);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 12);
INSERT INTO `sys_role_permission` VALUES (1, 13);
INSERT INTO `sys_role_permission` VALUES (1, 24);
INSERT INTO `sys_role_permission` VALUES (1, 27);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 28);
INSERT INTO `sys_role_permission` VALUES (1, 18);
INSERT INTO `sys_role_permission` VALUES (1, 19);
INSERT INTO `sys_role_permission` VALUES (1, 20);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 22);
INSERT INTO `sys_role_permission` VALUES (2, 1);
INSERT INTO `sys_role_permission` VALUES (2, 2);
INSERT INTO `sys_role_permission` VALUES (2, 3);
INSERT INTO `sys_role_permission` VALUES (2, 4);
INSERT INTO `sys_role_permission` VALUES (2, 5);
INSERT INTO `sys_role_permission` VALUES (2, 25);
INSERT INTO `sys_role_permission` VALUES (2, 6);
INSERT INTO `sys_role_permission` VALUES (2, 7);
INSERT INTO `sys_role_permission` VALUES (2, 8);
INSERT INTO `sys_role_permission` VALUES (2, 9);
INSERT INTO `sys_role_permission` VALUES (2, 23);
INSERT INTO `sys_role_permission` VALUES (2, 26);
INSERT INTO `sys_role_permission` VALUES (2, 10);
INSERT INTO `sys_role_permission` VALUES (2, 11);
INSERT INTO `sys_role_permission` VALUES (2, 12);
INSERT INTO `sys_role_permission` VALUES (2, 13);
INSERT INTO `sys_role_permission` VALUES (2, 24);
INSERT INTO `sys_role_permission` VALUES (2, 27);
INSERT INTO `sys_role_permission` VALUES (2, 14);
INSERT INTO `sys_role_permission` VALUES (2, 15);
INSERT INTO `sys_role_permission` VALUES (2, 16);
INSERT INTO `sys_role_permission` VALUES (2, 17);
INSERT INTO `sys_role_permission` VALUES (2, 28);
INSERT INTO `sys_role_permission` VALUES (3, 3);
INSERT INTO `sys_role_permission` VALUES (3, 23);
INSERT INTO `sys_role_permission` VALUES (3, 12);
INSERT INTO `sys_role_permission` VALUES (3, 15);
INSERT INTO `sys_role_permission` VALUES (3, 16);
INSERT INTO `sys_role_permission` VALUES (3, 1);
INSERT INTO `sys_role_permission` VALUES (3, 2);
INSERT INTO `sys_role_permission` VALUES (3, 6);
INSERT INTO `sys_role_permission` VALUES (3, 10);
INSERT INTO `sys_role_permission` VALUES (3, 14);
INSERT INTO `sys_role_permission` VALUES (5, 3);
INSERT INTO `sys_role_permission` VALUES (5, 1);
INSERT INTO `sys_role_permission` VALUES (5, 2);
INSERT INTO `sys_role_permission` VALUES (8, 3);
INSERT INTO `sys_role_permission` VALUES (8, 1);
INSERT INTO `sys_role_permission` VALUES (8, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名称(用户名)',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `is_account_non_expired` tinyint NOT NULL COMMENT '帐户是否过期(1-未过期，0-已过期)',
  `is_account_non_locked` tinyint NOT NULL COMMENT '帐户是否被锁定(1-未过期，0-已过期)',
  `is_credentials_non_expired` tinyint NOT NULL COMMENT '密码是否过期(1-未过期，0-已过期)',
  `is_enabled` tinyint NOT NULL COMMENT '帐户是否可用(1-可用，0-禁用)',
  `real_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `department_id` bigint NULL DEFAULT NULL COMMENT '所属部门ID',
  `department_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属部门名称',
  `gender` tinyint NOT NULL COMMENT '性别(0-男，1-女)',
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT 'https://manong-authority.oss-cn-guangzhou.aliyuncs.com/avatar/default-avatar.gif' COMMENT '用户头像',
  `is_admin` tinyint NULL DEFAULT 0 COMMENT '是否是管理员(1-管理员)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `is_delete` tinyint NULL DEFAULT 0 COMMENT '是否删除(0-未删除，1-已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$TdEVQtGCkpo8L.jKjFB3/uxV5xkkDfiy0zoCa.ZS2yAXHe7H95OIC', 0, 0, 0, 0, '李明', '超级管理员', 1, '广州码农信息技术有限公司', 0, '13242587415', 'liming@163.com', 'https://xxsq-lean.oss-cn-shenzhen.aliyuncs.com/avatar/2023/11/04/b16041b2e12241de8c76ccfcd0c24e5d.gif', 1, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (2, 'liming', '$2a$10$WwhJ8dBezfyMFIn19.ELru58K65k6N2tgewtv2sWdClKiRCjC55wG', 0, 0, 0, 0, '黎明', '黎明', 2, '软件技术部', 0, '13578956652', '', 'https://xxsq-lean.oss-cn-shenzhen.aliyuncs.com/avatar/2023/11/04/636b5e09f54c4e4b816f481ee6e83a1a.gif', 0, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (3, 'zhangsan', '$2a$10$iBQbmrAEBE5B84/U3RY7c.zhObI4aIpjl807FV4LzL/uay7arIcpu', 1, 1, 1, 1, '张三', '张三', 2, '软件技术部', 0, '13245678965', 'zhangsan@163.com', 'https://manong-authority.oss-cn-guangzhou.aliyuncs.com/avatar/2022/05/16/bfa834a4c9424461a1ea0cbf8d4c9105-5acd2ed959790ec52b2825cbbc11b72d.jpeg', 0, NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (4, 'lisi', '$2a$10$QywHvELdRoFGiU6LKpd/X.LYpfaXETtS0pD4Nem2K3c0iMQwaZuAm', 0, 0, 0, 0, '李四', '李四', 2, '软件技术部', 0, '13754214568', '', 'https://xxsq-lean.oss-cn-shenzhen.aliyuncs.com/avatar/2023/11/04/65044f82e8044c00bc7b1265cc809437.jpeg', 0, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (5, 'wangwu', '$2a$10$O8uyPZFS9PLfR8JN.aMRi.l/YeykYYuKH.cg/HBAR.N4NJeNg8hQK', 0, 0, 0, 0, '王五', '王五', 2, '软件技术部', 0, '13212345678', '', 'https://xxsq-lean.oss-cn-shenzhen.aliyuncs.com/avatar/2023/11/04/ee3a4b3b622c4885b0a2e41e18556caf.jpg', 0, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (6, 'zhaoliu', '$2a$10$r45wkEYLHlteEr0KLI8y3.G506ylhQrEJkmGM.i2eHkcCnFvfbhCS', 0, 0, 0, 0, '赵六', '赵六', 2, '软件技术部', 0, '13212345676', '', 'https://xxsq-lean.oss-cn-shenzhen.aliyuncs.com/avatar/2023/11/04/1bad9ebcf66c454b9fc7b29855327399.jpeg', 0, NULL, NULL, 0);
INSERT INTO `sys_user` VALUES (7, 'tset', '$2a$10$vcIvjsw2jLK6EgaGqFWhDOIrk6rKZ4r98uEJd1Ni4X3Frxbf35/0u', 0, 0, 0, 0, 'gyiu', '', 2, '软件技术部', 0, '13479884684', '', '', 0, NULL, NULL, 1);
INSERT INTO `sys_user` VALUES (8, 'yr', '$2a$10$BPxiCG6YNlRP9ppPPg6yzO/YfYxR.y42i4rfmYX5ysPLK6yYX899y', 0, 0, 0, 0, 'yrd', '', 2, '软件技术部', 0, '13497846555', '', 'https://xxsq-lean.oss-cn-shenzhen.aliyuncs.com/avatar/2023/11/04/617aa63212b345da80721e08b5a8e00e.jpg', 0, NULL, NULL, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户编号',
  `role_id` bigint NOT NULL COMMENT '角色编号'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (4, 2);
INSERT INTO `sys_user_role` VALUES (5, 3);
INSERT INTO `sys_user_role` VALUES (6, 3);

SET FOREIGN_KEY_CHECKS = 1;
