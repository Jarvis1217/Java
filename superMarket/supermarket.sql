/*
 Navicat Premium Data Transfer

 Source Server         : Demo
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 13/01/2021 17:12:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `price` double NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (2, '拉菲干红', 300, 10);
INSERT INTO `goods` VALUES (3, '海飞丝洗发水', 45, 117);
INSERT INTO `goods` VALUES (4, '舒肤佳沐浴露', 30, 97);
INSERT INTO `goods` VALUES (5, '三只松鼠每日坚果', 139, 125);
INSERT INTO `goods` VALUES (9, '潘婷洗发水', 26.5, 98);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(25) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `userPasswd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `isAdmin` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 1);
INSERT INTO `user` VALUES (2, 'Tom', 'Tom123', 0);
INSERT INTO `user` VALUES (3, 'Jack', 'Jack123', 0);
INSERT INTO `user` VALUES (4, 'root', 'root', 1);

SET FOREIGN_KEY_CHECKS = 1;
