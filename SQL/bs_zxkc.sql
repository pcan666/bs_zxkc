/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50717 (5.7.17-log)
 Source Host           : localhost:3306
 Source Schema         : bs_zxkc

 Target Server Type    : MySQL
 Target Server Version : 50717 (5.7.17-log)
 File Encoding         : 65001

 Date: 26/12/2023 23:45:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_info
-- ----------------------------
DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `level` int(10) NULL DEFAULT 1 COMMENT '权限等级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin_info
-- ----------------------------
INSERT INTO `admin_info` VALUES (1, 'admin', '123456', '男', '20', '18888888888', 1);
INSERT INTO `admin_info` VALUES (3, 'admin1', '123456', '女', '31', '18888888888', 1);
INSERT INTO `admin_info` VALUES (4, 'admin2', '123456', '男', '32', '18888888888', 1);
INSERT INTO `admin_info` VALUES (7, 'admin5', '123456', '女', '35', '18888888888', 1);

-- ----------------------------
-- Table structure for class_info
-- ----------------------------
DROP TABLE IF EXISTS `class_info`;
CREATE TABLE `class_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '课程介绍',
  `zhuanyeId` bigint(20) NULL DEFAULT NULL COMMENT '专业ID',
  `score` int(10) NULL DEFAULT NULL COMMENT '课程学分',
  `teacherId` bigint(20) NULL DEFAULT NULL COMMENT '教师ID',
  `kaiban` int(10) NULL DEFAULT NULL COMMENT '开班人数',
  `yixuan` int(10) NULL DEFAULT NULL COMMENT '已选人数',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '上课时段',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '上课地点',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class_info
-- ----------------------------
INSERT INTO `class_info` VALUES (1, '微信公众号开发', '微信公众号开发啊', 1, 2, 2, 50, 2, '周五5-6节', 'S5-501');
INSERT INTO `class_info` VALUES (2, '音乐赏析', '全体欣赏音乐', 4, 1, 10, 40, 2, '周一1-2节', 'J1-101');

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '年龄',
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学号',
  `level` int(10) NULL DEFAULT 2 COMMENT '权限等级',
  `xueyuanId` bigint(20) NULL DEFAULT NULL COMMENT '学院ID',
  `score` int(10) NULL DEFAULT 0 COMMENT '总学分',
  `balance` double NULL DEFAULT 0 COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_info
-- ----------------------------
INSERT INTO `student_info` VALUES (1, 'student', '123456', '男', '20', '0001', 3, 1, 1, 170);
INSERT INTO `student_info` VALUES (3, 'student1', '123456', '女', '21', '0002', 3, 1, 3, 648);
INSERT INTO `student_info` VALUES (4, 'student2', '123456', '男', '22', '0003', 3, 2, 5, 0);

-- ----------------------------
-- Table structure for teacher_info
-- ----------------------------
DROP TABLE IF EXISTS `teacher_info`;
CREATE TABLE `teacher_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `age` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '年龄',
  `zhicheng` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '职称',
  `level` int(10) NULL DEFAULT 2 COMMENT '权限等级',
  `zhuanyeId` bigint(20) NULL DEFAULT NULL COMMENT '专业ID',
  `balance` double NULL DEFAULT 0 COMMENT '余额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher_info
-- ----------------------------
INSERT INTO `teacher_info` VALUES (1, 'teacher', '123456', '男', '28', '班主任', 2, 1, 0);
INSERT INTO `teacher_info` VALUES (2, '微老师', '123456', '女', '31', '微信公众号老师', 2, 2, 0);
INSERT INTO `teacher_info` VALUES (3, '英老师', '123456', '女', '32', '英语老师', 2, 4, 0);
INSERT INTO `teacher_info` VALUES (4, '高老师', '123456', '女', '33', '高数老师', 2, 1, 0);
INSERT INTO `teacher_info` VALUES (5, '体老师', '123456', '女', '34', '体育老师', 2, 3, 0);
INSERT INTO `teacher_info` VALUES (7, '毛老师', '123456', '女', '35', '毛概老师', 2, 1, 0);
INSERT INTO `teacher_info` VALUES (8, '金老师', '123456', '女', '36', 'java老师', 2, 1, 0);
INSERT INTO `teacher_info` VALUES (9, '潘老师', '123456', '女', '37', 'python老师', 2, 2, 0);
INSERT INTO `teacher_info` VALUES (10, '音老师', '123456', '男', '38', '音乐老师', 2, 4, 0);

-- ----------------------------
-- Table structure for xuanke_info
-- ----------------------------
DROP TABLE IF EXISTS `xuanke_info`;
CREATE TABLE `xuanke_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '课程名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '课程介绍',
  `zhuanyeId` bigint(20) NULL DEFAULT NULL COMMENT '专业ID',
  `score` int(10) NULL DEFAULT NULL COMMENT '课程学分',
  `teacherId` bigint(20) NULL DEFAULT NULL COMMENT '教师ID',
  `kaiban` int(10) NULL DEFAULT NULL COMMENT '开班人数',
  `studentId` bigint(20) NULL DEFAULT NULL COMMENT '学生ID',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '上课时段',
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '上课地点',
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '课程状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xuanke_info
-- ----------------------------
INSERT INTO `xuanke_info` VALUES (18, '微信公众号开发', '微信公众号开发啊', 1, 2, 2, 50, 1, '周五5-6节', 'S5-501', '已开课');
INSERT INTO `xuanke_info` VALUES (19, '微信公众号开发', '微信公众号开发啊', 1, 2, 2, 50, 3, '周五5-6节', 'S5-501', '待开课');
INSERT INTO `xuanke_info` VALUES (20, '音乐赏析', '全体欣赏音乐', 4, 1, 10, 40, 3, '周一1-2节', 'J1-101', '待开课');
INSERT INTO `xuanke_info` VALUES (21, '音乐赏析', '全体欣赏音乐', 4, 1, 10, 40, 1, '周一1-2节', 'J1-101', '待开课');

-- ----------------------------
-- Table structure for xueyuan_info
-- ----------------------------
DROP TABLE IF EXISTS `xueyuan_info`;
CREATE TABLE `xueyuan_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学院名称',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '学院介绍',
  `score` int(10) NULL DEFAULT NULL COMMENT '学分限制',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xueyuan_info
-- ----------------------------
INSERT INTO `xueyuan_info` VALUES (1, '信息工程学院', '计算机', 10);
INSERT INTO `xueyuan_info` VALUES (2, '金融学院', '金融', 10);
INSERT INTO `xueyuan_info` VALUES (4, '网络工程', '网络', 10);
INSERT INTO `xueyuan_info` VALUES (5, '大数据学院', '大数据', 10);
INSERT INTO `xueyuan_info` VALUES (6, '艺术学院', '艺术', 10);

-- ----------------------------
-- Table structure for zhuanye_info
-- ----------------------------
DROP TABLE IF EXISTS `zhuanye_info`;
CREATE TABLE `zhuanye_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '专业名称',
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '系名',
  `xueyuanId` bigint(20) NULL DEFAULT NULL COMMENT '学院ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of zhuanye_info
-- ----------------------------
INSERT INTO `zhuanye_info` VALUES (1, '软件工程', '计算机系', 1);
INSERT INTO `zhuanye_info` VALUES (2, '计算机科学与技术', '计算机系', 1);
INSERT INTO `zhuanye_info` VALUES (3, '金融学', '金融系', 2);
INSERT INTO `zhuanye_info` VALUES (4, '音乐学', '艺术系', 6);

SET FOREIGN_KEY_CHECKS = 1;
