-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: interview_system
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `interview_evaluations`
--

DROP TABLE IF EXISTS `interview_evaluations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interview_evaluations` (
                                         `evaluation_id` bigint unsigned NOT NULL AUTO_INCREMENT,
                                         `interview_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                         `interviewee_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                         `interviewer_id` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                                         `comprehensive_score` int DEFAULT NULL,
                                         `language_expression` int DEFAULT NULL,
                                         `logical_thinking` int DEFAULT NULL,
                                         `situational_response` int DEFAULT NULL,
                                         `professional_knowledge` int DEFAULT NULL,
                                         `personal_quality` int DEFAULT NULL,
                                         `comments` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci,
                                         `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
                                         `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                         `position` text NOT NULL COMMENT '应聘职位',
                                         `result` text,
                                         PRIMARY KEY (`evaluation_id`) USING BTREE,
                                         UNIQUE KEY `evaluation_id` (`evaluation_id`) USING BTREE,
                                         CONSTRAINT `interview_evaluations_chk_1` CHECK (((`comprehensive_score` >= 0) and (`comprehensive_score` <= 100))),
                                         CONSTRAINT `interview_evaluations_chk_2` CHECK (((`language_expression` >= 0) and (`language_expression` <= 20))),
                                         CONSTRAINT `interview_evaluations_chk_3` CHECK (((`logical_thinking` >= 0) and (`logical_thinking` <= 20))),
                                         CONSTRAINT `interview_evaluations_chk_4` CHECK (((`situational_response` >= 0) and (`situational_response` <= 20))),
                                         CONSTRAINT `interview_evaluations_chk_5` CHECK (((`professional_knowledge` >= 0) and (`professional_knowledge` <= 20))),
                                         CONSTRAINT `interview_evaluations_chk_6` CHECK (((`personal_quality` >= 0) and (`personal_quality` <= 20))),
                                         CONSTRAINT `interview_evaluations_chk_7` CHECK (((length(`comments`) >= 10) and (length(`comments`) <= 500)))
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview_evaluations`
--

LOCK TABLES `interview_evaluations` WRITE;
/*!40000 ALTER TABLE `interview_evaluations` DISABLE KEYS */;
INSERT INTO `interview_evaluations` VALUES (4,'2223','张三','111',10,2,2,2,2,2,'表现欠佳！','2024-11-29 22:40:26','2024-11-30 13:04:36','公务员D023','未录用'),(7,'h01ox8','张三','李老师',75,15,16,15,14,15,'表现不错，建议录取！','2024-12-02 06:27:21','2024-12-02 06:27:21','公务员A123','录用');
/*!40000 ALTER TABLE `interview_evaluations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interviewee`
--

DROP TABLE IF EXISTS `interviewee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interviewee` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `interviewee_id` varchar(12) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `profile` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interviewee`
--

LOCK TABLES `interviewee` WRITE;
/*!40000 ALTER TABLE `interviewee` DISABLE KEYS */;
INSERT INTO `interviewee` VALUES (1,'20241128','123','张三','华南理工大学软件工程'),(2,'20241129','123','李四','中大电子信息'),(3,'20241130','123','王五','北大光华');
/*!40000 ALTER TABLE `interviewee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interviewer`
--

DROP TABLE IF EXISTS `interviewer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `interviewer` (
                               `id` int NOT NULL AUTO_INCREMENT,
                               `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `password` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
                               `interview_id` int DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20241202 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interviewer`
--

LOCK TABLES `interviewer` WRITE;
/*!40000 ALTER TABLE `interviewer` DISABLE KEYS */;
INSERT INTO `interviewer` VALUES (1,'面试官','123456Abc',20241201);
/*!40000 ALTER TABLE `interviewer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-04 19:50:03
