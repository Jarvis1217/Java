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

 Date: 21/04/2021 03:06:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_blog
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog`;
CREATE TABLE `tb_blog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `postTime` datetime(0) NOT NULL,
  `lng` double NOT NULL,
  `lat` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_blog
-- ----------------------------
INSERT INTO `tb_blog` VALUES (1, '<p>aaaaaaaaaaaaaa</p>', '2021-04-21 00:42:46', 114.436144, 30.417611);
INSERT INTO `tb_blog` VALUES (2, '<p>bbbbbbbbbbbbbb</p>', '2021-04-21 00:42:54', 114.436227, 30.418128);
INSERT INTO `tb_blog` VALUES (3, '<p>ccccccccccccccccccc</p>', '2021-04-21 00:43:04', 114.435779, 30.417322);
INSERT INTO `tb_blog` VALUES (4, '<p>qwerty</p>', '2021-04-21 00:43:22', 114.434436, 30.420682);

-- ----------------------------
-- Table structure for tb_comments
-- ----------------------------
DROP TABLE IF EXISTS `tb_comments`;
CREATE TABLE `tb_comments`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `postTime` datetime(0) NOT NULL,
  `lng` double NOT NULL,
  `lat` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_comments
-- ----------------------------
INSERT INTO `tb_comments` VALUES (1, 'root', 'bbb的测试评论一', '2021-04-21 02:00:37', 114.436227, 30.418128);
INSERT INTO `tb_comments` VALUES (2, 'root', '食堂的评论', '2021-04-21 03:02:27', 114.434436, 30.420682);
INSERT INTO `tb_comments` VALUES (3, 'root', 'bbb的测试评论二', '2021-04-21 03:03:16', 114.436227, 30.418128);
INSERT INTO `tb_comments` VALUES (4, 'root', '食堂的测试评论一\n', '2021-04-21 03:05:26', 114.434436, 30.420682);
INSERT INTO `tb_comments` VALUES (5, 'root', '食堂的测试评论二', '2021-04-21 03:05:40', 114.434436, 30.420682);
INSERT INTO `tb_comments` VALUES (6, 'root', 'aaaaaaaaa', '2021-04-21 03:05:51', 114.434436, 30.420682);
INSERT INTO `tb_comments` VALUES (7, 'root', 'bbbbbbbbbbbbbbb', '2021-04-21 03:05:56', 114.434436, 30.420682);
INSERT INTO `tb_comments` VALUES (8, 'root', 'ccccccccccccccccc', '2021-04-21 03:06:04', 114.434436, 30.420682);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `upass` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `role` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'admin', 'admin', 1);
INSERT INTO `tb_user` VALUES (2, 'root', 'root', 0);

SET FOREIGN_KEY_CHECKS = 1;
