/*
 Navicat Premium Data Transfer

 Source Server         : Demo
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : cash

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 04/03/2021 11:54:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bill
-- ----------------------------
DROP TABLE IF EXISTS `bill`;
CREATE TABLE `bill`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `price` double NOT NULL,
  `date` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `mark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bill
-- ----------------------------
INSERT INTO `bill` VALUES (7, '收入', 1000, '1614700800', 'test1');
INSERT INTO `bill` VALUES (8, '收入', 1000, '1614528000', 'test2');
INSERT INTO `bill` VALUES (9, '收入', 1000, '1583078400', 'test3');
INSERT INTO `bill` VALUES (10, '支出', 1000, '1583337600', 'test4');
INSERT INTO `bill` VALUES (11, '收入', 1000, '1583251200', 'qwer');

SET FOREIGN_KEY_CHECKS = 1;
