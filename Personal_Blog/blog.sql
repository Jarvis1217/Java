/*
 Navicat Premium Data Transfer

 Source Server         : Demo
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 24/03/2021 02:18:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `intro` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `postTime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------

-- ----------------------------
-- Table structure for pass_key
-- ----------------------------
DROP TABLE IF EXISTS `pass_key`;
CREATE TABLE `pass_key`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `passKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pass_key
-- ----------------------------
INSERT INTO `pass_key` VALUES (1, '123456');

SET FOREIGN_KEY_CHECKS = 1;
