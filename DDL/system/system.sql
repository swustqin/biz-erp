/*
 Navicat Premium Data Transfer

 Source Server         : fwqMysqlroot
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 120.79.222.244
 Source Database       : BMG

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 03/10/2018 13:37:40 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(64) NOT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `url` varchar(200) DEFAULT NULL,
  `perms` varchar(300) DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `order_num` int(11) DEFAULT NULL,
  `entry_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(64) NOT NULL COMMENT '角色id',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `entry_id` varchar(64) NOT NULL COMMENT '创建者id',
  `role_status` tinyint(4) DEFAULT 0 COMMENT '状态0正常1禁用',
  `default_role` tinyint(4) DEFAULT 0 COMMENT '是否默认角色0不是1是',
  `entry_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(64) NOT NULL COMMENT '角色菜单表',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) NOT NULL COMMENT '菜单id',
  `entry_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '系统用户表',
  `username` varchar(64) NOT NULL UNIQUE COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '联系方式',
  `user_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态0正常1禁用',
  `entry_id` varchar(64) NOT NULL COMMENT '创建者id',
  `entry_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` varchar(64) NOT NULL COMMENT '更新者id',
  `update_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `super_manager` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否超级管理员0否1是',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `workshop_id` varchar(64) DEFAULT NULL COMMENT '工作车间',
  `last_login_date` DATETIME DEFAULT NULL COMMENT '最后登录日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(64) NOT NULL,
  `user_id` varchar(64) NOT NULL COMMENT '用户id',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `entry_id` varchar(64) NOT NULL COMMENT '创建者id',
  `entry_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_id` varchar(64) NOT NULL COMMENT '更新者id',
  `update_datetime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;



INSERT INTO sys_user (id, username, password, email, mobile, entry_id, update_id, name)
  VALUE ('1', 'admin', 'admin', 'tuchuntong@qq.com', '13980613115', '1', '1', '涂纯童');

INSERT INTO `sys_menu`(id,parent_id,name,url,perms,type,order_num)
  VALUES ('1', '0', '菜单管理', '/menu', null, '2', '0');
