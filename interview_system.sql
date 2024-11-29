/*
 Navicat Premium Dump SQL

 Source Server         : test1
 Source Server Type    : MySQL
 Source Server Version : 80038 (8.0.38)
 Source Host           : localhost:3306
 Source Schema         : interview_system

 Target Server Type    : MySQL
 Target Server Version : 80038 (8.0.38)
 File Encoding         : 65001

 Date: 30/11/2024 00:30:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interview_evaluations
-- ----------------------------
DROP TABLE IF EXISTS `interview_evaluations`;
CREATE TABLE `interview_evaluations`  (
  `evaluation_id` bigint UNSIGNED NOT NULL AUTO_INCREMENT,
  `interview_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `interviewee_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `interviewer_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `comprehensive_score` int NULL DEFAULT NULL,
  `language_expression` int NULL DEFAULT NULL,
  `logical_thinking` int NULL DEFAULT NULL,
  `situational_response` int NULL DEFAULT NULL,
  `professional_knowledge` int NULL DEFAULT NULL,
  `personal_quality` int NULL DEFAULT NULL,
  `comments` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `suggestions` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`evaluation_id`) USING BTREE,
  UNIQUE INDEX `evaluation_id`(`evaluation_id` ASC) USING BTREE,
  CONSTRAINT `interview_evaluations_chk_1` CHECK ((`comprehensive_score` >= 0) and (`comprehensive_score` <= 100)),
  CONSTRAINT `interview_evaluations_chk_2` CHECK ((`language_expression` >= 0) and (`language_expression` <= 20)),
  CONSTRAINT `interview_evaluations_chk_3` CHECK ((`logical_thinking` >= 0) and (`logical_thinking` <= 20)),
  CONSTRAINT `interview_evaluations_chk_4` CHECK ((`situational_response` >= 0) and (`situational_response` <= 20)),
  CONSTRAINT `interview_evaluations_chk_5` CHECK ((`professional_knowledge` >= 0) and (`professional_knowledge` <= 20)),
  CONSTRAINT `interview_evaluations_chk_6` CHECK ((`personal_quality` >= 0) and (`personal_quality` <= 20)),
  CONSTRAINT `interview_evaluations_chk_7` CHECK ((length(`comments`) >= 10) and (length(`comments`) <= 500)),
  CONSTRAINT `interview_evaluations_chk_8` CHECK (length(`suggestions`) <= 200)
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interview_evaluations
-- ----------------------------
INSERT INTO `interview_evaluations` VALUES (1, '20241129', '20241128', '20241201', 10, 10, 10, 10, 10, 10, '很好很好很好很好很好很好很好', '很好', '2024-11-29 14:55:38', '2024-11-29 14:55:38');

-- ----------------------------
-- Table structure for interviewee
-- ----------------------------
DROP TABLE IF EXISTS `interviewee`;
CREATE TABLE `interviewee`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `interviewee_id` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `profile` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interviewee
-- ----------------------------
INSERT INTO `interviewee` VALUES (1, '20241128', '123456Abc', '张三', '1');
INSERT INTO `interviewee` VALUES (2, '20241129', '123456Abc', '李四', '1');
INSERT INTO `interviewee` VALUES (3, '20241130', '123456Abc', '王五', '1');

-- ----------------------------
-- Table structure for interviewer
-- ----------------------------
DROP TABLE IF EXISTS `interviewer`;
CREATE TABLE `interviewer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `interview_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20241202 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interviewer
-- ----------------------------
INSERT INTO `interviewer` VALUES (20241201, '面试官', '123456Abc', NULL);

SET FOREIGN_KEY_CHECKS = 1;
